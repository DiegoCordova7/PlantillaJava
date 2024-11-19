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

		}
}