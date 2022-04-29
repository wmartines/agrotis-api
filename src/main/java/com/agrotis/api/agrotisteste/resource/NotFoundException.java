package com.agrotis.api.agrotisteste.resource;

import java.text.MessageFormat;

/**
 * The Class NotFoundException.
 */
public class NotFoundException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5573033775552922143L;

	/**
	 * Contructor.
	 */
    public NotFoundException() {
        super();
    }

    /**
     * Creates an exception informing the key of the error message, 
     * its cause, and message parameters.
     *
     * @param message the message
     * @param cause the cause
     * @param params the params
     */
    public NotFoundException(String message, Throwable cause, Object... params) {
        super(MessageFormat.format(message, params), cause);
    }

    /**
     * Creates an exception by entering the error message key and the message parameters.
     *
     * @param message the message
     * @param params the params
     */
    public NotFoundException(String message, Object... params) {
        super(MessageFormat.format(message, params));
    }

    /**
     * Creates an exception stating only its cause.
     *
     * @param cause the cause
     */
    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
