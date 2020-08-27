
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
       for (int i = 0; i< tab.length; i++){
           val = val + tab[i];
       }
        return val;
    }
    public  ArrayList<Objet> union(ArrayList<Objet> list1, ArrayList<Objet> list2) {
        Set<Objet> set = new HashSet<Objet>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<Objet>(set);
    }

    public  ArrayList<Objet> intersection(ArrayList<Objet> list1, ArrayList<Objet> list2) {
        ArrayList<Objet> list = new ArrayList<Objet>();

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
        for (int i=0; i < TD.length; i++){
            System.out.print(TD[i][0] + "   ");
            System.out.print(TD[i][1]);
            System.out.println("");
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
        for (int i=0; i < TDN.length; i++){
            System.out.print(TDN[i][0] + "   ");
            System.out.print(TDN[i][1]);
            System.out.println("");
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
            System.out.println("");
        }
        //2- Calcule la moyenne pondéré
        ArrayList<Double> LIO= new ArrayList<Double>();
        for (int i=0; i < TDNP.length; i++){
            double[] tab = new double[2];
            for (int j=0; j <= 1; j++){
                tab[j] = TDNP[i][j];
            }
            LIO.add(somme(tab)/somme(poids));
        }
        // affichage
        System.out.println(LIO.size());
        System.out.println("Affichage l'importance des objets");
        for (int i=0; i < LIO.size(); i++){
            System.out.println(LIO.get(i));
        }
        return LIO;
    }

    public GAPtree solapGAPtree(ArrayList<Objet> listObjet, double[] poids){
        // Pour chaque objet de la carte un noeud vide non connécté dans GAPtree est crée
        // un noeud est caractérisé par (id, liste des fils, importance)

        ArrayList<Noeud> LNoeud = new ArrayList<Noeud>();
        ArrayList<Double> Seuils = importance(listObjet,poids);
        System.out.println(Seuils);
        for (int i=0; i< listObjet.size();i++){
            Noeud noeud= new Noeud(i,Seuils.get(i),null,null);
            System.out.println(noeud.getElement());
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
            double imp_a = Seuils.get(IdObjetMin-1);
            System.out.println("imp_a:  " + imp_a);
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
            if (LIO_a.isEmpty()==false){
                int IdObjetMax = LIO_a.indexOf(Collections.max(LIO_a))+1;
                System.out.println("Id objet max   " + IdObjetMax);
                Objet b =  listObjet_a.get(IdObjetMax-1);
                System.out.println("id de l'objet b:  " + b.getIdObjet());
                double imp_b= LIO.get(IdObjetMax-1);

                System.out.println("imp_b   " + imp_b);

                // Fusionner l'objet "a" dans "b".
                listObjet.remove(a);
                System.out.println(listObjet);
                double mesure = b.getMesureObjet() + a.getMesureObjet();
                System.out.println(mesure);
                double surface = b.getSurfaceObjet() + a.getSurfaceObjet();
                System.out.println(surface);
                b.setMesureObjet(mesure);
                b.setSurfaceObjet(surface);
                b.setListIdObjetsAdjas(union(b.getListIdObjetsAdjas(),a.getListIdObjetsAdjas()));
                System.out.println(b.getMesureObjet()+"   "+b.getSurfaceObjet());
                System.out.println(b.getListIdObjetsAdjas());
                //Integer n = b.getListIdObjetsAdjas().remove(a.getIdObjet());
                // System.out.println("-------------" + n);

                // Creer un nouveau noeud et le lier à ses fils
                double s = imp_a + imp_b;
                System.out.println("liste des noeuds  " + LNoeud);
                System.out.println( LNoeud.get(a.getIdObjet()-1));
                System.out.println(LNoeud.get(b.getIdObjet()-1));
                System.out.println( a.getIdObjet());
                System.out.println(b.getIdObjet());
                System.out.println(LNoeud);
                //Noeud noeud2 = new Noeud(p,s,LNoeud.get(a.getIdObjet()-1),r);
                Noeud noeud2 = new Noeud(p,s,LNoeud.get(a.getIdObjet()-1),LNoeud.get(b.getIdObjet()-1));
                LNoeud.add(noeud2);
                r = noeud2;
                System.out.println("racine  " + r.getElement());
                b.setIdObjet(p+1);
                p++;
                c=a;

            }
        }

        GAPtree arbre = new GAPtree(r);
        return  arbre;
    }
}
