package com.springboot.eduportal.studentservice.exception;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class ErrorDetails {
	
	private String code;
	
	private String message;
	
	private LocalDate timestamp;
	
	public ErrorDetails() {
	}

	public ErrorDetails(String code, String message, LocalDate timestamp) {
		this.code = code;
		this.message = message;
		this.timestamp = timestamp;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDate getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}

}
