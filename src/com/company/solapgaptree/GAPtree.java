package com.company.solapgaptree;

//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;

import static java.lang.Integer.max;

//import java.util.Collections;

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
            if (!list.contains(list.get(i).getGauche())){
                   if (list.contains(list.get(i).getDroite())){
                       ArrayList<Noeud> l = new ArrayList<>();
                       l=chercher(list.get(i).getDroite(),0.00,l);
                       list.remove(list.get(i).getDroite());
                       list.removeAll(l);
                   }
            }
            if (!list.contains(list.get(i).getDroite())){
                if (list.contains(list.get(i).getGauche())){
                    ArrayList<Noeud> l = new ArrayList<>();
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
        ArrayList<Noeud> listCopy;
        listCopy=(ArrayList<Noeud>)list.clone();
        for (int i=0; i< listCopy.size(); i++){
            if (list.contains(listCopy.get(i).getGauche()) && listCopy.contains(listCopy.get(i).getDroite())){
                list.remove(listCopy.get(i));

            }
        }

        return list;
    }

    // Navigation dans l'arbre GAPtree
    public ArrayList<Noeud> navigation(Double seuil){
        ArrayList<Noeud> listNoeuds = new ArrayList<>();
        listNoeuds = chercher(this.getRacine(),seuil,listNoeuds);
        listNoeuds = suppNoeudFrr(listNoeuds);
        listNoeuds = supFilsSelec(listNoeuds);
        return listNoeuds;
    }

    public ArrayList<Noeud> principeauxFils(Noeud r, ArrayList<Integer> listObjets){
        ArrayList<Noeud> l = new ArrayList<>();
        ArrayList<Noeud> list = chercher(r,0.00, l);
        ArrayList<Noeud> result = new ArrayList<>();
        for (int i=0; i < list.size(); i++){
            if (listObjets.contains(list.get(i).getElement()+1)){
                result.add(list.get(i));
            }
        }
        return result;
    }

    // fusionner les polygones adjacents
    private String url =  "jdbc:oracle:thin:@localhost:1521:orcl";
    private String user = "pfef";
    private String pwd = "1234";

    /**
     * Get geom fusion
     * @return geom
     */
    public void fusion(ArrayList<Noeud> list, ArrayList<Integer> listObjets) {
        Connection connection;
        try {

            connection = DriverManager.getConnection(url, user, pwd);
            Statement statement = connection.createStatement();

            statement.executeQuery("DROP TABLE COMMUNE_ECHELLE");
            statement.executeQuery("create table COMMUNE_ECHELLE(\n" +
                    "COMMUNE_MB\t VARCHAR2(35 CHAR) primary key,\n" +
                    "WILAYA_MB\t VARCHAR2(35 CHAR),\n" +
                    "DAIRA_MB\t VARCHAR2(50 CHAR),\n" +
                    "ID_MB\t\t NUMBER,\n" +
                    "GEOMETRY MDSYS.SDO_GEOMETRY,\n" +
                    "NVIEG\t\t NUMBER,\n" +
                    "TAUXSG\t\t NUMBER,\n" +
                    "NBRCRIMEG\t NUMBER\n" +
                    ")");
            statement.executeQuery("\n" +
                    "INSERT INTO COMMUNE_ECHELLE\n" +
                    "SELECT * FROM COMMUNE");


            System.out.println(" ");
            for (int i = 0; i< list.size(); i++) {
                GAPtree r = new GAPtree(list.get(i));
                ArrayList<Noeud> l = principeauxFils(r.getRacine(), listObjets);

                if (!l.isEmpty()){
                    for (int j = 1; j < l.size(); j++) {

                        CallableStatement cst = connection.prepareCall("{CALL Fusion(?,?) }");
                        cst.registerOutParameter(2, Types.INTEGER);
                        cst.setInt(1, l.get(0).getElement()+1);
                        cst.setInt(2, l.get(j).getElement()+1);
                        cst.execute();
                        cst.close();
                    }
                }
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

    }

}
