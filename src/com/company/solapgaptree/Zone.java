
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
        double[][] TD = new double[listObjet.size()][2];
        for (int i=0; i< TD.length; i++){
            TD[i][0] = listObjet.get(i).getSurfaceObjet();
            TD[i][1] = listObjet.get(i).getMesureObjet();
        }
        // affichage
        System.out.println("Affichage de la table des donnees");
        for (double[] doubles : TD) {
            System.out.print(doubles[0] + "   ");
            System.out.print(doubles[1]);
            System.out.println("  ");
        }
        // Normalisation
        double[][] TDN= new double[listObjet.size()][2];
        double[] t=new double[listObjet.size()];
        for (int j=0; j <= 1; j++){
            for (int i=0; i < TDN.length; i++){
                t[i]= TD[i][j];
            }
            for (int i=0; i < listObjet.size(); i++) {
                TDN[i][j] = (TD[i][j] - min(t)[0]) / (max(t)[0] - min(t)[0]);
            }
        }

        System.out.println("Affichage de la table des donnees normalisé");
        for (double[] doubles : TDN) {
            System.out.print(doubles[0] + "   ");
            System.out.print(doubles[1]);
            System.out.println("  ");
        }

        // Calcule les valeurs d'importance
        //1- Considération les poids des critères
        double[][] TDNP= new double[listObjet.size()][2];
        for (int i=0; i < TDNP.length; i++){
            for (int j=0; j <= 1; j++){
                TDNP[i][j] = TDN[i][j] * poids[j];
            }
        }
        System.out.println("Affichage de la table des donnees pondéré");
        for (int i=0; i < TDN.length; i++){
            System.out.print(TDNP[i][0] + "   ");
            System.out.print(TDNP[i][1]);
            System.out.println("  ");
        }
        //2- Calcule la moyenne pondéré
        ArrayList<Double> LIO= new ArrayList<>();
        for (double[] doubles : TDNP) {
            double[] tab = new double[2];
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

        // affichage
        System.out.println(LIO.size());
        System.out.println("Affichage l'importance des objets");
        for (Double aDouble : LIO) {
            System.out.println(aDouble);
        }
        return LIO;
    }

    public GAPtree solapGAPtree(ArrayList<Objet> listObjet, double[] poids){

        // Pour chaque objet de la carte un noeud vide non connécté dans GAPtree est crée
        // un noeud est caractérisé par (id, liste des fils, importance)

        ArrayList<Noeud> LNoeud = new ArrayList<>();
        ArrayList<Double> Seuils = importance(listObjet,poids);
        System.out.println(Seuils);
        for (int i=0; i< listObjet.size();i++){
            Noeud noeud= new Noeud(i,null,null);
            noeud.setImportance(Seuils.get(i));
            System.out.println(noeud.getElement());
            System.out.println(noeud.getImportance());
            LNoeud.add(noeud);
        }
        System.out.println(LNoeud);
        Noeud r= LNoeud.get(0);
        System.out.println(r.getElement());
        int p = listObjet.size();
        System.out.println("le p = n+1     " + p);
        //ArrayList<Objet> listObjetCopy = listObjet;
        ArrayList<Objet> listObjetCopy = (ArrayList<Objet>)listObjet.clone();
        System.out.println("liste d'objets copy   " + listObjetCopy);

        Objet c=null;
        // Création les noeuds de l'arbre SOLAP GAPtree

        while (listObjetCopy.size() != 1){
            // Eliminer l'objet "a" ayant la plus petite valeur d'importance
            ArrayList<Double> LIO = importance(listObjet, poids);
            System.out.println(LIO);
            int IdObjetMin =LIO.indexOf(Collections.min(LIO))+1;
            System.out.println("IdObjetMin =  " + IdObjetMin);
            Objet a = listObjet.get(IdObjetMin-1);
            System.out.println("Id objet a   " + a.getIdObjet());
            System.out.println("le p   " + p);
            //double imp_a = Seuils.get(a.getIdObjet()-p-1);
            //double imp_a = Seuils.get(IdObjetMin-1);
            //System.out.println("imp_a:  " + imp_a);
            listObjetCopy.remove(IdObjetMin-1);
            System.out.println("listObjetCopy:   " + listObjetCopy);

            // Chercher parmis tous les voisin de "a" l'objet "b" ayant la plus grande valeur d'importance
           ArrayList<Objet> listObjet_a = a.getListIdObjetsAdjas();
           /*
               for (int i=0; i<listObjet.size(); i++){
                   for (int j=0; j<listObjet_a.size(); j++){
                       if (listObjet.get(i).getIdObjet() == listIdObjet_a.get(j)){
                            listObjet_a.add(listObjet.get(i));
                       }
                   }
               }
            */
            // System.out.println("---------" + listIdObjet_a);
            listObjet_a.remove(c);
            System.out.println("---------" + listObjet_a);

            ArrayList<Double> LIO_a = importance(listObjet_a, poids);
            if (!LIO_a.isEmpty()){
                int IdObjetMax = LIO_a.indexOf(Collections.max(LIO_a))+1;
                System.out.println("Id objet max   " + IdObjetMax);
                Objet b =  listObjet_a.get(IdObjetMax-1);
                System.out.println("id de l'objet b:  " + b.getIdObjet());
                //double imp_b = Seuils.get(b.getIdObjet()-p);
                //double imp_b= Seuils.get(IdObjetMax-1);
                //System.out.println("imp_b   " + imp_b);

                // Fusionner l'objet "a" dans "b".
                for (Objet o : listObjet){
                    if (o.getListIdObjetsAdjas().contains(a)){
                        o.getListIdObjetsAdjas().remove(a);
                        o.getListIdObjetsAdjas().add(b);
                    }
                }
                listObjet.remove(a);
                System.out.println(listObjet);
                double mesure = b.getMesureObjet() + a.getMesureObjet();
                System.out.println(mesure);
                double surface = b.getSurfaceObjet() +
                        a.getSurfaceObjet();
                System.out.println(surface);
                b.setMesureObjet(mesure);
                b.setSurfaceObjet(surface);
                b.setListIdObjetsAdjas(union(b.getListIdObjetsAdjas(),a.getListIdObjetsAdjas()));
                System.out.println(b.getMesureObjet()+"   "+b.getSurfaceObjet());
                System.out.println(b.getListIdObjetsAdjas());
                //Integer n = b.getListIdObjetsAdjas().remove(a.getIdObjet());
                // System.out.println("-------------" + n);

                // Creer un nouveau noeud et le lier à ses fils
                System.out.println("liste des noeuds  " + LNoeud);
                System.out.println( LNoeud.get(a.getIdObjet()-1));
                System.out.println(LNoeud.get(b.getIdObjet()-1));
                System.out.println(a.getIdObjet());
                System.out.println(b.getIdObjet());
                System.out.println(LNoeud);
                //Noeud noeud2 = new Noeud(p,s,LNoeud.get(a.getIdObjet()-1),r);
                Noeud noeud2 = new Noeud(p,LNoeud.get(b.getIdObjet()-1),LNoeud.get(a.getIdObjet()-1));
                noeud2.setImportance(noeud2.getDroite().getImportance() + noeud2.getGauche().getImportance());
                System.out.println(noeud2.getImportance());
                //noeud2.setImportance(imp_a + imp_b);
                LNoeud.add(noeud2);
                r = noeud2;
                System.out.println("racine  " + r.getElement());
                b.setIdObjet(p+1);
                p++;
                c=a;

            }
        }

        return new GAPtree(r);
    }
}

