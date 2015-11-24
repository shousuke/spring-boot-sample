package entpay.exception;

public class SilverException extends Exception {

	private static final long serialVersionUID = 7724821509303344255L;
	
	private Integer errorCode;

	public SilverException(String message) {
		super(message);
	}
	
	public SilverException(String message, Integer errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

}
