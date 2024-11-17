package Utilidades.Estructuras;
/**
 * Esta clase representa una fila de datos en una tabla.
 */
public final class Fila {
    private String[] datos;
    /**
     * Constructor que inicializa la fila con los datos proporcionados.
     *
     * @param datos Un arreglo de cadenas que contiene los datos de la fila.
     */
    public Fila(String[] datos) {
        setDatos(datos);
    }
    /**
     * Obtiene los datos de la fila.
     *
     * @return Un arreglo de cadenas que representa los datos de la fila.
     */
    public String[] getDatos() {
        return datos;
    }
    /**
     * Establece los datos de la fila.
     *
     * @param datos Un arreglo de cadenas que representa los nuevos datos de la fila.
     */
    public void setDatos(String[] datos) {
        this.datos = datos;
    }
}