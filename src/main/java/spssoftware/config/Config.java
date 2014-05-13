package spssoftware.config;


import org.jooq.SQLDialect;

public abstract class Config {

	public String getDatabaseUsername() {
		return "badbccb0d21256";
	}

	public abstract String getDatabasePassword();

	public abstract String getDatabaseUrl();
	
	public String getDatabaseDriver() {
		return "com.mysql.jdbc.Driver";
	}

	public SQLDialect getDatabaseDialect() {
		return SQLDialect.MYSQL;
	}

	public String getDatabaseSchema() {
		return "cb_hicks";
	}
}
