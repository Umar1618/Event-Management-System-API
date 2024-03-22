package com.gyangrove.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.gyangrove.exception.EventsNotFoundException;

@RestControllerAdvice
public class ApplicationHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> eventsNotFoundException(EventsNotFoundException e){
		ErrorStructure<String> errorStructure = new ErrorStructure<>();
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setErrorMessage(e.getMessage());
		errorStructure.setErrorData("Event Object with the given Data did not Exist!!!");
		return new ResponseEntity<ErrorStructure<String>>(errorStructure,HttpStatus.NOT_FOUND);
	}
}
