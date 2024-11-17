package Utilidades.Menus;
/**
 * Clase abstracta que representa la base para un menu.
 * Proporciona un titulo y un metodo abstracto para mostrar el menu.
 */
public abstract class MenuBase {
    protected String titulo;
    /**
     * Constructor que inicializa el menu con un titulo.
     *
     * @param titulo El titulo del menu.
     */
    public MenuBase(String titulo) {
        this.titulo = titulo;
    }
    /**
     * Metodo abstracto que debe ser implementado por las subclases
     * para mostrar las opciones del menu.
     */
    public abstract void mostrar();
}