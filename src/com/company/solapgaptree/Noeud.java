package com.company.solapgaptree;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class Noeud implements Cloneable{
    private Integer element;
    private Double importance;
    private Noeud gauche;
    private Noeud droite;

    public Noeud (){
        element = null;
        importance = null;
        gauche = null;
        droite = null;
    }

    public Noeud(Integer element, Noeud gauche, Noeud droite){
        this.element=element;
        this.gauche=gauche;
        this.droite=droite;
    }


    public int getElement() {
        return element;
    }

    public Double getImportance() {
         return importance;
    }

    public Noeud getGauche() {
        return gauche;
    }

    public Noeud getDroite() {
        return droite;
    }

    public void setID(int element) {
        this.element = element;
    }

    public void setImportance(Double importance) {
        this.importance = importance;
    }

    public void setGauche(Noeud gauche) {
       this.gauche = gauche;
    }

    public void setDroite(Noeud droite) {
        this.droite = droite;
    }

    public Object clone() throws CloneNotSupportedException{
        Object clone = super.clone();
        // copie en profondeur d’un noeud
        Noeud g = null;
        if( gauche != null ) g = (Noeud)gauche.clone();
        Noeud d = null;
        if( droite != null ) d = (Noeud)droite.clone();
        return new Noeud(element, g, d);
    }

    // affichage dans un fichier
    public void printTree(OutputStreamWriter out) throws IOException {
        if (droite != null) {
            droite.printTree(out, true, "");
        }
        printNodeValue(out);
        if (gauche != null) {
            gauche.printTree(out, false, "");
        }
    }
    public void printNodeValue(OutputStreamWriter out) throws IOException {
        if (importance == 0) {
            out.write("<null>");
        } else {
            out.write(String.valueOf(importance));
        }
        out.write('\n');
    }
    // use string and not stringbuffer on purpose as we need to change the indent at each recursion
    public void printTree(OutputStreamWriter out, boolean isRight, String indent) throws IOException {
        if (droite != null) {
            droite.printTree(out, true, indent + (isRight ? "        " : " |      "));
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
            System.out.println (prefix + (isLeft ? "|-- " : "\\-- ") + n.element  + "   " + n.importance);
            print(prefix + (isLeft ? "|   " : "    "), n.gauche, true);
            print(prefix + (isLeft ? "|   " : "    "), n.droite, false);
        }
    }
}
