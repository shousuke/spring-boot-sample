package entpay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import entpay.dao.MemberDao;
import entpay.dao.MemberDaoImpl;
import entpay.dao.PermissionDao;
import entpay.dao.PermissionDaoImpl;

@Configuration
public class DaoConfig {
	
	@Bean
	public PermissionDao permissionDao() {
		return new PermissionDaoImpl();
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDaoImpl();
	}

}
