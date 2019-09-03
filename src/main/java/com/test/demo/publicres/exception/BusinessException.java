package com.test.demo.publicres.exception;

/**
 * 业务异常类
 * @author Ban shifeng
 * @date 2019-1-9
 *
 */
public class BusinessException extends Exception {
	
	static final long serialVersionUID = -35466L;
	
	public BusinessException() {
		super();
	}
	
	public BusinessException(String msg) {
		super(msg);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
