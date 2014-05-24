package spssoftware.config;

public enum Environment {

    LOCAL(new LocalConfig()),
    LIVE(new LiveConfig());

    private final Config config;

    private Environment(Config config) {
        this.config = config;
    }

    public Config getConfig() {
        return this.config;
    }

    public static Environment getByName(String environmentName) {
        for (Environment environment : Environment.values()) {
            if (environment.name().equalsIgnoreCase(environmentName)) {
                return environment;
            }
        }
        return LIVE;
    }
}