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
public class Token {
    
    public enum Tipo{
        Numero,
        Identificador,
        PR_CONJ,
        DobleBarra,
        BeginCM,
        EndCM,
        Flecha, 
        AbreLlaves,
        CierraLlaves,
        DoblePorcentaje,
        DosPuntos,
        PuntoYComa,
        Punto,
        Absoluto,
        Kleene,
        Interrogacion,
        Mas,
        enie,
        cadena,
        coma,
        aceptacion
    }
    
    private Tipo tipoToken;
    private String valor;
    private int ID;
    private int columna;
    private int fila;

    public Token(Tipo tipoDelToken, String val, int fila, int columna)
        {
            this.tipoToken = tipoDelToken;
            this.valor = val;
            this.fila = fila;
            this.columna = columna;
        }

        public String GetVal()
        {
            return valor;
        }
        public int GetFila()
        {
            return fila;
        }
        public int GetColumna()
        {
            return columna;
        }

    public void setTipoToken(Tipo tipoToken) {
        this.tipoToken = tipoToken;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public String GetTipo(){
        switch(tipoToken){
            case Numero:
                return "Numero";
            case Identificador:
                return "Identificador";
            case PR_CONJ:
                return "Palabra Reservada CONJ";
            case DobleBarra:
                return "Doble Barra";
            case BeginCM:
                return "Inicio Comentario";
            case EndCM:
                return "Fin Comentario";
            case AbreLlaves:
                return "Símbolo Abrir Llaves";
            case CierraLlaves:
                return "Símbolo Cerrar Llaves";
            case DoblePorcentaje:
                return "Doble Porcentaje";
            case DosPuntos:
                return "Dos Puntos";
            case PuntoYComa:
                return "Símbolo Punto y Coma";
            case Punto:
                return "Concatenacion";
            case Absoluto:
                return "Disyunción";
            case Kleene:
                return "Cerradura de Kleene";
            case Interrogacion:
                return "Signo de Interrogacion";
            case Mas:
                return "Simbolo Suma";
            case enie:
                return "Simbolo de la ñ";
            case cadena:
                return "Cadena";
            case coma:
                return "Símbolo coma";
            case Flecha:
                return "Flecha";
            case aceptacion:
                return "Simbolo Aceptación";
            default:
                return "desconocido";
        }
    }
    
    public int GetID(){
        switch(tipoToken){
            case Numero:
                return 1;
            case Identificador:
                return 2;
            case PR_CONJ:
                return 3;
            case DobleBarra:
                return 4;
            case BeginCM:
                return 5;
            case EndCM:
                return 6;
            case AbreLlaves:
                return 7;
            case CierraLlaves:
                return 8;
            case DoblePorcentaje:
                return 9;
            case DosPuntos:
                return 10;
            case PuntoYComa:
                return 11;
            case Punto:
                return 12;
            case Absoluto:
                return 13;
            case Kleene:
                return 14;
            case Interrogacion:
                return 15;
            case Mas:
                return 16;
            case enie:
                return 17;
            case cadena:
                return 18;
            case coma:
                return 19;
            case Flecha:
                return 20;
            case aceptacion:
                return 21;
            default:
                return 22;
        }
    }
    
    
}
