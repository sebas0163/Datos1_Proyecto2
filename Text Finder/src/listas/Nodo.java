package listas;

/**
 * @author Erick
 * @param <T>
 */
public class Nodo <T> {
    private T dato;    
    private Nodo<T> siguiente;
    
    /**
     * Constructor del Nodo vacio
     */
    public Nodo(){
        siguiente=null;        
    }
    /**
     * Método del constructor del Nodo con un dato
     * @param data
     */
    public Nodo(T data){
        siguiente=null;
        this.dato=data;
    }
    /**
     * Método para obtener el dato almacenado en el Nodo
     * @return 
     */
    public T getDato(){
        return this.dato; 
    }
    /**
     * Método para establecer el nodo siguiente 
     * @param Next 
     */    
    public void setNext(Nodo<T> Next){
            this.siguiente= Next;
    }
    /**
     * Metodo para obtener el nodo siguiente 
     * @return Nodo que le sigue al Nodo actual
     */
    public Nodo<T> getNext(){
        return this.siguiente;
    }
}
