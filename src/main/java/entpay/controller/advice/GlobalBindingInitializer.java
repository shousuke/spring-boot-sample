package entpay.controller.advice;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import entpay.util.DateUtil;

@ControllerAdvice
public class GlobalBindingInitializer {

	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			
			public void setAsText(String value) {
				if ("today".equals(value)) {
		            setValue(DateUtil.today());
				} else if ("tomorrow".equals(value)) {
		            setValue(DateUtil.tomorrow());
				} else if ("yesterday".equals(value)) {
		            setValue(DateUtil.yesterday());
	            } else {
	            	setValue(DateUtil.parseDate(value, "yyyy-MM-dd"));
	            }
			}
		});
	}
}
