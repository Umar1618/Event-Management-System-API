package com.gyangrove.exception;

@SuppressWarnings("serial")
public class ExternalApiException extends RuntimeException {
	
	private String message;

	public ExternalApiException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
