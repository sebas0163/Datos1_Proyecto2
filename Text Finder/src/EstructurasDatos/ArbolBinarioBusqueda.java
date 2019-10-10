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
        }
        NodoABB<Palabra> aux=raiz;
        while (true){            
            if (palabra.getValor()== aux.getDato().getValor()){
                aux.getDato().addapariciones();
                return;
            }
            if (palabra.getValor()> aux.getDato().getValor()) {
                if(aux.getNodoD()==null){
                    aux.getNodoD().setDato(palabra);
                    aux.getNodoD().setPadre(aux);
                    return;
                }
                aux=aux.getNodoD();
                continue;
            }
            if (palabra.getValor() < aux.getDato().getValor()){
                if(aux.getNodoI()==null){
                   aux.getNodoI().setDato(palabra);
                   aux.getNodoI().setPadre(aux); 
                }
                aux=aux.getNodoI();
                continue;
            }
        }            
<<<<<<< HEAD
        }
    public void agregar(String dato,String url){
        Palabra palabra=new Palabra(dato,url);
        
        if (esVacio()) {            
            NodoABB nuevo = new NodoABB(palabra);
            raiz = nuevo;
        }
        NodoABB<Palabra> aux=raiz;
        while (true){            
            if (palabra.getValor()== aux.getDato().getValor()){
                aux.getDato().addapariciones();
                return;
            }
            if (palabra.getValor()> aux.getDato().getValor()) {
                if(aux.getNodoD()==null){
                    aux.getNodoD().setDato(palabra);
                    aux.getNodoD().setPadre(aux);
                    return;
                }
                aux=aux.getNodoD();
                continue;
            }
            if (palabra.getValor() < aux.getDato().getValor()){
                if(aux.getNodoI()==null){
                   aux.getNodoI().setDato(palabra);
                   aux.getNodoI().setPadre(aux); 
                }
                aux=aux.getNodoI();
                continue;
            }
        }            
        }
=======
    }

>>>>>>> master
    public Palabra buscar(String dato){
        Palabra palabra=new Palabra(dato);
        NodoABB<Palabra> aux=raiz;
        while(aux!=null){
            if(aux.getDato().getPalabra()==palabra.getPalabra()){
                return aux.getDato();
            }
            if(palabra.getValor()<aux.getDato().getValor()){
                aux=aux.getNodoI();
            }
            if(palabra.getValor()>aux.getDato().getValor()){
                aux=aux.getNodoD();
            }
        }
        return null;
    }
}
