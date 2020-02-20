/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.security.Principal;
import UI.Form;
import beans.AnalizadorLexico;

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
        AnalizadorLexico anal = new AnalizadorLexico();
        anal.escanear(ejemplo);
    }
    
}
