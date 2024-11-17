package Utilidades.BaseDeDatos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static Utilidades.Impresora.imprimirAviso;
import static Utilidades.Impresora.imprimirError;

public class ConstructorTablaSQL {
		public DbFunciones dbf = new DbFunciones();
		private String nombreTabla;
		private List<Columna> columnas;

		public ConstructorTablaSQL(String nombreTabla) {
				this.nombreTabla = nombreTabla;
				this.columnas = new ArrayList<>();
		}
		/**
			* Agrega una columna a una tabla
			* @param nombre el nombre de la columna
			* @param tipo el tipo de dato, se usa el Enum TipoDatoSQL con sus metodos para longitud si lo amerita
			* @param restricciones las restricciones del campo, se usa el Enum RestriccionSQL para colocarlas
			*/
		public void agregarColumna(String nombre, String tipo, RestriccionSQL... restricciones) {
				Columna columna = new Columna(nombre, tipo);

				for (RestriccionSQL restriccion : restricciones) {
						if (restriccion == RestriccionSQL.CHECK) {
								imprimirError("Agregar_Columna", "CHECK requiere una expresion personalizada.");
								return;
						}
						if (restriccion == RestriccionSQL.FOREIGN_KEY) {
								imprimirError("Agregar_Columna", "FOREIGN KEY requiere una expresion personalizada.");
								return;
						}
						columna.agregarRestriccion(restriccion);
				}
				columnas.add(columna);
		}

		public void agregarColumnaConCheck(String nombre, String tipo, String... condicionesCheck) {
				Columna columna = new Columna(nombre, tipo);
				columna.agregarRestriccion(RestriccionSQL.CHECK, condicionesCheck);
				columnas.add(columna);
		}
		/**
			* Crea una tabla si no existe, despues de validar esquema y tabla.
			*
			* @param conexion     Conexion a la base de datos.
			* @param nombreEsquema Nombre del esquema donde se creara la tabla.
			*/
		public void crearTabla(Connection conexion, String nombreEsquema) {
				try {
						if (columnas.isEmpty()) {
								imprimirError("Creacion_Tabla", "No se pueden crear tablas sin columnas.");
								return;
						}
						String esquema = (nombreEsquema != null) ? nombreEsquema : "public";
						if (nombreEsquema != null) {
								if (dbf.verificarEsquema(conexion, esquema)) {
										imprimirError("Creacion_Tabla", String.format("El esquema '%s' no existe.", esquema));
										return;
								}
						}
						if (dbf.verificarTabla(conexion, esquema, nombreTabla)) {
								imprimirAviso("Creacion_Tabla", String.format("La tabla '%s' ya existe en el esquema '%s'.",
												nombreTabla, esquema));
								return;
						}
						String id = String.format("%s_id SERIAL NOT NULL PRIMARY KEY", nombreTabla);
						StringBuilder consulta = new StringBuilder(String.format("CREATE TABLE IF NOT EXISTS %s.%s (",
										esquema, nombreTabla));
						consulta.append(id).append(",");
						for (Columna columna : columnas)
								consulta.append(columna.toSQL()).append(",");
						consulta.setLength(consulta.length() - 1);
						consulta.append(");");
						try (Statement sentencia = conexion.createStatement()) {
								sentencia.executeUpdate(consulta.toString());
								imprimirAviso("Creacion_Tabla", String.format("Tabla '%s' creada con exito en el esquema '%s'.",
												nombreTabla, esquema));
						}
				} catch (Exception e) {
						imprimirError("Creacion_Tabla", String.format("Error al crear la tabla '%s': %s", nombreTabla, e.getMessage()));
				}
		}
}