package spssoftware;

public class EnvironmentHelper {

    public enum Environment {
        DEV, LIVE
    }

    public static Environment getEnvironmentalConfig() {
        if ("DEV".equals(System.getProperty("HFC_ENV"))) {
            return Environment.DEV;
        } else {
            return Environment.LIVE;
        }
    }
}
