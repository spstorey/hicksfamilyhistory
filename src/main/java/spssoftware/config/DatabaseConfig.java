package spssoftware.config;

import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;
import org.jooq.DSLContext;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

	@Autowired
	Config config;

	@Bean
	public DataSource dataSource() {

		BoneCPConfig boneCpConfig = new BoneCPConfig();
		boneCpConfig.setDisableConnectionTracking(true);
        boneCpConfig.setStatisticsEnabled(true);
		BoneCPDataSource dataSource = new BoneCPDataSource(boneCpConfig);

        dataSource.setIdleConnectionTestPeriodInMinutes(2);
        dataSource.setIdleMaxAgeInMinutes(5);
        dataSource.setMaxConnectionsPerPartition(1);
        dataSource.setMinConnectionsPerPartition(11);
        dataSource.setPartitionCount(1);
        dataSource.setAcquireIncrement(1);
        dataSource.setStatementsCacheSize(100);
		dataSource.setDriverClass(config.getDatabaseDriver());
		dataSource.setJdbcUrl(config.getDatabaseUrl());
		dataSource.setUsername(config.getDatabaseUsername());
		dataSource.setPassword(config.getDatabasePassword());
		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public TransactionAwareDataSourceProxy transactionAwareDataSource() {
		return new TransactionAwareDataSourceProxy(dataSource());
	}

	@Bean
	public DataSourceConnectionProvider connectionProvider() {
		return new DataSourceConnectionProvider(transactionAwareDataSource());
	}

	@Bean
	public DefaultConfiguration dbConfig() {
		DefaultConfiguration dbConfig = new DefaultConfiguration();
		dbConfig.set(config.getDatabaseDialect());
		dbConfig.set(connectionProvider());
		return dbConfig;
	}

	@Bean
	public DSLContext connection() {
		return new DefaultDSLContext(dbConfig());
	}
}