package Aplicaciones.AplicacionX;

import Utilidades.Menus.Menu;

import static Utilidades.Impresora.imprimir;

public class Menus {
		public static void menuPrincipal() {
				Menu menuPrincipal = new Menu("Menu Principal");
				menuPrincipal.agregarOpcion("Opcion 1", () -> imprimir("a"));
				menuPrincipal.agregarOpcion("Opcion 2", () -> imprimir("b"));
				menuPrincipal.agregarOpcion("Opcion 3", () -> imprimir("c"));
				menuPrincipal.agregarOpcion("Menu Aplicacion", () -> menuAplicacion());
				menuPrincipal.ejecutar();
		}
		public static void menuAplicacion() {
				Menu menuAplicacion = new Menu("Menu Aplicacion");
				menuAplicacion.agregarOpcion("Opcion 1", () -> imprimir("a1"));
				menuAplicacion.agregarOpcion("Opcion 2", () -> imprimir("b1"));
				menuAplicacion.agregarOpcion("Opcion 3", () -> imprimir("c1"));
				menuAplicacion.ejecutar();
		}
}
