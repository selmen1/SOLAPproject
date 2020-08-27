package com.company.solapgaptree;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class Noeud implements Cloneable{
    private Integer element;
    private Double importance;
    private Noeud gauche;
    private Noeud droit;

    public Noeud (){
        element = null;
        importance = null;
        gauche = null;
        droit = null;
    }

    public Noeud(Integer element, Double Importance, Noeud gauche, Noeud droit){
        this.element=element;
        this.importance=Importance;
        this.gauche=gauche;
        this.droit=droit;
    }


    public int getElement() {
        return element;
    }

    public double getImportance() {
        return importance;
    }

    public Noeud getGauche() {
        return gauche;
    }

    public Noeud getDroite() {
        return droit;
    }

    public void setID(int element) {
        this.element = element;
    }

    public void setImportance(Double importance) {
        importance = importance;
    }

    public void setGauche(Noeud gauche) {
        gauche = gauche;
    }

    public void setDroite(Noeud droite) {
        droite = droite;
    }

    public Object clone() throws CloneNotSupportedException{
        // copie en profondeur d’un noeud
        Noeud g = null;
        if( gauche != null ) g = (Noeud)gauche.clone();
        Noeud d = null;
        if( droit != null ) d = (Noeud)droit.clone();
        return new Noeud(element,importance, g, d);
    }

    // affichage dans un fichier
    public void printTree(OutputStreamWriter out) throws IOException {
        if (droit != null) {
            droit.printTree(out, true, "");
        }
        printNodeValue(out);
        if (gauche != null) {
            gauche.printTree(out, false, "");
        }
    }
    public void printNodeValue(OutputStreamWriter out) throws IOException {
        if (importance == null) {
            out.write("<null>");
        } else {
            out.write(importance.toString());
        }
        out.write('\n');
    }
    // use string and not stringbuffer on purpose as we need to change the indent at each recursion
    public void printTree(OutputStreamWriter out, boolean isRight, String indent) throws IOException {
        if (droit != null) {
            droit.printTree(out, true, indent + (isRight ? "        " : " |      "));
        }
        out.write(indent);
        if (isRight) {
            out.write(" /");
        } else {
            out.write(" \\");
        }
        out.write("----- ");
        printNodeValue(out);
        if (gauche != null) {
            gauche.printTree(out, false, indent + (isRight ? " |      " : "        "));
        }
    }

    // affichage
    public void print() {
        print("", this, false);
    }

    public void print(String prefix, Noeud n, boolean isLeft) {
        if (n != null) {
            System.out.println (prefix + (isLeft ? "|-- " : "\\-- ") + n.element);
            print(prefix + (isLeft ? "|   " : "    "), n.gauche, true);
            print(prefix + (isLeft ? "|   " : "    "), n.droit, false);
        }
    }
}
