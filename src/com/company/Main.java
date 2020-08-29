package com.company;

import com.company.solapgaptree.*;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here

        //ArrayList<Integer> lo1=new ArrayList<Integer>();
        //lo1.add(2);
        //lo1.add(3);
        /*
        Point p1o1 = new Point(0,0);
        Point p2o1 = new Point(0,5);
        Point p3o1 = new Point(5,5);
        ArrayList<Point> Lpoint= new ArrayList<Point>();
        Lpoint.add(p1o1);
        Lpoint.add(p2o1);
        Lpoint.add(p3o1);
        Polygone polyo1 = new Polygone(Lpoint);
        Objet o1= new Objet(1,"zone1",15,5,polyo1);
        System.out.println(o1.getIdObjet());

        //ArrayList<Integer> lo2=new ArrayList<Integer>();
        //lo2.add(1);
        //lo2.add(3);
        Point p1o2 = new Point(5,5);
        Point p2o2 = new Point(0,0);
        Point p3o2 = new Point(0,8);
        Point p4o2 = new Point(8,8);
        ArrayList<Point> Lpoint2= new ArrayList<Point>();
        Lpoint.add(p1o2);
        Lpoint.add(p2o2);
        Lpoint.add(p3o2);
        Lpoint.add(p4o2);
        Polygone polyo2 = new Polygone(Lpoint2);
        Objet o2= new Objet(2,"zone2",20,7,polyo2);
        System.out.println(o2.getIdObjet());

        //ArrayList<Integer> lo3=new ArrayList<Integer>();
        //lo3.add(1);
        //lo3.add(2);
        Point p1o3 = new Point(0,5);
        Point p2o3 = new Point(0,8);
        Point p3o3 = new Point(0,8);
        Point p4o3 = new Point(5,5);
        ArrayList<Point> Lpoint3= new ArrayList<Point>();
        Lpoint3.add(p1o3);
        Lpoint3.add(p2o3);
        Lpoint3.add(p3o3);
        Lpoint3.add(p4o3);
        Polygone polyo3 = new Polygone(Lpoint2);
        Objet o3= new Objet(3,"zone3",12,3,polyo3);
        System.out.println(o3.getIdObjet());

        ArrayList<Objet> lo1_adj = new ArrayList<Objet>();
        lo1_adj.add(o2);
        lo1_adj.add(o3);
        o1.setListIdObjetsAdjas(lo1_adj);
        System.out.println("la liste des objets adjacants   " + o1.getListIdObjetsAdjas());

        ArrayList<Objet> lo2_adj = new ArrayList<Objet>();
        lo2_adj.add(o1);
        lo2_adj.add(o3);
        o2.setListIdObjetsAdjas(lo2_adj);
        System.out.println("la liste des objets adjacants   " + o2.getListIdObjetsAdjas());

        ArrayList<Objet> lo3_adj = new ArrayList<Objet>();
        lo3_adj.add(o1);
        lo3_adj.add(o2);
        o3.setListIdObjetsAdjas(lo3_adj);
        System.out.println("la liste des objets adjacants   " + o3.getListIdObjetsAdjas());

        ArrayList<Objet> listObjets = new ArrayList<Objet>();
        listObjets.add(o1);
        listObjets.add(o2);
        listObjets.add(o3);
        Zone Z1 = new Zone(1,listObjets);
        System.out.println(Z1.getIdZone());
        double[] poids = new double[2];
        poids[0]= 0.5;
        poids[1]= 0.5;
        */
        // Z1.importance(listObjets,poids);

        //System.out.println(Z1.solapGAPtree(listObjets,poids).parenthesee());
        /*
        OutputStream outputStream       = new FileOutputStream("D:\\output.txt ");
        OutputStreamWriter out = new OutputStreamWriter(outputStream);
        Z1.solapGAPtree(listObjets,poids).getRacine().printTree(out);
        */

        Point p1o1 = new Point(0,0);
        Point p2o1 = new Point(9,0);
        Point p3o1 = new Point(10,10);
        Point p4o1 = new Point(0,24);
        ArrayList<Point> Lpoint= new ArrayList<Point>();
        Lpoint.add(p1o1);
        Lpoint.add(p2o1);
        Lpoint.add(p3o1);
        Lpoint.add(p4o1);
        Polygone polyo1 = new Polygone(Lpoint);
        Objet o1= new Objet(1,"zone1",100,30,polyo1);
        System.out.println(o1.getIdObjet());

        //ArrayList<Integer> lo2=new ArrayList<Integer>();
        //lo2.add(1);
        //lo2.add(3);
        Point p1o2 = new Point(9,0);
        Point p2o2 = new Point(36,0);
        Point p3o2 = new Point(21,12);
        Point p4o2 = new Point(10,10);
        ArrayList<Point> Lpoint2= new ArrayList<Point>();
        Lpoint.add(p1o2);
        Lpoint.add(p2o2);
        Lpoint.add(p3o2);
        Lpoint.add(p4o2);
        Polygone polyo2 = new Polygone(Lpoint2);
        Objet o2= new Objet(2,"zone2",200,45,polyo2);
        System.out.println(o2.getIdObjet());

        //ArrayList<Integer> lo3=new ArrayList<Integer>();
        //lo3.add(1);
        //lo3.add(2);
        Point p1o3 = new Point(10,10);
        Point p2o3 = new Point(21,12);
        Point p3o3 = new Point(28,17);
        Point p4o3 = new Point(0,24);
        ArrayList<Point> Lpoint3= new ArrayList<Point>();
        Lpoint3.add(p1o3);
        Lpoint3.add(p2o3);
        Lpoint3.add(p3o3);
        Lpoint3.add(p4o3);
        Polygone polyo3 = new Polygone(Lpoint2);
        Objet o3= new Objet(3,"zone3",170,20,polyo3);
        System.out.println(o3.getIdObjet());

        //ArrayList<Integer> lo3=new ArrayList<Integer>();
        //lo3.add(1);
        //lo3.add(2);
        Point p1o4 = new Point(28,17);
        Point p2o4 = new Point(36,24);
        Point p3o4 = new Point(0,24);
        ArrayList<Point> Lpoint4= new ArrayList<Point>();
        Lpoint3.add(p1o4);
        Lpoint3.add(p2o4);
        Lpoint3.add(p3o4);
        Polygone polyo4 = new Polygone(Lpoint2);
        Objet o4 = new Objet(4,"zone4",80,10,polyo3);
        System.out.println(o4.getIdObjet());

        //ArrayList<Integer> lo3=new ArrayList<Integer>();
        //lo3.add(1);
        //lo3.add(2);
        Point p1o5 = new Point(28,12);
        Point p2o5 = new Point(36,0);
        Point p3o5 = new Point(36,24);
        Point p4o5 = new Point(28,17);
        ArrayList<Point> Lpoint5= new ArrayList<Point>();
        Lpoint3.add(p1o5);
        Lpoint3.add(p2o5);
        Lpoint3.add(p3o5);
        Polygone polyo5 = new Polygone(Lpoint2);
        Objet o5 = new Objet(5,"zone4",230,50,polyo3);
        System.out.println(o5.getIdObjet());

        ArrayList<Objet> lo1_adj = new ArrayList<Objet>();
        lo1_adj.add(o2);
        lo1_adj.add(o3);
        o1.setListIdObjetsAdjas(lo1_adj);
        System.out.println("la liste des objets adjacants   " + o1.getListIdObjetsAdjas());

        ArrayList<Objet> lo2_adj = new ArrayList<Objet>();
        lo2_adj.add(o1);
        lo2_adj.add(o3);
        lo2_adj.add(o5);
        o2.setListIdObjetsAdjas(lo2_adj);
        System.out.println("la liste des objets adjacants   " + o2.getListIdObjetsAdjas());

        ArrayList<Objet> lo3_adj = new ArrayList<Objet>();
        lo3_adj.add(o1);
        lo3_adj.add(o2);
        lo3_adj.add(o4);
        lo3_adj.add(o5);
        o3.setListIdObjetsAdjas(lo3_adj);
        System.out.println("la liste des objets adjacants   " + o3.getListIdObjetsAdjas());

        ArrayList<Objet> lo4_adj = new ArrayList<Objet>();
        lo4_adj.add(o3);
        lo4_adj.add(o5);
        o4.setListIdObjetsAdjas(lo4_adj);
        System.out.println("la liste des objets adjacants   " + o4.getListIdObjetsAdjas());

        ArrayList<Objet> lo5_adj = new ArrayList<Objet>();
        lo5_adj.add(o2);
        lo5_adj.add(o3);
        lo5_adj.add(o4);
        o5.setListIdObjetsAdjas(lo5_adj);
        System.out.println("la liste des objets adjacants   " + o5.getListIdObjetsAdjas());

        ArrayList<Objet> listObjets = new ArrayList<Objet>();
        listObjets.add(o1);
        listObjets.add(o2);
        listObjets.add(o3);
        listObjets.add(o4);
        listObjets.add(o5);
        Zone Z1 = new Zone(1,listObjets);
        System.out.println(Z1.getIdZone());
        double[] poids = new double[2];
        poids[0]= 0.5;
        poids[1]= 0.5;

        GAPtree tree = Z1.solapGAPtree(listObjets,poids);
        tree.getRacine().print();

        //ArrayList<Noeud> list =new ArrayList<>();
        /*
        ArrayList<Noeud> resultat =new ArrayList<Noeud>();
        resultat = tree.chercher(tree.getRacine(),0.2,list);
        System.out.println(resultat);
        for (Noeud l : resultat){
            System.out.println(l.getElement());
        }
        System.out.println("--------------------------------------------");
        ArrayList<Noeud> re=tree.suppNoeudFrr(resultat);
        for (Noeud l : re){
            System.out.println(l.getElement());
        }
        System.out.println("--------------------------------------------");
        ArrayList<Noeud> re2=tree.supFilsSelec(re);
        for (Noeud l : re2){
            System.out.println(l.getElement());
        }*/

        ArrayList<Noeud> list = tree.navigation(0.2);
        for (Noeud l : list){
            System.out.println(l.getElement());
        }
    }
}
