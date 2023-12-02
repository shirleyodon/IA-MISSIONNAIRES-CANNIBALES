/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eni.m1.ia;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shirleyodon
 */
public class Etat {
    /**
     * Abréviation des noms des attributs
     * misG : nombre de missionnaires à la rive gauche
     * misD : nombre de missionnaires à la rive droite
     * canG : nombre de cannibale à la rive gauche
     * canD : nombre de cannibale à la rive droite
     */
    
    private int misG, misD, canG, canD;
    private Position bateau;
    private Etat pere;

    public Etat(int misG, int canG, Position bateau, int misD,  int canD) {
        this.misG = misG;
        this.misD = misD;
        this.canG = canG;
        this.canD = canD;
        this.bateau = bateau;
    }
    
    public boolean estArrive(){
        return misG==0 && canG==0;
    }
    
    public boolean etatValide(){
        boolean valide=false;
        if(misG>=0 && misD>=0 && canG>=0 && canD>=0 && (misG==0 || misG>= canG) && (misD==0 || misD>=canD))
            valide=true;
        return valide;
    }
    
    private void testerEtAjouter(List<Etat> list, Etat e){
        if(e.etatValide()){
            e.setPere(this);
            list.add(e);
        }
    }
    
    public List<Etat> genererSuccesseurs(){
        List<Etat> list=new ArrayList();
        if(bateau==Position.GAUCHE){
            testerEtAjouter(list, new Etat(misG-1, canG, Position.DROITE, misD+1, canD)); //Un Missionnaire fait une traversée de Gauche vers la Droite
            testerEtAjouter(list, new Etat(misG-2, canG, Position.DROITE, misD+2, canD)); //Deux Missionnaires font une traversée de Gauche vers la Droite
            testerEtAjouter(list, new Etat(misG-1, canG-1, Position.DROITE, misD+1, canD+1)); // Un couple Missionnaire-Cannibale fait une traversée de Gauche vers la Droite
            testerEtAjouter(list, new Etat(misG, canG-1, Position.DROITE, misD, canD+1)); // Un Cannibale fait une traversée de Gauche vers la Droite
            testerEtAjouter(list, new Etat(misG, canG-2, Position.DROITE, misD, canD+2)); // Deux Cannibales font une traversée de Gauche vers la Droite
        }else{
            testerEtAjouter(list, new Etat(misG+1, canG, Position.GAUCHE, misD-1, canD)); //Un Missionnaire fait une traversée de Droite vers la Gauche
            testerEtAjouter(list, new Etat(misG+2, canG, Position.GAUCHE, misD-2, canD)); //Deux Missionnaires font une traversée de Droite vers la Gauche
            testerEtAjouter(list, new Etat(misG+1, canG+1, Position.GAUCHE, misD-1, canD-1)); // Un couple Missionnaire-Cannibale fait une traversée de Droite vers la Gauche
            testerEtAjouter(list, new Etat(misG, canG+1, Position.GAUCHE, misD, canD-1)); // Un Cannibale fait une traversée de Droite vers la Gauche
            testerEtAjouter(list, new Etat(misG, canG+2, Position.GAUCHE, misD, canD-2)); // Deux Cannibales font une traversée de Droite vers la Gauche
        }
        return list;
    }

    public int getMisG() {
        return misG;
    }

    public void setMisG(int misG) {
        this.misG = misG;
    }

    public int getMisD() {
        return misD;
    }

    public void setMisD(int misD) {
        this.misD = misD;
    }

    public int getCanG() {
        return canG;
    }

    public void setCanG(int canG) {
        this.canG = canG;
    }

    public int getCanD() {
        return canD;
    }

    public void setCanD(int canD) {
        this.canD = canD;
    }

    public Etat getPere() {
        return pere;
    }

    public void setPere(Etat pere) {
        this.pere = pere;
    }
    
    public boolean estAGauche(){
        return (this.bateau==Position.GAUCHE);
    }
    
    public boolean estADroite(){
        return (this.bateau==Position.DROITE);
    }

    @Override
    public String toString() {
        if(bateau==Position.DROITE)
            return "(" + misG + "," + canG + "," + "D" + "," + misD + "," + canD + ")";
        else
            return "(" + misG + "," + canG + "," + "G" + "," + misD + "," + canD + ")";
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Etat other = (Etat) obj;
        return (this.misG == other.misG) && (this.misD == other.misD)
            && (this.canG == other.canG) && (this.canD == other.canD)
            && (this.bateau == other.bateau);
    }
}
