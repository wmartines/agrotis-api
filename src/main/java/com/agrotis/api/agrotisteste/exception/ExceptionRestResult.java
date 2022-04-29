package com.agrotis.api.agrotisteste.exception;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


/**
 * The Class RestResult.
 *
 * @param <T> the generic type
 */
@Getter
@Setter
public class ExceptionRestResult<T>  {

	
	/** The cd status. */
	private Integer cdStatus;
	
		/** The timestamp. */
		private Date timestamp;
		
	/** The data. */
	private T data;

	/**
	 * Instantiates a new rest result.
	 */
	public ExceptionRestResult() {
		super();
		timestamp = new Date();
	}

	/**
	 * Instantiates a new rest result.
	 *
	 * @param data the data
	 */
	public ExceptionRestResult(T data, Integer cdStatus) {
		this();
		this.data = data;
		this.cdStatus = cdStatus;
	}
}
