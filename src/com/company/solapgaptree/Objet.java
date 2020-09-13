package com.company.solapgaptree;

import oracle.spatial.geometry.JGeometry;

import java.util.ArrayList;

public class Objet implements Cloneable{
    private int IdObjet;
    private String NonObjet;
    private double SurfaceObjet;
    private double MesureObjet;
    private ArrayList<Objet> listIdObjetsAdjas;
    private JGeometry geom;

    // Constructeur

    public Objet(int IdObjet, String NonObjet, double SurfaceObjet, double MesureObjet,JGeometry geom){
        this.IdObjet=IdObjet;
        this.NonObjet=NonObjet;
        this.SurfaceObjet=SurfaceObjet;
        this.MesureObjet=MesureObjet;
        //this.listIdObjetsAdjas=listIdObjetsAdjas;
        this.geom=geom;
    }

    public Objet(int numzone, String nomezone, Double area, JGeometry geom) {
        this.IdObjet=numzone;
        this.NonObjet=nomezone;
        this.SurfaceObjet=area;
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

    public JGeometry getGeom() {
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

    public void setGeom(JGeometry geom) {
        this.geom = geom;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Objet cloned = (Objet) super.clone();
        cloned.setIdObjet(cloned.getIdObjet());
        cloned.setNonObjet(cloned.getNonObjet());
        cloned.setMesureObjet(cloned.getMesureObjet());
        cloned.setListIdObjetsAdjas(cloned.getListIdObjetsAdjas());
        cloned.setGeom(cloned.getGeom());
        cloned.setSurfaceObjet(cloned.getSurfaceObjet());
        // the above is applicable in case of primitive member types,
        // however, in case of non primitive types
        // cloned.setNonPrimitiveType(cloned.getNonPrimitiveType().clone());
        return cloned;
    }
}
