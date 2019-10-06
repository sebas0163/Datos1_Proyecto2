/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

/**
 *
 * @author Erick
 */
public class NodoABB<T>{
    private T dato;
    private T nodoD;
    private T nodoR;
    
    public void NodoABB(){
        this.dato=null;
        this.nodoD=null;
        this.nodoR=null;        
    }
    public void NodoABB(T dato){
        this.dato=dato;
        this.nodoD=null;
        this.nodoR=null;        
    }
    
}
