package com.test.demo.publicres.exception;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理类
 * 
 * @author Ban shifeng
 * @date 2019-1-9
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public Object exceptionHandler(Exception e, HttpServletResponse response) {
		
		Map<String, Object> responseMap = new HashMap<>();
		
		if (e instanceof MissingServletRequestParameterException) {
			responseMap.put("resultCode", 999);
			responseMap.put("message", "请求参数有误，请检查参数");
		} else {
			responseMap.put("resultCode", 999);
			responseMap.put("message", "服务器发生错误，稍后请求");
			e.printStackTrace();
		}
		return responseMap;
	}
}
