package spssoftware;

import com.googlecode.flyway.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import spssoftware.config.Config;
import spssoftware.config.SystemHelper;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class Application {

    public static void main(String... args) {
        System.out.print("Opening on port" + System.getenv("PORT"));
        System.out.print("Opening db " + System.getenv("DATABASE_URL"));
        Config config = Application.getConfig();

        Flyway flyway = new Flyway();
        flyway.setDataSource(config.getDatabaseUrl(), config.getDatabaseUsername(), config.getDatabasePassword());
        flyway.setSchemas(config.getDatabaseSchema());
        flyway.setInitOnMigrate(true);
        flyway.setTable("CHANGELOG");
        flyway.migrate();
        SpringApplication application = new SpsApplication(Application.class);
        application.run(args);
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        return new JettyEmbeddedServletContainerFactory(System.getenv("PORT") == null ? 8080 : Integer.valueOf(System.getenv("PORT")));
    }

    @Bean
    public static Config getConfig() {
        return SystemHelper.getEnvironment().getConfig();
    }
}