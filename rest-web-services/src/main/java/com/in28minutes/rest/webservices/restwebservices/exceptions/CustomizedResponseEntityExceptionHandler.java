package com.in28minutes.rest.webservices.restwebservices.exceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minutes.rest.webservices.restwebservices.user.UserNotFoundException;




// The reason why we want to implement a customized response entity is that
// we found that the response body is set by defaul, but in our usage, we want some customized message, which is structured in the way as "ErroDetails" class which we defined
// therefore, we implement this class and return the customized response body we want to show;

// pls check the code in @ControllerAdvice, it is specifically for class containing methods with @ExceptionHandler, etc.
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(), 
				request.getDescription(false));
		
		// check the ResponseEntity constructor, errorDetails here is responsebody, httpStatus.xxx is status code
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(UserNotFoundException.class) 
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
}
