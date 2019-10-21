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

    /**
     * Metodo encargado de eliminar el documento del programa.
     * @param documento documento a eliminar.
     */
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
