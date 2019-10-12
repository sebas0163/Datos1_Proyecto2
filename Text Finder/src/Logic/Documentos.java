package Logic;

import EstructurasDatos.ArbolBinarioBusqueda;

public class Documentos {
    private String ruta;
    private ArbolBinarioBusqueda<Integer> arbolPalabras;
    private String rutaTxt;

    public Documentos(String ruta1,String ruta2,ArbolBinarioBusqueda arbol){
        this.ruta = ruta1;
        this.rutaTxt = ruta2;
        this.arbolPalabras = arbol;
    }

    public String getRuta() {
        return ruta;
    }

    public ArbolBinarioBusqueda<Integer> getArbolPalabras() {
        return arbolPalabras;
    }

    public String getRutaTxt() {
        return rutaTxt;
    }
}
