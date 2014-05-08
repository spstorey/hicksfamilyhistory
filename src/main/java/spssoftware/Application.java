package spssoftware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.naming.NamingException;
import javax.sql.DataSource;

@ComponentScan(basePackages = "spssoftware")
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public DataSource dataSource() {
        JndiObjectFactoryBean dataSource = new JndiObjectFactoryBean();
        dataSource.setJndiName("java:comp/env/jdbc/hicksdb");
        try {
            dataSource.afterPropertiesSet();
        } catch (IllegalArgumentException | NamingException e) {
            // rethrow
            //throw new RuntimeException(e);
        }
        return (DataSource)dataSource.getObject();
    }
}
