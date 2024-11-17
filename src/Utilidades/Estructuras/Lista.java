package Utilidades.Estructuras;
import Utilidades.Impresiones.ListaConVinetas;
import Utilidades.Impresiones.ListaNumerada;
import Utilidades.Impresora;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Esta clase proporciona una estructura de lista que permite agregar,
 * eliminar e imprimir elementos de manera numerada o con viñetas.
 */
@SuppressWarnings("unused")
public final class Lista {
    private List<String> elementos;
    /**
     * Constructor que inicializa la lista vacia.
     */
    public Lista() {
        elementos = new ArrayList<>();
    }
    /**
     * Agrega uno o mas elementos a la lista.
     *
     * @param elementosAAgregar Un arreglo de cadenas que contiene los elementos a agregar.
     */
    public void agregar(String... elementosAAgregar) {
        elementos.addAll(Arrays.asList(elementosAAgregar));
    }
    /**
     * Elimina un elemento de la lista en el indice especificado.
     *
     * @param indice El indice del elemento a eliminar.
     */
    public void eliminar(int indice) {
        if (indice > 0 && indice <= elementos.size())
            elementos.remove(indice - 1);
        else
            Impresora.imprimirError("Fuera De Rango", "Indice fuera de rango.");
    }
    /**
     * Verifica si la lista esta vacia.
     *
     * @return true si la lista no contiene elementos, false en caso contrario.
     */
    public boolean estaVacia() {
        return elementos.isEmpty();
    }
    /**
     * Imprime los elementos de la lista en la consola.
     * Puede imprimir la lista de manera numerada o con viñetas.
     *
     * @param numerada Indica si la lista debe ser numerada (true) o con viñetas (false).
     */
    public void imprimir(boolean numerada) {
        String[] opciones = elementos.toArray(new String[0]);
        if (numerada)
            ListaNumerada.imprimirListaNumerada(opciones);
        else
            ListaConVinetas.imprimirListaConVinetas(opciones);
    }
}