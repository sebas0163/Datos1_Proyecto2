package Logic;

import EstructurasDatos.ArbolBinarioBusqueda;
import EstructurasDatos.DoubleEndedLinkedList;
import javafx.scene.control.TreeItem;
import palabras.Palabra;

import java.io.File;

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
        DoubleEndedLinkedList listaDocs = biblioteca.getListaDocumentos();
        // terminar
    }
    public void addDocx(String url,String nombre){
        Documentos doc = manejoArchivos.indizarDocx(url, nombre);
        biblioteca.agregarDocumento(doc);
    }
    public void addTxt(String url,String nombre){
        Documentos doc = manejoArchivos.indizarTxt(url,nombre);
        biblioteca.agregarDocumento(doc);
    }
    public void addPdf(String url, String nombre){
        Documentos doc = manejoArchivos.indizarPdf(url,nombre);
        biblioteca.agregarDocumento(doc);
    }
    public void addCarpeta(TreeItem item, String url){
        File file = new File(url);
        for (File fichero: file.listFiles()){
            int largo = fichero.getName().length();
            String formato = fichero.getName().substring(largo-4,largo);
            String formato2 = fichero.getName().substring(largo-5,largo);
            if (formato.equals(".txt")){
                addTxt(fichero.getAbsolutePath(),fichero.getName());
                TreeItem item1 = new TreeItem(fichero.getName());
                item.getChildren().add(item1);
            }
            else if(formato.equals(".pdf")){
                int length = fichero.getName().length();
                addPdf(fichero.getAbsolutePath(),fichero.getName().substring(0,length-5));
                TreeItem item1 = new TreeItem(fichero.getName());
                item.getChildren().add(item1);
            }
            else if (formato2.equals(".docx")){
                int length = fichero.getName().length();
                addDocx(fichero.getAbsolutePath(),fichero.getName().substring(0,length-5));
                TreeItem item1 = new TreeItem(fichero.getName());
                item.getChildren().add(item1);
            } else{
                System.out.println("no coincide el formato del archivo");
            }
        }
    }
}
