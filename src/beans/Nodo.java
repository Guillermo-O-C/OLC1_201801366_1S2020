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
public class Nodo {
    public enum Tipo{
        Terminal,
        Operador_Binario,
        Operador_Unario
    }    
    
    private Nodo Previous;
    private Nodo Left;
    private Nodo Right;
    private Tipo tipo;
    private Token Value;
    private boolean anulable;
    private int ID;
    private LinkedList<Nodo> Primeros;
    private LinkedList<Nodo> Ultimos;
    private LinkedList<Nodo> Siguientes;

    public Nodo(Tipo tipo, Token Value, int ID){
        this.Previous = null;
        this.Left = null;
        this.Right = null;
        this.tipo = tipo;
        this.Value = Value;
        this.ID = ID;
        this.Primeros = new LinkedList<>();
        this.Ultimos = new LinkedList<>();
        this.Siguientes = new LinkedList<>();
    }    
    
    public LinkedList<Nodo> getSiguientes() {
        return Siguientes;
    }

    public void setSiguientes(LinkedList<Nodo> Siguientes) {
        this.Siguientes = Siguientes;
    }

    public LinkedList<Nodo> getPrimeros() {
        return Primeros;
    }

    public void setPrimeros(LinkedList<Nodo> Primeros) {
        this.Primeros = Primeros;
    }

    public LinkedList<Nodo> getUltimos() {
        return Ultimos;
    }

    public void setUltimos(LinkedList<Nodo> Ultimos) {
        this.Ultimos = Ultimos;
    }
    
    public Nodo getPrevious() {
        return Previous;
    }

    public Nodo getLeft() {
        return Left;
    }

    public Nodo getRight() {
        return Right;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Token getValue() {
        return Value;
    }

    public void setPrevious(Nodo Previous) {
        this.Previous = Previous;
    }

    public void setLeft(Nodo Left) {
        this.Left = Left;
    }

    public void setRight(Nodo Right) {
        this.Right = Right;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setValue(Token Value) {
        this.Value = Value;
    }

    public boolean isAnulable() {
        return anulable;
    }

    public void setAnulable(boolean anulable) {
        this.anulable = anulable;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    
}
