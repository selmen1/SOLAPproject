package com.company.crime;

import oracle.spatial.geometry.JGeometry;

public class Crime {
     private int ID_CRIME;
     private int NUMZONE;
     private JGeometry GEOM;

     public Crime(int ID_CRIME,int NUMZONE,JGeometry GEOM){
          this.ID_CRIME=ID_CRIME;
          this.NUMZONE=NUMZONE;
          this.GEOM=GEOM;
     }

     public int getNUMZONE() {
          return NUMZONE;
     }

     public int getID_CRIME() {
          return ID_CRIME;
     }

     public JGeometry getGEOM() {
          return GEOM;
     }

     public void setNUMZONE(int NUMZONE) {
          this.NUMZONE = NUMZONE;
     }

     public void setGEOM(JGeometry GEOM) {
          this.GEOM = GEOM;
     }

     public void setID_CRIME(int ID_CRIME) {
          this.ID_CRIME = ID_CRIME;
     }

}
