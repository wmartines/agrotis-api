package com.agrotis.api.agrotisteste.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * The Class ExceptionResponse.
 */
@Data
public class ExceptionResponse {

	/** The message. */
	@JsonProperty("mensagem: ")
	private String dsMessage;


	/**
	 * Constructor.
	 *
	 * @param dsMessage the ds message
	 */
	public ExceptionResponse(String dsMessage) {
		this.dsMessage = dsMessage;
	}

}
