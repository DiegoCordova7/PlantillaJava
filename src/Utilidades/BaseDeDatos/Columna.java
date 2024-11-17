package Utilidades.BaseDeDatos;
import java.util.ArrayList;
import java.util.List;
/**
	* Esta clase representa una columna en una tabla SQL.
	* Permite definir el nombre, tipo de datos, longitud y restricciones de la columna.
	*/
public class Columna {
		private String nombre;
		private String tipo;
		private Integer longitud;
		private List<String> restricciones;
		/**
			* Constructor que inicializa una columna con su nombre y tipo de datos.
			* La lista de restricciones se inicializa vacia por defecto.
			*
			* @param nombre El nombre de la columna.
			* @param tipo El tipo de datos de la columna (por ejemplo, VARCHAR, INTEGER).
			*/
		public Columna(String nombre, String tipo) {
				this.nombre = nombre;
				this.tipo = tipo;
				this.restricciones = new ArrayList<>();
		}
		/**
			* Establece la longitud de la columna, utilizada para tipos de datos como VARCHAR.
			*
			* @param longitud La longitud de la columna.
			*/
		public void setLongitud(Integer longitud) {
				this.longitud = longitud;
		}
		/**
			* Agrega una restriccion a la columna. Las restricciones son convertidas a su formato SQL
			* utilizando el metodo getSQL de la clase RestriccionSQL.
			*
			* @param restriccion La restriccion a agregar.
			* @param parametros Los parametros adicionales que pueden ser necesarios para
			*                   la restriccion (por ejemplo, valores de longitud o claves).
			*/
		public void agregarRestriccion(RestriccionSQL restriccion, String... parametros) {
				this.restricciones.add(restriccion.getSQL(parametros));
		}
		/**
			* Genera la representacion SQL de la columna, que incluye el nombre, tipo de datos
			* y todas las restricciones asociadas.
			*
			* @return Una cadena que representa la columna en formato SQL (por ejemplo, nombre tipo restricciones).
			*/
		public String toSQL() {
				StringBuilder sql = new StringBuilder(String.format("%s %s", nombre, tipo));
				if (longitud != null) {
						sql.append(String.format("(%d)", longitud));
				}
				for (String restriccion : restricciones) {
						sql.append(" ").append(restriccion);
				}
				return sql.toString();
		}
}