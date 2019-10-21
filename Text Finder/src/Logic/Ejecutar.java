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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import palabras.Palabra;

import java.io.File;

/**
 * Clase encargada de manejar la logica del programa.
 * @author Sebastian Moya
 */
public class Ejecutar {
    private ManejoArchivos manejoArchivos;
    private Biblioteca biblioteca;
    private String path;
    private String buscado;
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
        this.buscado = buscado;
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
     * Metodo que recibe el documento .docx cargado y lo envia a su indizacion.
     * @param url ruta del documento.
     * @param nombre nombre del archivo.
     */
    public void addDocx(String url,String nombre, TreeItem item,String rutaBib){
        Documentos doc = manejoArchivos.indizarDocx(url, nombre,rutaBib);
        doc.setItem(item);
        biblioteca.agregarDocumento(doc);
    }

    /**
     * Metodo que recibe el documento .txt cargado y lo envia a su indizacion.
     * @param url ruta del docuemento
     * @param nombre Nombre del documento
     */
    public void addTxt(String url,String nombre, TreeItem item,String rutaBib){
        Documentos doc = manejoArchivos.indizarTxt(url,nombre,rutaBib);
        doc.setItem(item);
        biblioteca.agregarDocumento(doc);
    }

    /**
     * Metodo que recibe el documento .pdf cargado y lo envia a su indizacion.
     * @param url ruta del documento.
     * @param nombre Nombre del documento
     */
    public void addPdf(String url, String nombre, TreeItem item,String rutaBib){
        Documentos doc = manejoArchivos.indizarPdf(url,nombre,rutaBib);
        doc.setItem(item);
        biblioteca.agregarDocumento(doc);
    }

    /**
     * Mentodo encargado de enviar una carpeta a su indización y sus archivos
     * @param item Nodo del arbol en el que e va a mostrar la carpeta.
     * @param url ruta de la carpeta
     */
    public void addCarpeta(TreeItem item, String url,String rutaBib){
        File file = new File(url);
        File file1 = new File(rutaBib,file.getName());
        file1.mkdir();
        for (File fichero: file.listFiles()){
            int largo = fichero.getName().length();
            String formato = fichero.getName().substring(largo-4,largo);
            String formato2 = fichero.getName().substring(largo-5,largo);
            if (formato.equals(".txt")){
                TreeItem item1 = new TreeItem(fichero.getName());
                addTxt(fichero.getAbsolutePath(),fichero.getName(),item1,file1.getAbsolutePath());
                item.getChildren().add(item1);
            }
            else if(formato.equals(".pdf")){
                int length = fichero.getName().length();
                TreeItem item1 = new TreeItem(fichero.getName());
                addPdf(fichero.getAbsolutePath(),fichero.getName().substring(0,length-5),item1,file1.getAbsolutePath());
                item.getChildren().add(item1);
            }
            else if (formato2.equals(".docx")) {
                int length = fichero.getName().length();
                TreeItem item1 = new TreeItem(fichero.getName());
                addDocx(fichero.getAbsolutePath(), fichero.getName().substring(0, length - 5), item1, file1.getAbsolutePath());
                item.getChildren().add(item1);
            }else if (!(fichero.getName().contains("."))){
                TreeItem item1 = new TreeItem(fichero.getName());
                addCarpeta(item1,fichero.getAbsolutePath(),file1.getAbsolutePath());
                item.getChildren().add(item1);
            }
            else{

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
        String [] texto = {palabra.getPalabra()+"\n",documento.getNombreOrg() +"     ",documento.getFecha()+"      ",String.valueOf(documento.getTamano())};
        int apariciones = palabra.getApariciones();
        int cont = 1;
        while(cont <= apariciones){
            TextFlow flow = new TextFlow();
            flow.setOnMouseClicked(click);
            flow.setPrefWidth(1074);
            Text text1 = new Text(texto[0]);
            text1.setFill(Color.DARKGREEN);
            text1.setFont(new Font("Arial",18));
            flow.getChildren().add(text1);
            for(int i =1; i< texto.length; i++ ){
                Text text = new Text(texto[i]);
                text.setFont(new Font("Arial",18));
                flow.getChildren().add(text);
            }
            Resultado resultado = new Resultado(documento.getRutaTxt(),texto,flow,documento.getNombreOrg());
            listaResultado.add(resultado);
            resultados.getChildren().add(flow);
            cont += 1;
        }
    }

    /**
     * Metodo encargado de elimnar algun documento cargado
     * @param item nodo del arbol donde se encuentra el documento
     */
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

    /**
     * Metodo que permite conocer la ruta de un archivo cargado.
     * @param flow contenerdor de texto del archivo
     * @return ruta del archivo
     */
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
    public String buscarNombre(String ruta){
        Nodo temp = listaResultado.getHead();
        Resultado resultado = (Resultado) temp.getDato();
        while(temp.getDato() != null){
            resultado = (Resultado) temp.getDato();
            if(resultado.getRuta().equals(ruta)){
                break;
            }else{
                temp = temp.getNext();
            }
        }
        return resultado.getNombre();
    }

    /**
     * Metodo encargado de controlar los eventos del mouse.
     */
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
                    stage.setTitle("Apariciones");
                    stage.setResizable(false);
                    stage.show();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    };

    public String getPath() {
        return path;
    }

    public String getBuscado() {
        return buscado;
    }
}
