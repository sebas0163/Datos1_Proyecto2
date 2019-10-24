package text.finder;

import EstructurasDatos.DoubleEndedLinkedList;
import EstructurasDatos.Nodo;
import Logic.Documentos;
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
    }
    private String getText(){
        String textoFinal = "";
        try {
            String ruta = ejecutar.getPath();
            BufferedReader bf = new BufferedReader(new FileReader(ruta));
            String lineaText;
            while((lineaText = bf.readLine())!= null){
                lineaText += "\n";
                textoFinal += lineaText;
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
        DoubleEndedLinkedList listadocs = ejecutar.getBiblioteca().getListaDocumentos();
        Nodo temp = listadocs.getHead();
        Documentos doc = (Documentos) temp.getDato();
        while(temp != null){
            doc = (Documentos) temp.getDato();
            if (doc.getRutaTxt().equals(ejecutar.getPath())){
                break;
            }else{
                temp= temp.getNext();
            }
        }
        ejecutar.recalcularArbol(doc);
    }
}
