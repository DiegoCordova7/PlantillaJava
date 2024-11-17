package Utilidades.BaseDeDatos;

import static Utilidades.Impresora.imprimirError;
/**
	* Enum que representa las restricciones SQL comunes que pueden
	* aplicarse a las columnas de una tabla en una base de datos.
	* Las restricciones incluyen: NOT NULL, UNIQUE, CHECK y FOREIGN KEY.
	*/
public enum RestriccionSQL {
		NOT_NULL("NOT NULL"),
		UNIQUE("UNIQUE"),
		CHECK("CHECK"),
		FOREIGN_KEY("FOREIGN KEY");

		private final String sql;
		/**
			* Constructor del enum que inicializa la restricción SQL con su representacion en cadena.
			*
			* @param sql La representación SQL de la restricción (por ejemplo, "NOT NULL").
			*/
		RestriccionSQL(String sql) {
				this.sql = sql;
		}
		/**
			* Genera la cadena SQL correspondiente para la restriccion, añadiendo parametros si es necesario.
			* Dependiendo del tipo de restriccion, se requiere uno o mas parametros.
			*
			* @param parametros Parametros adicionales necesarios para la restriccion (por ejemplo, columnas, tablas, etc.).
			* @return La cadena SQL correspondiente a la restriccion con los parametros proporcionados.
			*/
		public String getSQL(String... parametros) {
				return switch (this) {
						case CHECK -> {
								if (parametros.length == 0) {
										String error = "CHECK requiere al menos una condicion.";
										imprimirError("CHECK", error);
										yield String.format("Error: %s", error);
								}
								yield String.format("%s (%s)", sql, String.join(" AND ", parametros));
						}
						case FOREIGN_KEY -> {
								if (parametros.length != 3) {
										String error = "FOREIGN KEY requiere tres parametros: columna FK, tabla referenciada y columna referenciada.";
										imprimirError("FOREIGN_KEY", error);
										yield String.format("ERROR: %s", error);
								}
								yield String.format("%s (%s) REFERENCES %s (%s)", sql, parametros[0], parametros[1], parametros[2]);
						}
						case UNIQUE -> {
								if (parametros.length == 0) {
										String error = "UNIQUE requiere al menos un campo.";
										imprimirError("UNIQUE", error);
										yield String.format("Error: %s", error);
								}
								yield String.format("%s (%s)", sql, String.join(", ", parametros));
						}
						default -> sql;
				};
		}
}