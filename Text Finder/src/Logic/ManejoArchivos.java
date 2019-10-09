package Logic;

import EstructurasDatos.DoubleEndedLinkedList;

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

}
