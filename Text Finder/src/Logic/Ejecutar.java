package Logic;

import EstructurasDatos.ArbolBinarioBusqueda;
import EstructurasDatos.DoubleEndedLinkedList;
import EstructurasDatos.Nodo;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import palabras.Palabra;
import text.finder.MostrarController;

import java.io.File;

/**
 * Clase encargada de manejar la logica del programa.
 * @author Sebastian Moya
 */
public class Ejecutar {
    private ManejoArchivos manejoArchivos;
    private Biblioteca biblioteca;
    public static String path;
    private DoubleEndedLinkedList<Resultado> listaResultado;

    /**
     * Metodo constructor de la clase.
     */
    public Ejecutar(){
        this.manejoArchivos = new ManejoArchivos();
        this.biblioteca = new Biblioteca();
        this.listaResultado = new DoubleEndedLinkedList<>();
        this.path = "";
    }

    /**
     * Metodo que busca la palabra en el arbol y envia el nodo que la posee con todos sus datos al metodo que los trabaja.
     * @param buscado palabra que se desea buscar en el arbol.
     */
    public void buscarPalabra(String buscado,VBox resultados){
        listaResultado = new DoubleEndedLinkedList<>();
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
    public void addDocx(String url,String nombre, TreeItem item){
        Documentos doc = manejoArchivos.indizarDocx(url, nombre);
        doc.setItem(item);
        biblioteca.agregarDocumento(doc);
    }

    /**
     *
     * @param url
     * @param nombre
     */
    public void addTxt(String url,String nombre, TreeItem item){
        Documentos doc = manejoArchivos.indizarTxt(url,nombre);
        doc.setItem(item);
        biblioteca.agregarDocumento(doc);
    }

    /**
     *
     * @param url
     * @param nombre
     */
    public void addPdf(String url, String nombre, TreeItem item){
        Documentos doc = manejoArchivos.indizarPdf(url,nombre);
        doc.setItem(item);
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
                TreeItem item1 = new TreeItem(fichero.getName());
                addTxt(fichero.getAbsolutePath(),fichero.getName(),item1);
                item.getChildren().add(item1);
            }
            else if(formato.equals(".pdf")){
                int length = fichero.getName().length();
                TreeItem item1 = new TreeItem(fichero.getName());
                addPdf(fichero.getAbsolutePath(),fichero.getName().substring(0,length-5),item1);
                item.getChildren().add(item1);
            }
            else if (formato2.equals(".docx")){
                int length = fichero.getName().length();
                TreeItem item1 = new TreeItem(fichero.getName());
                addDocx(fichero.getAbsolutePath(),fichero.getName().substring(0,length-5),item1);
                item.getChildren().add(item1);
            } else{
                System.out.println("no coincide el formato del archivo");
            }
        }
    }

    /**
     * Método encargado de mostrar las apariciones de la palabra en los distintos documentos.
     * @param palabra Instancia de la palabra.
     * @param resultados Pantalla donde se van a mostrar los resultados de la busqueda
     * @param documento Documento con el cual se esta trabajando.
     */
    private void mostrarApariciones(Palabra palabra, VBox resultados, Documentos documento){
        String [] texto = {palabra.getPalabra()+"\n",documento.getNombre() +"     ",documento.getFecha()+"      ",String.valueOf(documento.getTamano())};
        int apariciones = palabra.getApariciones();
        int cont = 1;
        while(cont <= apariciones){
            TextFlow flow = new TextFlow();
            flow.setOnMouseClicked(click);
            flow.setPrefWidth(1074);
            for(int i =0; i< texto.length; i++ ){
                Text text = new Text(texto[i]);
                text.setFont(new Font("Arial",18));
                flow.getChildren().add(text);
            }
            Resultado resultado = new Resultado(documento.getRutaTxt(),texto,flow);
            listaResultado.add(resultado);
            resultados.getChildren().add(flow);
            cont += 1;
        }
    }
    public void eliminarDoc(TreeItem item){
        DoubleEndedLinkedList listadocs = biblioteca.getListaDocumentos();
        Nodo temp = listadocs.getHead();
        while(temp != null){
            Documentos doc = (Documentos) temp.getDato();
            if (doc.getItem().equals(item)) {
                biblioteca.eliminarDocumento(doc);
                break;
            }
            temp = temp.getNext();
        }
    }
    private String buscarRuta(TextFlow flow){
        Nodo temp = listaResultado.getHead();
        Resultado resultado = (Resultado) temp.getDato();
        while(temp.getDato() != null){
            resultado = (Resultado) temp.getDato();
            if(resultado.getFlow().equals(flow)){
                break;
            }else{
                temp = temp.getNext();
            }
        }
        return resultado.getRuta();
    }
    EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getButton().equals(MouseButton.PRIMARY)){
                path= buscarRuta((TextFlow) event.getSource());
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("MostrarText.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    };
}
