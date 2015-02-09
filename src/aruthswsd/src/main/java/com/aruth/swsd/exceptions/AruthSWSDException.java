package com.aruth.swsd.exceptions;

public class AruthSWSDException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String errorCode;

	public AruthSWSDException (String errorCode, String errorMessage, Throwable t) {
		super(errorMessage, t);
		
		this.errorCode = errorCode;		
	}
	
	public String getErrorCode () {
		return errorCode;
	}
}
