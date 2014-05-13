package spssoftware;

import com.googlecode.flyway.core.Flyway;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import spssoftware.config.Config;
import spssoftware.config.SystemHelper;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String... args) {

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
    public static Config getConfig() {
        return SystemHelper.getEnvironment().getConfig();
    }
}