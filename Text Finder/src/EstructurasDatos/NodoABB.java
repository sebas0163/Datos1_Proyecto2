package EstructurasDatos;
import palabras.Palabra;

/**
 *
 * @author Erick
 */
public class NodoABB<T>{
    private T dato;
    private NodoABB<T> nodoD;
    private NodoABB<T> nodoI;
    private NodoABB<T> padre;
   

    public  NodoABB(){
        this.dato=null;
        this.nodoD=null;
        this.nodoI=null;        
    }
    public  NodoABB(T dato){
        this.dato=dato;
        this.nodoD=null;
        this.nodoI=null; 
        
    }
    public  NodoABB(T dato,NodoABB r,NodoABB d){
        this.dato=dato;
        this.nodoD=d;
        this.nodoI=r; 
    }
    public  NodoABB(T dato,NodoABB<T> padre){
        this.dato=dato;
        this.nodoD=null;
        this.nodoI=null; 
        this.padre=padre;
    }
    public NodoABB<T> getPadre() {
        return this.padre;
    }
    public void setNodoD(NodoABB<T> nodoD) {
        this.nodoD = nodoD;
    }

    public void setNodoI(NodoABB<T> nodoI) {
        this.nodoI = nodoI;
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

    public NodoABB<T> getNodoI() {
        return nodoI;
    }
      
      
}
