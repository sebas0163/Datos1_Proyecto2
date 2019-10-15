package Logic;

import EstructurasDatos.ArbolBinarioBusqueda;
import EstructurasDatos.DoubleEndedLinkedList;
import Logic.ManejoArchivos;


public class Documentos {
    private String ruta;
    private ArbolBinarioBusqueda<Integer> arbolPalabras;
    private String rutaTxt;
    private DoubleEndedLinkedList listaTXT;

    public Documentos(String ruta1,String ruta2,ArbolBinarioBusqueda arbol){
        this.ruta = ruta1;
        this.rutaTxt = ruta2;
        this.arbolPalabras = arbol;
        ManejoArchivos lec=new ManejoArchivos();
        this.listaTXT= lec.read(rutaTxt);
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
        DoubleEndedLinkedList list= new DoubleEndedLinkedList();
        for (int i=1;i<listaTXT.len();i++){
            if (listaTXT.getNodo(i).getDato().toString().toUpperCase().contains(frase.toUpperCase())){
                list.add(listaTXT.getNodo(i-1).getDato().toString());
                list.add(listaTXT.getNodo(i).getDato().toString());
                list.add(listaTXT.getNodo(i+1).getDato().toString());
                
            }
        }
        return list;
    }
}
