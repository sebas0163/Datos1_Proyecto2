/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palabras;

import EstructurasDatos.DoubleEndedLinkedList;
import text.finder.*;

/**
 *
 * @author Erick
 */
public class Palabra {
    private String palabra;
    private double valor;
    private int apariciones;

    public Palabra(String palabra){
        this.palabra=palabra;
        this.valor= strToInt(palabra);
        this.apariciones=1;
        
    }    
    

    public double strToInt(String a){
        a=a.toUpperCase();
        String palabra="";
        int len=a.length();
        //len--;
        for (int i=0;i<len;i++){
            palabra+=Integer.toString(a.codePointAt(i));
        }
        return Double.parseDouble(palabra);
    }

    public int getApariciones() {
        return apariciones;
    }
    public String getPalabra() {
        return palabra;
    }

    public double getValor() {
        return valor;
    }
    public void addApariciones(){
        this.apariciones++;
    }
}
