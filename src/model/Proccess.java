package model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *Clase de representación de un proceso para una gramática A->T|B.
 *@author Equipo de trabajo
 */

public class Proccess {
    private char notTerminal; // no terminal , lado izquierdo de un proceso
    private String [] results;// resultados de un procesp , lado derecho de un proceso

    /**
     *Crea una Proceso a partir de un carácter no terminal y una lista resultados
     * @param notTerminal carácter asignado al atributo de no terminal.
     * @param results Listado de los resultados que produce el no terminal.
     */
    public  Proccess(char notTerminal, String [] results){
        this.notTerminal = notTerminal;
        this.results = results;
    }

    @Override
    /**
     * Obtener representación en texto de un proceso
     * @return String de representación en texto de un proceso
     */
    public String toString() {
        StringBuilder sb= new StringBuilder();
        for (int i = 0; i < results.length; i++) {
            sb.append(results[i]);
            if (i!=results.length-1) {
                sb.append("|");
            }
        }
        return notTerminal + "->" + sb.toString();
    }

    /**
     * Obtener los resultados de un proceso
     * @return Listado de resultados de un proceso
     */
    public String[] getResults(){
        return  this.results;
    }

<<<<<<< HEAD
    //---------------------------------------Metodos de Fabián---------------------------------//
    //
=======
    /**
     * Obtener el símbolo no terminal de un proceso
     * @return char símbolo no terminal de un proceso
     */
>>>>>>> 5abadb5afd3fe483658d1efac4675f525af9cb22
    public char getNotTerminal(){
        return this.notTerminal;
    }

}
