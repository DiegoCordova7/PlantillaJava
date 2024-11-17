package Utilidades.BaseDeDatos;

public enum TipoDatoSQL {
		CHAR("CHAR"),
		VARCHAR("VARCHAR"),
		INTEGER("INTEGER"),
		BOOLEAN("BOOLEAN"),
		SERIAL("SERIAL"),
		TEXT("TEXT"),
		DOUBLE("DOUBLE PRECISION"),
		SMALLINT("SMALLINT"),
		BIGINT("BIGINT"),
		NUMERIC("NUMERIC"),
		DECIMAL("DECIMAL"),
		REAL("REAL"),
		DATE("DATE"),
		TIME("TIME"),
		TIMESTAMP("TIMESTAMP"),
		TIMESTAMPTZ("TIMESTAMPTZ"),
		INTERVAL("INTERVAL"),
		BYTEA("BYTEA"),
		UUID("UUID"),
		JSON("JSON"),
		JSONB("JSONB"),
		MONEY("MONEY"),
		CIDR("CIDR"),
		INET("INET"),
		MACADDR("MACADDR"),
		POINT("POINT"),
		LINE("LINE"),
		LSEG("LSEG"),
		BOX("BOX"),
		PATH("PATH"),
		POLYGON("POLYGON"),
		CIRCLE("CIRCLE");

		private final String tipoSQL;

		TipoDatoSQL(String tipoSQL) {
				this.tipoSQL = tipoSQL;
		}

		public String getTipoSQL() {
				return tipoSQL;
		}

		/**
			* Genera el tipo SQL con longitud (si aplica).
			*
			* @param longitud Longitud para tipos que lo soporten (ejemplo VARCHAR(100)).
			* @return Tipo SQL como cadena.
			*/
		public String longitudCadena(int longitud) {
				if ((this == VARCHAR || this == CHAR) && longitud > 0)
						return String.format("%s(%s)",tipoSQL,longitud);
				return tipoSQL;
		}

		/**
			* Genera el tipo SQL con longitud (si aplica).
			*
			* @param terminosGlobales numero de termimos totales.
			* @param terminosDecimales parte decimal del numero (se deebe contemplar dentro de los globales.
			* Ejemplo (4,2) = 00.00 hasta 99.99
			* @return Tipo SQL como cadena.
			*/
		public String longitudDecimal(int terminosGlobales, int terminosDecimales) {
				if ((this == DECIMAL) && terminosDecimales > 0 && (terminosGlobales > terminosDecimales))
						return String.format("%s(%s,%s)",tipoSQL,terminosGlobales,terminosDecimales);
				return tipoSQL;
		}
}