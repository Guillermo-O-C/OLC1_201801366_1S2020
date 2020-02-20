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
public class Errores {
        private int Fila;
        private int Columna;
        private String Caracter;
        private String Descripcion;

        public Errores(int fila, int columna, String car, String descripcion)
        {
            this.Fila = fila;
            this.Columna = columna;
            this.Caracter = car;
            this.Descripcion = descripcion;
        }

        public int GetFila()
        {
            return Fila;
        }
        public int GetColumna()
        {
            return Columna;
        }
        public String GetCaracter()
        {
            return Caracter;
        }
        public String GetDescripcion()
        {
            return Descripcion;
        }
}
