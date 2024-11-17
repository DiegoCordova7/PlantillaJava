package Utilidades.Impresiones;
/**
 * Enum que gestiona los colores utilizados en la aplicacion.
 * <p>
 * Este enum proporciona los códigos de color ANSI para diferentes colores utilizados en la aplicación.
 * Los colores disponibles incluyen amarillo, verde, rojo, azul, gris y reset.
 * Cada color tiene un código ANSI asociado que se puede utilizar para aplicar colores a los textos en la consola.
 * </p>
 */
public enum Colores {
    /**
     * Avisos
     */
    AMARILLO("\u001B[33m"),
    /**
     * Reinicia el color a su valor por defecto
     */
    RESET("\u001B[0m"),
    /**
     * Pide un dato
     */
    VERDE("\u001B[32m"),
    /**
     * Error
     */
    ROJO("\u001B[31m"),
    /**
     * Titulo
     */
    AZUL("\u001B[34m"),
    /**
     * Estructura de una tabla
     */
    GRIS("\u001B[37m");
    private final String codigoColor;
    /**
     * Constructor del enum.
     * @param codigoColor El codigo de color ANSI asociado al color.
     */
    Colores(String codigoColor) {
        this.codigoColor = codigoColor;
    }
    /**
     * Obtiene el codigo de color ANSI asociado a este color.
     * @return El codigo de color ANSI como una cadena de texto.
     */
    public String getCodigoColor() {
        return codigoColor;
    }
    /**
     * Colorea un texto con el color especificado.
     * <p>
     * Este metodo aplica el color especificado al texto y luego restablece el color de la consola
     * al valor por defecto despues del texto.
     * </p>
     * @param color El color que se desea aplicar al texto. Debe ser uno de los valores del enum {@link Colores}.
     * @param texto El texto a colorear.
     * @return El texto coloreado seguido de un reset, que restablece el color por defecto en la consola.
     */
    public static String colorear(Colores color, String texto) {
        return color.getCodigoColor() + texto + RESET.getCodigoColor();
    }
}