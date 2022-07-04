package com.carlosdev.controller.exception;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.JDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.carlosdev.service.exception.DataIntegrityException;
import com.carlosdev.service.exception.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionsHandler {
	
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, ServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> objectNotFound(DataIntegrityException e, ServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, ServletRequest request){
		
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "erro de validação" ,System.currentTimeMillis());
			
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
			
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(JDBCException.class)

	public ResponseEntity<StandardError> JdbcSQLException(JDBCException e, HttpServletResponse request){

	ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(),

	"Email existente na atualização",System.currentTimeMillis());

	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

	}

	}
		
	


