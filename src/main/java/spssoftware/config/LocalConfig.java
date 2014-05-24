package spssoftware.config;

import org.jooq.SQLDialect;

public class LocalConfig extends Config {

    public String getDatabaseUsername() {
        return "sa";
    }

    public String getDatabasePassword() {
        return "";
    }

    public String getDatabaseUrl() {
        return "jdbc:h2:target/runtime-db;AUTO_SERVER=TRUE;CACHE_SIZE=131072";
    }

    public String getDatabaseDriver() {
        return "org.h2.Driver";
    }

    public SQLDialect getDatabaseDialect() {
        return SQLDialect.H2;
    }
}