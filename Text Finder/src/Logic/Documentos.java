package Logic;

import EstructurasDatos.ArbolBinarioBusqueda;
import EstructurasDatos.DoubleEndedLinkedList;
import Logic.ManejoArchivos;


public class Documentos {
    private String ruta;
    private ArbolBinarioBusqueda<Integer> arbolPalabras;
    private String rutaTxt;
    

    public Documentos(String ruta1,String ruta2,ArbolBinarioBusqueda arbol){
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
}
