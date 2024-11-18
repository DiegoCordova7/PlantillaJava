Aplicación X - Documentación de Uso Algun dia
Descripción
La Aplicación X es una plantilla modular en Java para aplicaciones de consola. Su estructura facilita la creación y gestión de menús, tablas, listas y la captura de datos de usuario de manera eficiente. Este README proporciona una guía para configurar el proyecto, iniciar la aplicación y hacer uso de sus principales funcionalidades.

Estructura del Proyecto
Para comenzar un proyecto siguiendo esta plantilla, se recomienda la siguiente organización de archivos y packages:

/Aplicaciones
 └── /AplicacionX
     ├── AplicacionX.java        // Clase de la aplicación que define su lógica principal
     └── Menus.java              // Clase que contiene los menús de la aplicación
/MainApp.java                    // Clase principal que inicia la aplicación
/Utilidades
 ├── Menus
 ├── Estructuras
 ├── Impresora
 ├── UltraTeclado
 └── TipoDato
MainApp.java: Clase principal que inicia y finaliza la aplicación (fuera del package de la aplicación).
AplicacionX.java: Clase en el package de la aplicación que gestiona la lógica de la misma y organiza sus métodos.
Menus.java: Clase específica del package de la aplicación donde se definen los menús.
Iniciar un Proyecto
Para iniciar un proyecto utilizando esta estructura, crea una clase MainApp.java fuera del package de la aplicación, que sea responsable de instanciar y ejecutar la aplicación.

Ejemplo de Clase Principal (MainApp.java)

public class MainApp {
    public static void main(@SuppressWarnings("unused") String[] args) {
        AplicacionX app = new AplicacionX("AplicacionX");
        app.inicializar();
        app.ejecutar();
        app.finalizar();
    }
}
Definir la Lógica de la Aplicación (AplicacionX.java)
Dentro del package Aplicaciones.AplicacionX, define la clase AplicacionX para manejar la lógica de la aplicación y llamar a los menús.

Ejemplo de Lógica de la Aplicación (AplicacionX.java)

package Aplicaciones.AplicacionX;

import Aplicaciones.AplicacionBase;
import Utilidades.Estructuras.Lista;
import Utilidades.Estructuras.Tabla;
import Utilidades.Impresora;
import Utilidades.UltraTeclado;
import Utilidades.TipoDato;

public class AplicacionX extends AplicacionBase {
    /**
     * Constructor que inicializa la aplicacion con un log especifico.
     *
     * @param nombreArchivo El nombre del archivo de log para esta aplicacion.
     */
    public AplicacionX(String nombreArchivo) {
        super(nombreArchivo);
    }

    /**
     * Metodo abstracto que implementa la logica de la aplicacion.
     */
    @Override
    protected void logicaApp() {
        Impresora.imprimirEncabezado("Bienvenido a Aplicacion X");
        Menus.menuPrincipal();

        // Ejemplo de uso de Tabla
        Tabla tabla = new Tabla(new String[]{"Nombre", "Edad"});
        tabla.agregarFila("Diego", "19");
        tabla.imprimir();

        // Ejemplo de uso de Lista
        Lista lista = new Lista();
        lista.agregar("Item 1", "Item 2", "Item 3");
        lista.imprimir(true);

        // Ejemplo de entrada de usuario
        int numero = UltraTeclado.input("AplicacionX", "Ingresa un número entero positivo", TipoDato.ENTERO_POSITIVO, false, _ -> {});
        Impresora.imprimir("Número ingresado: " + numero);
    }
}
Configuración de Menús en una Clase Específica (Menus.java)
Para gestionar los menús de la aplicación, crea una clase Menus.java dentro del package Aplicaciones.AplicacionX. Define cada menú en métodos separados para una organización clara y modular.

Ejemplo de Menús (Menus.java)
package Aplicaciones.AplicacionX;

import Utilidades.Menus.Menu;
import static Utilidades.Impresora.imprimir;

public class Menus {
    public static void menuPrincipal() {
        Menu menuPrincipal = new Menu("Menú Principal");
        menuPrincipal.agregarOpcion("Opción 1", () -> imprimir("Acción de Opción 1"));
        menuPrincipal.agregarOpcion("Opción 2", () -> imprimir("Acción de Opción 2"));
        menuPrincipal.agregarOpcion("Ir al Submenú", Menus::menuAplicacion);
        menuPrincipal.ejecutar();
    }

    public static void menuAplicacion() {
        Menu menuAplicacion = new Menu("Submenú de Aplicación");
        menuAplicacion.agregarOpcion("Opción A", () -> imprimir("Acción de Opción A"));
        menuAplicacion.agregarOpcion("Opción B", () -> imprimir("Acción de Opción B"));
        menuAplicacion.ejecutar();
    }
}
Principales Funcionalidades de la Aplicación
1. Gestión de Menús
La clase Menu permite definir menús interactivos de consola. Cada menú puede contener opciones que llaman a acciones específicas o submenús.

2. Manipulación de Tablas
La clase Tabla permite crear y gestionar tablas dinámicas en la consola, con métodos para agregar, actualizar y eliminar datos.

Tabla tabla = new Tabla(new String[]{"Columna1", "Columna2"});
tabla.agregarFila("Valor1", "Valor2");
tabla.imprimir();
3. Gestión de Listas
La clase Lista permite crear listas que se pueden imprimir con o sin numeración.

Lista lista = new Lista();
lista.agregar("Elemento1", "Elemento2", "Elemento3");
lista.imprimir(true); // Imprime lista numerada
4. Captura y Validación de Datos del Usuario
Utiliza la clase UltraTeclado para capturar y validar entradas del usuario de manera sencilla.

int numero = UltraTeclado.input("AplicacionX", "Ingrese un número", TipoDato.ENTERO_POSITIVO, false, _ -> {});
Impresora.imprimir("Número ingresado: " + numero);
Este README guía al usuario en la configuración y uso de la plantilla para desarrollar una aplicación modular en Java.

SIGUE Y PULE MAS ESTO
TEMAS QUE FALTAN:
-ARCHIVOS

TEMAS QUE FALTAN POR PULIR:
-TODOS