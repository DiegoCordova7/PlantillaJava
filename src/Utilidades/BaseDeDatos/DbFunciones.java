package Utilidades.BaseDeDatos;
import Utilidades.Estructuras.Lista;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static Utilidades.Impresora.*;
/**
	* Clase DbFunciones.
	* Proporciona metodos para interactuar con bases de datos PostgreSQL, incluyendo
	* la gestion de conexiones, esquemas y tablas.
	*/
public class DbFunciones {
		/**
			* Establece una conexion con la base de datos PostgreSQL utilizando los parametros
			* predeterminados de host y puerto.
			*
			* @param nombreDb Nombre de la base de datos a la que se desea conectar.
			* @param usuario Nombre de usuario para la conexion.
			* @param contrasena Contraseña del usuario.
			* @return Un objeto Connection si la conexion es exitosa; de lo contrario, null.
			*/
		public Connection conexionADB(String nombreDb, String usuario, String contrasena) {
				return conexionADB(nombreDb, usuario, contrasena, "localhost");
		}
		/**
			* Establece una conexion con la base de datos PostgreSQL con parametros personalizados.
			*
			* @param nombreDb   Nombre de la base de datos.
			* @param usuario    Nombre de usuario.
			* @param contrasena Contraseña del usuario.
			* @param host       Direccion del servidor.
			* @return Un objeto Connection si la conexion es exitosa; de lo contrario, null.
			*/
		private Connection conexionADB(String nombreDb, String usuario, String contrasena, String host) {
				String partePrograma = "Conexion_DB";
				Connection conexion = null;
				try {
						Class.forName("org.postgresql.Driver");
						String url = String.format("jdbc:postgresql://%s:%s/%s", host, "5432", nombreDb);
						conexion = DriverManager.getConnection(url, usuario, contrasena);
						if (conexion == null)
								imprimirError(partePrograma, "Conexion fallida");
						else
								imprimirAviso(partePrograma, "Conexion exitosa");
				} catch (Exception e) {
						imprimirError(partePrograma, e.getMessage());
				}
				return conexion;
		}
		/**
			* Lista todos los esquemas en la base de datos, excluyendo los predeterminados de PostgreSQL.
			*
			* @param conexion Objeto Connection activo.
			*/
		public void listarEsquemas(Connection conexion) {
				if (conexion == null) {
						imprimirError("Listar_Esquemas", "La conexion es nula.");
						return;
				}
				String consulta = "SELECT nspname FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' " +
								"AND nspname != 'information_schema';";
				List<String> listaEsquemas = ejecutarConsultaSimple(conexion, consulta, "nspname");
				Lista lista = new Lista();
				assert listaEsquemas != null;
				for (String listaEsquema : listaEsquemas)
						lista.agregar(listaEsquema);
				lista.imprimir(false);
		}
		/**
			* Lista todas las tablas de un esquema especifico.
			*
			* @param conexion Objeto Connection activo.
			* @param esquema Nombre del esquema.
			*/
		public void listarTablas(Connection conexion, String esquema) {
				if (conexion == null) {
						imprimirError("Listar_Tablas", "La conexion es nula.");
						return;
				}
				String consulta = "SELECT tablename FROM pg_tables WHERE schemaname = ?;";
				List<String> listaTablas = ejecutarConsultaSimple(conexion, consulta, "tablename");
				if (listaTablas == null)
						return;
				Lista lista = new Lista();
				for (String listaTabla : listaTablas)
						lista.agregar(listaTabla);
				imprimirEncabezado(String.format("Tablas Del Esquema %s:", esquema));
				lista.imprimir(false);
		}
		/**
			* Ejecuta una consulta SQL que retorna múltiples filas y columnas, extrayendo una columna especifica.
			*
			* @param conexion Objeto Connection activo.
			* @param consulta Consulta SQL a ejecutar.
			* @param columna Nombre de la columna a extraer.
			* @return Una lista con los valores de la columna especificada.
			*/
		private List<String> ejecutarConsultaSimple(Connection conexion, String consulta, String columna) {
				if (!PrevencionInyeccionesSQL.esConsultaSegura(consulta))
						return null;
				List<String> resultados = new ArrayList<>();
				try (Statement sentencia = conexion.createStatement();
				     ResultSet resultado = sentencia.executeQuery(consulta)) {
						while (resultado.next())
								resultados.add(resultado.getString(columna));
				} catch (Exception e) {
						imprimirError("Consulta_Simple", String.format("Error ejecutando la consulta: %s", e.getMessage()));
				}
				return resultados;
		}
		/**
			* Ejecuta una consulta SQL que no retorna resultados, como CREATE, DROP o SET.
			*
			* @param conexion Objeto Connection activo.
			* @param consulta Consulta SQL a ejecutar.
			* @param parametros Valores para los parámetros en la consulta.
			* @return true si la consulta fue ejecutada con exito; de lo contrario, false.
			*/
		private boolean ejecutarConsultaSimple(Connection conexion, String consulta, String... parametros) {
				if (!PrevencionInyeccionesSQL.esConsultaSegura(consulta))
						return false;
				try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
						for (int i = 0; i < parametros.length; i++)
								stmt.setString(i + 1, parametros[i]);
						stmt.executeUpdate();
						return true;
				} catch (Exception e) {
						imprimirError("Consulta_Simple", String.format("Error ejecutando la consulta: %s", e.getMessage()));
						return false;
				}
		}
		/**
			* Verifica si un esquema existe en la base de datos.
			*
			* @param conexion Objeto Connection activo.
			* @param esquema Nombre del esquema a verificar.
			* @return true si el esquema existe; de lo contrario, false.
			*/
		boolean verificarEsquema(Connection conexion, String esquema) {
				String consulta = "SELECT schema_name FROM information_schema.schemata WHERE schema_name = ?";
				return verificarExistencia(conexion, consulta, esquema);
		}
		/**
			* Verifica si una tabla existe dentro de un esquema especifico.
			*
			* @param conexion Objeto Connection activo.
			* @param esquema Nombre del esquema.
			* @param nombreTabla Nombre de la tabla.
			* @return true si la tabla existe; de lo contrario, false.
			*/
		boolean verificarTabla(Connection conexion, String esquema, String nombreTabla) {
				String consulta = "SELECT table_name FROM information_schema.tables WHERE table_schema = ? AND table_name = ?";
				return verificarExistencia(conexion, consulta, esquema, nombreTabla);
		}
		/**
			* Verifica si una consulta SQL encuentra resultados, utilizando parámetros dinámicos.
			*
			* @param conexion Objeto Connection activo.
			* @param consulta Consulta SQL parametrizada.
			* @param parametros Valores para los parametros en la consulta.
			* @return true si existen resultados; de lo contrario, false.
			*/
		private boolean verificarExistencia(Connection conexion, String consulta, String... parametros) {
				if (!PrevencionInyeccionesSQL.esConsultaSegura(consulta))
						return false;
				try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
						for (int i = 0; i < parametros.length; i++)
								stmt.setString(i + 1, parametros[i]);
						try (ResultSet rs = stmt.executeQuery()) {
								return rs.next();
						}
				} catch (SQLException e) {
						imprimirError("Verificacion_Existencia", String.format("Error en la consulta '%s': %s", consulta, e.getMessage()));
						return false;
				}
		}
}