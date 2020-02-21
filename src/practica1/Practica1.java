/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.security.Principal;
import UI.Form;
import beans.AnalizadorLexico;
import beans.ListasAnalisis;
import beans.Nodo;
import beans.Token;
import java.util.LinkedList;

/**
 *
 * @author Guillermo
 */
public class Practica1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Form first = new Form();
        first.show();
        String ejemplo = "hola amigos ; : , CONJ -> f";
    }
    
    public static void Analizar(String texto){
        AnalizadorLexico analizador = new AnalizadorLexico();
        ListasAnalisis resultado = analizador.escanear(texto);
        analizador.imprimiListaToken(resultado.getSalida());
        System.out.println("Expreisones Regulares");
        ListasAnalisis expresiones = analizador.escanear(resultado.getExpresionesRegulares().get(2).getExpresion());
        analizador.imprimiListaToken(expresiones.getSalida());
        LinkedList<Nodo> temporal = setType(expresiones.getSalida());
        System.out.println(temporal.size());
        temporal.addFirst(new Nodo(Nodo.Tipo.Operador_Binario, new Token(Token.Tipo.Punto, ".", 0, 0)));
        temporal.addLast(new Nodo(Nodo.Tipo.Terminal, new Token(Token.Tipo.aceptacion, "#", 0, 0)));
        Nodo cabeza = GenerarArbol(temporal);
        System.out.println(cabeza.getValue().GetVal());
    }
    
    
    
    public static LinkedList<Nodo> setType(LinkedList<Token> lista){
        LinkedList<Nodo> Ramas = new LinkedList<>();
        for(Token objeto:lista){
           if("Concatenacion".equals(objeto.GetTipo())){
               Ramas.add(new Nodo(Nodo.Tipo.Operador_Binario,objeto));
           }else if("Disyunción".equals(objeto.GetTipo())){
               Ramas.add(new Nodo(Nodo.Tipo.Operador_Binario,objeto));
           }else if("Cerradura de Kleene".equals(objeto.GetTipo())){
               Ramas.add(new Nodo(Nodo.Tipo.Operador_Unario,objeto));
           }else if("Asterisco".equals(objeto.GetTipo())){
               Ramas.add(new Nodo(Nodo.Tipo.Operador_Unario,objeto));
           }else if("Simbolo Suma".equals(objeto.GetTipo())){
               Ramas.add(new Nodo(Nodo.Tipo.Operador_Unario,objeto));
           }else if("Cadena".equals(objeto.GetTipo())){
               Ramas.add(new Nodo(Nodo.Tipo.Terminal,objeto));
           }else if("Identificador".equals(objeto.GetTipo())){
               Ramas.add(new Nodo(Nodo.Tipo.Terminal,objeto));
           }
        }
        return Ramas;
    }   
    public static Nodo GenerarArbol(LinkedList<Nodo> lista){
            for(int i =0; i<lista.size(); i++){
                //OTT
                if(lista.get(i).getTipo()==Nodo.Tipo.Operador_Binario &&
                   lista.get(i+1).getTipo()==Nodo.Tipo.Terminal &&
                   lista.get(i+2).getTipo()==Nodo.Tipo.Terminal){
                   lista.get(i).setLeft(lista.get(i+1));
                   lista.get(i+1).setPrevious(lista.get(i));
                   lista.get(i).setRight(lista.get(i+2));
                   lista.get(i+2).setPrevious(lista.get(i));
                   lista.get(i).setTipo(Nodo.Tipo.Terminal);
                   lista.remove(i+2);
                   lista.remove(i+1);
                    GenerarArbol(lista);
                    break;
                }//OUTUT
                else if(lista.get(i).getTipo()==Nodo.Tipo.Operador_Binario &&
                   lista.get(i+1).getTipo()==Nodo.Tipo.Operador_Unario&&
                   lista.get(i+2).getTipo()==Nodo.Tipo.Terminal &&
                   lista.get(i+3).getTipo()==Nodo.Tipo.Operador_Unario&&
                   lista.get(i+4).getTipo()==Nodo.Tipo.Terminal){
                   lista.get(i).setLeft(lista.get(i+1));
                   lista.get(i+1).setPrevious(lista.get(i));
                   lista.get(i+1).setLeft(lista.get(i+2));
                   lista.get(i+2).setPrevious(lista.get(i+1));
                   lista.get(i).setRight(lista.get(i+3));
                   lista.get(i+3).setPrevious(lista.get(i));
                   lista.get(i+3).setLeft(lista.get(i+4));
                   lista.get(i+4).setPrevious(lista.get(i+3));
                   lista.get(i).setTipo(Nodo.Tipo.Terminal);
                   lista.remove(i+4);
                   lista.remove(i+3);
                   lista.remove(i+2); 
                   lista.remove(i+1);
                    GenerarArbol(lista);
                    break;
                }//OTUT
                else if(lista.get(i).getTipo()==Nodo.Tipo.Operador_Binario &&
                   lista.get(i+1).getTipo()==Nodo.Tipo.Terminal &&
                   lista.get(i+2).getTipo()==Nodo.Tipo.Operador_Unario&&
                   lista.get(i+3).getTipo()==Nodo.Tipo.Terminal){
                   lista.get(i).setLeft(lista.get(i+1));
                   lista.get(i+1).setPrevious(lista.get(i));
                   lista.get(i).setRight(lista.get(i+2));
                   lista.get(i+2).setPrevious(lista.get(i));
                   lista.get(i+2).setLeft(lista.get(i+3));
                   lista.get(i+3).setPrevious(lista.get(i+2));
                   lista.get(i).setTipo(Nodo.Tipo.Terminal);
                   lista.remove(i+3);
                   lista.remove(i+2);
                   lista.remove(i+1);
                    GenerarArbol(lista);
                    break;
                }//OUTT
                else if(lista.get(i).getTipo()==Nodo.Tipo.Operador_Binario &&
                   lista.get(i+1).getTipo()==Nodo.Tipo.Operador_Unario&&
                   lista.get(i+2).getTipo()==Nodo.Tipo.Terminal &&
                   lista.get(i+3).getTipo()==Nodo.Tipo.Terminal){
                   lista.get(i).setLeft(lista.get(i+1));
                   lista.get(i+1).setPrevious(lista.get(i));
                   lista.get(i+1).setLeft(lista.get(i+2));
                   lista.get(i+2).setPrevious(lista.get(i+1));
                   lista.get(i).setRight(lista.get(i+3));
                   lista.get(i+3).setPrevious(lista.get(i));
                   lista.get(i).setTipo(Nodo.Tipo.Terminal);
                   lista.remove(i+3);
                   lista.remove(i+2); 
                   lista.remove(i+1);
                    GenerarArbol(lista);
                    break;
                }
                        
        }        
        return lista.get(0);
    }
    public static String GraphTree(Nodo Cabeza){
        Nodo aux = Cabeza;
        int i = 0;
        String head = "digraph G {\n nodesep=0.3;\n ranksep=0.2;\n    margin=0.1;\n   node [shape=circle];\n  edge [arrowsize=0.8];";
        return head;
    }
    
}



