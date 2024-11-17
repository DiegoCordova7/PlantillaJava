import Aplicaciones.AplicacionX.AplicacionX;
/**
 * Metodo principal que inicia la/s aplicacion/es.
 *
 * @param args Argumentos de la linea de comandos.
 */
public static void main(@SuppressWarnings("unused") String[] args) {
    AplicacionX app = new AplicacionX("AplicacionX");
    app.inicializar();
    app.ejecutar();
    app.finalizar();
}