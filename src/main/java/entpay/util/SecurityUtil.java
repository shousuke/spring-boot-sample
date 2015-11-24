package entpay.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.util.DigestUtils;

public class SecurityUtil {
	
	public static String md5(String str) throws UnsupportedEncodingException {
		byte[] bytesOfMessage = str.getBytes("UTF-8");
		
		return DigestUtils.md5DigestAsHex(bytesOfMessage);
	}
	
	public static String sortedValString(Map<String, String[]> params) {
        StringBuffer sb = new StringBuffer();
        SortedSet<String> keys = new TreeSet<String>(params.keySet());
        
        for (String key : keys) {
           String[] values = params.get(key);
           for (String val : values) {
        	   sb.append(val);
           }
        }
        
        return sb.toString();
	}
	
	public static String pathVarString(Map<String, String> vars) {
		StringBuffer sb = new StringBuffer();
        
        for (String var : vars.values()) {
        	sb.append(var);
        }
        
        return sb.toString();
	}
	
	public static String generateQueryString(Map<String, String[]> params) {
		StringBuffer sb = new StringBuffer();
        
        for (String key : params.keySet()) {
        	String[] vals = params.get(key);
        	for (String val : vals) {
        		if (sb.length() == 0) {
        			sb.append("?");
        		} else {
        			sb.append("&");
        		}
            	sb.append(key).append("=").append(val);
        	}
        }
        
        return sb.toString();
	}
	
}
