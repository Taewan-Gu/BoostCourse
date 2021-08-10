package com.ntscorp.intern.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exception(Exception exception) {
		LOGGER.error("SERVER ERROR", exception);

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<?> exception(IllegalStateException illegalArgumentException) {
		LOGGER.error("Parameters are not Validated", illegalArgumentException);

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}