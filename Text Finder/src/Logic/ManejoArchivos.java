package Logic;

import EstructurasDatos.ArbolBinarioBusqueda;
import EstructurasDatos.DoubleEndedLinkedList;
import EstructurasDatos.Nodo;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Clase encargada de realizar todas las operaciones relacinadas con la interaccion con los archivos.
 * @author Sebastian Moya
 */
public class ManejoArchivos {
    private DoubleEndedLinkedList<String> palabras;

    public ManejoArchivos(){
        palabras = new DoubleEndedLinkedList<>();
    }

    public Documentos indizarDoc(String url, String nombre){
        try {
            XWPFDocument doc = new XWPFDocument(new FileInputStream(url));
            XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
            System.out.println(extractor.getText());
            File nuevoDoc = new File(nombre);
            nuevoDoc.createNewFile();
            while(extractor.getText()!= null){
                escribirTxt(nuevoDoc,extractor.getText());
            }
            leerArchivo(nuevoDoc.getPath());
            Documentos documento = new Documentos(url,nuevoDoc.getPath(),arbolDoc());
            return documento;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    private void escribirTxt(File file, String texto){
        try {
            PrintWriter escribir = new PrintWriter(file);
            escribir.println(texto);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    /**
     * Método encargado de leer el archivo linea por linea y mandar cada linea a un separador de palabras.
     * @param direccion dirección de en que lugar se encuentra el archivo que se desea leer.
     */
    public void leerArchivo(String direccion){
        try{
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String lineaTexto;
            while((lineaTexto = bf.readLine()) != null){
                separarPalabras(lineaTexto);
            }

        }catch (Exception e){

        }
    }

    /**
     * Método encargado de separar todas las palabras de una linea de texto.
     * @param lineaTexto linea de texto que se quiere separar
     */
    private void separarPalabras(String lineaTexto){
        StringTokenizer token = new StringTokenizer(lineaTexto,",.  ");
        while (token.hasMoreElements()){
            palabras.add(token.nextToken());
        }
    }
    private ArbolBinarioBusqueda arbolDoc(){
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<>();
        Nodo temp = palabras.getHead();
        while (temp != null){
            arbol.agregar((String) temp.getDato());
            temp = temp.getNext();
        }
        return arbol;
    }
}
