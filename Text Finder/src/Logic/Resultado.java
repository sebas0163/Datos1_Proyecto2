package Logic;


import javafx.scene.text.TextFlow;

/**
 * Clase encargada de almacenar los datos de cada resultado de una busqueda
 */
public class Resultado {
    private String ruta;
    private String nombre;
    private String[] text;
    private TextFlow flow;
    private String buscado;
    private int linea;
    private int lineasDoc;

    /**
     * Metodo constructor de la clase.
     * @param ruta ruta del archivo en el cual se encuentra la palabra encontrada.
     * @param text Cadena de texto que se muestra como resultado.
     * @param flow contenedor del texto que se muestra como resultado.
     */
    public Resultado(String ruta,String[] text,TextFlow flow,String nombre,String buscado,int linea, int lineasDoc){
        this.ruta=ruta;
        this.text = text;
        this.flow = flow;
        this.buscado = buscado;
        this.nombre = nombre;
        this.linea = linea;
        this.lineasDoc = lineasDoc;

    }

    public String getRuta() {
        return ruta;
    }

    public String[] getText() {
        return text;
    }

    public String getNombre() {
        return nombre;
    }

    public TextFlow getFlow() {
        return flow;
    }

    public String getBuscado() {
        return buscado;
    }

    public int getLinea() {
        return linea;
    }

    public int getLineasDoc() {
        return lineasDoc;
    }
}
