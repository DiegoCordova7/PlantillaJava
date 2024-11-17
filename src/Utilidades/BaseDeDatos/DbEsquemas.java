package Utilidades.BaseDeDatos;
import java.sql.*;
import static Utilidades.Impresora.*;
/**
	* Clase DbEsquemas.
	* Proporciona metodos para la gestion de Esquemas.
	*/
public class DbEsquemas {
		/**
			* Crea un esquema en la base de datos si no existe previamente.
			*
			* @param conexion       Conexion a la base de datos.
			* @param nombreEsquema  Nombre del esquema a crear.
			*/
		public void crearEsquema(Connection conexion, String nombreEsquema) {
				if (verificarEsquema(conexion, nombreEsquema)) {
						imprimirAviso("Crear_Esquema", String.format("El esquema '%s' ya existe. Creacion abortada.", nombreEsquema));
						return;
				}
				String consulta = String.format("CREATE SCHEMA %s;", nombreEsquema);
				imprimirAviso("Crear_Esquema", String.format("El esquema '%s' ha sido creado.", nombreEsquema));
				ejecutarConsultaSimple(conexion, consulta);
		}
		/**
			* Renombra un esquema en la base de datos.
			*
			* @param conexion       Conexion a la base de datos.
			* @param esquemaActual  Nombre del esquema a renombrar.
			* @param nuevoEsquema   Nuevo nombre para el esquema.
			*/
		public void renombrarEsquema(Connection conexion, String esquemaActual, String nuevoEsquema) {
				if (!verificarEsquema(conexion, esquemaActual)) {
						imprimirError("Renombrar_Esquema", String.format("El esquema %s no existe.", esquemaActual));
						return;
				}
				if (verificarEsquema(conexion, nuevoEsquema)) {
						imprimirError("Renombrar_Esquema", String.format("El esquema %s ya existe.", nuevoEsquema));
						return;
				}
				String consulta = String.format("ALTER SCHEMA %s RENAME TO %s;", esquemaActual, nuevoEsquema);
				imprimirAviso("Renombrar_Esquema", String.format("El esquema '%s' ha sido renombrado a %s.",
								esquemaActual, nuevoEsquema));
				ejecutarConsultaSimple(conexion, consulta);
		}
		/**
			* Elimina un esquema en la base de datos.
			*
			* @param conexion       Conexion a la base de datos.
			* @param nombreEsquema  Nombre del esquema a eliminar.
			*/
		public void eliminarEsquema(Connection conexion, String nombreEsquema) {
				if (!verificarEsquema(conexion, nombreEsquema)) {
						imprimirError("Eliminar_Esquema", String.format("El esquema %s no existe.", nombreEsquema));
						return;
				}
				String consulta = String.format("DROP SCHEMA %s CASCADE;", nombreEsquema);
				imprimirAviso("Eliminar_Esquema", String.format("El esquema '%s' ha sido eliminado.", nombreEsquema));
				ejecutarConsultaSimple(conexion, consulta);
		}
		/**
			* Verifica si un esquema existe en la base de datos.
			*
			* @param conexion  Conexion a la base de datos.
			* @param esquema   Nombre del esquema a verificar.
			* @return true si el esquema existe, false si no existe.
			*/
		public boolean verificarEsquema(Connection conexion, String esquema) {
				String consulta = "SELECT schema_name FROM information_schema.schemata WHERE schema_name = ?";
				return !verificarExistencia(conexion, consulta, esquema);
		}
		/**
			* Realiza la verificacion de existencia de un esquema mediante una consulta SQL.
			*
			* @param conexion   Conexion a la base de datos.
			* @param consulta   Consulta SQL para verificar la existencia.
			* @param parametro  Parametro que sera utilizado en la consulta.
			* @return true si existe el esquema, false si no existe.
			*/
		private boolean verificarExistencia(Connection conexion, String consulta, String parametro) {
				try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
						stmt.setString(1, parametro);
						ResultSet rs = stmt.executeQuery();
						return rs.next();
				} catch (SQLException e) {
						imprimirError("Consulta_Verificacion", String.format("Al ejecutar la consulta de verificacion: %s", e.getMessage()));
						return false;
				}
		}
		/**
			* Ejecuta una consulta simple en la base de datos.
			*
			* @param conexion  Conexion a la base de datos.
			* @param consulta  Consulta SQL a ejecutar.
			*/
		private void ejecutarConsultaSimple(Connection conexion, String consulta) {
				try (Statement stmt = conexion.createStatement()) {
						stmt.executeUpdate(consulta);
				} catch (SQLException e) {
						imprimirError("Consulta_Simple", String.format("Ejecutando la consulta: %s", e.getMessage()));
				}
		}
}