package Utilidades.Estructuras;
import Utilidades.Impresora;
import java.util.ArrayList;
import java.util.List;
/**
 * Esta clase representa una tabla que permite agregar, eliminar e imprimir filas
 * de datos, junto con un encabezado para las columnas.
 */
public final class Tabla {
    private String titulo;
    private String[] encabezado;
    private List<Fila> filas;
    public Tabla(String titulo ,String[] encabezado) {
        this.titulo = titulo;
        this.encabezado = encabezado;
        this.filas = new ArrayList<>();
    }
    /**
     * Agrega una nueva fila a la tabla con los datos especificados.
     * La cantidad de datos debe coincidir con la cantidad de columnas en el encabezado.
     *
     * @param datos Un arreglo de objetos que representa los datos de la fila, que se convierten a String.
     */
    public void agregarFila(Object... datos) {
        String[] datosComoString = new String[datos.length];
        for (int i = 0; i < datos.length; i++)
            datosComoString[i] = String.valueOf(datos[i]);
        if (datosComoString.length == encabezado.length)
            filas.add(new Fila(datosComoString));
        else
            Impresora.imprimirError("Contenido Faltante", "La cantidad de columnas no coincide con el encabezado.");
    }
    /**
     * Elimina una fila de la tabla en el indice especificado.
     *
     * @param indice El indice de la fila a eliminar.
     */
    public void eliminarFila(int indice) {
        if (indice >= 0 && indice < filas.size())
            filas.remove(indice);
        else
            Impresora.imprimirError("Indice fuera de rango", "El indice especificado no es valido.");
    }
    /**
     * Imprime la tabla en la consola, mostrando el encabezado y las filas de datos.
     */
    public void imprimir() {
        String[][] tabla = new String[filas.size()][];
        for (int i = 0; i < filas.size(); i++)
            tabla[i] = filas.get(i).getDatos();
        Impresora.imprimirTabla(tabla, encabezado, titulo);
    }
    /**
     * Agrega una nueva columna a la tabla.
     * Si no se especifica el nombre de la columna, se agregara un valor nulo.
     *
     * @param nombreColumna Nombre de la nueva columna a agregar.
     */
    public void agregarColumna(String nombreColumna) {
        String[] nuevoEncabezado = new String[encabezado.length + 1];
        System.arraycopy(encabezado, 0, nuevoEncabezado, 0, encabezado.length);
        nuevoEncabezado[encabezado.length] = nombreColumna;
        encabezado = nuevoEncabezado;
        for (Fila fila : filas) {
            String[] datosFila = fila.getDatos();
            String[] nuevaFila = new String[datosFila.length + 1];
            System.arraycopy(datosFila, 0, nuevaFila, 0, datosFila.length);
            nuevaFila[datosFila.length] = "N/A";
            fila.setDatos(nuevaFila);
        }
    }
    /**
     * Elimina una columna de la tabla en el indice especificado.
     *
     * @param indice El indice de la columna a eliminar.
     */
    public void eliminarColumna(int indice) {
        int index = indice - 1;
        if (index >= 0 && index < encabezado.length) {
            String[] nuevoEncabezado = new String[encabezado.length - 1];
            System.arraycopy(encabezado, 0, nuevoEncabezado, 0, index);
            System.arraycopy(encabezado, index + 1, nuevoEncabezado, index, encabezado.length - index - 1);
            encabezado = nuevoEncabezado;
            for (Fila fila : filas) {
                String[] datosFila = fila.getDatos();
                String[] nuevaFila = new String[datosFila.length - 1];
                System.arraycopy(datosFila, 0, nuevaFila, 0, index);
                System.arraycopy(datosFila, index + 1, nuevaFila, index, datosFila.length - index - 1);
                fila.setDatos(nuevaFila);
            }
        } else
            Impresora.imprimirError("Índice fuera de rango", "El indice especificado no es valido.");
    }
    /**
     * Actualiza el dato en la tabla en las coordenadas especificadas (fila, columna).
     *
     * @param fila    La fila donde se encuentra el dato.
     * @param columna La columna donde se encuentra el dato.
     * @param nuevoDato El nuevo dato a establecer.
     */
    public <T> void actualizarDato(int fila, int columna, T nuevoDato) {
        int filaIndex = fila - 1;
        int columnaIndex = columna - 1;
        if (filaIndex >= 0 && filaIndex < filas.size() && columnaIndex >= 0 && columnaIndex < encabezado.length) {
            String[] datosFila = filas.get(filaIndex).getDatos();
            datosFila[columnaIndex] = String.valueOf(nuevoDato);
            filas.get(filaIndex).setDatos(datosFila);
        } else
            Impresora.imprimirError("Indice fuera de rango", "Los indices especificados no son validos.");
    }
    /**
     * Borra el dato en la coordenada especificada (1-based).
     * Utiliza el metodo actualizarDato para establecer el valor "N/A" en la posicion.
     *
     * @param filaIndex   El indice de la fila (1-based).
     * @param columnaIndex El indice de la columna (1-based).
     */
    public void borrarDato(int filaIndex, int columnaIndex) {
        actualizarDato(filaIndex, columnaIndex, "N/A");
    }
}