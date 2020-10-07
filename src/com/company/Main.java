package com.company;

import com.company.crime.ZoneCrime;
import com.company.dao.ZoneCrimeDAO;
import com.company.solapgaptree.GAPtree;
import com.company.solapgaptree.Noeud;
import com.company.solapgaptree.Objet;
import com.company.solapgaptree.Zone;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
	// write your code here


        System.out.println("-------------------");
        ArrayList<ZoneCrime> zones;
        zones= new ZoneCrimeDAO().getAll();

        ArrayList<Objet> listObjets = new ArrayList<>();
        for (ZoneCrime z : zones){
            Objet o= new Objet(z.getNUMZONE(),z.getNOMEZONE(),z.getAREA(),z.getGEOM(), z.getNBCRIME(),z.getTAUXSEC(),z.getNIVEAUVIE());
            listObjets.add(o);
        }


        ArrayList<Objet> listOs = new ArrayList<>();
        for (Objet o : listObjets){
            listOs.add((Objet) o.clone());
        }

        Zone Z1 = new Zone(1,listObjets);

        ArrayList<Objet> listOz1;
        listOz1 = Z1.getListObjet();
        for (Objet o : listOz1){
            ArrayList<Integer> list;
            list = new ZoneCrimeDAO().getAdj(o.getIdObjet());
            ArrayList<Objet> listOAdj = new ArrayList<>();
            for (Integer c:list){
                listOAdj.add(Z1.getListObjet().get(c-1));
            }
            o.setListObjetsAdjas(listOAdj);
        }

        double[] poids = new double[4];
        poids[0]= 0.3;
        poids[1]= 0.5;
        poids[2]= 0.1;
        poids[3]= 0.1;

        GAPtree tree = Z1.solapGAPtree(listObjets,poids);
        tree.getRacine().print();

        ArrayList<Noeud> list = tree.navigation(0.02);

        System.out.println(" ");
        ArrayList<Integer> listIdObjet = new ArrayList<>();

        for (int o=0; o< listOs.size(); o++){
            listIdObjet.add(listOs.get(o).getIdObjet());
        }



        tree.fusion(list,listIdObjet);
    }
}
