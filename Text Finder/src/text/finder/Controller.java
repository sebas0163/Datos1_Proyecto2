/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.finder;

import java.net.URL;
import java.util.ResourceBundle;

import Logic.Ejecutar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Erick
 */
public class Controller implements Initializable {

    @FXML
    private TextField txtIn;
    @FXML
    private Button btnBuscar;
    private Ejecutar ejecutar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.ejecutar = new Ejecutar();
    }

    /**
     * Metodo encargado de tomar la palabra o frase que se encuentra en el textField y la envia para ser procesada.
     */
    public void buscar (){
        String palabra = txtIn.getText();
        ejecutar.buscarPalabra(palabra);

    }

    /**
     * Ordena los resultados de las apariciones por nombre usando QuickSort
     */
    public void ordenarNombre(){
        System.out.println("Se ordenó usando quickSort");
    }
    /**
     * Ordena los resultados de las apariciones por tamaño usando RadixSort
     */
    public void ordenarTamaño(){
        System.out.println("Se ordenó usando RadixSort");
    }
    /**
     * Ordena los resultados de las apariciones por Fecha usando BubbleSort
     */
    public void ordenarFecha(){
        System.out.println("Se ordenó usando BubbleSort");
    }
}
