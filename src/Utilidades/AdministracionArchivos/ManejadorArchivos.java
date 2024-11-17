package Utilidades.AdministracionArchivos;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import static Utilidades.Impresora.*;
public abstract class ManejadorArchivos {
    protected String archivo;
    protected boolean seIntentaRehacer;
    /**
     * Constructor de la clase ManejadorArchivos.
     *
     * @param tipo El tipo de archivo (texto o binario).
     * @param archivo El nombre del archivo que se gestionara.
     * @throws NullPointerException Si el archivo es nulo.
     */
    public ManejadorArchivos(String tipo, String archivo) {
        if (archivo != null) this.archivo = String.format("archivos/%s/%s", tipo, archivo);
        else imprimirError("Archivo","El archivo no puede ser nulo");
    }
    /**
     * Asegura que la carpeta del archivo exista. Si no, la crea.
     */
    protected void asegurarCarpeta() {
        Path ruta = Paths.get(archivo).getParent();
        if (ruta != null && !Files.exists(ruta)) {
            try {
                Files.createDirectories(ruta);
                imprimir(String.format("Carpeta creada: %s", ruta));
            } catch (IOException e) {
                imprimirError("Carpeta", String.format("No se pudo crear la carpeta: %s", e.getMessage()));
            }
        }
    }
    /**
     * Asegura que el archivo exista, creandolo si es necesario.
     */
    public void asegurarExistencia() {
        asegurarCarpeta();
        if (!Files.exists(Paths.get(archivo))) {
            try {
                Files.createFile(Paths.get(archivo));
                imprimir(String.format("Archivo creado: %s", archivo));
            } catch (IOException e) {
                imprimirError("Archivo", String.format("Al asegurar la existencia del archivo: %s", e.getMessage()));
            }
        } else if (seIntentaRehacer) imprimir(String.format("El archivo ya existe: %s", archivo));
    }
    /**
     * Escribe contenido en el archivo. Debe ser implementado en las clases derivadas.
     *
     * @param contenido El contenido a escribir en el archivo.
     * @param opcion La opcion de escritura (agregar, sobrescribir, etc.).
     * @param rehacer Indica si se intenta rehacer el archivo.
     */
    @SuppressWarnings("unused")
    public abstract void escribir(String contenido, String opcion, boolean rehacer);
    /**
     * Lee el contenido del archivo. Debe ser implementado en las clases derivadas.
     */
    public abstract void leer();
    /**
     * Elimina el archivo si existe.
     */
    @SuppressWarnings("unused")
    public void eliminar() {
        asegurarExistencia();
        try {
            Files.deleteIfExists(Paths.get(archivo));
            imprimir(String.format("Archivo eliminado: %s", archivo));
        } catch (IOException e) {
            imprimirError("Archivo", String.format("No se pudo eliminar el archivo: %s", e.getMessage()));
        }
    }
    /**
     * Renombra el archivo a un nuevo nombre.
     *
     * @param nuevoNombre El nuevo nombre del archivo.
     */
    @SuppressWarnings("unused")
    public void renombrar(String nuevoNombre) {
        asegurarExistencia();
        try {
            Files.move(Paths.get(archivo), Paths.get(nuevoNombre));
            this.archivo = nuevoNombre;
            imprimir(String.format("Archivo renombrado a: %s", nuevoNombre));
        } catch (IOException e) {
            imprimirError("Archivo", String.format("No se pudo renombrar el archivo: %s", e.getMessage()));
        }
    }
    /**
     * Limpia el contenido del archivo.
     */
    @SuppressWarnings("unused")
    public void limpiar() {
        asegurarExistencia();
        try {
            Files.write(Paths.get(archivo), new byte[0]);
            imprimir(String.format("Contenido del archivo limpiado: %s", archivo));
        } catch (IOException e) {
            imprimirError("Archivo", String.format("No se pudo limpiar el archivo: %s", e.getMessage()));
        }
    }
    /**
     * Cuenta el numero de lineas en el archivo.
     *
     * @return Numero de lineas en el archivo.
     */
    @SuppressWarnings("unused")
    protected long contarLineas() {
        asegurarExistencia();
        try (Stream<String> stream = Files.lines(Paths.get(archivo))) {
            return stream.count();
        } catch (IOException e) {
            imprimirError("Archivo", String.format("No se pudo contar las lineas del archivo: %s", e.getMessage()));
            return 0;
        }
    }
    /**
     * Obtiene la ruta del archivo.
     *
     * @return Ruta del archivo.
     */
    public String getArchivo() {
        return this.archivo;
    }
    /**
     * Obtiene el nombre del archivo sin la ruta.
     *
     * @return Nombre del archivo.
     */
    @SuppressWarnings("unused")
    public String getNombreArchivo() {
        return Paths.get(archivo).getFileName().toString();
    }
}