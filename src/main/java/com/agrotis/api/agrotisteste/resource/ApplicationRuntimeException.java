package com.agrotis.api.agrotisteste.resource;

import java.text.MessageFormat;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ApplicationRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5303777459178949604L;

	/**
     * Creates an exception with no additional information
     */
    public ApplicationRuntimeException() {
        super();
    }

	/**
     * Creates an exception informing the key of the error message, its cause, and message parameters
     *
     * @param message
     * @param cause
     * @param params
     */
    public ApplicationRuntimeException(String message, Throwable cause, Object... params) {
        super(MessageFormat.format(message, params), cause);
    }

	/**
     * Creates an exception by entering the error message key and the message parameters
     *
     * @param message
     * @param params
     */
    public ApplicationRuntimeException(String message, Object... params) {
        super(MessageFormat.format(message, params));
    }
}