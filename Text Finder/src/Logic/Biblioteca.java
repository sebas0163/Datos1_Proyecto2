package Logic;

import EstructurasDatos.DoubleEndedLinkedList;

import java.io.File;

/**
 * Clase encargada de alamacenar y gestionar todos los datos que tengan relacion con el ingreso de archivos al programa.
 * @author Sebastian Moya
 */
public class Biblioteca {
    private DoubleEndedLinkedList<Documentos> listaDocumentos;

    /**
     * Metodo contructor de la clase.
     */
    public Biblioteca(){
        this.listaDocumentos = new DoubleEndedLinkedList<>();
    }

    /**
     * Metodo que agrega documentos a la lista de documentos de la biblioteca.
     * @param documento Documento que se desea agregar.
     */
    public void agregarDocumento(Documentos documento){
        listaDocumentos.add(documento);
    }
    

    public DoubleEndedLinkedList<Documentos> getListaDocumentos() {
        return listaDocumentos;
    }
    public void setListaDocumentos(DoubleEndedLinkedList<Documentos> list) {
        this.listaDocumentos=list;
    }
}
