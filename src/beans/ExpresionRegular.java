/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.LinkedList;

/**
 *
 * @author Guillermo
 */
public class ExpresionRegular {
    private LinkedList<Token> Nodos;
    private String expID;
    private String expresion;

    public ExpresionRegular(String expID, String expresion) {
        this.expID = expID;
        this.expresion = expresion;
    }

    public LinkedList<Token> getNodos() {
        return Nodos;
    }

    public void setNodos(LinkedList<Token> Nodos) {
        this.Nodos = Nodos;
    }

    public String getExpID() {
        return expID;
    }

    public void setExpID(String expID) {
        this.expID = expID;
    }

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }
    
    
    
    
}
