package spssoftware.config;


import org.jooq.SQLDialect;

public abstract class Config {

    public abstract String getDatabaseUsername();

    public abstract String getDatabasePassword();

    public abstract String getDatabaseUrl();

    public abstract String getDatabaseDriver();

    public abstract SQLDialect getDatabaseDialect();

    public String getDatabaseSchema() {
        return "cb_hicks";
    }
}
