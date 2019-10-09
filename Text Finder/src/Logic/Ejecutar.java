package Logic;

import EstructurasDatos.ArbolBinarioBusqueda;
import palabras.Palabra;

public class Ejecutar {
    private ArbolBinarioBusqueda<Integer> arbolPrincpal;
    private Palabra editorPalabras;

    public Ejecutar(Palabra palabra){
        this.arbolPrincpal = new ArbolBinarioBusqueda<>();
        this.editorPalabras = palabra;
    }

    public void buscarPalabra(String palabra){
         Palabra palabraAux = arbolPrincpal.buscar(palabra);
    }
}
