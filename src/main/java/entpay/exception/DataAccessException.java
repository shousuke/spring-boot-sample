package entpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class DataAccessException extends SilverException {

	private static final long serialVersionUID = 1532905603447987672L;

	public DataAccessException() {
		super("An error occurred while accessing data.");
	}
	
	public DataAccessException(String message) {
		super(message);
	}
}
