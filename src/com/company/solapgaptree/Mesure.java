package com.company.solapgaptree;

public class Mesure {
    private String label;
    private String type;
    private double valeur;

    public Mesure(String label, String type, double valeur){
        this.label=label;
        this.type=type;
        this.valeur=valeur;
    }

    public String getLabel() {
        return label;
    }

    public String getType() {
        return type;
    }

    public double getValeur() {
        return valeur;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

}
