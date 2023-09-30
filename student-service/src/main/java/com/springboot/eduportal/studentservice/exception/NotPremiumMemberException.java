package com.springboot.eduportal.studentservice.exception;

import org.springframework.stereotype.Component;

@Component
public class NotPremiumMemberException extends RuntimeException {
	
	private String message;

	public NotPremiumMemberException() {
	}
	
	public NotPremiumMemberException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
