package Utilidades.Menus;
import Utilidades.TipoDato;
import Utilidades.UltraTeclado;
import java.util.ArrayList;
import java.util.List;
import static Utilidades.Impresora.*;
/**
 * Clase que representa un menu interactivo en consola.
 * Permite agregar opciones y ejecutar acciones asociadas a cada opcion seleccionada.
 */
public final class Menu extends MenuBase implements AccionMenu {
    private final List<String> opciones;
    private final List<AccionMenu> acciones;
    /**
     * Constructor de la clase Menu.
     *
     * @param titulo Título del menu que se mostrara en la consola.
     */
    public Menu(String titulo) {
        super(titulo);
        this.opciones = new ArrayList<>();
        this.acciones = new ArrayList<>();
    }
    /**
     * Agrega una opcion al menu con su respectiva accion.
     *
     * @param opcion Nombre de la opcion a agregar.
     * @param accion Accion que se ejecutara al seleccionar la opcion.
     */
    public void agregarOpcion(String opcion, AccionMenu accion) {
        opciones.add(opcion);
        acciones.add(accion);
    }
    /**
     * Muestra el menu en la consola, incluyendo el titulo y las opciones disponibles.
     */
    @Override
    public void mostrar() {
        imprimirEncabezado(String.format("\n%s",titulo));
        for (int i = 0; i < opciones.size(); i++) imprimir(String.format("%d. %s", (i + 1), opciones.get(i)));
        imprimir(String.format("%d. Salir", (opciones.size() + 1)));
    }
    /**
     * Ejecuta el menu, permitiendo al usuario seleccionar una opcion.
     * Las acciones correspondientes a las opciones seleccionadas se ejecutan.
     */
    @SuppressWarnings("ConstantConditions")
    public void ejecutar() {
        int eleccion;
        do {
            mostrar();
            eleccion = UltraTeclado.input(titulo, "Seleccione la opcion", TipoDato.ENTERO_POSITIVO, false, _ -> mostrar());
            if (eleccion <= opciones.size()) acciones.get(eleccion - 1).ejecutar();
            else if (eleccion != opciones.size() + 1) imprimirError("Menu", "Opcion no valida. Intenta de nuevo.");
        } while (eleccion != opciones.size() + 1);
    }
}