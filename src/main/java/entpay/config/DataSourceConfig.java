package entpay.config;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class DataSourceConfig {

private static final Logger log = Logger.getLogger("debugLogger");
	
	@Autowired 
	private Environment env;
	
	@Bean(destroyMethod="close")
	@Primary
	public DataSource dataSource() {
		return createDataSource("datasource");
	}

	@Bean(name="merchant", destroyMethod="close")
	public DataSource merchantDataSource() {
		return createDataSource("datasource.merchant");
	}
	
	private DataSource createDataSource(String prefix) {
		try {
			ComboPooledDataSource ds = new ComboPooledDataSource();
			ds.setDriverClass(env.getRequiredProperty(prefix + ".driver-class-name"));
			ds.setJdbcUrl(env.getRequiredProperty(prefix + ".url"));
			ds.setUser(env.getRequiredProperty(prefix + ".username"));
			ds.setPassword(env.getRequiredProperty(prefix + ".password"));
			ds.setInitialPoolSize(3);
			ds.setAcquireIncrement(5);
			ds.setIdleConnectionTestPeriod(60);
			ds.setMaxPoolSize(100);
			ds.setMaxStatements(50);
			ds.setMinPoolSize(10);
			
			return ds;
		} catch (Exception e) {
			log.error("Failed to obtain data source.");
			throw new RuntimeException(e);
		}
	}
}
