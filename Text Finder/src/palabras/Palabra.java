/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palabras;

import text.finder.*;

/**
 *
 * @author Erick
 */
public class Palabra {
    private String palabra;
    private int valor;
    private OperPalabras oprPlbr;
    public Palabra(String palabra){
        this.palabra=palabra;
        this.valor=oprPlbr.strToInt(palabra);
    }

    public String getPalabra() {
        return palabra;
    }

    public int getValor() {
        return valor;
    }
    
    
}
