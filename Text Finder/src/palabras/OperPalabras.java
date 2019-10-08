/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palabras;

/**
 *
 * @author Erick
 */
public class OperPalabras {
   // public OperPalabras(){
    //}
    public int strToInt(String a){
        a=a.toUpperCase(); 
        String palabra=""; 
        int len=a.length();
        //len--;
        for (int i=0;i<len;i++){
            palabra+=Integer.toString(a.codePointAt(i));            
        }
        return Integer.parseInt(palabra);
    }
}
