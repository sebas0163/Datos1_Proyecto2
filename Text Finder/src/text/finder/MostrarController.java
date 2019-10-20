/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.finder;

import java.io.BufferedReader;

import Logic.Ejecutar;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Erick
 */
public class MostrarController implements Initializable {

    private String path;
    @FXML
    public TextFlow texto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.path = Ejecutar.path;
        setText();
    }
    private void setText(){
        try{
            BufferedReader bf = new BufferedReader(new FileReader(path));
            String lineaTexto;
            while((lineaTexto = bf.readLine()) != null){
                lineaTexto += "\n";
                Text linea = new Text(lineaTexto);
                texto.getChildren().add(linea);
            }

        }catch (Exception e){

        }
    }
}
