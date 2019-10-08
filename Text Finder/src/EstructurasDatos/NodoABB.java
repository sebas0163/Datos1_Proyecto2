/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstructurasDatos;

/**
 *
 * @author Erick
 */
public class NodoABB<T>{
    private T dato;
    private NodoABB<T> nodoD;
    private NodoABB<T> nodoR;
    private NodoABB<T> padre;

    public NodoABB<T> getPadre() {
        return padre;
    } 
    
    public  NodoABB(){
        this.dato=null;
        this.nodoD=null;
        this.nodoR=null;        
    }
    public  NodoABB(T dato){
        this.dato=dato;
        this.nodoD=null;
        this.nodoR=null; 
    }
    public  NodoABB(T dato,NodoABB r,NodoABB d){
        this.dato=dato;
        this.nodoD=d;
        this.nodoR=r; 
    }
    public  NodoABB(T dato,NodoABB<T> padre){
        this.dato=dato;
        this.nodoD=null;
        this.nodoR=null; 
        this.padre=padre;
    }

    public void setNodoD(NodoABB<T> nodoD) {
        this.nodoD = nodoD;
    }

    public void setNodoR(NodoABB<T> nodoR) {
        this.nodoR = nodoR;
    }

    public void setPadre(NodoABB<T> padre) {
        this.padre = padre;
    }
    public void setDato(T dato) {
        this.dato = dato;
    }

    public T getDato() {
        return dato;
    }

    public NodoABB<T> getNodoD() {
        return nodoD;
    }

    public NodoABB<T> getNodoR() {
        return nodoR;
    }
    
        
}
