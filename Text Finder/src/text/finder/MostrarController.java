/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.finder;

import java.io.BufferedReader;

import EstructurasDatos.DoubleEndedLinkedList;
import Logic.Ejecutar;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

import Logic.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Clase encargada de controlar la interfaz que muestra los resultados.
 * @author Sebastian Moya
 */
public class MostrarController implements Initializable {

    private String path;
    private Ejecutar ejecutar;
    @FXML
    public TextFlow texto;
    @FXML
    public Label titulo;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.ejecutar = Singleton.getInstancia(null,null,null);
        this.path = ejecutar.getPath();
        titulo.setText(ejecutar.buscarNombre(path));
        setText();
    }

    /**
     * Metodo que coloca el texto del archivo en en textflow para ser mostrado en pantalla.
     */
    private void setText(){
        try{
            BufferedReader bf = new BufferedReader(new FileReader(path));
            String lineaTexto;
            while((lineaTexto = bf.readLine()) != null){
                lineaTexto += "\n";
                DoubleEndedLinkedList listaText = modificarTexto(ejecutar.getBuscado(),lineaTexto);
                for(int i =0; i<listaText.len();i++){
                    if(i %2 !=0){
                        Text linea = new Text((String) listaText.getNodo(i).getDato());
                        linea.setFont(new Font("Arial",16));
                        linea.setFill(Color.RED);
                        texto.getChildren().add(linea);
                    }else{
                        Text linea = new Text((String) listaText.getNodo(i).getDato());
                        linea.setFont(new Font("Arial",16));
                        texto.getChildren().add(linea);
                    }
                }
            }
            bf.close();

        }catch (Exception e){

        }
    }
    private DoubleEndedLinkedList modificarTexto(String palabra, String lineaTexto){
        DoubleEndedLinkedList<String> listatexto = new DoubleEndedLinkedList<>();
        if (!(lineaTexto.contains(palabra))){
            listatexto.add(lineaTexto);
            return listatexto;
        }
        while(lineaTexto.contains(palabra)){
            if (lineaTexto.indexOf(palabra) == 0){
                listatexto.add(palabra);
                lineaTexto = lineaTexto.substring(palabra.length());
            }else {
                listatexto.add(lineaTexto.substring(0, lineaTexto.indexOf(palabra)));
                lineaTexto = lineaTexto.substring((lineaTexto.indexOf(palabra) + palabra.length()));
                listatexto.add(palabra);
                //System.out.println(lineaTexto.substring((lineaTexto.indexOf(palabra)+palabra.length()-1)));
            }
        }
        listatexto.add(lineaTexto);
        return listatexto;
    }
}
