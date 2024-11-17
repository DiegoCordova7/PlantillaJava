package Aplicaciones.AplicacionX;
import Aplicaciones.AplicacionBase;
import Utilidades.BaseDeDatos.*;

import static Utilidades.UltraTeclado.input;

import Utilidades.TipoDato;
import java.sql.Connection;

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
				DbFunciones db = new DbFunciones();
				DbEsquemas esquemas = new DbEsquemas();
				ConstructorTablaSQL tablaEmpleados = new ConstructorTablaSQL("empleados");
				GestionTablasSQL tablas = new GestionTablasSQL();
				tablaEmpleados.agregarColumna("nombre",TipoDatoSQL.VARCHAR.longitudCadena(20),RestriccionSQL.NOT_NULL);
				tablaEmpleados.agregarColumna("pago",TipoDatoSQL.DECIMAL.longitudDecimal(7,2),RestriccionSQL.NOT_NULL);
				Connection conexion = db.conexionADB("java","postgres","Daigus7105CC");

				esquemas.crearEsquema(conexion,"esquemaejemplo1");
				db.listarEsquemas(conexion);
				String esquema = input("Logica", "Ingrese el esquema para listar tablas", TipoDato.TEXTO, false, _ -> {});
				db.listarTablas(conexion, esquema);

				tablaEmpleados.crearTabla(conexion,"esquemaejemplo1");

				tablas.renombrarTabla(conexion, "esquemaejemplo1", "empleados", "trabajadores");

				db.listarTablas(conexion, esquema);

				tablas.eliminarColumna(conexion, "esquemaejemplo1", "trabajadores", "pago");
				tablas.modificarColumna(conexion, "esquemaejemplo1", "trabajadores", "nombre",
								TipoDatoSQL.VARCHAR.longitudCadena(100), RestriccionSQL.NOT_NULL, RestriccionSQL.UNIQUE);
		}
}