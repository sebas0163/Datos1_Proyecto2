package Logic;

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
    public static Ejecutar getInstancia(){
        if (ejecutar == null){
            ejecutar = new Ejecutar();
        }
        return ejecutar;
    }
}
