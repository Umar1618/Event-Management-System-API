package com.gyangrove.exception;

@SuppressWarnings("serial")
public class EventsNotFoundException extends RuntimeException {

	private String message;

	public EventsNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}	
}
