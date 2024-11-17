package Utilidades.Impresiones;
/**
 * Clase que extiende ImpresionLista para imprimir listas con viñetas.
 */
public class ListaConVinetas extends ImpresionLista {
    /**
     * Imprime una lista de opciones con viñetas.
     *
     * @param opciones Un arreglo de cadenas que contiene las opciones a imprimir.
     */
    public static void imprimirListaConVinetas(String[] opciones) {
        imprimirLista(opciones, false);
    }
}