package Logic;

import EstructurasDatos.ArbolBinarioBusqueda;
import palabras.Palabra;

/**
 * Clase encargada de manejar la logica del programa.
 * @author Sebastian Moya
 */
public class Ejecutar {
    private ManejoArchivos manejoArchivos;
    private Biblioteca biblioteca;

    /**
     * Metodo constructor de la clase.
     */
    public Ejecutar(){
        this.manejoArchivos = new ManejoArchivos();
        this.biblioteca = new Biblioteca();
    }

    /**
     * Metodo que busca la palabra en el arbol y envia el nodo que la posee con todos sus datos al metodo que los trabaja.
     * @param palabra
     */
    public void buscarPalabra(String palabra){

    }
    public void addDocumento(String url,String nombre){
        Documentos doc = manejoArchivos.indizarDoc(url, nombre);
        biblioteca.agregarDocumento(doc);
    }
}
