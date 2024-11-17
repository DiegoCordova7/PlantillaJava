package Utilidades.Impresiones;
import Utilidades.Impresora;
/**
 * La clase ImpresionLista proporciona metodos para imprimir listas de opciones,
 * ya sea numeradas o con viñetas, en la consola.
 */
public class ImpresionLista extends Impresora {
    /**
     * Imprime una lista de opciones, ya sea numeradas o con viñetas.
     *
     * @param opciones   Un arreglo de cadenas que contiene las opciones a imprimir.
     * @param conNumeros Indica si las opciones deben ser numeradas (true) o con viñetas (false).
     */
    @SuppressWarnings("preview")
    public static void imprimirLista(String[] opciones, boolean conNumeros) {
        if (opciones == null || opciones.length == 0) {
            Impresora.imprimirAviso("Contenido Faltante", "No hay opciones disponibles.");
            return;
        }
        for (int i = 0; i < opciones.length; i++) {
            String formato = conNumeros ? "%d. %s" : "- %s";
            if (conNumeros) Impresora.imprimir(String.format(formato, (i + 1), opciones[i]));
            else Impresora.imprimir(String.format(formato, opciones[i]));
        }
    }
}