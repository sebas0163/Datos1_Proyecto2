
package listas;

/**
 * Clase para crear una lista enlazada 
 * @author Erick
 * @param <T>
 */
public class DoubleEndedLinkedList<T>{
    private Nodo<T> head;
    private Nodo<T> tile;
    private int lenn;
   
    /**
     * Constructor de lista 
     *  
     */
    public DoubleEndedLinkedList(){
   
        head=null;
        tile=null;
        lenn=0;        
    }
    /**
     * Método para obtener el largo de la lista
     * @return 
     */
    public int len(){
        return this.lenn;
    }
    /**
     * Metodo para determinar si todos los datos son iguales
     * @return boolean
     */
    public boolean same(){
        Nodo<T> aux=this.head.getNext();
        while(aux!=null){
            if(aux.getDato()!=this.head.getDato()){
                return false;
            }
        aux=aux.getNext();
        }
    return true;
    }
    /**
     * Metodo para hacer un print de los elementos dwe la lista 
     */
    public void print(){
        Nodo<T> aux= head;
        while(aux !=null){
            System.out.println(aux.getDato());
            aux=aux.getNext();
            
        }
    
    }
    /**
     * Metodo para obtener un nodo de acuerdo a la posicion que se necesite 
     * @param pos int para buscar la posicion de un nodo
     * @return el Nodo en la posicion que se solicito 
     */
    public Nodo<T> getNodo(int pos){
        Nodo<T> aux=this.head;
        int cont=0;
        while(aux!=null){
            if(cont==pos){
                return aux;
            }
            aux=aux.getNext();
            cont++;
        }
    return null;
    }   
    /**
     * Metodo para conseguir el valor de una posición de la lista
     * @param i
     * @return 
     */
    public T getInfo(int i){
        Nodo<T> aux=this.head;
        int cont=0;
        while(aux!=null){
            if(cont==i){
                return aux.getDato();
            }
            aux=aux.getNext();
            cont++;
        }
    return null;
    }
    /**
     * Metodo para agregar un espacio vacio al inicio de la lista
     */
    public void addFirst(){
        if (this.head==null){
            this.head= new Nodo();
            this.tile= this.head;
        }
        else{
            Nodo<T> temp= this.head;
            this.head= new Nodo();
            this.head.setNext(temp);
        }
        lenn++;
    }
    /**
     * Metodo para agregar un dato al inicio de la lista
     * @param dato 
     */
    public void addFirst(T dato){
        if (this.head==null){
            this.head= new Nodo(dato);
            this.tile= this.head;
            
        }
        else{
            Nodo<T> temp= this.head;
            this.head= new Nodo();
            this.head.setNext(temp);
        }
        lenn++;
    }
    /**
     * Metodo para agregar un dato al final de la lista
     * @param dato 
     */
    public void add(T dato){
        Nodo<T> aux= new Nodo<>(dato);
        if (this.head==null){
            this.head=aux;
            this.tile=aux;
            this.lenn ++;
            return;
        }           
        
        this.tile.setNext(aux);
        this.tile=aux;
        this.lenn ++;
        }
    /**
     * Metodo para agregar un dato a una posicion dada de la lista
     * @param i
     * @param dato 
     */
    public void add(int i,T dato){
        
        if (i==0){
           Nodo<T> aux=new Nodo(dato);
           aux.setNext(head);
           this.head=aux;
           lenn++;
           return;
        }
        if(this.head==null){
            this.head= new Nodo<>(dato);
            return;
        }
        if (i==this.lenn-1){
            add(dato);
            return ;
        }
        if (i==lenn){
            return ;
        }
        int pos = 1;
        Nodo<T> aux=this.head;
        while(pos<i){
            aux=aux.getNext();
            pos++;
        }
        Nodo<T> temp=aux.getNext();
        aux.setNext(new Nodo(dato));
        aux.getNext().setNext(temp);
        lenn++;
    
    }
    /**
     * Metodo para buscar si un dato esta en la lista
     * @param dato
     * @return 
     */
    public boolean in(T dato){
        Nodo<T> aux=head;
        while (aux != null){
            if (aux.getDato().equals(dato)){
                //System.out.println(aux.getDato());
                return true;
            }
            aux=aux.getNext();  
        }
        return false;
    }
    /**
     * Metodo para eliminar el primer elemento 
     */
    private void removeFirst(){
        this.head=this.head.getNext();
    }
    /**
     * Metodo para eliminar un elemento en una posicion dada
     * @param pos 
     */
    public void remove(int pos){
        if (pos==0){
            removeFirst();
        }
        if (pos>lenn){
            System.out.println("indice inexistente");
            return;
        }
        int cont=1;
        Nodo<T> aux=this.head;
        while (cont<pos){
            cont++;
            aux= aux.getNext();
        }
        if (aux.getNext().getNext()==this.tile){
            aux.setNext(null);
            tile=aux;
            return;
        }
        aux.setNext(aux.getNext().getNext());
    }
    /**
     * Metodo para obterner el nodo en la ultima posicion     * 
     * @return Nodo en el tile de la lista
     */
    public Nodo<T> getLast(){
        return this.tile;
    }
}
