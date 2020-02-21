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

    public Nodo(Tipo tipo, Token Value) {
        this.Previous = null;
        this.Left = null;
        this.Right = null;
        this.tipo = tipo;
        this.Value = Value;
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
    
    
}
