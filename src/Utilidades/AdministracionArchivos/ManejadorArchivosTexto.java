package Utilidades.AdministracionArchivos;
import Utilidades.Registro.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import static Utilidades.Impresora.imprimir;
import static Utilidades.Impresora.imprimirError;
public class ManejadorArchivosTexto extends ManejadorArchivos {
    public ManejadorArchivosTexto(String archivo) {
        super("texto", archivo);
    }
    /**
     * Escribe contenido en el archivo de texto.
     *
     * @param contenido El contenido a escribir.
     * @param opcion La opcion de escritura (agregar, sobrescribir, etc.).
     * @param rehacer Indica si se intenta rehacer el archivo.
     */
    @Override
    public void escribir(String contenido, String opcion, boolean rehacer) {
        this.seIntentaRehacer = rehacer;
        asegurarExistencia();
        try {
            Files.write(Paths.get(archivo), Collections.singletonList(contenido), establecerOpcion(opcion));
            String texto = String.format("Contenido escrito en el archivo: %s", archivo);
            imprimir(texto);
            Log.info(texto);
        } catch (IOException e) {
            imprimirError("Archivo", String.format("No se logro escribir en el archivo: %s", e.getMessage()));
        }
    }
    /**
     * Lee el contenido del archivo de texto.
     */
    @Override
    public void leer() {
        asegurarExistencia();
        try {
            Files.readAllLines(Paths.get(archivo));
        } catch (IOException e) {
            imprimirError("Archivo", String.format("No se logro leer el archivo: %s", e.getMessage()));
        }
    }
    /**
     * Establece la opcion de escritura en el archivo.
     *
     * @param opcion Opcion de escritura.
     * @return Opcion de escritura correspondiente.
     */
    private StandardOpenOption establecerOpcion(String opcion) {
        OpcionApertura opcionApertura;
        try {
            opcionApertura = OpcionApertura.valueOf(opcion.toUpperCase());
        } catch (IllegalArgumentException e) {
            imprimirError("Archivo", "Opcion no valida. Se usara la opcion por defecto: CREAR.");
            opcionApertura = OpcionApertura.CREAR;
        }
        return convertirAOpenOption(opcionApertura);
    }
    /**
     * Convierte el Enum OpcionApertura a un tipo OpenOption.
     *
     * @param opcionApertura El Enum que representa la opcion de apertura.
     * @return El tipo OpenOption correspondiente.
     */
    private StandardOpenOption convertirAOpenOption(OpcionApertura opcionApertura) {
        StandardOpenOption opcion;
        switch (opcionApertura) {
            case AGREGAR -> opcion = StandardOpenOption.APPEND;
            case SOBRESCRIBIR -> opcion = StandardOpenOption.TRUNCATE_EXISTING;
            case CREAR_NUEVO -> opcion = StandardOpenOption.CREATE_NEW;
            default -> opcion = StandardOpenOption.CREATE;
        }
        return opcion;
    }
}