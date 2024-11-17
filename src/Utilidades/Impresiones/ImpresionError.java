package Utilidades.Impresiones;
import Utilidades.Impresora;
import Utilidades.Registro.Log;
import static Utilidades.Impresiones.Colores.colorear;
/**
 * La clase ImpresionError se encarga de manejar la impresion de mensajes de error
 * en la consola. Los mensajes se formatean con color rojo para destacar los errores.
 */
public class ImpresionError extends Impresora {
    /**
     * Imprime un mensaje de error en la consola.
     *
     * @param tipoError El tipo de error que se esta informando.
     * @param mensaje   El mensaje de error que se desea mostrar.
     */
    public static void imprimirError(String tipoError, String mensaje) {
        String mensajeCompleto = String.format("%s: %s", tipoError, mensaje);
        imprimir(colorear(Colores.ROJO, String.format("Error: %s", mensaje)));
        Log.error(mensajeCompleto);
    }
}