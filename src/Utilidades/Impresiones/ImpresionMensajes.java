package Utilidades.Impresiones;
import Utilidades.AdministracionArchivos.*;
import Utilidades.Impresora;
import java.text.NumberFormat;
import java.util.Locale;
import static Utilidades.Impresiones.Colores.colorear;
/**
 * La clase ImpresionMensajes proporciona metodos para imprimir mensajes
 * en la consola, gestionar la presentación de datos y manejar la
 * impresión de contenido de archivos de texto y binarios.
 */
public class ImpresionMensajes extends Impresora {
    /**
     * Imprime un mensaje en la consola con un salto de línea.
     *
     * @param mensaje El mensaje a imprimir.
     */
    public static void imprimir(String mensaje) {
        System.out.println(mensaje);
    }
    /**
     * Imprime un mensaje en la consola sin salto de línea.
     *
     * @param mensaje El mensaje a imprimir.
     */
    public static void imprimirSeguido(String mensaje) {
        System.out.print(mensaje);
    }
    /**
     * Imprime una linea en blanco en la consola.
     */
    public static void imprimirEspacio() {
        System.out.println();
    }
    /**
     * Imprime un texto con un formato especificado
     * @param mensaje El texto a imprimir
     * @param formato El formato que debe seguir el texto
     */
    public static void imprimirFormateado(String mensaje, String formato) {
        System.out.printf(formato, mensaje);
    }
    /**
     * Solicita al usuario un dato mostrando un mensaje.
     *
     * @param mensaje El mensaje que solicita el dato.
     */
    public static void pedirDato(String mensaje) {
        imprimirSeguido(colorear(Colores.VERDE, mensaje));
    }
    /**
     * Imprime un encabezado en la consola, formateado en color amarillo.
     *
     * @param titulo El titulo del encabezado.
     */
    public static void imprimirEncabezado(String titulo) {
        imprimir(colorear(Colores.AZUL, titulo));
    }

    /**
     * Imprime el contenido de un archivo, dependiendo de su tipo.
     *
     * @param manejador Un objeto de tipo ManejadorArchivos que gestiona el archivo.
     */
    public static void imprimirContenidoArchivo(ManejadorArchivos manejador) {
        switch (manejador) {
            case ManejadorArchivosTexto texto ->
                imprimir(String.format("Contenido del archivo de texto %s:", texto.getArchivo()));
            case ManejadorArchivosBinarios binario ->
                imprimir(String.format("Contenido del archivo binario %s:", binario.getArchivo()));
            default -> {
                imprimirError("Archivo", "Tipo de archivo no soportado.");
                return;
            }
        }
        manejador.leer();
    }
    /**
     * Formatea un numero a una representacion de moneda según la configuracion regional.
     *
     * @param numero El numero a formatear.
     * @return El numero formateado como cadena en formato de moneda.
     */
    public static String formatearNumero(double numero) {
        NumberFormat formato = NumberFormat.getCurrencyInstance(Locale.getDefault());
        return formato.format(numero);
    }
    /**
     * Alinea una cadena en un ancho especificado, ya sea centrada o alineada a la izquierda.
     *
     * @param texto La cadena a alinear.
     * @param ancho El ancho total que ocupara la cadena alineada.
     * @param centrar Si true, la cadena se centrara; si false, se alineara a la izquierda.
     * @return La cadena alineada.
     */
    public static String alinearCadena(String texto, int ancho, boolean centrar) {
        if (centrar) {
            int padding = (ancho - texto.length()) / 2;
            return " ".repeat(Math.max(0, padding)) + texto + " ".repeat(Math.max(0, ancho - texto.length() - padding));
        } else return String.format("%%-%ds".formatted(ancho), texto);
    }
}