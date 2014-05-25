package spssoftware.config;

import org.jooq.SQLDialect;

import java.net.URI;
import java.net.URISyntaxException;

public class LiveConfig extends Config {

    public String getDatabaseDriver() {
        String driver = "org.postgres.Driver";
        System.out.println(driver);
        return driver;
    }

    public String getDatabaseUsername() {
        String username = getDBUri().getUserInfo().split(":")[0];
        System.out.println(username);
        return username;
    }

    public String getDatabasePassword() {
        String password = getDBUri().getUserInfo().split(":")[1];
        System.out.println(password);
        return password;
    }

    public String getDatabaseUrl() {
        String url = "jdbc:postgresql://" + getDBUri().getHost() + ":" + getDBUri().getPath() + getDBUri().getPath();
        System.out.println(url);
        return url;
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