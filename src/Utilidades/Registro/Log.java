package Utilidades.Registro;
import Utilidades.AdministracionArchivos.ManejadorArchivosLogs;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * La clase Log se encarga de manejar el registro de logs en un archivo.
 * Proporciona metodos para registrar mensajes de diferentes niveles (info, error, advertencia)
 * y guarda estos registros en un archivo de log especifico.
 * Esta clase es completamente estática para ser utilizada sin necesidad de instanciarla.
 */
public class Log {
    /**
     * Manejador de archivos que se utiliza para escribir en el archivo de log.
     * Este objeto es responsable de la creación y manipulación del archivo donde se registran los logs.
     */
    private static ManejadorArchivosLogs manejadorArchivos;

    /**
     * Formato de fecha utilizado para timestamp en los registros de log.
     * Se establece en el formato "yyyy-MM-dd HH:mm:ss" para facilitar la lectura.
     */
    private static DateTimeFormatter formatoFecha;
    private static final String ERROR_LOG_NO_INICIALIZADO =
            "El logger no ha sido inicializado. Llama a Log.inicializar(nombreArchivo).";
    /**
     * Inicializa el logger con el nombre del archivo de log.
     * Este metodo debe ser llamado antes de utilizar cualquier otro metodo de Log.
     *
     * @param nombreArchivo El nombre del archivo de log que se creara o usara si ya existe.
     */
    public static void inicializar(String nombreArchivo) {
        if (manejadorArchivos == null) {
            manejadorArchivos = new ManejadorArchivosLogs(nombreArchivo);
            formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        }
    }
    /**
     * Verifica si el logger ha sido inicializado correctamente.
     * Si no lo ha sido, lanza una excepción.
     */
    private static void verificarInicializacion() {
        if (manejadorArchivos == null)
            throw new IllegalStateException(ERROR_LOG_NO_INICIALIZADO);
    }
    /**
     * Registra un mensaje en el archivo de log con el nivel especificado.
     *
     * @param nivel   El nivel del mensaje de log (INFO, ERROR, WARNING).
     * @param mensaje El mensaje que se desea registrar en el log.
     */
    private static void registrar(String nivel, String mensaje) {
        String fecha = formatoFecha.format(LocalDateTime.now());
        String registro = String.format("[%s] [%s] %s", fecha, nivel, mensaje);
        manejadorArchivos.escribir(registro);
    }
    /**
     * Registra un mensaje de informacion en el archivo de log.
     *
     * @param mensaje El mensaje de informacion que se desea registrar.
     */
    public static void info(String mensaje) {
        verificarInicializacion();
        registrar("INFO", mensaje);
    }
    /**
     * Registra un mensaje de error en el archivo de log.
     *
     * @param mensaje El mensaje de error que se desea registrar.
     */
    public static void error(String mensaje) {
        verificarInicializacion();
        registrar("ERROR", mensaje);
    }
    /**
     * Registra un mensaje de advertencia en el archivo de log.
     *
     * @param mensaje El mensaje de advertencia que se desea registrar.
     */
    public static void advertencia(String mensaje) {
        verificarInicializacion();
        registrar("WARNING", mensaje);
    }
}