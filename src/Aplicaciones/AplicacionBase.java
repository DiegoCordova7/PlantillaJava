package Aplicaciones;
import Utilidades.Registro.Log;
/**
 * Clase abstracta que representa la base para todas las aplicaciones.
 * Proporciona metodos comunes para el registro, la inicializacion, ejecucion y finalizacion.
 */
public abstract class AplicacionBase {
    protected String nombreApp;
    private boolean estaIniciada = false;
    private boolean estaEjecutada = false;
    private static AplicacionBase appEnEjecucion = null;
    /**
     * Constructor que inicializa la aplicacion con un log especifico.
     *
     * @param nombreArchivo El nombre del archivo de log para esta aplicacion.
     */
    public AplicacionBase(String nombreArchivo) {
        if (nombreArchivo == null || nombreArchivo.isEmpty())
            throw new IllegalArgumentException("El nombre del archivo de log no puede ser nulo o vacio.");
        this.nombreApp = nombreArchivo;
        Log.inicializar(nombreArchivo);
    }
    /**
     * Metodo abstracto que debe ser implementado por las clases hijas para ejecutar la logica de la aplicacion.
     */
    protected abstract void logicaApp();
    /**
     * Inicializa la aplicacion y registra el inicio en el log.
     * Verifica si hay otra aplicacion en ejecucion.
     */
    @SuppressWarnings("preview")
    public void inicializar() {
        if (appEnEjecucion != null) {
            Log.error(String.format("No se puede iniciar %s mientras %s esta en ejecucion.", nombreApp,
                    appEnEjecucion.nombreApp));
            return;
        }
        estaIniciada = true;
        estaEjecutada = false;
        appEnEjecucion = this;
        Log.info(String.format("La aplicacion %s ha iniciado correctamente.", nombreApp));
    }
    /**
     * Ejecuta la logica de la aplicacion solo si esta inicializada.
     */
    @SuppressWarnings("preview")
    public void ejecutar() {
        if (!estaIniciada) {
            Log.error(String.format("No se puede ejecutar la aplicacion %s sin haber sido inicializada.", nombreApp));
            return;
        }
        if (estaEjecutada) {
            Log.error(String.format("La aplicacion %s ya se encuentra en ejecucion.", nombreApp));
            return;
        }
        estaEjecutada = true;
        Log.info(String.format("La aplicacion %s ha comenzado su ejecucion.", nombreApp));
        logicaApp();
    }
    /**
     * Finaliza la aplicacion solo si ha sido ejecutada y registra la finalizacion en el log.
     */
    @SuppressWarnings("preview")
    public void finalizar() {
        if (!estaEjecutada) {
            Log.error(String.format("No se puede finalizar la aplicacion %s sin haber sido ejecutada.", nombreApp));
            return;
        }
        estaIniciada = false;
        appEnEjecucion = null;
        Log.info(String.format("La aplicacion %s ha finalizado correctamente.", nombreApp));
    }
}