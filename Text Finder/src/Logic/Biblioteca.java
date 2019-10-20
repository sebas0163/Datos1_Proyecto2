package Logic;

import EstructurasDatos.DoubleEndedLinkedList;

import java.io.File;

public class Biblioteca {
    private DoubleEndedLinkedList<Documentos> listaDocumentos;

    public Biblioteca(){
        this.listaDocumentos = new DoubleEndedLinkedList<>();
    }
    public void agregarDocumento(Documentos documento){
        listaDocumentos.add(documento);
    }
    public void eliminarDocumento(Documentos documento){
        int pos = listaDocumentos.buscarPos(documento);
        Documentos doc = listaDocumentos.getInfo(pos);
        File file = new File(doc.getRutaTxt());
        file.delete();
        listaDocumentos.remove(pos);
    }

    public DoubleEndedLinkedList<Documentos> getListaDocumentos() {
        return listaDocumentos;
    }
}
