package Utilidades.Impresiones;
import Utilidades.Impresora;
import Utilidades.Registro.Log;
import static Utilidades.Impresiones.Colores.colorear;
/**
 * La clase ImpresionAviso se encarga de manejar la impresion de mensajes de aviso
 * en la consola. Los mensajes se formatean con color amarillo para destacar los avisos.
 */
public class ImpresionAviso extends Impresora {
    /**
     * Imprime un mensaje de aviso en la consola.
     *
     * @param tipoAviso El tipo de aviso que se esta informando.
     * @param mensaje   El mensaje de aviso que se desea mostrar.
     */
    public static void imprimirAviso(String tipoAviso, String mensaje) {
        String mensajeCompleto = String.format("%s: %s", tipoAviso, mensaje);
        imprimir(colorear(Colores.AMARILLO, String.format("Aviso: %s", mensaje)));
        Log.advertencia(mensajeCompleto);
    }
}