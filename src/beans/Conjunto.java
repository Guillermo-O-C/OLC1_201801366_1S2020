/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Guillermo
 */
public class Conjunto {
    private String ID;
    private String conjunto;

    public Conjunto(String ID, String conjunto) {
        this.ID = ID;
        this.conjunto = conjunto;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getConjunto() {
        return conjunto;
    }

    public void setConjunto(String conjunto) {
        this.conjunto = conjunto;
    }
    
    
    
}
