package Logic;

import EstructurasDatos.ArbolBinarioBusqueda;
import EstructurasDatos.DoubleEndedLinkedList;
import EstructurasDatos.Nodo;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    /**
     * Metodo encargado de indizar los documentos docx.
     * @param url direccion del archivo.
     * @param nombre nombre del archivo
     * @return instancia del de la clase documento con los datos del archivo.
     */
    public Documentos indizarDocx(String url, String nombre){
        try {
            XWPFDocument doc = new XWPFDocument(new FileInputStream(url));
            XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
            File nuevoDoc = new File("C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto2\\biblioteca",nombre+".txt");
            nuevoDoc.createNewFile();
            String fecha = obtenerFecha(nuevoDoc);
            escribirTxt(nuevoDoc,extractor.getText());
            leerArchivo(nuevoDoc.getPath());
            Documentos documento = new Documentos(url,nuevoDoc.getPath(),arbolDoc(),nuevoDoc.getName(),nuevoDoc.length(),fecha);
            palabras.reset();
            return documento;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    /**
     * Metodo encargado de indizar los documentos de formato pdf.
     * @param url direccion del archivo.
     * @param nombre nombre del archivo
     * @return instancia del de la clase documento con los datos del archivo.
     */
    public Documentos indizarPdf(String url, String nombre){
        try{
            File file = new File(url);
            PDFParser parser = new PDFParser(new RandomAccessFile(file,"r"));
            parser.parse();
            COSDocument cosdoc = parser.getDocument();
            PDDocument doc = new PDDocument(cosdoc);
            PDFTextStripper stripper = new PDFTextStripper();
            System.out.println(stripper.getText(doc));

        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    /**
     * Metodo encagado de indizar los archivos de formato txt.
     * @param url direccion del archivo.
     * @param nombre nombre del archivo
     * @return instancia del de la clase documento con los datos del archivo.
     */
    public Documentos indizarTxt(String url, String nombre){
        try{
            File nuevoDoc = new File("C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto2\\biblioteca",nombre);
            nuevoDoc.createNewFile();
            String fecha = obtenerFecha(nuevoDoc);
            leerArchivo(nuevoDoc.getPath());
            Documentos documento = new Documentos(url,nuevoDoc.getPath(),arbolDoc(),nuevoDoc.getName(),nuevoDoc.length(),fecha);
            palabras.reset();
            return documento;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    private String obtenerFecha(File file){
        BasicFileAttributes attrs;
        try{
            attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            FileTime time = attrs.creationTime();
            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String formatted = simpleDateFormat.format( new Date( time.toMillis() ) );
            return formatted;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    /**
     * Metodo encargado de crear archivos txt.
     * @param file archivo txt creado.
     * @param texto texto que se le va a annadir al archivo.
     */
    private void escribirTxt(File file, String texto){
        try {
            PrintWriter escribir = new PrintWriter(file);
            escribir.println(texto);
            escribir.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    /**
     * Metodo para abrir y un archivo txt y converirlo a una lista que contiene las lineas del archivo
     * @param dir dirección del archivo txt que se va a leer
     * @return devuelve DoubleEndedLinkedList que contiene las lineas del archivo
     */
    public DoubleEndedLinkedList<String> read(String dir) {
        DoubleEndedLinkedList <String> listalineas= new DoubleEndedLinkedList<>(); 
        BufferedReader bufferLectura = null;
        
        try {
            // Abrir el txt en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader(dir));
            // Leer una linea del archivo
            String linea = bufferLectura.readLine();  
            
            while (linea != null) {
                listalineas.add(linea);
                // Volver a leer otra línea del fichero
                linea = bufferLectura.readLine();
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
            }
        finally {
            // Cierro el buffer de lectura
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                    } 
                catch (IOException e) {
                    e.printStackTrace();
                    }
                }   
        }
    return listalineas;
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
        StringTokenizer token = new StringTokenizer(lineaTexto,",. );:( DEMO");
        while (token.hasMoreElements()){
            palabras.add(token.nextToken());
        }
        palabras.print();
    }

    /**
     * Método que agrega las palabras del documento al arbol.
     * @return
     */
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
