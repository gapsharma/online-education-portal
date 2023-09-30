package com.springboot.eduportal.studentservice.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import feign.FeignException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = {StudentNotFoundException.class})
	public ResponseEntity<ErrorDetails> handleCourseNotFoundException(StudentNotFoundException snfe, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails("STUDENT_NOT_FOUND", snfe.getMessage(), LocalDate.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = {StudentNotEnrolledToCourseException.class})
	public ResponseEntity<ErrorDetails> handleStudentNotEnrolledToCourseException(StudentNotEnrolledToCourseException snetce, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails("STUDENT_NOT_ENROLLED", snetce.getMessage(), LocalDate.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {NotPremiumMemberException.class})
	public ResponseEntity<ErrorDetails> handleNotPremiumMemberException(NotPremiumMemberException npme, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails("STUDENT_NOT_PREMIUM_MEMBER", npme.getMessage(), LocalDate.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {OpenQuestionsAllowedExceededException.class})
	public ResponseEntity<ErrorDetails> handleOpenQuestionsAllowedExceededException(OpenQuestionsAllowedExceededException oqaee, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails("OPEN_QUESTIONS_ALLOWED_EXCEEDED", oqaee.getMessage(), LocalDate.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {FeignException.class})
	public ResponseEntity<ErrorDetails> handleFeignExceptions(FeignException fex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails("SERVER_FAULT", fex.getMessage(), LocalDate.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	
}
