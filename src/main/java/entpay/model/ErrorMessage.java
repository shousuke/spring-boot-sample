package entpay.model;


public class ErrorMessage extends BaseModel {

	private String errorMessage;

	public ErrorMessage(String message) {
		this.errorMessage = message;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	
}
