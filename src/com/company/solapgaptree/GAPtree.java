package com.company.solapgaptree;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Integer.max;

public class GAPtree {
    private Noeud racine;

    public GAPtree(Noeud Racine){
        this.racine=Racine;
    }


    public Noeud getRacine() {
        return racine;
    }

    public void setRacine(Noeud racine) {
        racine = racine;
    }

    // Affichage
    public void infixe(){
        infixe (this.racine);
    }

    public void infixe(Noeud r){
        if (r!=null) {
            infixe(r.getGauche());
            System.out.print(r.getElement() +" ");
            infixe(r.getDroite());
        }
    }

    public void indente(){
        indente (racine, 0);
    }

    public void indente(Noeud r, int n){
        if (r!=null){
            indente(r.getGauche(), n+3);
            for( int i = 0; i< n; ++i) System.out.print(" ");
            System.out.print(r.getElement());
            indente(r.getDroite(), n+3);
        }
    }

    // Nombre d’éléments
    public int nombreElement(){
        return nombreElement(racine);
    }

    public int nombreElement(Noeud r){
        if (r==null)return 0;
        else return nombreElement(r.getGauche()) + 1 + nombreElement(r.getDroite());
    }

    // Hauteur
    public int hauteur(){
        return hauteur(racine);
    }

    public int hauteur(Noeud r){
        if (r==null) return 0;
        else return max( hauteur(r.getGauche()), hauteur(r.getDroite())) + 1;
    }

    // Nombre de feuilles
    public int nombreFeuilles(){
        return nombreFeuilles(racine);
    }

    public int nombreFeuilles(Noeud r){
        if (r==null)return 0;
        else
            if (r.getGauche()==null && r.getDroite()==null){
                //System.out.println("rani hna");
                return 1;}
            else return nombreFeuilles(r.getGauche()) + nombreFeuilles(r.getDroite());
    }

    // Forme parenthésée.
    public String parenthesee(){
        return parenthesee(racine);
    }

    public String parenthesee(Noeud r){
        if (r==null) return "";
        else return r.getElement() +"("+ parenthesee(r.getGauche())+", "+parenthesee(r.getDroite())+")";
    }

    // Recherche

    // Sélectionner tous les nœuds ayant leurs seuil >= s
    public ArrayList<Noeud> chercher(Noeud racine,Double s,ArrayList<Noeud> list ){
        if (racine == null) return null;
        else {
            if (racine.getImportance() >= s) {
                list.add(racine);
                chercher(racine.getGauche(),s,list);
                chercher(racine.getDroite(), s,list);
            }
            return list;
        }
    }

    // Supprimer parmi les nœuds de cet ensemble résultant qui ont des frères non sélectionnés.
    public ArrayList<Noeud> suppNoeudFrr(ArrayList<Noeud> list){
        list.toArray();

        for (int i=0; i< list.size(); i++){
            if (list.contains(list.get(i).getGauche()) == false ){
                   if (list.contains(list.get(i).getDroite()) == true){ ;
                       ArrayList<Noeud> l =new ArrayList<Noeud>();
                       l=chercher(list.get(i).getDroite(),0.00,l);
                       list.remove(list.get(i).getDroite());
                       list.removeAll(l);
                   }
            }
            if (list.contains(list.get(i).getDroite()) == false ){
                if (list.contains(list.get(i).getGauche()) == true){
                    ArrayList<Noeud> l =new ArrayList<Noeud>();
                    l=chercher(list.get(i).getGauche(),0.00,l);
                    list.remove(list.get(i).getGauche());
                    list.removeAll(l);

                }
            }
        }
        return list;
    }

    //Supprimer tous les nœuds qui ont tous leurs fils sélectionnés.
    public ArrayList<Noeud> supFilsSelec(ArrayList<Noeud> list){

        for (int i=0; i< list.size(); i++){
            if (list.contains(list.get(i).getGauche()) == true && list.contains(list.get(i).getDroite()) == true ){
                list.remove(list.get(i));
            }
        }
        return list;
    }

    // Navigation dans l'arbre GAPtree
    public ArrayList<Noeud> navigation(Double seuil){
        ArrayList<Noeud> listNoeuds = new ArrayList<Noeud>();
        listNoeuds = chercher(this.getRacine(),seuil,listNoeuds);
        listNoeuds = suppNoeudFrr(listNoeuds);
        listNoeuds = supFilsSelec(listNoeuds);
        return listNoeuds;
    }


}
