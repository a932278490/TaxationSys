package com.dsjsys.exception;

public class ParameterException extends Exception {


	private String message;

	public String getMessage() {
		return message;
	}

	public ParameterException() {
		super();
	}

	public ParameterException(String message) {
		super();
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
