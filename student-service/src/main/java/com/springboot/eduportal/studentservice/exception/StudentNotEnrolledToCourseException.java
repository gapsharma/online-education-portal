package com.springboot.eduportal.studentservice.exception;

import org.springframework.stereotype.Component;

@Component
public class StudentNotEnrolledToCourseException extends RuntimeException {
	
	private String message;

	public StudentNotEnrolledToCourseException() {
	}
	
	public StudentNotEnrolledToCourseException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
