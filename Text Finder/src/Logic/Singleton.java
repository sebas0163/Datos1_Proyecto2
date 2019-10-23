package Logic;

import javafx.scene.control.MenuButton;
import javafx.scene.control.TreeItem;

/**
 * Clase encargada de verificar que se cree solo una instancia de la clase ejecutar.
 */
public class Singleton {
    public static Ejecutar ejecutar;

    private Singleton(){
    }

    /**
     * metodo encargado de crear o retornar la instancia de la clase ejecutar.
     * @return instancia de la clase ejecutar.
     */
    public static Ejecutar getInstancia(TreeItem raiz, MenuButton agregar,MenuButton eliminar){
        if (ejecutar == null){
            ejecutar = new Ejecutar(raiz,agregar,eliminar);
        }
        return ejecutar;
    }
}
