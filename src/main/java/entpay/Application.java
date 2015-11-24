package entpay;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter  {

	private static final Logger log = Logger.getLogger("debugLogger");
	public static final Properties ENTPAY_PROPS = getEntpayProps();
	public static final Properties APP_PROPS = getAppProps();
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    private static String getHostname() throws UnknownHostException {
    	InetAddress ip = InetAddress.getLocalHost();
    	String hostname = ip.getHostName();
    	log.info("Hostname: " + hostname);
    	
        return hostname;
    }
    
    public static boolean isProdEnv() {
    	try {
    		String prodHostname = ENTPAY_PROPS.getProperty("prod.hostname");
        	return getHostname().equals(prodHostname);
    	} catch (Exception e) {
    		return false;
    	}
    }
    
    private static Properties getAppProps() {
		Properties props = null;
		
		try {
			String filename = isProdEnv() ? "application.properties" : "application-test.properties";
			Resource resource = new ClassPathResource(filename);
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			log.error(e);
		}
		
		return props;
	}
	
	private static Properties getEntpayProps() {
		Properties props = null;
		
		try {
			Resource resource = new ClassPathResource("entpay.properties");
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			log.error(e);
		}
		
		return props;
	}
    
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/").setViewName("dashboard");
	}
}
