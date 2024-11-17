package Utilidades.AdministracionArchivos;
import Utilidades.Impresora;
import static Utilidades.Impresora.*;
import java.io.*;
import java.nio.file.*;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
/**
 * La clase ManejadorArchivosBinarios extiende ManejadorArchivos y se encarga de manejar archivos binarios,
 * proporcionando métodos para escribir y leer datos binarios de diferentes tipos, como double, int y String.
 */
public class ManejadorArchivosBinarios extends ManejadorArchivos {
    /**
     * Constructor que inicializa la ruta del archivo binario y asegura que el archivo exista.
     *
     * @param archivo El nombre del archivo binario a manejar.
     */
    public ManejadorArchivosBinarios(String archivo) {
        super("binarios", archivo);
    }
    /**
     * Escribe un numero decimal (double) en el archivo binario.
     *
     * @param numero El numero a escribir.
     * @param opcion La opcion de escritura (agregar, sobrescribir, etc.).
     * @param rehacer Indica si se intenta rehacer el archivo.
     */
    @SuppressWarnings({"unused", "preview"})
    public void escribirDouble(double numero, String opcion, boolean rehacer) {
        this.seIntentaRehacer = rehacer;
        asegurarExistencia();
        try (DataOutputStream dos = new DataOutputStream(Files.newOutputStream(Paths.get(archivo), establecerOpcion(opcion)))) {
            dos.writeDouble(numero);
            imprimirAviso("Escritura De Archivo", String.format("Numero decimal escrito en el archivo binario: %s", numero));
        } catch (IOException e) {
            imprimirError("Archivo", String.format("No se logró escribir en el archivo binario: %s", e.getMessage()));
        }
    }
    /**
     * Escribe un numero entero (int) en el archivo binario.
     *
     * @param numero El número a escribir.
     * @param opcion La opción de escritura (agregar, sobrescribir, etc.).
     * @param rehacer Indica si se intenta rehacer el archivo.
     */
    @SuppressWarnings({"unused", "preview"})
    public void escribirInt(int numero, String opcion, boolean rehacer) {
        this.seIntentaRehacer = rehacer;
        asegurarExistencia();
        try (DataOutputStream dos = new DataOutputStream(Files.newOutputStream(Paths.get(archivo), establecerOpcion(opcion)))) {
            dos.writeInt(numero);
            imprimirAviso("Escritura De Archivo", String.format("Numero entero escrito en el archivo binario: %s", numero));
        } catch (IOException e) {
            imprimirError("Archivo", String.format("No se logro escribir en el archivo binario: %s", e.getMessage()));
        }
    }
    /**
     * Escribe un texto (String) en el archivo binario.
     *
     * @param texto El texto a escribir.
     * @param opcion La opcion de escritura (agregar, sobrescribir, etc.).
     * @param rehacer Indica si se intenta rehacer el archivo.
     */
    @SuppressWarnings({"unused", "preview"})
    public void escribirString(String texto, String opcion, boolean rehacer) {
        this.seIntentaRehacer = rehacer;
        asegurarExistencia();
        try (DataOutputStream dos = new DataOutputStream(Files.newOutputStream(Paths.get(archivo), establecerOpcion(opcion)))) {
            dos.writeUTF(texto);
            imprimirAviso("Escritura De Archivo", String.format("Texto escrito en el archivo binario: %s", texto));
        } catch (IOException e) {
            imprimirError("Archivo", String.format("No se logro escribir en el archivo binario: %s", e.getMessage()));
        }
    }
    /**
     * Metodo que debe ser implementado en las clases derivadas para escribir contenido en el archivo.
     *
     * @param contenido El contenido a escribir en el archivo.
     * @param opcion La opción de escritura (agregar, sobrescribir, etc.).
     * @param rehacer Indica si se intenta rehacer el archivo.
     */
    @Override
    public void escribir(String contenido, String opcion, boolean rehacer) {
        // Implementación especifica en las clases derivadas.
    }
    /**
     * Guarda los datos binarios de una operación, incluyendo los números, su longitud, el tipo de operación y el resultado.
     *
     * @param numeros El array de números involucrados en la operación.
     * @param longitud La longitud del array de numeros.
     * @param tipoOperacion El tipo de operacion realizada (por ejemplo, "suma", "multiplicacion").
     * @param resultado El resultado de la operacion.
     */
    public void guardarDatosBinarios(double[] numeros, int longitud, String tipoOperacion, double resultado) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("archivos/binario/resultadosOperacion.bin", true))) {
            out.writeObject(numeros);
            out.writeInt(longitud);
            out.writeObject(tipoOperacion);
            out.writeDouble(resultado);
            Impresora.imprimirAviso("Acción Realizada", "Datos guardados correctamente en el archivo binario.");
        } catch (IOException e) {
            Impresora.imprimirError("Manejador Archivos Binarios", String.format("Error al guardar en archivo binario: %s", e.getMessage()));
        }
    }
    /**
     * Lee el contenido del archivo binario y lo convierte en una lista de Strings.
     */
    @Override
    public void leer() {
        asegurarExistencia();
        List<String> lineas = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(Files.newInputStream(Paths.get(archivo)))) {
            while (dis.available() > 0) lineas.add(dis.readUTF());
        } catch (IOException e) {
            imprimirError("Archivo", String.format("No se logro leer el archivo binario: %s", e.getMessage()));
        }
    }
    /**
     * Establece la opción de apertura del archivo basada en la opcion proporcionada.
     *
     * @param opcion La opcion a establecer ("agregar", "sobrescribir", etc.).
     * @return La opción de apertura correspondiente.
     */
    private OpenOption establecerOpcion(String opcion) {
        switch (opcion.toLowerCase()) {
            case "agregar" -> { return StandardOpenOption.APPEND; }
            case "sobrescribir" -> { return StandardOpenOption.WRITE; }
            case "crear" -> { return StandardOpenOption.CREATE; }
            case "crear_nuevo" -> { return StandardOpenOption.CREATE_NEW; }
            default -> {
                imprimirError("Manejador Archivos Binarios", String.format("Opcion no valida: %s", opcion));
                return StandardOpenOption.WRITE;
            }
        }
    }
}