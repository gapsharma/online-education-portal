package com.springboot.eduportal.studentservice.exception;

import org.springframework.stereotype.Component;

@Component
public class OpenQuestionsAllowedExceededException extends RuntimeException {
	
	private String message;

	public OpenQuestionsAllowedExceededException() {
	}
	
	public OpenQuestionsAllowedExceededException(String message) {
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
