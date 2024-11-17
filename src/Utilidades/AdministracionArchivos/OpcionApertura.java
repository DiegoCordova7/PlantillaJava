package Utilidades.AdministracionArchivos;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
/**
 * Enum que define las diferentes opciones de apertura de archivos disponibles
 * al utilizar clases que gestionan archivos en el sistema.
 * Estas opciones son utilizadas para determinar como se abrira o creara un archivo.
 */
public enum OpcionApertura {
    /**
     * Opcion para agregar contenido al final de un archivo existente.
     * Si el archivo no existe, se lanzara un error.
     */
    AGREGAR(StandardOpenOption.APPEND),
    /**
     * Opcion para sobrescribir el contenido de un archivo existente.
     * Si el archivo no existe, se lanzara un error.
     */
    SOBRESCRIBIR(StandardOpenOption.WRITE),
    /**
     * Opcion para crear un archivo si no existe.
     * Si el archivo ya existe, no se realizara ninguna accion.
     */
    CREAR(StandardOpenOption.CREATE),
    /**
     * Opcion para crear un archivo nuevo.
     * Si el archivo ya existe, se lanzara un error.
     */
    CREAR_NUEVO(StandardOpenOption.CREATE_NEW);
    private final OpenOption openOption;
    /**
     * Constructor del enum que asigna una opcion de apertura de archivo.
     *
     * @param openOption La opcion de apertura de archivo que se asociara con el enum.
     */
    OpcionApertura(OpenOption openOption) {
        this.openOption = openOption;
    }
    /**
     * Obtiene el valor asociado a la opcion de apertura de archivo.
     *
     * @return La opcion de apertura de archivo correspondiente.
     */
    @SuppressWarnings("unused")
    public OpenOption getOpenOption() {
        return openOption;
    }
}