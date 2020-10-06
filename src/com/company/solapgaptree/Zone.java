
package com.company.solapgaptree;

import java.util.*;

public class Zone {
    private int IdZone;
    private ArrayList<Objet> listObjet;

    public Zone(int IdZone, ArrayList<Objet> listObjet) {
        this.IdZone=IdZone;
        this.listObjet=listObjet;
    }

    public int getIdZone() {
        return IdZone;
    }
    public void setIdZone(int idZone) {
        IdZone = idZone;
    }

    public ArrayList<Objet> getListObjet() {
        return listObjet;
    }

    public void setListObjet(ArrayList<Objet> listObjet) {
        this.listObjet = listObjet;
    }

    public double[] max(double[] TD){
        double val = TD[0];
        double indice = 0;
        for (int i=0;i< TD.length;i++){
            if(val < TD[i])
            {
                val= TD[i];
                indice=i;
            }
        }
        double[] maximum= new double[2];
        maximum[0]=val;
        maximum[1]=indice;
        return maximum;
    }

    public double[] min(double[] TD){
        double val = TD[0];
        double indice = 0;
        for (int i=1;i< TD.length;i++)
            if (val > TD[i]) {
                val = TD[i];
                indice = i;
            }
        double[] minimum = new double[2];
        minimum[0]=val;
        minimum[1]=indice;
        return minimum;
    }
    public double somme(double[] tab){
        double val=0;
        for (double v : tab) {
            val = val + v;
        }
        return val;
    }
    public  ArrayList<Objet> union(ArrayList<Objet> list1, ArrayList<Objet> list2) {
        Set<Objet> set = new HashSet<>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<>(set);
    }

    public  ArrayList<Objet> intersection(ArrayList<Objet> list1, ArrayList<Objet> list2) {
        ArrayList<Objet> list = new ArrayList<>();

        for (Objet t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }

    public ArrayList<Double> importance(ArrayList<Objet> listObjet, double[] poids){

        //Création de tableau de données
        double[][] TD = new double[listObjet.size()][4];
        for (int i=0; i< TD.length; i++){
            TD[i][0] = listObjet.get(i).getSurfaceObjet();
            TD[i][1] = listObjet.get(i).getNbCrime();
            TD[i][2] = listObjet.get(i).getTauxSec();
            TD[i][3] = listObjet.get(i).getNiveauxVie();
        }
        // Normalisation
        double[][] TDN= new double[listObjet.size()][4];
        double[] t=new double[listObjet.size()];
        for (int j=0; j <= 3; j++){
            for (int i=0; i < TDN.length; i++){
                t[i]= TD[i][j];
            }
            for (int i=0; i < listObjet.size(); i++) {
                if (j == 0 || j == 1 ){TDN[i][j] = (TD[i][j] - min(t)[0]) / (max(t)[0] - min(t)[0]);}
                else {TDN[i][j] = (max(t)[0] - TD[i][j]) / (max(t)[0] - min(t)[0]);}
            }
        }
        // Calcule les valeurs d'importance
        //1- Considération les poids des critères
        double[][] TDNP= new double[listObjet.size()][4];
        for (int i=0; i < TDNP.length; i++){
            for (int j=0; j <= 3; j++){
                TDNP[i][j] = TDN[i][j] * poids[j];
            }
        }
        //2- Calcule la moyenne pondéré
        ArrayList<Double> LIO= new ArrayList<>();
        for (double[] doubles : TDNP) {
            double[] tab = new double[4];
            System.arraycopy(doubles, 0, tab, 0, 2);
            LIO.add(somme(tab) / somme(poids));
        }
        double n=0 ;
        for (Double x : LIO){
            n=n+x;
        }
        for (int i =0; i< LIO.size();i++){
            LIO.set(i, LIO.get(i)/n);
        }
        return LIO;
    }

    public GAPtree solapGAPtree(ArrayList<Objet> listObjet, double[] poids){

        // Pour chaque objet de la carte un noeud vide non connécté dans GAPtree est crée
        // un noeud est caractérisé par (id, liste des fils, importance)
        ArrayList<Noeud> LNoeud = new ArrayList<>();
        ArrayList<Double> Seuils = importance(listObjet,poids);
        for (int i=0; i< listObjet.size();i++){
            Noeud noeud= new Noeud(i,null,null);
            noeud.setImportance(Seuils.get(i));
            LNoeud.add(noeud);
        }
        Noeud r= LNoeud.get(0);

        // Création les noeuds de l'arbre SOLAP GAPtree
        int p = listObjet.size();
        ArrayList<Objet> listObjetCopy = (ArrayList<Objet>)listObjet.clone();
        Objet c=null;
        while (listObjetCopy.size() != 1){
            // Eliminer l'objet "a" ayant la plus petite valeur d'importance
            ArrayList<Double> LIO = importance(listObjet, poids);
            int IdObjetMin =LIO.indexOf(Collections.min(LIO))+1;
            Objet a = listObjet.get(IdObjetMin-1);
            listObjetCopy.remove(IdObjetMin-1);

            // Chercher parmis tous les voisin de "a" l'objet "b" ayant la plus grande valeur d'importance
            ArrayList<Objet> listObjet_a = a.getListObjetsAdjas();
            listObjet_a.remove(c);

            ArrayList<Double> LIO_a = importance(listObjet_a, poids);
            if (!LIO_a.isEmpty()){
                int IdObjetMax = LIO_a.indexOf(Collections.max(LIO_a))+1;
                Objet b =  listObjet_a.get(IdObjetMax-1);

                // Fusionner l'objet "a" dans "b".
                for (Objet o : listObjet){
                    if (o.getListObjetsAdjas().contains(a)){
                        o.getListObjetsAdjas().remove(a);
                        o.getListObjetsAdjas().add(b);
                    }
                }
                listObjet.remove(a);
                double nbcrime = b.getNbCrime() + a.getNbCrime();
                double surface = b.getSurfaceObjet() + a.getSurfaceObjet();
                b.setNbCrime(nbcrime);
                b.setSurfaceObjet(surface);
                b.setListObjetsAdjas(union(b.getListObjetsAdjas(),a.getListObjetsAdjas()));

                // Creer un nouveau noeud et le lier à ses fils
                Noeud noeud2 = new Noeud(p,LNoeud.get(b.getIdObjet()-1),LNoeud.get(a.getIdObjet()-1));
                noeud2.setImportance(noeud2.getDroite().getImportance() + noeud2.getGauche().getImportance());
                LNoeud.add(noeud2);
                r = noeud2;
                b.setIdObjet(p+1);
                p++;
                c=a;

            }
        }
        return new GAPtree(r);
    }
}

