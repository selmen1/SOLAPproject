package com.company.crime;

import oracle.spatial.geometry.JGeometry;

public class ZoneCrime {
     private int NUMZONE;
     private String NOMEZONE;
     private JGeometry GEOM;
     private Double AREA;
     private Double TAUXSEC;
     private Double NIVEAUVIE;

    public ZoneCrime(int numzone, String nomezone, JGeometry j_geom, Double area,Double TAUXSEC, Double NIVEAUVIE) {
        this.NUMZONE=numzone;
        this.NOMEZONE=nomezone;
        this.GEOM=j_geom;
        this.AREA = area;
        this.TAUXSEC = TAUXSEC;
        this.NIVEAUVIE = NIVEAUVIE;
    }

    // les geteurs
    public String getNOMEZONE() {
        return NOMEZONE;
    }

    public int getNUMZONE() {
        return NUMZONE;
    }

    public JGeometry getGEOM() {
        return GEOM;
    }

    public Double getAREA() {
        return AREA;
    }

    public Double getTAUXSEC() { return TAUXSEC; }

    public Double getNIVEAUVIE() { return NIVEAUVIE; }

    // les seteurs
    public void setNUMZONE(int NUMZONE) {
        this.NUMZONE = NUMZONE;
    }

    public void setNOMEZONE(String NOMEZONE) {
        this.NOMEZONE = NOMEZONE;
    }

    public void setGEOM(JGeometry GEOM) { this.GEOM = GEOM; }

    public void setAREA(Double AREA) {
        this.AREA = AREA;
    }

    public void setTAUXSEC(Double TAUXSEC) { this.TAUXSEC = TAUXSEC; }

    public void setNIVEAUVIE(Double NIVEAUVIE) { this.NIVEAUVIE = NIVEAUVIE; }
}
