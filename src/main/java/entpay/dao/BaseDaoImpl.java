package entpay.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import entpay.entity.BaseEntity;
import entpay.exception.DataAccessException;
import entpay.util.DaoUtil;
import entpay.util.ModelUtil;

public abstract class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {
	
	private static final Logger log = Logger.getLogger("debugLogger");
	protected Class<T> entityClass;
	protected String tableName;
	
	@Autowired
	protected DataSource dataSource;
	
	public BaseDaoImpl(Class<T> entityClass) {
		this(entityClass, ModelUtil.guessTableName(entityClass));
	}
	
	public BaseDaoImpl(Class<T> entityClass, String tableName) {
		this.entityClass = entityClass;
		this.tableName = tableName;
	}
	
	@Override
	public void insert(T entity) throws DataAccessException {
		entity.init();
		
		Map<String, Object> primaryKeyVals = entity.getPrimaryFields();
		String primaryKey = primaryKeyVals.keySet().iterator().next();
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(dataSource).withTableName(entity.getTableName());
		
		if (primaryKeyVals.size() == 1) {
			insertActor.usingGeneratedKeyColumns(primaryKey);
		}
		
		try {
			int newId = insertActor.executeAndReturnKey(entity.getColumns()).intValue();
			
			if (primaryKeyVals.size() == 1) {
				ModelUtil.setFieldValue(entity, primaryKey, newId);
				primaryKeyVals.put(primaryKey, newId);
			}
			log.info(DaoUtil.saveSuccessMsg(entity.getTableName(), primaryKeyVals));
		} catch (Exception e) {
			String msg = DaoUtil.saveErrorMsg(entity.getTableName(), primaryKeyVals);
			log.error(msg, e);
			throw new DataAccessException(msg);
		}
	}

	@Override
	public T getById(int id) {
		return getEntity(new String[] { "id" }, new Object[] { id });
	}
	
	@Override
	public T getBy(String[] columnNames, Object[] columnValues) {
		return getEntity(columnNames, columnValues);
	}
	
	private T getEntity(String[] columnNames, Object[] columnValues) {
		String query = "SELECT * FROM " + tableName + " WHERE ";
		String delim = "";
		
		for (String colName : columnNames) {
			query += delim + colName + " = ?";
			delim = " AND ";
		}
		
		List<T> list =  null;
		
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, columnValues);
			list = DaoUtil.parseRows(rows, entityClass);
			
			log.info(DaoUtil.fetchSuccessMsg(tableName, columnNames, columnValues));
		} catch (DataAccessException e) {
			log.error(DaoUtil.fetchErrorMsg(tableName, columnNames, columnValues), e);
		}
		
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public void update(T entity) throws DataAccessException {
		Map<String, Object> primaryKeyVals = entity.getPrimaryFields();
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			jdbcTemplate.update(DaoUtil.getUpdateQuery(entity), DaoUtil.getUpdateParams(entity));
			log.info(DaoUtil.updateSuccessMsg(entity.getTableName(), primaryKeyVals));
		} catch (Exception e) {
			String msg = DaoUtil.updateErrorMsg(entity.getTableName(), primaryKeyVals);
			log.error(msg, e);
			throw new DataAccessException(msg);
		}
	}

	@Override
	public int deleteById(int id) {
		String query = "DELETE from " + tableName + " WHERE id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int count = 0;
		
		try {
			count = jdbcTemplate.update(query, id);
			log.info("delete from " + tableName + " with id = " + id);
		} catch (Exception e) {
			log.error("Failed to delete from " + tableName + " with id = " + id, e);
		}
		
		return count;
	}

	@Override
	public List<T> getAll() {
		String query = "SELECT * FROM " + tableName;
		List<T> list = null;
		
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
			list = DaoUtil.parseRows(rows, entityClass);
		} catch (DataAccessException e) {
			log.info("failed to fetch all from " + tableName);
		}
		
		return list;
	}
	
	@Override
	public int deleteAll() {
		String query = "TRUNCATE " + tableName;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int count = 0;
		
		try {
			count = jdbcTemplate.update(query);
			log.info("delete all " + tableName);
		} catch (Exception e) {
			log.error("Failed to delete all " + tableName, e);
		}
		
		return count;
	}
	
}
