package spssoftware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import static spssoftware.EnvironmentHelper.Environment.*;

@Configuration
public class DataSourceConfiguration {

//    @Bean
//    public DataSource dataSource() {
//        System.out.println("SHAUN SHAUN SHAUN SHAUN SHAUN SHAUN SHAUN SHAUN SHAUN SHAUN SHAUN SHAUN ");
//
//        if (DEV.equals(EnvironmentHelper.getEnvironmentalConfig())) {
//            SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//            dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
//            dataSource.setUrl("jdbc:mysql://us-cdbr-cb-east-01.cleardb.net/cb_hicks");
//            dataSource.setUsername("badbccb0d21256");
//            dataSource.setPassword("76e76ed4");
//            return dataSource;
//        } else {
//            try {
//                Context initContext = new InitialContext();
//                Context envContext = (Context) initContext.lookup("java:/comp/env");
//                DataSource dataSource = (DataSource) envContext.lookup("jdbc/hicksdb");
//                return dataSource;
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}
