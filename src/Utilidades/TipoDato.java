package Utilidades;
/**
 * Enum que define los diferentes tipos de datos que pueden ser utilizados
 * en el sistema. Este enum categoriza tipos de datos en base a sus caracteristicas,
 * como si son enteros, decimales, fechas, entre otros, y sus posibles valores
 * positivos o negativos.
 */
public enum TipoDato {
    /**
     * Tipo de dato booleano, que puede tener los valores `si`, `true`, o `s` para `true`,
     * y `no`, `false`, o `n` para `false`.
     */
    BOOLEANO,
    /**
     * Tipo de dato para caracteres individuales, como letras o simbolos.
     */
    CARACTER,
    /**
     * Tipo de dato para texto, que puede ser una cadena de caracteres.
     */
    TEXTO,
    /**
     * Tipo de dato para fechas, en formato de solo fecha (dia, mes, ano).
     */
    FECHA,
    /**
     * Tipo de dato para horas, en formato de solo hora.
     */
    HORA,
    /**
     * Tipo de dato para fecha y hora combinados en un solo valor.
     */
    FECHA_Y_HORA,
    /**
     * Tipo de dato para numeros enteros pequenos, con un rango de -128 a 127.
     */
    BYTE,
    /**
     * Tipo de dato para numeros enteros pequenos, pero solo positivos (0 a 255).
     */
    BYTE_POSITIVO,
    /**
     * Tipo de dato para numeros enteros pequenos negativos, en el rango de -128 a -1.
     */
    BYTE_NEGATIVO,
    /**
     * Tipo de dato para numeros enteros medianos, con un rango de -32,768 a 32,767.
     */
    SHORT,
    /**
     * Tipo de dato para numeros enteros medianos, pero solo positivos (0 a 65,535).
     */
    SHORT_POSITIVO,
    /**
     * Tipo de dato para numeros enteros medianos negativos, en el rango de -32,768 a -1.
     */
    SHORT_NEGATIVO,
    /**
     * Tipo de dato para numeros enteros grandes, con un rango de -2,147,483,648 a 2,147,483,647.
     */
    ENTERO,
    /**
     * Tipo de dato para numeros enteros grandes, pero solo positivos (0 a 4,294,967,295).
     */
    ENTERO_POSITIVO,
    /**
     * Tipo de dato para numeros enteros grandes negativos, en el rango de -2,147,483,648 a -1.
     */
    ENTERO_NEGATIVO,
    /**
     * Tipo de dato para numeros enteros muy grandes, con un rango mayor a los enteros tradicionales.
     */
    NUMERO_LARGO,
    /**
     * Tipo de dato para numeros enteros muy grandes, pero solo positivos.
     */
    NUMERO_LARGO_POSITIVO,
    /**
     * Tipo de dato para numeros enteros muy grandes negativos.
     */
    NUMERO_LARGO_NEGATIVO,
    /**
     * Tipo de dato para numeros decimales con punto flotante, con mayor precision que los enteros.
     */
    DECIMAL,
    /**
     * Tipo de dato para numeros decimales positivos.
     */
    DECIMAL_POSITIVO,
    /**
     * Tipo de dato para numeros decimales negativos.
     */
    DECIMAL_NEGATIVO,
    /**
     * Tipo de dato para numeros con coma flotante de precision simple.
     */
    FLOTANTE,
    /**
     * Tipo de dato para numeros de punto flotante positivos.
     */
    FLOTANTE_POSITIVO,
    /**
     * Tipo de dato para numeros de punto flotante negativos.
     */
    FLOTANTE_NEGATIVO,
    /**
     * Tipo de dato para numeros decimales de gran precision.
     */
    DECIMAL_GRANDE,
    /**
     * Tipo de dato para numeros decimales de gran precision positivos.
     */
    DECIMAL_GRANDE_POSITIVO,
    /**
     * Tipo de dato para numeros decimales de gran precision negativos.
     */
    DECIMAL_GRANDE_NEGATIVO
}