package entpay.controller.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import entpay.exception.SilverException;
import entpay.exception.UserRequestException;
import entpay.model.ErrorMessage;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger log = Logger.getLogger("debugLogger");
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage missingParamterException(Exception e, HttpServletRequest request, HttpServletResponse resp) {
		log.error(e.getMessage(), e);
		
		return new ErrorMessage(e.getMessage());
	} 
	
	@ExceptionHandler(UserRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage userRequestException(UserRequestException e, HttpServletRequest request, HttpServletResponse resp) {
		log.error(e.getMessage(), e);
		
		return new ErrorMessage(e.getMessage());
	} 
	
	@ExceptionHandler(SilverException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorMessage handleModelNotFoundException(SilverException e, HttpServletRequest request, HttpServletResponse resp) {
		log.error(e.getMessage(), e);
		
		return new ErrorMessage(e.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorMessage handleException(Exception e, HttpServletRequest request, HttpServletResponse resp) {
		log.error(e.getMessage(), e);
		
		return new ErrorMessage(e.getMessage());
	}
}
