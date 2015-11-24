package entpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ModelNotFoundException extends SilverException {

	private static final long serialVersionUID = 1536362450405963732L;

	public ModelNotFoundException() {
		super("Model not found.");
	}
	
	public ModelNotFoundException(String message) {
		super(message);
	}
}
