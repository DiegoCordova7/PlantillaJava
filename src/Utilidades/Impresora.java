package Utilidades;
import Utilidades.AdministracionArchivos.ManejadorArchivos;
import Utilidades.Impresiones.*;

/**
 * Clase encargada de manejar la impresion de mensajes, errores, tablas, listas y menus.
 */
public class Impresora {
    /**
     * Imprime un mensaje en la consola.
     *
     * @param mensaje El mensaje a imprimir.
     */
    public static <T> void imprimir(T mensaje) {
        ImpresionMensajes.imprimir(String.valueOf(mensaje));
    }
    /**
     * Imprime un mensaje en la consola sin salto de linea.
     *
     * @param mensaje El mensaje a imprimir.
     */
    public static <T> void imprimirSeguido(T mensaje) {
        ImpresionMensajes.imprimirSeguido(String.valueOf(mensaje));
    }
    /**
     * Imprime un espacio en blanco en la consola.
     *
     */
    public static void imprimirEspacio() {
        ImpresionMensajes.imprimirEspacio();
    }
    /**
     * Pide un dato al usuario.
     *
     * @param mensaje El mensaje a mostrar al usuario.
     */
    public static void pedirDato(String mensaje) {
        ImpresionMensajes.pedirDato(mensaje);
    }
    /**
     * Imprime un texto con un formato especificado
     * @param mensaje El texto a imprimir
     * @param formato El formato que debe seguir el texto
     */
    @SuppressWarnings("unused")
    public static void imprimirFormateado(String mensaje, String formato) {
        ImpresionMensajes.imprimirFormateado(mensaje, formato);
    }
    /**
     * Imprime un mensaje de error.
     *
     * @param tipoError El tipo de error.
     * @param mensaje El mensaje de error.
     */
    public static void imprimirError(String tipoError, String mensaje) {
        ImpresionError.imprimirError(tipoError, mensaje);
    }
    /**
     * Imprime un aviso/notificacion para el usuario.
     *
     * @param tipoAviso El tipo de aviso
     * @param mensaje El mensaje del aviso
     */
    public static void imprimirAviso(String tipoAviso, String mensaje) {
        ImpresionAviso.imprimirAviso(tipoAviso, mensaje);
    }
    /**
     * Imprime una tabla en la consola.
     *
     * @param tabla La tabla a imprimir.
     * @param encabezado Los encabezados de la tabla.
     */
    public static void imprimirTabla(String[][] tabla, String[] encabezado) {
        ImpresionTabla.imprimirTabla(tabla, encabezado);
    }
    /**
     * Itera sobre las opciones e imprime cada una de ellas.
     *
     * @param opciones Las opciones a imprimir.
     * @param conNumeros Indica si se imprimen los numeros de las opciones.
     */
    @SuppressWarnings({"unused"})
    public static void iterarOpciones(String[] opciones, boolean conNumeros) {
        if (opciones == null || opciones.length == 0) {
            imprimirError("Contenido Faltante", "No hay opciones disponibles.");
            return;
        }
        for (int i = 0; i < opciones.length; i++)
            imprimir(String.format("%s%s", conNumeros ? "%d. ".formatted(i + 1) : "- ", opciones[i]));
    }
    /**
     * Imprime un encabezado en la consola.
     *
     * @param titulo El titulo del encabezado.
     */
    public static void imprimirEncabezado(String titulo) {
        ImpresionMensajes.imprimirEncabezado(titulo);
    }
    /**
     * Imprime una lista con numeros con las opciones proporcionadas.
     *
     * @param lista Las opciones a imprimir en la lista.
     */
    @SuppressWarnings("unused")
    public static void imprimirListaConNumeros(String[] lista) {
        ListaNumerada.imprimirListaNumerada(lista);
    }
    /**
     * Imprime una lista sin numeros con las opciones proporcionadas.
     *
     * @param lista Las opciones a imprimir en la lista.
     */
    @SuppressWarnings("unused")
    public static void imprimirListaSinNumeros(String[] lista) {
        ListaConVinetas.imprimirListaConVinetas(lista);
    }
    /**
     * Imprime el contenido de un archivo.
     *
     * @param manejador El manejador de archivos que contiene el archivo a imprimir.
     */
    @SuppressWarnings("unused")
    public static void imprimirContenidoArchivo(ManejadorArchivos manejador) {
        ImpresionMensajes.imprimirContenidoArchivo(manejador);
    }
    /**
     * Formatea un numero a una cadena.
     *
     * @param numero El numero a formatear.
     * @return El numero formateado como cadena.
     */
    @SuppressWarnings("unused")
    public static String formatearNumero(double numero) {
        return ImpresionMensajes.formatearNumero(numero);
    }
    /**
     * Alinea una cadena a un ancho especifico.
     *
     * @param texto La cadena a alinear.
     * @param ancho El ancho al que alinear la cadena.
     * @param centrar Indica si se debe centrar la cadena.
     * @return La cadena alineada.
     */
    @SuppressWarnings("unused")
    public static String alinearCadena(String texto, int ancho, boolean centrar) {
        return ImpresionMensajes.alinearCadena(texto, ancho, centrar);
    }
}