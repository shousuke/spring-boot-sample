package entpay.util;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RequestUtil {

	private static final Logger log = Logger.getLogger("debugLogger");
	
	public static HttpHeaders jsonRequest() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		return headers;
	}
	
	public static <T> ResponseEntity<T> call(String url, HttpMethod method, 
			String requestBody, Class<T> responseEntity) {
		
		ResponseEntity<T> resp = null;
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = RequestUtil.jsonRequest();
			HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
			
			log.info("invokeCallback: url=" + url + "; content=" + requestBody);
			resp = restTemplate.exchange(url, method, entity, responseEntity);
		} catch (Exception e) {
			log.error("Failed to invoke callback", e);
		}
		
		return resp;
	}
	
	public static HttpStatus callStatus(String url, HttpMethod method, String requestBody) {
		ResponseEntity<String> resp = call(url, method, requestBody, String.class);
		
		return resp.getStatusCode();
	}
}
