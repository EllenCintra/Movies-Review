package com.movies.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler
	public ResponseEntity<ErrorObject> handleUnprocessableEntityException(UnprocessableEntityException ex){
		ErrorObject errorObj = new ErrorObject();
		errorObj.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
		errorObj.setMessage(ex.getMessage());
		errorObj.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorObject> handleBadRequestException(BadRequestException ex){
		ErrorObject errorObj = new ErrorObject();
		errorObj.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorObj.setMessage(ex.getMessage());
		errorObj.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorObject> handleResourceNoDataException(NoDataFoundException ex){
		ErrorObject errorObj = new ErrorObject();
		errorObj.setStatusCode(HttpStatus.NO_CONTENT.value());
		errorObj.setMessage(ex.getMessage());
		errorObj.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorObject> handleResourceNotFoundException(ResourceNotFoundException ex){
		ErrorObject errorObj = new ErrorObject();
		errorObj.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorObj.setMessage(ex.getMessage());
		errorObj.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.NOT_FOUND);
	}
}
