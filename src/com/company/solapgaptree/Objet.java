package com.company.solapgaptree;

import oracle.spatial.geometry.JGeometry;

import java.util.ArrayList;

public class Objet implements Cloneable{
    private int IdObjet;
    private String NonObjet;
    private double SurfaceObjet;
    private double NbCrime;
    private double TauxSec;
    private double NiveauxVie;
    private ArrayList<Objet> listObjetsAdjas;
    private JGeometry geom;

    // Constructeur

    public Objet(int numzone, String nomezone, Double area, JGeometry geom, Double NbCrime, Double TauxSec, Double NiveauxVie) {
        this.IdObjet=numzone;
        this.NonObjet=nomezone;
        this.SurfaceObjet=area;
        //this.listIdObjetsAdjas=listIdObjetsAdjas;
        this.geom=geom;
        this.NbCrime=NbCrime;
        this.NiveauxVie=NiveauxVie;
        this.TauxSec=TauxSec;
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

    public double getNbCrime() {
        return NbCrime;
    }

    public double getTauxSec(){return TauxSec;}

    public double getNiveauxVie() { return NiveauxVie; }

    public ArrayList<Objet> getListObjetsAdjas() {
        return listObjetsAdjas;
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

    public void setNbCrime(double Nbcrime) { NbCrime = Nbcrime; }

    public void setTauxSec(double tauxSec) { TauxSec = tauxSec; }

    public void setNiveauxVie(double niveauxVie) { NiveauxVie = niveauxVie; }

    public void setListObjetsAdjas(ArrayList<Objet> listObjetsAdjas) {
        this.listObjetsAdjas = listObjetsAdjas;
    }

    public void setGeom(JGeometry geom) {
        this.geom = geom;
    }

    //les methodes
    @Override
    public Object clone() throws CloneNotSupportedException {
        Objet cloned = (Objet) super.clone();
        cloned.setIdObjet(cloned.getIdObjet());
        cloned.setNonObjet(cloned.getNonObjet());
        cloned.setNbCrime(cloned.getNbCrime());
        cloned.setListObjetsAdjas(cloned.getListObjetsAdjas());
        cloned.setGeom(cloned.getGeom());
        cloned.setSurfaceObjet(cloned.getSurfaceObjet());
        // the above is applicable in case of primitive member types,
        // however, in case of non primitive types
        // cloned.setNonPrimitiveType(cloned.getNonPrimitiveType().clone());
        return cloned;
    }
}
