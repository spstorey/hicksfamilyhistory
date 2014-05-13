package spssoftware;

import org.springframework.boot.SpringApplication;

public class SpsApplication extends SpringApplication {

	public SpsApplication(Object... sources) {
		super(sources);
	}

	protected void printBanner() {
		SpsBannerWriter.write(System.out);
	}
}
