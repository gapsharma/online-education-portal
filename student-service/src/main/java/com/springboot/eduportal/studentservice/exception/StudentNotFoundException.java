package com.springboot.eduportal.studentservice.exception;

import org.springframework.stereotype.Component;

@Component
public class StudentNotFoundException extends RuntimeException {
	
	private String message;

	public StudentNotFoundException() {
	}
	
	public StudentNotFoundException(String message) {
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
