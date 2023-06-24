package com.cts.influentia.subscriptionmanagement.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MultipleSubscriptionsAreNotAllowedException.class)
	public ResponseEntity<String> handleMultipleSubscriptionsAreNotAllowedException(MultipleSubscriptionsAreNotAllowedException ex){
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		return ResponseEntity.badRequest().body("Validation failed for argument. Please give correct argument value");
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullPointerException(NullPointerException ex){
		return ResponseEntity.badRequest().body("User do not exists");
	}
	
}
