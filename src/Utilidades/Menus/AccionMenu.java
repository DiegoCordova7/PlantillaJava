package Utilidades.Menus;
/**
 * Interfaz funcional que representa una accion que puede ser ejecutada
 * al seleccionar una opcion en un menu.
 */
@FunctionalInterface
public interface AccionMenu {
    /**
     * Metodo que se ejecuta al seleccionar la accion correspondiente.
     * Implementaciones de este metodo deben definir el comportamiento
     * de la accion al ser invocada.
     */
    void ejecutar();
}
