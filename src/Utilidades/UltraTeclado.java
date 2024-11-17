package Utilidades;
import Utilidades.Registro.Log;
import java.math.BigDecimal;
import java.time.*;
import java.time.format.*;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;
import static Utilidades.Impresora.imprimirError;
/**
 * Clase UltraTeclado proporciona metodos para obtener datos de entrada del usuario
 * con validacion especifica de tipos de datos.
 */
public class UltraTeclado {
    private static final Scanner TECLADO = new Scanner(System.in);
    /**
     * Metodo ultraTeclado centraliza la captura de entradas desde la consola, realiza validaciones
     * sobre el tipo de dato esperado y maneja errores. También puede reimprimir contenido, como un menu,
     * si la entrada es invalida.
     *
     * @param <T>        El tipo de dato que se espera capturar (como Entero, Decimal, Texto, etc.).
     * @param ubicacion  Parte de la aplicacion donde se solicita el dato.
     * @param texto      El mensaje que se muestra al usuario para pedir la entrada.
     * @param tipoDato   El tipo de dato esperado, que define la validacion que se aplicara a la entrada.
     * @param esPregunta Indica si el texto debe terminar con un signo de interrogacion (?) o dos puntos (:).
     * @param reimprimir Una funcion que se ejecuta cuando hay un error para volver a mostrar algo,
     *                   como un menu u otro contexto, sino es un menu o algo asi pon: _ -> {}.
     * @return Devuelve el valor convertido a partir de la entrada del usuario, en el tipo de dato esperado.
     */
    @SuppressWarnings("unchecked")
    public static <T> T input(String ubicacion, String texto, TipoDato tipoDato, boolean esPregunta,
                              Consumer<Void> reimprimir) {
        Object respuesta = null;
        String entrada;
        boolean datoCorrecto = false;
        Class<?> clase = obtenerClase(tipoDato);
        if (clase == null) {
            imprimirError("TipoDatoInvalido", "Tipo de dato no reconocido.");
            return null;
        }
        String textoCompleto = getTextoCompleto(texto, esPregunta);
        do {
            entrada = capturarDato(textoCompleto);
            Log.info(String.format("Texto: %s| Ubicacion: %s | Tipo: %s | Dato proporcionado: %s",
                    textoCompleto, ubicacion, tipoDato, entrada));
            if (entrada.trim().isEmpty()) {
                imprimirError("EntradaVacia", "La entrada no puede estar vacia.");
                reimprimir.accept(null);
                continue;
            }
            if (validarEntrada(entrada, tipoDato)) {
                respuesta = convertirEntrada(entrada, clase);
                datoCorrecto = true;
            } else {
                String mensajeError = String.format("El tipo de dato no coincide, se espera un %s.", tipoDato);
                imprimirError("TipoDatoIncorrecto", mensajeError);
                reimprimir.accept(null);
            }
        } while (!datoCorrecto);
        return (T) respuesta;
    }
    /**
     * Combina el texto de solicitud con una indicacion adicional, si es necesario.
     *
     * @param texto        El texto a mostrar al usuario.
     * @param esPregunta   Indica si el texto se utiliza en un contexto de pregunta.
     * @return El texto completo a mostrar.
     */
    private static String getTextoCompleto(String texto, boolean esPregunta) {
        String complemento = (esPregunta) ? "?" : ":";
        return String.format("%s%s ", texto, complemento);
    }
    /**
     * Solicita un dato desde la consola, mostrando el mensaje correspondiente.
     *
     * @param textoCompleto El mensaje completo que se muestra antes de capturar el dato.
     * @return La entrada ingresada por el usuario como cadena de texto.
     */
    private static String capturarDato(String textoCompleto) {
        Impresora.pedirDato(textoCompleto);
        return TECLADO.nextLine();
    }
    /**
     * Determina la clase correspondiente al tipo de dato especificado.
     *
     * @param tipoDato El tipo de dato como cadena.
     * @return La clase correspondiente al tipo de dato, o null si no es reconocido.
     */
    private static Class<?> obtenerClase(TipoDato tipoDato) {
        return switch (tipoDato) {
            case BOOLEANO -> Boolean.class;
            case CARACTER -> Character.class;
            case TEXTO -> String.class;
            case FECHA -> LocalDate.class;
            case HORA -> LocalTime.class;
            case FECHA_Y_HORA -> LocalDateTime.class;
            case BYTE, BYTE_POSITIVO, BYTE_NEGATIVO -> Byte.class;
            case SHORT, SHORT_POSITIVO, SHORT_NEGATIVO -> Short.class;
            case ENTERO, ENTERO_POSITIVO, ENTERO_NEGATIVO -> Integer.class;
            case NUMERO_LARGO, NUMERO_LARGO_POSITIVO, NUMERO_LARGO_NEGATIVO -> Long.class;
            case DECIMAL, DECIMAL_POSITIVO, DECIMAL_NEGATIVO -> Double.class;
            case FLOTANTE, FLOTANTE_POSITIVO, FLOTANTE_NEGATIVO -> Float.class;
            case DECIMAL_GRANDE, DECIMAL_GRANDE_POSITIVO, DECIMAL_GRANDE_NEGATIVO -> BigDecimal.class;
        };
    }
    /**
     * Valida la entrada del usuario segun el tipo de dato esperado.
     *
     * @param entrada  La entrada del usuario como cadena de texto.
     * @param tipoDato El tipo de dato que se espera.
     * @return true si la entrada es valida para el tipo de dato, false en caso contrario.
     */
    private static boolean validarEntrada(String entrada, TipoDato tipoDato) {
        return switch (tipoDato) {
            case BOOLEANO -> {
                String entradaLower = entrada.trim().toLowerCase();
                yield Set.of("si", "true", "no", "false", "s", "n").contains(entradaLower);
            }
            case CARACTER -> entrada.length() == 1;
            case TEXTO -> true;
            case FECHA -> entrada.matches("\\d{2}/\\d{2}/\\d{4}");
            case HORA -> entrada.matches("\\d{2}:\\d{2}");
            case FECHA_Y_HORA -> entrada.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}");
            case BYTE, SHORT, ENTERO, NUMERO_LARGO -> entrada.matches("-?\\d+");
            case BYTE_POSITIVO, SHORT_POSITIVO, ENTERO_POSITIVO, NUMERO_LARGO_POSITIVO ->
                esPositivo(entrada, "^[1-9]\\d*$");
            case BYTE_NEGATIVO, SHORT_NEGATIVO, ENTERO_NEGATIVO, NUMERO_LARGO_NEGATIVO ->
                esNegativo(entrada, "-\\d+");
            case DECIMAL, FLOTANTE, DECIMAL_GRANDE -> entrada.matches("-?\\d*(\\.\\d+)?");
            case DECIMAL_POSITIVO, FLOTANTE_POSITIVO, DECIMAL_GRANDE_POSITIVO ->
                esPositivo(entrada, "^(0\\.\\d+|[1-9]\\d*(\\.\\d+)?)$");
            case DECIMAL_NEGATIVO, FLOTANTE_NEGATIVO, DECIMAL_GRANDE_NEGATIVO ->
                esNegativo(entrada, "^-0\\.\\d+|-[1-9]\\d*(\\.\\d+)?$");
        };
    }
    /**
     * Convierte la entrada del usuario en el tipo de dato esperado.
     *
     * @param entrada La entrada del usuario como cadena.
     * @param clase   La clase correspondiente al tipo de dato esperado.
     * @return El valor convertido al tipo correspondiente.
     */
    private static Object convertirEntrada(String entrada, Class<?> clase) {
        return switch (clase.getSimpleName()) {
            case "Boolean" -> {
                String entradaLower = entrada.trim().toLowerCase();
                if (Set.of("si", "true", "s").contains(entradaLower)) yield Boolean.TRUE;
                else yield Boolean.FALSE;
            }
            case "Character" -> entrada.charAt(0);
            case "String" -> entrada;
            case "LocalDate" -> LocalDate.parse(entrada, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            case "LocalTime" -> LocalTime.parse(entrada, DateTimeFormatter.ofPattern("HH:mm"));
            case "LocalDateTime" -> LocalDateTime.parse(entrada, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            case "Byte" -> Byte.parseByte(entrada);
            case "Short" -> Short.parseShort(entrada);
            case "Integer" -> Integer.parseInt(entrada);
            case "Long" -> Long.parseLong(entrada);
            case "Double" -> Double.parseDouble(entrada);
            case "Float" -> Float.parseFloat(entrada);
            case "BigDecimal" -> new BigDecimal(entrada);
            default -> null;
        };
    }
    /**
     * Verifica si un valor es positivo segun el patron.
     *
     * @param entrada La entrada proporcionada por el usuario.
     * @param patron  El patron de validacion.
     * @return true si la entrada es positiva, false en caso contrario.
     */
    private static boolean esPositivo(String entrada, String patron) {
        return entrada.matches(patron);
    }
    /**
     * Verifica si un valor es negativo segun el patron.
     *
     * @param entrada La entrada proporcionada por el usuario.
     * @param patron  El patron de validacion.
     * @return true si la entrada es negativa, false en caso contrario.
     */
    private static boolean esNegativo(String entrada, String patron) {
        return entrada.matches(patron);
    }
}