package EstructurasDatos;
import palabras.Palabra;
/**
 *
 * @author Erick
 */
public class ArbolBinarioBusqueda<T> {
    private NodoABB<Palabra> raiz;
    
    public void ArbolBinarioBusqueda(){
        this.raiz=null;
    }
    /**
     * metodo que indica si el arbol esta vacio 
     * @return boolean 
     */
    public boolean esVacio(){
        return(raiz==null);
    }

    /**
     * metodo que recibe la pabra que se quiere agregar y la acomoda en el arbol
     * @param dato palabra que se desea agregar
     */
    public void agregar(String dato){
        Palabra palabra=new Palabra(dato);
        
        if (esVacio()) {            
            NodoABB nuevo = new NodoABB(palabra);
            raiz = nuevo;
            return;
        }
        NodoABB<Palabra> aux=raiz;
        while (true){            
            if (palabra.getValor()== aux.getDato().getValor()){
                aux.getDato().addApariciones();
                return;
            }
            if (palabra.getValor()> aux.getDato().getValor()) {
                if(aux.getNodoD()==null){
                    aux.setNodoD(new NodoABB(palabra));
                    aux.getNodoD().setPadre(aux);
                    return;
                }
                aux=aux.getNodoD();
                continue;
            }
            if (palabra.getValor() < aux.getDato().getValor()){
                if(aux.getNodoI()==null){
                   aux.setNodoI(new NodoABB(palabra));                          
                   aux.getNodoI().setPadre(aux);
                   return;
                }
                aux=aux.getNodoI();                
            }
        }
    }
    /**
     * Metodo que recorre el arbol recursivamente para obtener el dato más pequeño 
     * @param nodo nodo inicial
     * @return nodo menor 
     */
    private NodoABB menor(NodoABB<T> nodo){
        NodoABB aux=nodo;
        if(nodo.getNodoI()==null){
            return nodo.getPadre();
        }          
        return menor(nodo.getNodoI());
    }
    /**
     * Metodo que recorre el arbol recursivamente para obtener el dato más grande 
     * @param nodo nodo inicial
     * @return nodo mayor 
     */
    private NodoABB mayor(NodoABB<T> nodo){
        NodoABB aux=nodo;
        if(nodo.getNodoD()==null){
            return nodo.getPadre();
        }          
        return menor(nodo.getNodoD());
    }


    /**
     *Metodo que busca una palabra en el arbol
     * @param dato palabra qwue se desae buscar en el arbol
     * @return el nodo que contiene la palabra buscada o null si no la encuentra
     */
    public Palabra buscar(String dato){
        Palabra palabra=new Palabra(dato);
        NodoABB<Palabra> aux=raiz;
        while(aux!=null){
            if(aux.getDato().getValor()== palabra.getValor()){
                return aux.getDato();
            }
            else if(palabra.getValor()<aux.getDato().getValor()){
                aux=aux.getNodoI();
                continue;
            }
            else if(palabra.getValor()>aux.getDato().getValor()){
                aux=aux.getNodoD();
            }
        }
        return null;
    }
}
