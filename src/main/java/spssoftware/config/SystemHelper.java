package spssoftware.config;

public abstract class SystemHelper {
	
	private static final String SPS_ENVIRONMENT_VARIABLE_NAME = "SPS_ENVIRONMENT";

	public static Environment getEnvironment() {
		return Environment.getByName(System.getProperty(SPS_ENVIRONMENT_VARIABLE_NAME));
	}

}
