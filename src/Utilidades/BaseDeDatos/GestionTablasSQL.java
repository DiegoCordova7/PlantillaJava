package Utilidades.BaseDeDatos;
import Utilidades.Estructuras.Lista;
import java.sql.*;
import java.util.Arrays;
import java.util.Objects;

import static Utilidades.Impresora.*;
/**
	* Esta clase proporciona funcionalidades para gestionar tablas dentro de una base de datos SQL.
	* Permite realizar operaciones como verificar si una tabla existe, eliminar, renombrar y listar tablas.
	*/
public class GestionTablasSQL {
		private final DbFunciones dbf;
		/**
			* Constructor que inicializa el objeto DbFunciones para realizar consultas SQL.
			*/
		public GestionTablasSQL() {
				dbf = new DbFunciones();
		}
		/**
			* Verifica si una tabla existe en la base de datos en un esquema especifico.
			*
			* @param conexion La conexion a la base de datos.
			* @param nombreEsquema El nombre del esquema en el que buscar la tabla.
			* @param nombreTabla El nombre de la tabla a verificar.
			* @return true si la tabla existe, false en caso contrario.
			*/
		public boolean verificarTabla(Connection conexion, String nombreEsquema, String nombreTabla) {
				return dbf.verificarTabla(conexion, nombreEsquema, nombreTabla);
		}
		/**
			* Ejecuta una consulta SQL de tipo UPDATE o ALTER para realizar operaciones sobre tablas.
			* Este metodo es utilizado internamente para ejecutar las operaciones de eliminacion y renombrado.
			*
			* @param conexion La conexión a la base de datos.
			* @param consulta La consulta SQL a ejecutar.
			* @param operacion El tipo de operacion (por ejemplo, "Eliminar", "Renombrar").
			* @param nombreTabla El nombre de la tabla sobre la cual se realiza la operacion.
			* @return true si la operacion se realizo correctamente, false en caso de error.
			*/
		private boolean ejecutarConsultaSQL(Connection conexion, String consulta, String operacion, String nombreTabla) {
				try (Statement stmt = conexion.createStatement()) {
						stmt.executeUpdate(consulta);
						if (!Objects.equals(operacion, "Renombrar"))
								imprimirAviso(operacion, String.format("Tabla '%s' %s correctamente.", nombreTabla, operacion));
						return true;
				} catch (SQLException e) {
						imprimirError(operacion, String.format("Al %s la tabla '%s': %s", operacion, nombreTabla, e.getMessage()));
						return false;
				}
		}
		/**
			* Elimina una tabla de la base de datos.
			* La tabla se elimina de forma segura si existe, y se asegura que no haya dependencias mediante CASCADE.
			*
			* @param conexion La conexion a la base de datos.
			* @param nombreEsquema El nombre del esquema donde se encuentra la tabla.
			* @param nombreTabla El nombre de la tabla a eliminar.
			* @return true si la tabla fue eliminada correctamente, false en caso contrario.
			*/
		public boolean eliminarTabla(Connection conexion, String nombreEsquema, String nombreTabla) {
				String consulta = String.format("DROP TABLE IF EXISTS %s.%s CASCADE;", nombreEsquema, nombreTabla);
				return ejecutarConsultaSQL(conexion, consulta, "Eliminar", nombreTabla);
		}
		/**
			* Renombra una tabla en la base de datos.
			*
			* @param conexion      La conexion a la base de datos.
			* @param nombreEsquema El nombre del esquema donde se encuentra la tabla.
			* @param tablaActual   El nombre actual de la tabla.
			* @param nuevoNombre   El nuevo nombre para la tabla.
			*/
		public void renombrarTabla(Connection conexion, String nombreEsquema, String tablaActual, String nuevoNombre) {
				String consulta = String.format("ALTER TABLE %s.%s RENAME TO %s;", nombreEsquema, tablaActual, nuevoNombre);
				imprimirAviso("Tabla_Renombrada", String.format("Tabla '%s' renombrada a '%s' correctamente.",
								tablaActual, nombreEsquema));
				ejecutarConsultaSQL(conexion, consulta, "Renombrar", tablaActual);
		}
		/**
			* Lista todas las tablas presentes en un esquema especifico de la base de datos.
			* Si no se encuentran tablas, se imprime un mensaje de aviso.
			*
			* @param conexion La conexion a la base de datos.
			* @param nombreEsquema El nombre del esquema del cual listar las tablas.
			*/
		public void listarTablas(Connection conexion, String nombreEsquema) {
				String consulta = "SELECT table_name FROM information_schema.tables WHERE table_schema = ?";
				try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
						stmt.setString(1, nombreEsquema);
						try (ResultSet rs = stmt.executeQuery()) {
								Lista listadoTablas = new Lista();
								while (rs.next())
										listadoTablas.agregar(rs.getString("table_name"));
								if (listadoTablas.estaVacia())
										imprimirAviso("Listar_Tablas", String.format("No se encontraron tablas en el esquema '%s'.",
														nombreEsquema));
								else
										listadoTablas.imprimir(true);
						}
				} catch (SQLException e) {
						imprimirError("Listar_Tablas", String.format("Al listar tablas del esquema '%s': %s",
										nombreEsquema, e.getMessage()));
				}
		}
		/**
			* Agrega una columna a una tabla en la base de datos.
			*
			* @param conexion La conexion a la base de datos.
			* @param nombreEsquema El nombre del esquema donde se encuentra la tabla.
			* @param nombreTabla El nombre de la tabla a modificar.
			* @param nombreColumna El nombre de la columna a agregar.
			* @param tipo El tipo de dato de la nueva columna.
			* @param restricciones Las restricciones de la nueva columna.
			* @return true si la columna fue agregada correctamente, false en caso contrario.
			*/
		public boolean agregarColumna(Connection conexion, String nombreEsquema, String nombreTabla, String nombreColumna,
		                              String tipo, RestriccionSQL... restricciones) {
				String consulta = String.format("ALTER TABLE %s.%s ADD COLUMN %s %s %s;", nombreEsquema, nombreTabla, nombreColumna,
								tipo, Arrays.toString(restricciones));
				return ejecutarConsultaSQL(conexion, consulta, "Agregar columna", nombreTabla);
		}
		/**
			* Elimina una columna de una tabla en la base de datos.
			*
			* @param conexion      La conexion a la base de datos.
			* @param nombreEsquema El nombre del esquema donde se encuentra la tabla.
			* @param nombreTabla   El nombre de la tabla a modificar.
			* @param nombreColumna El nombre de la columna a eliminar.
			*/
		public void eliminarColumna(Connection conexion, String nombreEsquema, String nombreTabla, String nombreColumna) {
				String consulta = String.format("ALTER TABLE %s.%s DROP COLUMN %s;", nombreEsquema, nombreTabla, nombreColumna);
				ejecutarConsultaSQL(conexion, consulta, "Eliminar columna", nombreTabla);
		}
		/**
			* Modifica el tipo de datos de una columna en una tabla de la base de datos.
			*
			* @param conexion      La conexion a la base de datos.
			* @param nombreEsquema El nombre del esquema donde se encuentra la tabla.
			* @param nombreTabla   El nombre de la tabla a modificar.
			* @param nombreColumna El nombre de la columna a modificar.
			* @param nuevoTipo     El nuevo tipo de datos para la columna.
			*/
		public void modificarColumna(Connection conexion, String nombreEsquema, String nombreTabla, String nombreColumna,
		                             String nuevoTipo, RestriccionSQL... restricciones) {
				String consulta = String.format("ALTER TABLE %s.%s ALTER COLUMN %s TYPE %s;", nombreEsquema, nombreTabla,
								nombreColumna, nuevoTipo);
				ejecutarConsultaSQL(conexion, consulta, "Modificar columna", nombreTabla);
		}
}