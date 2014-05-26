package spssoftware.config;

import org.jooq.SQLDialect;

import java.net.URI;
import java.net.URISyntaxException;

public class LiveConfig extends Config {

    public String getDatabaseDriver() {
        return "org.postgresql.Driver";
    }

    public String getDatabaseUsername() {
        return getDBUri().getUserInfo().split(":")[0];
    }

    public String getDatabasePassword() {
        return getDBUri().getUserInfo().split(":")[1];
    }

    public String getDatabaseUrl() {
        return "jdbc:postgresql://" + getDBUri().getHost() + ":" + getDBUri().getPort() + getDBUri().getPath();
    }

    public SQLDialect getDatabaseDialect() {
        return SQLDialect.POSTGRES;
    }

    private URI getDBUri() {
        try {
            return new URI(System.getenv("DATABASE_URL"));
        } catch (URISyntaxException e) {
            System.err.print("Cannot parse db uri " + System.getenv("DATABASE_URL"));
            throw new RuntimeException(e);
        }
    }
}