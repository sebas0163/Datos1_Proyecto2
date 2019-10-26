package Logic;

import EstructurasDatos.ArbolBinarioBusqueda;
import EstructurasDatos.DoubleEndedLinkedList;
import EstructurasDatos.Nodo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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

import javax.print.Doc;
import java.io.*;
import java.util.StringTokenizer;

/**
 * Clase encargada de manejar la logica del programa.
 * @author Sebastian Moya
 */
public class Ejecutar {
    private ManejoArchivos manejoArchivos;
    private MenuButton agregar;
    private MenuButton eliminar;
    private Biblioteca biblioteca;
    private String path;
    private Resultado resultado;
    private String buscado;
    private DoubleEndedLinkedList<Resultado> listaResultado;
    private TreeItem raiz;

 
    /**
     * Metodo constructor de la clase.
     */
    public Ejecutar(TreeItem raiz,MenuButton agregar, MenuButton eliminar){
        this.manejoArchivos = new ManejoArchivos();
        this.agregar = agregar;
        this.eliminar = eliminar;
        this.biblioteca = new Biblioteca();
        this.listaResultado = new DoubleEndedLinkedList<>();
        this.raiz = raiz;
        this.path = "";
    }
    public double strToInt(String a){
        a=a.toUpperCase();
        String palabra="";
        int len=a.length();
        //len--;
        for (int i=0;i<len;i++){
            palabra+=Integer.toString(a.codePointAt(i));
        }
        return Double.parseDouble(palabra);
    }
    
    public void quickSort(){        
        DoubleEndedLinkedList<Documentos> list=biblioteca.getListaDocumentos();
        quickSort(list,0,list.len()-1);  
        biblioteca.setListaDocumentos(list);
    }
    /**
     * Metodo metodo que define donde se debe de partir la parte de la lista que se esta ordenando
     * @param list lista que se esta ordenando 
     * @param low indice menor de la parte que se esta ordenando 
     * @param high indice mayor de la parte que se esta ordenando 
     * @return indice en el cual se debe de partir la lista 
     */
    private int quickaux(DoubleEndedLinkedList<Documentos> list, int low, int high){
        double pivot = strToInt(list.getNodo(high).getDato().getNombre()); 
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than the pivot 
            if (strToInt(list.getNodo(j).getDato().getNombre()) < pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                Documentos temp = list.getNodo(i).getDato(); 
                list.getNodo(i).setDato(list.getNodo(j).getDato()); 
                list.getNodo(j).setDato(temp); 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        Documentos temp = list.getNodo(i+1).getDato(); 
        list.getNodo(i+1).setDato(list.getNodo(high).getDato());
        list.getNodo(high).setDato(temp);
         
  
        return i+1; 
    } 
  
    /**
     * Metodo que va a ordenar la lista
     * @param list lista a ordenar 
     * @param low 
     * @param high 
     */
    private void quickSort(DoubleEndedLinkedList<Documentos> list, int low, int high){
        if (low < high){ 
            int pi = (int) quickaux(list, low, high); 
            quickSort(list, low, pi-1); 
            quickSort(list, pi+1, high); 
        } 
    } 
    
    
    /**
     * Metodo que obtiene el dato de mayor valor de la lista
     * @param list lista donde se realiza la busqyeda
     * @return el valor maximo encontrado
     */
    private static long getMax(DoubleEndedLinkedList<Documentos> list) 
    {
        Nodo<Documentos> aux=list.getNodo(0);
        
        long max=0;
        while(aux!=null){
            if (aux.getDato().getTamano()>max)
                max=aux.getDato().getTamano();
            aux=aux.getNext();
        }
    return max;
    }   
 
    /**
     * Metodo que rellana una lista de un larno solicitado con un valor dado
     * @param list lista que se desea rellenar
     * @param lenn largo final de la lista
     * @param relleno dato con el que se desea rellenar la lista
     */
    private static void filList(DoubleEndedLinkedList list,int lenn,int relleno){
        int cont=0;
        while(cont<lenn){
            list.add(relleno);
            cont++;
        }        
    }
    /**
     * funcion principal del radixSort la cual ordena la lista segun la posicion (unidades, decenas, centas...)
     * @param list lista que se desea ordenar 
     * @param n largo de la lista 
     * @param exp exponente para obtener la posicion por la que se va a ordenar la lista
     * @return lista ordenada segun la posicion dada 
     */
    private DoubleEndedLinkedList<Documentos> radixSort(DoubleEndedLinkedList<Documentos> list, int n, int exp) 
    { 
        DoubleEndedLinkedList<Documentos> lista=list;
        DoubleEndedLinkedList<Integer> count=new DoubleEndedLinkedList();
        filList(count,10,0);
        
        for (int i = 0; i < n; i++) 
            count.getNodo((int) ((list.getNodo(i).getDato().getTamano()/exp)%10)).setDato(count.getNodo((int) ((list.getNodo(i).getDato().getTamano()/exp)%10)).getDato()+1);   
             
        for (int i = 1; i < 10; i++) 
            count.getNodo(i).setDato( count.getNodo(i).getDato()+ count.getNodo(i-1).getDato());
             
        
        for (int i = n - 1; i >= 0; i--){
            Documentos dat=list.getNodo(i).getDato();
            int pos=count.getNodo((int) (list.getNodo(i).getDato().getTamano()/exp%10)).getDato()-1;
            lista.getNodo(pos).setDato(dat); 
            count.getNodo((int) (((list.getNodo(i).getDato().getTamano())/exp)%10)).setDato((count.getNodo((int) ((list.getNodo(i).getDato().getTamano())/exp%10)).getDato())-1);
        }   
        
        return lista;
    } 
  
    /**
     * Funcion que llama a la funcion principal del radixsort
     * @return lista ordenada 
     */
    public void radixSort(){ 
        DoubleEndedLinkedList<Documentos> list=biblioteca.getListaDocumentos();
        double m = getMax(list); 
        for (int exponente = 1; m/exponente > 0; exponente *= 10) 
            list=radixSort(list,list.len(), exponente);
        biblioteca.setListaDocumentos(list);
        
    } 
    
    public void bubble(){
        DoubleEndedLinkedList<Documentos> list=biblioteca.getListaDocumentos();
        list=bubble(0,list);
        biblioteca.setListaDocumentos(list);
    }
    private DoubleEndedLinkedList bubble(int inicial,DoubleEndedLinkedList list){
        DoubleEndedLinkedList<Documentos> lista=list;
        int swaps=0;
        for(int i=inicial;i<lista.len()-1;i++){
            if ((int)strToInt(lista.getNodo(i).getDato().getFecha())>(int) strToInt(lista.getNodo(i+1).getDato().getFecha())){
                Documentos temp= lista.getNodo(i).getDato();
                lista.getNodo(i).setDato(lista.getNodo(i+1).getDato());
                lista.getNodo(i+1).setDato(temp);
                swaps++;
            }
        }
        if (swaps>1){
            return bubble(inicial++,lista);
        }
        return lista;   
    }
    /**
     * Metodo que busca la palabra en el arbol y envia el nodo que la posee con todos sus datos al metodo que los trabaja.
     * @param buscado palabra que se desea buscar en el arbol.
     */
    public void buscarPalabra(String buscado,VBox resultados,boolean borrar){
        this.buscado = buscado;
        if (borrar) {
            listaResultado = new DoubleEndedLinkedList<>();
        }
        DoubleEndedLinkedList listaDocs = biblioteca.getListaDocumentos();
        Nodo temp = listaDocs.getHead();
        while(temp != null) {
            Documentos documento = (Documentos) temp.getDato();
            ArbolBinarioBusqueda arbol = documento.getArbolPalabras();
            if (arbol.buscar(buscado)!= null){
                Palabra palabra = arbol.buscar(buscado);
                mostrarApariciones(palabra, resultados,documento,buscado);
                temp = temp.getNext();
            }else {
                System.out.println("La palabra no está en el documento");
                temp = temp.getNext();
            }
        }
    }

    /**
     * Metodo que recibe el documento .docx cargado y lo envia a su indizacion.
     * @param url ruta del documento.
     * @param nombre nombre del archivo.
     */
    public void addDocx(String url,String nombre, TreeItem item,String rutaBib,MenuItem opcion1,MenuItem opcion2,MenuButton modificar,MenuButton eliminar){
        Documentos doc = manejoArchivos.indizarDocx(url, nombre,rutaBib);
        doc.setItem(item);
        opcion2.setOnAction(eliminarAct);
        opcion1.setOnAction(modificarDoc);
        doc.setAgregar(opcion1);
        doc.setEliminar(opcion2);
        biblioteca.agregarDocumento(doc);
        modificar.getItems().add(opcion1);
        eliminar.getItems().add(opcion2);
    }

    /**
     * Metodo que recibe el documento .txt cargado y lo envia a su indizacion.
     * @param url ruta del docuemento
     * @param nombre Nombre del documento
     */
    public void addTxt(String url,String nombre, TreeItem item,String rutaBib,MenuItem opcion1,MenuItem opcion2,MenuButton modificar,MenuButton eliminar){
        Documentos doc = manejoArchivos.indizarTxt(url,nombre,rutaBib);
        opcion2.setOnAction(eliminarAct);
        opcion1.setOnAction(modificarDoc);
        doc.setEliminar(opcion2);
        doc.setAgregar(opcion1);
        doc.setItem(item);
        biblioteca.agregarDocumento(doc);
        modificar.getItems().add(opcion1);
        eliminar.getItems().add(opcion2);
    }

    /**
     * Metodo que recibe el documento .pdf cargado y lo envia a su indizacion.
     * @param url ruta del documento.
     * @param nombre Nombre del documento
     */
    public void addPdf(String url, String nombre, TreeItem item,String rutaBib,MenuItem opcion1,MenuItem opcion2,MenuButton modificar,MenuButton eliminar){
        Documentos doc = manejoArchivos.indizarPdf(url,nombre,rutaBib);
        opcion2.setOnAction(eliminarAct);
        opcion1.setOnAction(modificarDoc);
        doc.setAgregar(opcion1);
        doc.setEliminar(opcion2);
        doc.setItem(item);
        biblioteca.agregarDocumento(doc);
        modificar.getItems().add(opcion1);
        eliminar.getItems().add(opcion2);
    }

    /**
     * Mentodo encargado de enviar una carpeta a su indización y sus archivos
     * @param item Nodo del arbol en el que e va a mostrar la carpeta.
     * @param url ruta de la carpeta
     */
    public void addCarpeta(TreeItem item, String url,String rutaBib,MenuItem opcion2,MenuButton modificar,MenuButton eliminar){
        File file = new File(url);
        File file1 = new File(rutaBib,file.getName());
        file1.mkdir();
        Documentos doc = new Documentos(url,file1.getPath(),null,file.getName(),file.length(),null,file.getName(),0);
        doc.setEliminar(opcion2);
        doc.setItem(item);
        biblioteca.getListaDocumentos().add(doc);
        if(opcion2 != null){
            opcion2.setOnAction(eliminarAct2);
        }
        doc.setEliminar(opcion2);
        for (File fichero: file.listFiles()){
            int largo = fichero.getName().length();
            String formato = fichero.getName().substring(largo-4,largo);
            String formato2 = fichero.getName().substring(largo-5,largo);
            if (formato.equals(".txt")){
                TreeItem item1 = new TreeItem(fichero.getName());
                MenuItem item2 = new MenuItem(fichero.getName());
                MenuItem item3 = new MenuItem(fichero.getName());
                addTxt(fichero.getAbsolutePath(),fichero.getName(),item1,file1.getAbsolutePath(),item2,item3,modificar,eliminar);
                item.getChildren().add(item1);
                eliminar.getItems().add(opcion2);
            }
            else if(formato.equals(".pdf")){
                int length = fichero.getName().length();
                TreeItem item1 = new TreeItem(fichero.getName());
                MenuItem item2 = new MenuItem(fichero.getName());
                MenuItem item3 = new MenuItem(fichero.getName());
                addPdf(fichero.getAbsolutePath(),fichero.getName().substring(0,length-5),item1,file1.getAbsolutePath(),item2,item3,modificar,eliminar);
                item.getChildren().add(item1);
                eliminar.getItems().add(opcion2);
            }
            else if (formato2.equals(".docx")) {
                int length = fichero.getName().length();
                TreeItem item1 = new TreeItem(fichero.getName());
                MenuItem item2 = new MenuItem(fichero.getName());
                MenuItem item3 = new MenuItem(fichero.getName());
                addDocx(fichero.getAbsolutePath(), fichero.getName().substring(0, length - 5), item1, file1.getAbsolutePath(),item2,item3,modificar,eliminar);
                item.getChildren().add(item1);
                eliminar.getItems().add(opcion2);
            }else if (!(fichero.getName().contains("."))){
                TreeItem item1 = new TreeItem(fichero.getName());
                MenuItem item2 = new MenuItem(fichero.getName());
                addCarpeta(item1,fichero.getAbsolutePath(),file1.getAbsolutePath(),item2,modificar,eliminar);
                item.getChildren().add(item1);
                eliminar.getItems().add(opcion2);
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
    private void mostrarApariciones(Palabra palabra, VBox resultados, Documentos documento,String busc){
        String [] texto = {palabra.getPalabra()+"\n",documento.getNombreOrg() +"     ",documento.getFecha()+"      ",String.valueOf(documento.getTamano())};
        int apariciones = palabra.getApariciones();
        DoubleEndedLinkedList lineas = palabra.getLineas();
        int cont = 1;
        int pos = 0;
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
            Resultado resultado = new Resultado(documento.getRutaTxt(),texto,flow,documento.getNombreOrg(),busc,(int)lineas.getNodo(pos).getDato(),documento.getLineas());
            listaResultado.add(resultado);
            resultados.getChildren().add(flow);
            cont += 1;
            //pos++;
        }
    }

    /**
     * Metodo encargado de buscar el resultado seleccionado
     * @param flow texflow
     * @return resultado
     */
    private Resultado buscaResult(TextFlow flow){
        for (int i = 0;i<listaResultado.len();i++){
            Resultado r = listaResultado.getNodo(i).getDato();
            if (r.getFlow().equals(flow)){
                return r;
            }
        }
        return null;
    }
    private void mostrarApariciones(DoubleEndedLinkedList texto,VBox resultados,Documentos documento,String busc){
        Nodo temp = texto.getHead();
        while(temp != null){
            String text = (String) temp.getDato();
            String [] textFinal = {text+"\n",documento.getNombreOrg()+"     ",documento.getFecha()+"        "+ String.valueOf(documento.getTamano())};
            TextFlow flow = new TextFlow();
            flow.setOnMouseClicked(click);
            flow.setPrefWidth(1074);
            Text text1 = new Text(textFinal[0]);
            text1.setFont(new Font("Arial",18));
            flow.getChildren().add(text1);
            for(int i =1; i< textFinal.length; i++ ){
                Text text2 = new Text(textFinal[i]);
                text2.setFont(new Font("Arial",18));
                flow.getChildren().add(text2);
            }
            Resultado resultado = new Resultado(documento.getRutaTxt(),textFinal,flow,documento.getNombreOrg(),busc,0,documento.getLineas());
            listaResultado.add(resultado);
            resultados.getChildren().add(flow);
        }
    }

    /**
     * Metodo encargado de elimnar algun documento cargado
     * @param doc instancia del dcoumento que contiene la informacion del archivo.
     */
    public void eliminarDoc(Documentos doc){
        listaResultado.reset();
        DoubleEndedLinkedList listadocs = biblioteca.getListaDocumentos();
        File file = new File(doc.getRutaTxt());
        file.delete();
        raiz.getChildren().remove(doc.getItem());
        agregar.getItems().remove(doc.getAgregar());
        eliminar.getItems().remove(doc.getEliminar());
        int pos = listadocs.buscarPos(doc);
        listadocs.remove(pos);
    }

    /**
     * Metodo encargado de eliminar carpetas del programa
     * @param doc instancia del dcoumento que contiene la informacion de la carpeta.
     */
    public void eliminarCarpeta(Documentos doc){
        listaResultado.reset();

        DoubleEndedLinkedList listaDocs = biblioteca.getListaDocumentos();
        File file = new File(doc.getRutaTxt());
        for(File fichero: file.listFiles()){
            Nodo temp = listaDocs.getHead();
            if (fichero.isDirectory()){
                while (temp != null) {
                    Documentos archivo = (Documentos) temp.getDato();
                    if (archivo.getRutaTxt().equals(fichero.getPath())) {
                        eliminarCarpeta(archivo);
                    }
                    temp = temp.getNext();
                }
            }else{
                while (temp != null) {
                    Documentos archivo = (Documentos) temp.getDato();
                    if (archivo.getRutaTxt().equals(fichero.getAbsolutePath())) {
                        eliminarDoc(archivo);
                    }
                    temp = temp.getNext();
                }
            }
        }
        raiz.getChildren().remove(doc.getItem());
        eliminar.getItems().remove(doc.getEliminar());
        file.delete();
        int pos = listaDocs.buscarPos(doc);
        System.out.println(listaDocs.len());
        listaDocs.remove(pos);

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
        buscado = resultado.getBuscado();
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
    public void recalcularArbol(Documentos doc){
        DoubleEndedLinkedList<String> palabras = new DoubleEndedLinkedList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(doc.getRutaTxt()));
            String lineaTexto;
            while ((lineaTexto = bf.readLine())!=null) {
                StringTokenizer token = new StringTokenizer(lineaTexto, ",. );:(");
                while (token.hasMoreElements()) {
                    palabras.add(token.nextToken());
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda<>();
        Nodo temp = palabras.getHead();
        while (temp != null){
            arbol.agregar((String) temp.getDato());
            temp = temp.getNext();
        }
        doc.setArbolPalabras(arbol);
    }
    /**
     * Metodo encargado de realizar la busqueda de una frase en el documento.
     * @param frase frase que se desea buscar.
     * @return
     */
    public void buscarFrase(String frase,VBox resultados){
        DoubleEndedLinkedList listadocs= biblioteca.getListaDocumentos();
        for(int j=0; j<listadocs.len();j++) {
            Documentos doc = (Documentos)listadocs.getNodo(j).getDato();
            DoubleEndedLinkedList listaTXT = manejoArchivos.read(doc.getRutaTxt());
            DoubleEndedLinkedList list = new DoubleEndedLinkedList();
            for (int i = 0; i < listaTXT.len(); i++) {
                if (listaTXT.getNodo(i).getDato().toString().toUpperCase().contains(frase.toUpperCase())) {
                    list.add(listaTXT.getNodo(i - 1).getDato().toString() + "\n" +
                            listaTXT.getNodo(i).getDato().toString() + "\n" +
                            listaTXT.getNodo(i + 1).getDato().toString());
                }
                mostrarApariciones(list,resultados,doc,frase);
            }
        }
    }

    /**
     * Metodo encargado de controlar los eventos del mouse.
     */
    EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getButton().equals(MouseButton.PRIMARY)){
                path= buscarRuta((TextFlow) event.getSource());
                resultado = buscaResult((TextFlow)event.getSource());
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
    EventHandler<ActionEvent> eliminarAct = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            MenuItem item = (MenuItem) event.getSource();
            Nodo temp = biblioteca.getListaDocumentos().getHead();
            while(temp != null){
                Documentos doc = (Documentos) temp.getDato();
                if(doc.getEliminar().equals(item)){
                    eliminarDoc(doc);
                    break;
                }else{
                    temp = temp.getNext();
                }
            }
        }
    };
    EventHandler<ActionEvent> eliminarAct2 = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            MenuItem item = (MenuItem) event.getSource();
            Nodo temp = biblioteca.getListaDocumentos().getHead();
            while(temp != null){
                Documentos doc = (Documentos) temp.getDato();
                if(doc.getEliminar().equals(item)){
                    eliminarCarpeta(doc);
                    break;
                }else{
                    temp = temp.getNext();
                }
            }
        }
    };
    EventHandler<ActionEvent> modificarDoc = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            MenuItem item = (MenuItem) event.getSource();
            Nodo temp = biblioteca.getListaDocumentos().getHead();
            while(temp != null){
                Documentos doc = (Documentos) temp.getDato();
                if(doc.getAgregar().equals(item)){
                    path = doc.getRutaTxt();
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("ModificarText.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("Modificar Texto");
                        stage.setResizable(false);
                        stage.show();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                }else{
                    temp = temp.getNext();
                }
            }
        }
    };

    public String getPath() {
        return path;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public String getBuscado() {
        return buscado;
    }

    public Resultado getResultado() {
        return resultado;
    }
}
