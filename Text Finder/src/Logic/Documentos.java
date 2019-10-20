package Logic;

import EstructurasDatos.ArbolBinarioBusqueda;
import EstructurasDatos.DoubleEndedLinkedList;
import Logic.ManejoArchivos;
import javafx.scene.control.TreeItem;


public class Documentos {
    private String ruta;
    private String nombre;
    private String fecha;
    private long tamano;
    private ArbolBinarioBusqueda<Integer> arbolPalabras;
    private String rutaTxt;
    private TreeItem item;
    

    public Documentos(String ruta1,String ruta2,ArbolBinarioBusqueda arbol,String nombre,long tamano,String fecha){
        this.fecha = fecha;
        this.nombre = nombre;
        this.tamano = tamano;
        this.ruta = ruta1;
        this.rutaTxt = ruta2;
        this.arbolPalabras = arbol;
        ManejoArchivos lec=new ManejoArchivos();        
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
    
    public DoubleEndedLinkedList buscarFrase(String frase){
        ManejoArchivos lec=new ManejoArchivos();
        DoubleEndedLinkedList listaTXT=lec.read(rutaTxt);;
        DoubleEndedLinkedList list= new DoubleEndedLinkedList();
        for (int i=0;i<listaTXT.len();i++){
            if (listaTXT.getNodo(i).getDato().toString().toUpperCase().contains(frase.toUpperCase())){
                list.add(listaTXT.getNodo(i-1).getDato().toString()+"\n"+
                         listaTXT.getNodo(i).getDato().toString()+"\n"+
                         listaTXT.getNodo(i+1).getDato().toString());  
            }
        }
        return list;
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
}
