package com.agrotis.api.agrotisteste.exception;

import java.text.MessageFormat;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * The Class ApplicationRuntimeException.
 */
@ControllerAdvice
public class ApplicationRuntimeException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5303777459178949604L;

	/**
	 * Creates an exception with no additional information.
	 */
    public ApplicationRuntimeException() {
        super();
    }

	/**
	 * Creates an exception informing the key of the error message, its cause, and message parameters.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param params the params
	 */
    public ApplicationRuntimeException(String message, Throwable cause, Object... params) {
        super(MessageFormat.format(message, params), cause);
    }

	/**
	 * Creates an exception by entering the error message key and the message parameters.
	 *
	 * @param message the message
	 * @param params the params
	 */
    public ApplicationRuntimeException(String message, Object... params) {
        super(MessageFormat.format(message, params));
    }
}