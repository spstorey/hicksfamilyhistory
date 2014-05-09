package spssoftware;

import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
@Import({ DataSourceConfiguration.class,})
public class ApplicationConfiguration {

//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public DataSourceTransactionManager getDataSourceTransactionManager() {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean
//    public TransactionAwareDataSourceProxy getTransactionAwareDataSourceProxy() {
//        return new TransactionAwareDataSourceProxy(dataSource);
//    }
//
//    @Bean
//    public DataSourceConnectionProvider getDataSourceConnectionProvider() {
//        return new DataSourceConnectionProvider(getTransactionAwareDataSourceProxy());
//    }
//
//    @Bean
//    public DefaultDSLContext getDefaultDSLContext() {
//        return new DefaultDSLContext(getDataSourceConnectionProvider(), SQLDialect.MYSQL);
//    }
}
