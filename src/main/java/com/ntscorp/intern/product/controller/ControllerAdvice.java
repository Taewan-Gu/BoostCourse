package com.ntscorp.intern.product.controller;

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

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<?> exception(IllegalStateException illegalStateException) {
		LOGGER.error("Parameters are not Validated", illegalStateException);

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}