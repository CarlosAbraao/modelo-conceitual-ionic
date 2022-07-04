package com.carlosdev.service.exception;

public class DataIntegrityException extends RuntimeException {

	// ESSA EXECESSÃO SERÁ EXECUTADA NO CONTROLLER
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String msg) {
		super(msg);
		
		
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}
	

}
