package Utilidades.Impresiones;
/**
 * Clase que extiende ImpresionLista para imprimir listas numeradas.
 */
public class ListaNumerada extends ImpresionLista {
    /**
     * Imprime una lista de opciones numeradas.
     *
     * @param opciones Un arreglo de cadenas que contiene las opciones a imprimir.
     */
    public static void imprimirListaNumerada(String[] opciones) {
        imprimirLista(opciones, true);
    }
}