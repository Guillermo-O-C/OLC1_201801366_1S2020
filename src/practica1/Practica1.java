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
import java.io.FileWriter;
import java.io.IOException;
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
    
    public static void Analizar(String texto) throws IOException{
        AnalizadorLexico analizador = new AnalizadorLexico();
        ListasAnalisis resultado = analizador.escanear(texto);
        analizador.imprimiListaToken(resultado.getSalida());
        System.out.println("Expreisones Regulares");
        for(int i =0; i<resultado.getExpresionesRegulares().size();i++){
            ListasAnalisis expresiones = analizador.escanear(resultado.getExpresionesRegulares().get(i).getExpresion());
            analizador.imprimiListaToken(expresiones.getSalida());
            LinkedList<Nodo> temporal = setType(expresiones.getSalida());
            System.out.println(temporal.size());
            temporal.addFirst(new Nodo(Nodo.Tipo.Operador_Binario, new Token(Token.Tipo.Punto, ".", 0, 0)));
            temporal.addLast(new Nodo(Nodo.Tipo.Terminal, new Token(Token.Tipo.aceptacion, "#", 0, 0)));
            Nodo cabeza = GenerarArbol(temporal);
            System.out.println(cabeza.getValue().GetVal());
            GraphTree(cabeza, resultado.getExpresionesRegulares().get(i).getExpID());
        }
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
               objeto.setValor(objeto.GetVal().substring(1, objeto.GetVal().length()));
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
    public static void GraphTree(Nodo Cabeza, String nombre) throws IOException{
        String head = "digraph G {\n nodesep=0.3;\n ranksep=0.2;\n    margin=0.1;\n   node [shape=circle];\n  edge [arrowsize=0.8];";       
        head += NextNodos(Cabeza, 0)+"}";
        //System.IO.File.WriteAllText(@"D:\\" + nombre[1] + ".txt", grafica);
        writeDOC(nombre, head);
        try {            
            ProcessBuilder pbuilder;
            pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", "D:\\"+nombre+".png","D:\\"+nombre+".dot");
            pbuilder.redirectErrorStream(true);
            pbuilder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String NextNodos(Nodo Central, int i ){
        String content="";
        if(Central.getLeft()!=null){
            content +="\""+ i+"_"+Central.getValue().GetVal()+"\" -> \""+(i+1)+"_"+Central.getLeft().getValue().GetVal()+"\";\n";
            content += NextNodos(Central.getLeft(), i+1);
        }
        if(Central.getRight()!=null){
            content +="\""+ i+"_"+Central.getValue().GetVal()+"\" -> \""+(i+1)+"_"+Central.getRight().getValue().GetVal()+"\";\n";
            content += NextNodos(Central.getRight(), i+1);            
        }
        //if(Central.getRight()==null && Central.getLeft()==null){
        //    content+=i+"_"+Central.getValue().GetVal();
        //}
        return content;   
    }
    
    public static void writeDOC(String name, String doc) throws IOException{
        try (FileWriter File = new FileWriter("D:\\"+name+".txt")) {
            File.write(doc);
        }
    }
}



