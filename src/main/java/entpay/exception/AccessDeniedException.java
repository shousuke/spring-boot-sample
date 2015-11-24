package entpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AccessDeniedException extends SilverException {

	private static final long serialVersionUID = 6630789742775175422L;

	public AccessDeniedException() {
		super("Access denied.");
	}
	
	public AccessDeniedException(String message) {
		super(message);
	}
}
