package Logic;


import javafx.scene.text.TextFlow;

public class Resultado {
    private String ruta;
    private String[] text;
    private TextFlow flow;

    public Resultado(String ruta,String[] text,TextFlow flow){
        this.ruta=ruta;
        this.text = text;
        this.flow = flow;

    }

    public String getRuta() {
        return ruta;
    }

    public String[] getText() {
        return text;
    }

    public TextFlow getFlow() {
        return flow;
    }
}
