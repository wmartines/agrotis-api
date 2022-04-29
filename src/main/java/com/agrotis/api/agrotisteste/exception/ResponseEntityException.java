package com.agrotis.api.agrotisteste.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 */
@ControllerAdvice
@RestController
public class ResponseEntityException extends ResponseEntityExceptionHandler {

	/**
	 * Handles bad requests exception
	 *
	 * @param exception
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ IllegalArgumentException.class, ApplicationRuntimeException.class})
	public final ResponseEntity<?> handleBadRequestException(Exception exception) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ExceptionRestResult<>(new ExceptionResponse(exception.getMessage()), HttpStatus.BAD_REQUEST.value()));
	}
	
	/**
	 * Handles not found exception
	 *
	 * @param exception
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ NotFoundException.class})
	public final ResponseEntity<?> handleNotFoundException(Exception exception) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ExceptionRestResult<>(new ExceptionResponse(exception.getMessage()), HttpStatus.NOT_FOUND.value()));
	}
	
}
	