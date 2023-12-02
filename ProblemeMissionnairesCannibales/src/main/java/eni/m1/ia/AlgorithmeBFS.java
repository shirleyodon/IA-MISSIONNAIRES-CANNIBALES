/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eni.m1.ia;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author shirleyodon
 */
public class AlgorithmeBFS {
    public Etat executerBFS(Etat debut){
        if(debut.estArrive()) return debut;
        
        Queue<Etat> listDAttente=new LinkedList();
        Set<Etat> traitee=new HashSet();
        listDAttente.add(debut);
        
        while(true){
            if(listDAttente.isEmpty()) return null;
            
            Etat pere=listDAttente.poll();
            traitee.add(pere);
            List<Etat> successeurs=pere.genererSuccesseurs();
            
            System.out.print(pere.toString() + " --> " + "{ ");
            
            for(Etat fils: successeurs){
                if(!traitee.contains(fils) || !listDAttente.contains(fils)){
                    
                    System.out.print(fils.toString() + ", ");
                    
                    if(fils.estArrive()) return fils;
                    else listDAttente.add(fils);
                }
            }
            
            System.out.println(" }");
        }
    }
}
