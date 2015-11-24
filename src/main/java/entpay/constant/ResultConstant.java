package entpay.constant;


public class ResultConstant {

	public static final int RESULT_SUCCESS = 1;
	public static final int RESULT_ERROR = 0;
	
	public static final int ERROR_CODE_OTHER = 100;
	public static final int ERROR_CODE_MISSING_PARAM = 1;
	public static final int ERROR_CODE_INVALID_HASH = 2;
	public static final int ERROR_CODE_DEVICE_NOT_FOUND = 3;
	public static final int ERROR_CODE_DEVICE_SAVE_FAILED = 4;
	
	public static final String ERROR_MSG_OTHER = "其他";
	public static final String ERROR_MSG_MISSING_PARAM = "没有请求参数";
	public static final String ERROR_MSG_INVALID_HASH = "身份验证失败";
	public static final String ERROR_MSG_DEVICE_NOT_FOUND = "数据库无该纪录";
	public static final String ERROR_MSG_DEVICE_SAVE_FAILED = "线路数据错误";
}
