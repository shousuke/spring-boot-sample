package entpay.util;

public class StringUtil {

	public static String capitalize(String string) {
		return Character.toUpperCase(string.charAt(0)) + string.substring(1);
	}
	
	public static String camelToSnake(String str) {
		return str.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
	}
}
