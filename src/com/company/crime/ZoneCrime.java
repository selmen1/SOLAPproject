package com.company.crime;

import oracle.spatial.geometry.JGeometry;

public class ZoneCrime {
     private int NUMZONE;
     private String NOMEZONE;
     private JGeometry GEOM;
     private Double AREA;

    public ZoneCrime(int numzone, String nomezone, JGeometry j_geom, Double area) {
        this.NUMZONE=numzone;
        this.NOMEZONE=nomezone;
        this.GEOM=j_geom;
        this.AREA = area;
    }


    public String getNOMEZONE() {
        return NOMEZONE;
    }

    public int getNUMZONE() {
        return NUMZONE;
    }

    public void setNUMZONE(int NUMZONE) {
        this.NUMZONE = NUMZONE;
    }

    public void setNOMEZONE(String NOMEZONE) {
        this.NOMEZONE = NOMEZONE;
    }

    public void setGEOM(JGeometry GEOM) {
        this.GEOM = GEOM;
    }

    public JGeometry getGEOM() {
        return GEOM;
    }

    public Double getAREA() {
        return AREA;
    }

    public void setAREA(Double AREA) {
        this.AREA = AREA;
    }

}
