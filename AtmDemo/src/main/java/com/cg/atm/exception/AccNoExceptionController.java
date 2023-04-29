package com.cg.atm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccNoExceptionController {

	 @ExceptionHandler(value = AccNoNotFoundException.class)
	   public ResponseEntity<Object> exception(AccNoNotFoundException exception) {
	      return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	   }
}
