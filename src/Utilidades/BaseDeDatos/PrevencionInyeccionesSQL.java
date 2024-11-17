package Utilidades.BaseDeDatos;

import java.util.Arrays;
import java.util.List;

public class PrevencionInyeccionesSQL {
		private static final List<String> palabrasProhibidas = Arrays.asList(
						"SELECT", "INSERT", "UPDATE", "DELETE", "DROP", "ALTER", "--", ";", "/*", "*/", "xp_", "exec", "union"
		);
		/**
			* Metodo para verificar si una consulta contiene alguna palabra prohibida
			* @param consulta La consulta SQL a verificar
			* @return Verdadero si la consulta es segura, falso si contiene palabras prohibidas
			*/
		public static boolean esConsultaSegura(String consulta) {
				if (consulta == null || consulta.trim().isEmpty()) {
						return true;
				}
				String consultaEnMayusculas = consulta.toUpperCase();
				for (String palabra : palabrasProhibidas) {
						if (consultaEnMayusculas.contains(palabra)) {
								return false;
						}
				}
				return true;
		}
}
