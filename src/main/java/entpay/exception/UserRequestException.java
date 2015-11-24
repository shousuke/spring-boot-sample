package entpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserRequestException extends SilverException {

	private static final long serialVersionUID = 3848465013463094548L;

	public UserRequestException() {
		super("Invalid data request.");
	}
	
	public UserRequestException(String message) {
		super(message);
	}
}
