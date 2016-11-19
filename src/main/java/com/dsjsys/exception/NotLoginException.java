package com.dsjsys.exception;

public class NotLoginException extends Exception {

	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NotLoginException(String message) {
		super();
		this.message = message;
	}
	
}
