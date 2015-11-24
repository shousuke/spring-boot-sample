package entpay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {
	
	@Autowired 
	private Environment env;
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory ob = new JedisConnectionFactory();
		ob.setHostName(env.getRequiredProperty("spring.redis.host"));
		ob.setPort(Integer.valueOf(env.getRequiredProperty("spring.redis.port")));
		ob.setDatabase(Integer.valueOf(env.getRequiredProperty("spring.redis.database")));
		
		return ob;
	}

	@Bean
	StringRedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
		StringRedisTemplate template = new StringRedisTemplate(connectionFactory);
		template.setConnectionFactory(jedisConnectionFactory());
		
		return template;
	}
}
