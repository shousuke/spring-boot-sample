package entpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class BusinessLogicException extends SilverException {

	private static final long serialVersionUID = -1424818603199441741L;

	public BusinessLogicException() {
		super("Invalid data request.");
	}
	
	public BusinessLogicException(String message) {
		super(message);
	}
}
