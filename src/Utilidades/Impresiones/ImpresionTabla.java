package Utilidades.Impresiones;
import Utilidades.Impresora;
import java.util.ArrayList;
import java.util.List;
import static Utilidades.Impresiones.Colores.colorear;
/**
 * Esta clase proporciona metodos para imprimir tablas en la consola.
 */
public class ImpresionTabla extends Impresora {
    /**
     * Imprime una tabla en la consola con un encabezado y varias filas de contenido.
     *
     * @param tabla      Un arreglo bidimensional de cadenas que contiene las filas de la tabla.
     * @param encabezado Un arreglo de cadenas que contiene los titulos de las columnas.
     */
    public static void imprimirTabla(String[][] tabla, String[] encabezado) {
        int columnas = encabezado.length;
        int[] anchos = new int[columnas];
        for (int i = 0; i < columnas; i++) anchos[i] = encabezado[i].length();
        for (String[] strings : tabla) for (int j = 0; j < strings.length; j++)
            if (strings[j].length() > anchos[j]) anchos[j] = strings[j].length();
        imprimirLineaSeparadora(anchos);
        imprimirFila(encabezado, anchos);
        imprimirLineaSeparadora(anchos);
        for (int i = 0; i < tabla.length; i++) {
            imprimirFila(tabla[i], anchos);
            if (i < tabla.length - 1) imprimirLineaSeparadora(anchos);
        }
        imprimirLineaSeparadora(anchos);
    }
    /**
     * Imprime una fila de la tabla con el formato adecuado.
     *
     * @param fila   Un arreglo de cadenas que representa una fila de la tabla.
     * @param anchos Un arreglo de enteros que contiene el ancho de cada columna.
     */
    private static void imprimirFila(String[] fila, int[] anchos) {
        imprimirSeguido(colorear(Colores.GRIS, "| "));
        for (int i = 0; i < fila.length; i++) {
            List<String> lineas = dividirTexto(fila[i], anchos[i]);
            for (String linea : lineas) imprimirSeguido(String.format("%%-%ds".formatted(anchos[i]), linea));
            if (i < fila.length - 1) imprimirSeguido(colorear(Colores.GRIS, " | "));
        }
        imprimir(colorear(Colores.GRIS, " |"));
    }
    /**
     * Divide una cadena en varias lineas si su longitud excede el limite especificado.
     *
     * @param texto  El texto a dividir.
     * @param limite El limite maximo de caracteres por linea.
     * @return Una lista de cadenas que representa el texto dividido en lineas.
     */
    private static List<String> dividirTexto(String texto, int limite) {
        List<String> lineas = new ArrayList<>();
        for (int i = 0; i < texto.length(); i += limite)
            lineas.add(texto.substring(i, Math.min(i + limite, texto.length())));
        return lineas;
    }
    /**
     * Imprime una linea separadora entre las filas de la tabla.
     *
     * @param anchos Un arreglo de enteros que contiene el ancho de cada columna.
     */
    private static void imprimirLineaSeparadora(int[] anchos) {
        imprimirSeguido(colorear(Colores.GRIS, "+"));
        for (int ancho : anchos) {
            imprimirSeguido(colorear(Colores.GRIS, "-".repeat(ancho + 2)));
            imprimirSeguido(colorear(Colores.GRIS, "+"));
        }
        Impresora.imprimirEspacio();
    }
}