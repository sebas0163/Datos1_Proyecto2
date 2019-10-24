package Logic;

import EstructurasDatos.ArbolBinarioBusqueda;
import EstructurasDatos.DoubleEndedLinkedList;
import Logic.ManejoArchivos;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;

/**
 * Clase encargada de administrar los datos relacionados a cada documento.
 * @author Sebastian Moya
 */
public class Documentos {
    private String ruta;
    private String nombre;
    private String fecha;
    private String nombreOrg;
    private long tamano;
    private ArbolBinarioBusqueda<Integer> arbolPalabras;
    private String rutaTxt;
    private TreeItem item;
    private MenuItem eliminar;
    private MenuItem agregar;


    /**
     * Metodo contructor de la clase.
     * @param ruta1 ruta de donde se tomó el documento.
     * @param ruta2 ruta donde se encuentra el documento ahora.
     * @param arbol arbol que contiene las palabras del documento.
     * @param nombre nombre del documento
     * @param tamano peso del documento
     * @param fecha fecha de cracion del documento.
     */
    public Documentos(String ruta1,String ruta2,ArbolBinarioBusqueda arbol,String nombre,long tamano,String fecha,String nombreOrg){
        this.fecha = fecha;
        this.nombreOrg = nombreOrg;
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


    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public long getTamano() {
        return tamano;
    }

    public TreeItem getItem() {
        return item;
    }

    public void setItem(TreeItem item) {
        this.item = item;
    }

    public void setArbolPalabras(ArbolBinarioBusqueda<Integer> arbolPalabras) {
        this.arbolPalabras = arbolPalabras;
    }

    public String getNombreOrg() {
        return nombreOrg;
    }

    public MenuItem getEliminar() {
        return eliminar;
    }

    public void setEliminar(MenuItem eliminar) {
        this.eliminar = eliminar;
    }

    public MenuItem getAgregar() {
        return agregar;
    }

    public void setAgregar(MenuItem agregar) {
        this.agregar = agregar;
    }
}
