package Logic;

import EstructurasDatos.ArbolBinarioBusqueda;
import palabras.Palabra;

/**
 * Clase encargada de manejar la logica del programa.
 * @author Sebastian Moya
 */
public class Ejecutar {
    private ArbolBinarioBusqueda<Integer> arbolPrincpal;

    /**
     * Metodo constructor de la clase.
     */
    public Ejecutar(){
        this.arbolPrincpal = new ArbolBinarioBusqueda<>();
    }

    /**
     * Metodo que busca la palabra en el arbol y envia el nodo que la posee con todos sus datos al metodo que los trabaja.
     * @param palabra
     */
    public void buscarPalabra(String palabra){
         Palabra palabraAux = arbolPrincpal.buscar(palabra);
         System.out.println(arbolPrincpal.buscar(palabra));
    }
}
