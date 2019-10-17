package Logic;

import EstructurasDatos.ArbolBinarioBusqueda;
import EstructurasDatos.DoubleEndedLinkedList;
import EstructurasDatos.Nodo;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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
     * @param buscado palabra que se desea buscar en el arbol.
     */
    public void buscarPalabra(String buscado,VBox resultados){
        DoubleEndedLinkedList listaDocs = biblioteca.getListaDocumentos();
        Nodo temp = listaDocs.getHead();
        while(temp != null) {
            Documentos documento = (Documentos) temp.getDato();
            ArbolBinarioBusqueda arbol = documento.getArbolPalabras();
            if (arbol.buscar(buscado).equals(null)){
                System.out.println("La palabra no está en el documento");
            }else {
                Palabra palabra = arbol.buscar(buscado);
                mostrarApariciones(palabra, resultados,documento);
                temp = temp.getNext();
            }
        }
    }

    /**
     *
     * @param url
     * @param nombre
     */
    public void addDocx(String url,String nombre){
        Documentos doc = manejoArchivos.indizarDocx(url, nombre);
        biblioteca.agregarDocumento(doc);
    }

    /**
     *
     * @param url
     * @param nombre
     */
    public void addTxt(String url,String nombre){
        Documentos doc = manejoArchivos.indizarTxt(url,nombre);
        biblioteca.agregarDocumento(doc);
    }

    /**
     *
     * @param url
     * @param nombre
     */
    public void addPdf(String url, String nombre){
        Documentos doc = manejoArchivos.indizarPdf(url,nombre);
        biblioteca.agregarDocumento(doc);
    }

    /**
     *
     * @param item
     * @param url
     */
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
    private void mostrarApariciones(Palabra palabra, VBox resultados, Documentos documento){
        String [] texto = {palabra.getPalabra()+"\n",documento.getNombre() +"     ",documento.getFecha()+"      ",String.valueOf(documento.getTamano())};
        int apariciones = palabra.getApariciones();
        int cont = 1;
        while(cont <= apariciones){
            TextFlow flow = new TextFlow();
            flow.setPrefWidth(1074);
            for(int i =0; i< texto.length; i++ ){
                Text text = new Text(texto[i]);
                text.setFont(new Font("Arial",18));
                flow.getChildren().add(text);
            }
            resultados.getChildren().add(flow);
            cont += 1;
        }
    }
}
