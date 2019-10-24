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

    public boolean esVacio(){
        return(raiz==null);
    }

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
    private NodoABB menor(NodoABB<T> nodo){
        NodoABB aux=nodo;
        if(nodo.getNodoI()==null){
            return nodo.getPadre();
        }          
        return menor(nodo.getNodoI());
    }
    private NodoABB mayor(NodoABB<T> nodo){
        NodoABB aux=nodo;
        if(nodo.getNodoD()==null){
            return nodo.getPadre();
        }          
        return menor(nodo.getNodoD());
    }


    /**
     *
     * @param dato
     * @return
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
