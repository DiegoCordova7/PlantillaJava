package Utilidades.AdministracionArchivos;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import static Utilidades.Impresora.imprimirError;
/**
 * La clase ManejadorArchivosLogs se encarga de la creacion y manejo de archivos de log.
 * Proporciona metodos para escribir mensajes en un archivo de log y garantiza que el archivo
 * exista antes de intentar escribir en el.
 */
public class ManejadorArchivosLogs {
    /**
     * Ruta del archivo de log donde se registraran los mensajes.
     */
    private final String rutaArchivo;
    /**
     * Constructor que inicializa la ruta del archivo de log utilizando el nombre proporcionado.
     * También crea el archivo si no existe.
     *
     * @param nombreArchivo El nombre del archivo de log que se creara.
     *                      El archivo se ubicara en la carpeta "archivos/logs/".
     */
    public ManejadorArchivosLogs(String nombreArchivo) {
        this.rutaArchivo = String.format("archivos/logs/%s.log", nombreArchivo);
        crearArchivoSiNoExiste();
    }
    /**
     * Crea el directorio para el archivo de log y el archivo si este no existen.
     * Si ocurre un error durante la creación, se imprime un mensaje de error.
     */
    private void crearArchivoSiNoExiste() {
        try {
            Files.createDirectories(Paths.get("archivos/logs/"));
            Files.write(Paths.get(rutaArchivo), new byte[0], StandardOpenOption.CREATE);
        } catch (IOException e) {
            imprimirError("CreacionLog", String.format("Al crear el archivo de log: %s", e.getMessage()));
        }
    }
    /**
     * Escribe un mensaje en el archivo de log. Agrega un salto de linea al final del mensaje.
     * Si ocurre un error durante la escritura, se imprime un mensaje de error.
     *
     * @param mensaje El mensaje que se desea registrar en el archivo de log.
     */
    public void escribir(String mensaje) {
        try {
            Files.write(Paths.get(rutaArchivo), ("%s\n".formatted(mensaje)).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            imprimirError("EscrituraLog", String.format("Al escribir en el archivo de log: %s", e.getMessage()));
        }
    }
}