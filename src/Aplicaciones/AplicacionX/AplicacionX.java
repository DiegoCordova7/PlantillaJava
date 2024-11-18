package Aplicaciones.AplicacionX;
import Aplicaciones.AplicacionBase;
import Utilidades.Estructuras.Tabla;

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
			* Metodo abstracto que debe ser implementado por las clases hijas para ejecutar la logica de la aplicacion.
			*/
		@Override
		protected void logicaApp() {
				String[] encabezadoTabla1 = {"Nombre", "Apellido", "Edad"};
				Tabla tabla1 = new Tabla(encabezadoTabla1);
				tabla1.agregarFila("Diego Emilio", "Cordova Ciscomani", 19);
				tabla1.agregarFila("Santiago", "Cordova Ciscomani", 15);
				tabla1.agregarColumna("Promedio");
				tabla1.actualizarDato(1, 4 , 10.0);
				tabla1.imprimir();
		}
}