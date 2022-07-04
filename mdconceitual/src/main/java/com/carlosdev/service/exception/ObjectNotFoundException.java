package com.carlosdev.service.exception;

public class ObjectNotFoundException extends RuntimeException {

	// ESSA EXECESSÃO SERÁ EXECUTADA NO CONTROLLER
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);
		
		
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
	

}
