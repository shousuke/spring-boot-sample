package entpay.dao;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import entpay.entity.Permission;

public class PermissionDaoImpl implements PermissionDao {
	
	private static final Logger log = Logger.getLogger("debugLogger");

	@Autowired
	@Qualifier("merchant") 
	private DataSource dataSource;

	@Override
	public Permission getById(int id, int type) {
		String sql = "SELECT *" +
				" FROM permission" + 
				" WHERE merchant_id = ?" +
				" AND type = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Permission record = null;
		
		try {
			record = jdbcTemplate.queryForObject(sql, new Object[] { id, type }, new Permission());
		} catch (EmptyResultDataAccessException e) {
			String msg = "No Permission found with merchantId = " + id;
			log.error(msg);
		} catch (Exception e) {
			String msg = "Get Permission failed with merchantId = " + id;
			log.error(msg);
		}
		
		return record;
	}
	
}
