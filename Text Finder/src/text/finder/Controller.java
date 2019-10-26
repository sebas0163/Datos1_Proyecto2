/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.finder;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import Logic.Ejecutar;
import Logic.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 *
 * @author Erick
 */
public class Controller implements Initializable {

    @FXML
    private TextField txtIn;
    @FXML
    private TreeView lib;
    @FXML
    private MenuButton btnModificar;
    @FXML
    private MenuButton btnEliminar;
    @FXML
    private VBox resultados;
    private Ejecutar ejecutar;
    private String path;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TreeItem<String> root = new TreeItem<>("Libreria");
        lib.setRoot(root);
        this.path = "C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto2\\biblioteca";
        this.ejecutar = Singleton.getInstancia(lib.getRoot(),btnModificar,btnEliminar);
        lib.setShowRoot(true);
    }

    /**
     * Metodo encargado de tomar la palabra o frase que se encuentra en el textField y la envia para ser procesada.
     */
    public void buscar (){
        resultados.getChildren().clear();
        if(txtIn.getText().contains(" ")){
            String frase = txtIn.getText();
            //ejecutar.buscarFrase(frase,resultados);
        }else {
            String palabra = txtIn.getText();
            ejecutar.buscarPalabra(palabra, resultados,true);
        }
    }
    public void buscarporPalabras(){
        resultados.getChildren().clear();
        StringTokenizer tokenizer = new StringTokenizer(txtIn.getText(),",. );:(");
        while(tokenizer.hasMoreElements()){
            ejecutar.buscarPalabra(tokenizer.nextToken(),resultados,false);
        }
        ejecutar.buscarFrase(txtIn.getText(),resultados);
        
        
    }
    /**
     * Metodo encargado de indizar los documentos .docx
     */
    public void agregarDocx(){
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("archivos docx","*docx"));
        File archivoSeleccionado = chooser.showOpenDialog(null);
        String archivo = archivoSeleccionado.getAbsolutePath();
        int largo = archivoSeleccionado.getName().length();
        TreeItem<String> item = new TreeItem<>(archivoSeleccionado.getName());
        MenuItem opcion = new MenuItem(archivoSeleccionado.getName());
        MenuItem opcion2 = new MenuItem(archivoSeleccionado.getName());
        ejecutar.addDocx(archivo,archivoSeleccionado.getName().substring(0,largo-5),item,path,opcion,opcion2,btnModificar,btnEliminar);// arreglar nombre
        lib.getRoot().getChildren().add(item);
    }

    /**
     * Método encargado de indizar los documentos .pdf
     */
    public void agragarPdf(){
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("archivos pdf","*.pdf"));
        File archivoSeleccionado = chooser.showOpenDialog(null);
        String archivo = archivoSeleccionado.getAbsolutePath();
        int largo = archivoSeleccionado.getName().length();
        TreeItem<String> item = new TreeItem<>(archivoSeleccionado.getName());
        MenuItem opcion = new MenuItem(archivoSeleccionado.getName());
        MenuItem opcion2 = new MenuItem(archivoSeleccionado.getName());
        ejecutar.addPdf(archivo,archivoSeleccionado.getName().substring(0,largo-4),item,path,opcion,opcion2,btnModificar,btnEliminar);// arreglar nombre
        lib.getRoot().getChildren().add(item);
    }
    /**
     * Método encargado de indizar los documentos .txt
     */
    public void agregarTxt(){
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("archivos txt","*.txt"));
        File archivoSeleccionado = chooser.showOpenDialog(null);
        String archivo = archivoSeleccionado.getAbsolutePath();
        TreeItem<String> item = new TreeItem<>(archivoSeleccionado.getName());
        MenuItem opcion = new MenuItem(archivoSeleccionado.getName());
        MenuItem opcion2 = new MenuItem(archivoSeleccionado.getName());
        ejecutar.addTxt(archivo,archivoSeleccionado.getName(),item,path,opcion,opcion2,btnModificar,btnEliminar);
        lib.getRoot().getChildren().add(item);
    }
    /**
     * Método encargado de agregar la carpeta e indizar cada documento de esta.
     */
    public void agregarCarpeta(){
        DirectoryChooser chooser = new DirectoryChooser();
        File archivoseleccionado = chooser.showDialog(null);
        String archivo = archivoseleccionado.getAbsolutePath();
        TreeItem<String> item = new TreeItem<>(archivoseleccionado.getName());
        MenuItem opcion = new MenuItem(archivoseleccionado.getName());
        ejecutar.addCarpeta(item,archivo,path,opcion,btnModificar,btnEliminar);
        lib.getRoot().getChildren().add(item);
    }
    /**
     * Ordena los resultados de las apariciones por nombre usando QuickSort
     */
    public void ordenarNombre(){
        System.out.println("Se ordenó usando quickSort");
        ejecutar.quickSort();
        buscar();
    }
    /**
     * Ordena los resultados de las apariciones por tamaño usando RadixSort
     */
    public void ordenarTamaño(){
        System.out.println("Se ordenó usando RadixSort");
        ejecutar.radixSort();
        buscar();
    }
    /**
     * Ordena los resultados de las apariciones por Fecha usando BubbleSort
     */
    public void ordenarFecha(){
        System.out.println("Se ordenó usando BubbleSort");
        ejecutar.bubble();
        buscar();
    }
}
