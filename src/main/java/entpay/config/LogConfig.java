package entpay.config;

import java.io.FileNotFoundException;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.Log4jConfigurer;

import entpay.Application;

@Configuration
public class LogConfig {

	@Profile("default")
	@PostConstruct
	public void prodLog() throws FileNotFoundException {
		if (Application.isProdEnv()) {
			Log4jConfigurer.initLogging("classpath:log4j.properties");
		} else {
			Log4jConfigurer.initLogging("classpath:log4j-test.properties");
		}
	}
	
//	@Profile("test")
//	@PostConstruct
//	public void testLog() throws FileNotFoundException {
//		Log4jConfigurer.initLogging("classpath:log4j-test.properties");
//	}
	
}
