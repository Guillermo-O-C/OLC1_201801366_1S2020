/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import UI.Form;
import beans.AnalizadorLexico;
import beans.Conjunto;
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
        GraphConjuntos(resultado.getConjuntos());
        analizador.imprimiListaToken(resultado.getSalida());
        System.out.println("Expreisones Regulares");
        for(int i =0; i<resultado.getExpresionesRegulares().size();i++){
            ListasAnalisis expresiones = analizador.escanear(resultado.getExpresionesRegulares().get(i).getExpresion());
            analizador.imprimiListaToken(expresiones.getSalida());
            LinkedList<Nodo> temporal = setType(expresiones.getSalida());
            System.out.println(temporal.size());
            temporal.addFirst(new Nodo(Nodo.Tipo.Operador_Binario, new Token(Token.Tipo.Punto, ".", 0, 0), 0));
            temporal.addLast(new Nodo(Nodo.Tipo.Terminal, new Token(Token.Tipo.aceptacion, "#", 0, 0), 1));
            Nodo cabeza = GenerarArbol(temporal);
            System.out.println(cabeza.getValue().GetVal());
            GraphTree(cabeza, resultado.getExpresionesRegulares().get(i).getExpID());
            PreOrderAnulables(cabeza);
            PreOrderPrimeros(cabeza);
            PreOrderUltimos(cabeza);
            PreOrderSiguientes(cabeza);
            LinkedList<Nodo> Hojas = new LinkedList<>();
            GetHojas(cabeza, Hojas);
            System.out.println(cabeza.getID());
            GraphSiguientes(Hojas, resultado.getExpresionesRegulares().get(i).getExpID());
        }
    }
    
    public static LinkedList<Nodo> setType(LinkedList<Token> lista){
        LinkedList<Nodo> Ramas = new LinkedList<>();
        int i = 2;
        for(Token objeto:lista){
           if("Concatenacion".equals(objeto.GetTipo())){
               Ramas.add(new Nodo(Nodo.Tipo.Operador_Binario,objeto, i));
           }else if("Disyunción".equals(objeto.GetTipo())){
               Ramas.add(new Nodo(Nodo.Tipo.Operador_Binario,objeto, i));
           }else if("Cerradura de Kleene".equals(objeto.GetTipo())){
               Ramas.add(new Nodo(Nodo.Tipo.Operador_Unario,objeto, i));
           }else if("Signo de Interrogacion".equals(objeto.GetTipo())){
               Ramas.add(new Nodo(Nodo.Tipo.Operador_Unario,objeto, i));
           }else if("Simbolo Suma".equals(objeto.GetTipo())){
               Ramas.add(new Nodo(Nodo.Tipo.Operador_Unario,objeto, i));
           }else if("Cadena".equals(objeto.GetTipo())){
               objeto.setValor(objeto.GetVal().substring(1, objeto.GetVal().length()-1));
               Ramas.add(new Nodo(Nodo.Tipo.Terminal,objeto, i));
           }else if("Identificador".equals(objeto.GetTipo())){
               Ramas.add(new Nodo(Nodo.Tipo.Terminal,objeto, i));
           }
           i++;
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
        head += NextNodos(Cabeza)+"}";
        writeDOC(nombre, head);
        try {            
            ProcessBuilder pbuilder;
            pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", "D://"+nombre+".png","D://"+nombre+".dot");
            pbuilder.redirectErrorStream(true);
            pbuilder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String NextNodos(Nodo Central){
        String content="";
        content +="\""+ Central.getID()+"_"+Central.getValue().GetVal()+"\" [label=\""+Central.getValue().GetVal()+"\"]";
        if(Central.getLeft()!=null){
            content +="\""+ Central.getID()+"_"+Central.getValue().GetVal()+"\" -> \""+Central.getLeft().getID()+"_"+Central.getLeft().getValue().GetVal()+"\";\n";
            content += NextNodos(Central.getLeft());
        }
        if(Central.getRight()!=null){
            content +="\""+ Central.getID()+"_"+Central.getValue().GetVal()+"\" -> \""+Central.getRight().getID()+"_"+Central.getRight().getValue().GetVal()+"\";\n";
            content += NextNodos(Central.getRight());            
        }
        
        return content;   
    }
    
    public static void writeDOC(String name, String doc) throws IOException{
        try (FileWriter File = new FileWriter("D:\\"+name+".dot")) {
            File.write(doc);
        }
    }
    
    public static void SetAnulables(Nodo Raiz){
        switch(Raiz.getValue().GetTipo()){
            case "Identificador":
                Raiz.setAnulable(false);
                break;
            case "Cadena":
                Raiz.setAnulable(false);
                break;
            case "Simbolo Aceptación":
                Raiz.setAnulable(true);
                break;
            case "Cerradura de Kleene":
                Raiz.setAnulable(true);
                break;
            case "Signo de Interrogacion":
                Raiz.setAnulable(true);
                break;
            case "Simbolo Suma":
                Raiz.setAnulable(false);
                break;
            case "Concatenacion":
                if(Raiz.getLeft().isAnulable() && Raiz.getRight().isAnulable()){
                    Raiz.setAnulable(true);
                }else{
                    Raiz.setAnulable(false);
                }
                break;
            case "Disyunción":
                if(Raiz.getLeft().isAnulable() || Raiz.getRight().isAnulable()){
                    Raiz.setAnulable(true);
                }else{
                    Raiz.setAnulable(false);
                }
                break;            
        }
    }
    
    public static void PreOrderAnulables(Nodo Raiz){
        if(Raiz.getLeft()!=null){
            PreOrderAnulables(Raiz.getLeft());
        }
        if(Raiz.getRight()!=null){
            PreOrderAnulables(Raiz.getRight());
        }
        SetAnulables(Raiz);        
    }
    
    public static void SetPrimeros(Nodo Raiz){
        switch(Raiz.getValue().GetTipo()){
            case "Identificador":
                Raiz.getPrimeros().add(Raiz);
                break;
            case "Cadena":                
                Raiz.getPrimeros().add(Raiz);
                break;
            case "Simbolo Aceptación":                
                Raiz.getPrimeros().add(Raiz);
                break;
            case "Cerradura de Kleene":                
                Raiz.getPrimeros().add(Raiz.getLeft());
                break;
            case "Signo de Interrogacion":                
                Raiz.getPrimeros().add(Raiz.getLeft());
                break;
            case "Simbolo Suma":                
                Raiz.getPrimeros().add(Raiz.getLeft());
                break;
            case "Concatenacion":
                if(Raiz.getLeft().isAnulable()){                    
                    Raiz.getPrimeros().add(Raiz.getLeft());
                    Raiz.getPrimeros().add(Raiz.getRight());
                }else{
                    Raiz.getPrimeros().add(Raiz.getLeft());
                }
                break;
            case "Disyunción":
                Raiz.getPrimeros().add(Raiz.getLeft());
                Raiz.getPrimeros().add(Raiz.getRight());
                break;            
        }
    }
    
    public static void PreOrderPrimeros(Nodo Raiz){
        if(Raiz.getLeft()!=null){
            PreOrderPrimeros(Raiz.getLeft());
        }
        if(Raiz.getRight()!=null){
            PreOrderPrimeros(Raiz.getRight());
        }
        SetPrimeros(Raiz);        
    }
    
    public static void SetUltimos(Nodo Raiz){
        switch(Raiz.getValue().GetTipo()){
            case "Identificador":
                Raiz.getUltimos().add(Raiz);
                break;
            case "Cadena":                
                Raiz.getUltimos().add(Raiz);
                break;
            case "Simbolo Aceptación":                
                Raiz.getUltimos().add(Raiz);
                break;
            case "Cerradura de Kleene":                
                Raiz.getUltimos().add(Raiz.getLeft());
                break;
            case "Signo de Interrogacion":                
                Raiz.getUltimos().add(Raiz.getLeft());
                break;
            case "Simbolo Suma":                
                Raiz.getUltimos().add(Raiz.getLeft());
                break;
            case "Concatenacion":
                if(Raiz.getRight().isAnulable()){                    
                    Raiz.getUltimos().add(Raiz.getLeft());
                    Raiz.getUltimos().add(Raiz.getRight());
                }else{
                    Raiz.getUltimos().add(Raiz.getRight());
                }
                break;
            case "Disyunción":
                Raiz.getUltimos().add(Raiz.getLeft());
                Raiz.getUltimos().add(Raiz.getRight());
                break;            
        }
    }
    
    public static void PreOrderUltimos(Nodo Raiz){
        if(Raiz.getLeft()!=null){
            PreOrderPrimeros(Raiz.getLeft());
        }
        if(Raiz.getRight()!=null){
            PreOrderPrimeros(Raiz.getRight());
        }
        SetUltimos(Raiz);        
    }
    
    public static void SetSiguientes(Nodo Raiz){
         switch(Raiz.getValue().GetTipo()){
            case "Cerradura de Kleene":   
                for(Nodo temporal:Raiz.getUltimos()){
                    for(Nodo auxiliar:Raiz.getPrimeros()){
                        temporal.getSiguientes().add(auxiliar);
                    }
                }
                break;
            case "Simbolo Suma":                
                for(Nodo temporal:Raiz.getUltimos()){
                    for(Nodo auxiliar:Raiz.getPrimeros()){
                        temporal.getSiguientes().add(auxiliar);
                    }
                }
                break;
            case "Concatenacion":
                Nodo izq = Raiz.getLeft();
                Nodo der = Raiz.getRight();
                for(Nodo Temporal :izq.getUltimos()){
                    for(Nodo Auxiliar :der.getPrimeros()){
                        Temporal.getSiguientes().add(Auxiliar);
                    }
                }
                break;           
        }
    }
    
    public static void PreOrderSiguientes(Nodo Raiz){
        if(Raiz.getLeft()!=null){
            PreOrderPrimeros(Raiz.getLeft());
        }
        if(Raiz.getRight()!=null){
            PreOrderPrimeros(Raiz.getRight());
        }
        SetSiguientes(Raiz); 
    }
    
    public static void GraphConjuntos(LinkedList<Conjunto> lista) throws IOException{
        String salida = "digraph { tbl [ shape=plaintext label=<"+
        "<table border='0' cellborder='1' cellspacing='0'>\n  "+"<tr><td>ID</td><td>SIMBOLOS</td></tr>\n";
        for(int i =0; i<lista.size();i++){
            salida+="<tr><td>"+lista.get(i).getID()+"<td><td>"+lista.get(i).getConjunto()+"<td><tr>";
        }
        salida +="<tr><td></td><td></td></tr></table>>];}";
        writeDOC("Conjuntos", salida);
        try {            
            ProcessBuilder pbuilder;
            pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", "D://Conjuntos.png","D://Conjuntos.dot");
            pbuilder.redirectErrorStream(true);
            pbuilder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    public static void GraphSiguientes(LinkedList<Nodo> lista, String nombre) throws IOException{
        String salida = "digraph { tbl [ shape=plaintext label=<"+
        "<table border='0' cellborder='1' cellspacing='0'>\n  "+"<tr><td colspan=2>Hoja</td><td>Siguientes</td></tr>\n";
        for(int i =0; i<lista.size();i++){
            salida+="<tr><td>"+lista.get(i).getValue().GetVal()+"<td><td>"+lista.get(i).getID()+"</td><td>";
            for(int e=0;e<lista.get(i).getSiguientes().size();e++){
                if(e==lista.get(i).getSiguientes().size()-1){                    
                    salida+=lista.get(i).getSiguientes().get(e).getID();
                }else{                    
                    salida+=lista.get(i).getSiguientes().get(e).getID()+", ";
                }
            }
            salida+="<td><tr>";
        }
        salida +="<tr><td></td><td></td></tr></table>>];}";
        writeDOC(nombre+"_Siguientes", salida);
        try {            
            ProcessBuilder pbuilder;
            pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", "D://"+nombre+"_Siguentes.png","D://"+nombre+"_Siguentes.dot");
            pbuilder.redirectErrorStream(true);
            pbuilder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void GetHojas(Nodo Raiz, LinkedList<Nodo> lista){
        if(Raiz.getLeft()!=null){
            PreOrderPrimeros(Raiz.getLeft());
        }
        if(Raiz.getRight()!=null){
            PreOrderPrimeros(Raiz.getRight());
        }
        if(Raiz.getLeft()==null && Raiz.getRight()==null){
            lista.add(Raiz);
        }        
    }
    
}



