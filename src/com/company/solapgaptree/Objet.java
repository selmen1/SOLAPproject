package com.company.solapgaptree;

import java.util.ArrayList;

public class Objet {
    private int IdObjet;
    private String NonObjet;
    private double SurfaceObjet;
    private double MesureObjet;
    private ArrayList<Objet> listIdObjetsAdjas;
    private Polygone geom;

    // Constructeur


    public Objet(int IdObjet, String NonObjet, double SurfaceObjet, double MesureObjet,Polygone geom){
        this.IdObjet=IdObjet;
        this.NonObjet=NonObjet;
        this.SurfaceObjet=SurfaceObjet;
        this.MesureObjet=MesureObjet;
        //this.listIdObjetsAdjas=listIdObjetsAdjas;
        this.geom=geom;
    }
    // les geteurs
    public int getIdObjet() {
        return IdObjet;
    }

    public String getNonObjet() {
        return NonObjet;
    }

    public double getSurfaceObjet() {
        return SurfaceObjet;
    }

    public double getMesureObjet() {
        return MesureObjet;
    }

    public ArrayList<Objet> getListIdObjetsAdjas() {
        return listIdObjetsAdjas;
    }

    public Polygone getGeom() {
        return geom;
    }

    //les seteurs
    public void setIdObjet(int idObjet) {
        IdObjet = idObjet;
    }

    public void setNonObjet(String nonObjet) {
        NonObjet = nonObjet;
    }

    public void setSurfaceObjet(double surfaceObjet) {
        SurfaceObjet = surfaceObjet;
    }

    public void setMesureObjet(double mesureObjet) {
        MesureObjet = mesureObjet;
    }

    public void setListIdObjetsAdjas(ArrayList<Objet> listIdObjetsAdjas) {
        this.listIdObjetsAdjas = listIdObjetsAdjas;
    }

    public void setGeom(Polygone geom) {
        this.geom = geom;
    }
}
