package entpay.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import entpay.entity.BaseEntity;
import entpay.exception.DataAccessException;

public class DaoUtil {

	private static final Logger log = Logger.getLogger("debugLogger");
	
	public static String getUpdateQuery(BaseEntity entity) {
		StringBuffer sb = new StringBuffer();
		String[] pkeys = entity.getPrimaryKeys();
		Map<String, String> colMapping = entity.getColumnMapping();
		
		sb.append("UPDATE ").append(entity.getTableName()).append(" SET ");
		
		String delim = "";
		for (String name : ModelUtil.getFieldNames(entity.getClass())) {
			if (!isPrimaryKey(name, pkeys)) {
				sb.append(delim).append(colMapping.get(name)).append(" = ?");
				delim = ", ";
			}
		}
		
		sb.append(" WHERE ");
		
		delim = "";
		for (String pkey : pkeys) {
			sb.append(delim).append(pkey).append(" = ?");
			delim = ", ";
		}
		
		return sb.toString();
	}
	
	private static boolean isPrimaryKey(String key, String[] primaryKeys) {
		for (String pkey : primaryKeys) {
			if (pkey.equals(key)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static Object[] getUpdateParams(BaseEntity entity) throws DataAccessException {
		int[] pkeyIdxs = ModelUtil.getPrimaryKeyIndices(entity);
		Object[] colVals = ModelUtil.getFieldValues(entity);
		Object[] updateParams = new Object[colVals.length];
		int colIdx = 0;
		int keyIdx = 0;
		
		for (int i = 0; i < colVals.length; i++) {
			Object colVal = colVals[i];
			
			if (!isPrimaryKeyIndex(i, pkeyIdxs)) {
				updateParams[colIdx] = colVal;
				colIdx++;
			} else {
				updateParams[(colVals.length - 1) + keyIdx] = colVal;
				keyIdx++;
			}
		}
		
		return updateParams;
	}
	
	private static boolean isPrimaryKeyIndex(int index, int[] primaryKeyIndices) {
		for (int pkIdx : primaryKeyIndices) {
			if (index == pkIdx) {
				return true;
			}
		}
		
		return false;
	}
	
	public static <T extends BaseEntity> List<T> parseRows(List<Map<String, Object>> rows, Class<T> entityClass) 
			throws DataAccessException {
		List<T> list = new ArrayList<T>();
		
		for (Map<String, Object> row : rows) {
			T entity = parseRow(row, entityClass);
			list.add(entity);
		}
		
		return list;
	}
	
	private static <T extends BaseEntity> T parseRow(Map<String, Object> row, Class<T> entityClass) 
			throws DataAccessException {
		T entity = null;
		
		try {
			entity = entityClass.getConstructor().newInstance();
			Map<String, String> colMapping = entity.getColumnMapping();
			
			for (String fieldName : colMapping.keySet()) {
				String colName = colMapping.get(fieldName);
				Object colVal = row.get(colName);
				
				if (row.get(colName) == null) {
					continue;
				}
				
				ModelUtil.setFieldValue(entity, fieldName, String.valueOf(colVal));
			}
		} catch (Exception e) {
			String msg = "Failed to parse row values.";
			log.error(msg);
			throw new DataAccessException(msg);
		}
		
		return entity;
	}
	
	public static String saveSuccessMsg(String tableName, Map<String, Object> pkeyVals) {
		return createLogMsg("save", true, tableName, pkeyVals);
	}
	
	public static String saveErrorMsg(String tableName, Map<String, Object> pkeyVals) {
		return createLogMsg("save", false, tableName, pkeyVals);
	}
	
	public static String updateSuccessMsg(String tableName, Map<String, Object> pkeyVals) {
		return createLogMsg("update", true, tableName, pkeyVals);
	}
	
	public static String updateErrorMsg(String tableName, Map<String, Object> pkeyVals) {
		return createLogMsg("update", false, tableName, pkeyVals);
	}
	
	public static String fetchSuccessMsg(String tableName, String[] keys, Object[] vals) {
		return createLogMsg("Fetch", true, tableName, keys, vals);
	}
	
	public static String fetchErrorMsg(String tableName, String[] keys, Object[] vals) {
		return createLogMsg("Fetch", false, tableName, keys, vals);
	}
	
	public static String createLogMsg(String operation, boolean isSuccess, String tableName, Map<String, Object> pkeyVals) {
		StringBuffer sb = new StringBuffer();
		String status = isSuccess ? "succeeded" : "failed";
		
		sb.append(operation).append(" ");
		sb.append(tableName).append(" ");
		sb.append(status).append(" with ");
		
		String delim = "";
		for (String fieldName : pkeyVals.keySet()) {
			sb.append(delim).append(fieldName).append(" = ").append(pkeyVals.get(fieldName));
			delim = ", ";
		}
		
		return sb.toString();
	}
	
	public static String createLogMsg(String operation, boolean isSuccess, String tableName, String[] keys, Object[] vals) {
		StringBuffer sb = new StringBuffer();
		String status = isSuccess ? "succeeded" : "failed";
		
		sb.append(operation).append(" ");
		sb.append(tableName).append(" ");
		sb.append(status).append(" with ");
		
		String delim = "";
		for (int i = 0; i < keys.length; i++) {
			String colName = keys[i];
			String colValue = vals[i].toString();
			sb.append(delim).append(colName).append(" = ").append(colValue);
			delim = ", ";
		}
		
		return sb.toString();
	}
}
