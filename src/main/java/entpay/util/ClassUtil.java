package entpay.util;

import java.util.HashMap;
import java.util.Map;

public class ClassUtil {

	private static final Map<Class<?>, Class<?>> PRIMITIVE_WRAPPERS = getWrapperMaps();

    public static Class<?> wrap(Class<?> classType)
    {
        return classType.isPrimitive() ? PRIMITIVE_WRAPPERS.get(classType) : classType;
    }

    private static Map<Class<?>, Class<?>> getWrapperMaps()
    {
    	Map<Class<?>, Class<?>> map = new HashMap<Class<?>, Class<?>>();
        map.put(boolean.class, Boolean.class);
        map.put(byte.class, Byte.class);
        map.put(char.class, Character.class);
        map.put(double.class, Double.class);
        map.put(float.class, Float.class);
        map.put(int.class, Integer.class);
        map.put(long.class, Long.class);
        map.put(short.class, Short.class);
        map.put(void.class, Void.class);
        
        return map;
    }
}
