package entpay.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entpay.entity.BaseEntity;
import entpay.exception.DataAccessException;

public class ModelUtil {

	private static final Logger log = Logger.getLogger("debugLogger");
    
	public static String guessTableName(Class<? extends BaseEntity> entityClass) {
		String entityName = StringUtil.camelToSnake(entityClass.getName());
		return entityName.substring(entityName.lastIndexOf('.') + 1);
	}
	
	public static String[] getFieldNames(Class<? extends BaseEntity> entityClass) {
		Field[] fields = entityClass.getDeclaredFields();
		List<String> fieldNames = new ArrayList<String>();

		for (Field field : fields) {
			if (Modifier.isFinal(field.getModifiers())) {
				continue;
			}
			
			fieldNames.add(field.getName());
		}

		return fieldNames.toArray(new String[fieldNames.size()]);
	}
	
	public static Object[] getFieldValues(BaseEntity entity) throws DataAccessException {
		Field[] fields = entity.getClass().getDeclaredFields();
		List<Object> fieldValues = new ArrayList<Object>();
		
		for (Field field : fields) {
			if (Modifier.isFinal(field.getModifiers())) {
				continue;
			}
			
			fieldValues.add(getFieldValue(entity, field.getName()));
		}
		
		return fieldValues.toArray();
	}
	
	public static Object getFieldValue(BaseEntity entity, String fieldName) throws DataAccessException {
		try {
			Method method = entity.getClass().getMethod(ModelUtil.getterName(fieldName));
			return method.invoke(entity);
		} catch (Exception e) {
			String msg = "Failed to get field value " + guessTableName(entity.getClass()) + "." + fieldName;
			log.error(msg);
			throw new DataAccessException(msg);
		}
	}
	
	public static void setFieldValue(BaseEntity entity, String fieldName, Object fieldValue) throws Exception {
		try {
			Field field = entity.getClass().getDeclaredField(fieldName);
			Class<?> fieldType = field.getType();
			Class<?> wrapper = ClassUtil.wrap(field.getType());
			Method method = entity.getClass().getMethod(ModelUtil.setterName(fieldName), fieldType);
			String fieldValStr = String.valueOf(fieldValue);
			
			// Date type requires a different approach to parse its corresponding field value
			if (fieldType == Date.class) {
				Date date = DateUtil.parseDate(fieldValStr, "yyyy-MM-dd HH:mm:ss");
				method.invoke(entity, date);
			} else {
				method.invoke(entity, wrapper.getConstructor(new Class[] { String.class }).newInstance(fieldValStr));
			}
		} catch (Exception e) {
			String msg = "Failed to set field value " + guessTableName(entity.getClass()) + "." + fieldName;
			log.error(msg);
			throw new Exception(msg);
		}
	}
	
	public static String getterName(String fieldName) {
		return "get" + StringUtil.capitalize(fieldName);
	}
	
	public static String setterName(String fieldName) {
		return "set" + StringUtil.capitalize(fieldName);
	}
	
	public static int[] getPrimaryKeyIndices(BaseEntity entity) {
		String[] pkeys = entity.getPrimaryKeys();
		String[] colNames = ModelUtil.getFieldNames(entity.getClass());
		int[] indices = new int[pkeys.length];
		int index = 0;
		
		for (int i = 0; i < colNames.length; i++) {
			String colName = colNames[i];
			
			for (String pkey : pkeys) {
				if (colName.equals(pkey)) {
					indices[index] = i;
					index++;
				}
			}
		}
		
		return indices;
	}
	
	public static String toJson(Object obj) {
		String jsonString = "";
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonString = mapper.writeValueAsString(obj);
		} catch(JsonProcessingException e) {
			log.error("Failed to convert object to json string.");
		}
		
		return  jsonString;
	}
	
	public static <T> T toModel(String json, Class<T> modelClass) {
		ObjectMapper mapper = new ObjectMapper();
		T model = null;
		
		try {
			model = mapper.readValue(json, modelClass);
		} catch (Exception e) {
			log.error("Failed to parse json to model.");
		}
		
		return model;
	}
}
