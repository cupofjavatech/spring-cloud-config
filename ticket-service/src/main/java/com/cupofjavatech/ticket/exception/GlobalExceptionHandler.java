package com.cupofjavatech.ticket.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	private Map<String, Object> getErrorMap(String message) {
		Map<String, Object> errorMap = new LinkedHashMap<>();
		errorMap.put("timestamp", LocalDateTime.now());
		errorMap.put("message", message);
		return errorMap;
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Object> handleRecordNotException(CustomException ex, WebRequest request) {

		LOGGER.error(ex.getMessage(), ex);
		return new ResponseEntity<>(getErrorMap(ex.getMessage()), ex.getHttpStatus());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleTicketException(IllegalArgumentException ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
		return new ResponseEntity<>(getErrorMap(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
		return new ResponseEntity<>(getErrorMap(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
