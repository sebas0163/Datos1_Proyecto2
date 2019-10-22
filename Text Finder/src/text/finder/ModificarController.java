package text.finder;

import Logic.Ejecutar;
import Logic.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ModificarController implements Initializable {
    @FXML
    public TextArea area;
    @FXML
    private Ejecutar ejecutar;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        this.ejecutar = Singleton.getInstancia(null,null,null);
        this.area.setText(getText());
        System.out.println(ejecutar.getPath());
    }
    private String getText(){
        String textoFinal = "";
        try {
            String ruta = ejecutar.getPath();
            BufferedReader bf = new BufferedReader(new FileReader(ruta));
            String lineaText;
            while((lineaText = bf.readLine())!= null){
                textoFinal += lineaText + "\n";
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return textoFinal;
    }
    public void gurdar(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ejecutar.getPath()));
            bw.write("");
            bw.write(area.getText());
            bw.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
