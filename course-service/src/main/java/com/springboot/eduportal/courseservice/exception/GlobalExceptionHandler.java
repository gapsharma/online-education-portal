package com.springboot.eduportal.courseservice.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = {CourseNotFoundException.class})
	public ResponseEntity<ErrorDetails> handleCourseNotFoundException(CourseNotFoundException cnfe, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails("COURSE_NOT_FOUND", cnfe.getMessage(), LocalDate.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
}
