package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Proccess {
    private char notTerminal;
    private String [] results;

    public  Proccess(char notTerminal, String [] results){
        this.notTerminal = notTerminal;
        this.results = results;
    }

    @Override
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

    public String[] getResults(){
        return  this.results;
    }

    //---------------------------------------Metodos de Fabián---------------------------------//
    //
    public char getNotTerminal(){
        return this.notTerminal;
    }

}
