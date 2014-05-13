package spssoftware.config;

public class LiveConfig extends Config {

	@Override
	public String getDatabasePassword() {
        return "76e76ed4";
	}

	@Override
	public String getDatabaseUrl() {
		return "jdbc:mysql://us-cdbr-cb-east-01.cleardb.net/cb_hicks?zeroDateTimeBehavior=convertToNull";
	}

}
