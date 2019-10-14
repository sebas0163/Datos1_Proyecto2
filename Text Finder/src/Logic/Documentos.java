package Logic;

import EstructurasDatos.ArbolBinarioBusqueda;

public class Documentos {
    private String ruta;
    private String nombre;
    private String fecha;
    private long tamano;
    private ArbolBinarioBusqueda<Integer> arbolPalabras;
    private String rutaTxt;

    public Documentos(String ruta1,String ruta2,ArbolBinarioBusqueda arbol,String nombre,long tamano,String fecha){
        this.fecha = fecha;
        this.nombre = nombre;
        this.tamano = tamano;
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
