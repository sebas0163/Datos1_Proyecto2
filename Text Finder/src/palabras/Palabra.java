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
    private int valor;
    private OperPalabras oprPlbr;
    private int apariciones;
    private DoubleEndedLinkedList ubicaciones;
    
    public Palabra(String palabra){
        this.palabra=palabra;
        this.valor=oprPlbr.strToInt(palabra);
        this.apariciones=apariciones;
    }
    public Palabra(String palabra,String url){
        this.palabra=palabra;
        this.valor=oprPlbr.strToInt(palabra);
        this.apariciones=apariciones;
        ubicaciones.add(url);
    }

    public String getPalabra() {
        return palabra;
    }

    public int getValor() {
        return valor;
    }
    public void addapariciones(){
        this.apariciones++;
    } 
    
}
