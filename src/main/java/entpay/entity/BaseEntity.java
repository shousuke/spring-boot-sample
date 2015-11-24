package entpay.entity;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;

import entpay.model.BaseModel;
import entpay.util.ModelUtil;
import entpay.util.StringUtil;

public abstract class BaseEntity extends BaseModel {
	
	private static final Logger log = Logger.getLogger("debugLogger");
	
	public void init() {}

	@JsonIgnore
	public String getTableName() {
		return ModelUtil.guessTableName(this.getClass());
	}
	
	@JsonIgnore
	public Map<String, String> getColumnMapping() {
		String[] fieldNames = ModelUtil.getFieldNames(this.getClass());
		Map<String, String> map = new HashMap<String, String>();
		
		for (String fieldName : fieldNames) {
			String colName = StringUtil.camelToSnake(fieldName);
			map.put(fieldName, colName);
		}
		
		return map;
	}
	
	@JsonIgnore
	public boolean isGeneratedKey() {
		return true;
	}
	
	@JsonIgnore
	public String[] getPrimaryKeys() {
		return new String[] {"id"};
	}
	
	@JsonIgnore
	public Map<String, Object> getPrimaryFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		for (String fieldName : getPrimaryKeys()) {
			try {
				Method method = this.getClass().getMethod(ModelUtil.getterName(fieldName));
				Object fieldValue = method.invoke(this);
				map.put(fieldName, fieldValue);
			} catch (Exception e) {
				log.error("Failed to get field value");
			}
		}
		
		return map;
	}
	
	@JsonIgnore
	public Map<String, Object> getColumns() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> columnMapping = getColumnMapping();

		try {
			for (String fieldName : columnMapping.keySet()) {
					Method method = this.getClass().getMethod(ModelUtil.getterName(fieldName));
					Object fieldValue = method.invoke(this);
					map.put(columnMapping.get(fieldName), fieldValue);
			}
		} catch (Exception e) {
			String msg = "Failed to get column values for table " + getTableName();
			log.error(msg);
			throw new Exception(msg);
		}
		
		return map;
	}
}
