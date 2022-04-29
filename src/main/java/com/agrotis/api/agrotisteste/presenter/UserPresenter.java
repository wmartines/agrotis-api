package com.agrotis.api.agrotisteste.presenter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class UserPresenter.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPresenter implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4784629165770538496L;
	
	/** The name. */
	@JsonProperty("idUsuario")
	private String id;

	/** The name. */
	@JsonProperty("nome")
	private String name;
	
	/** The start date. */
	@JsonProperty("dataInicial")
	private String startDate;
	
	/** The final date. */
	@JsonProperty("dataFinal")
	private String finalDate;
	
	/** The document id. */
	@JsonProperty("cnpj")
	private String documentId;
	
	/** The property information presenter. */
	@JsonProperty("infosPropriedade")
	private PropertyInformationPresenter propertyInformationPresenter;
	
	/** The laboratory. */
	@JsonProperty("laboratorio")
	private LaboratoryInformationPresenter laboratoryInformationPresenter;
	
	/** The observation. */
	@JsonProperty("observacoes")
	private String observation;
	
	/**
	 * Instantiates a new property information presenter.
	 */
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static final class PropertyInformationPresenter implements Serializable {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 6765667818628756453L;
		
		/** The id. */
		private Integer id;
		/** The property name. */
		@JsonProperty("nome")
		private String propertyName;	 	
		
	}
	
	/**
	 * Instantiates a new laboratory information presenter.
	 */
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static final class LaboratoryInformationPresenter implements Serializable {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -2979084471233469807L;

		/** The id. */
		private Integer id;
		/** The laboratory name. */
		@JsonProperty("nome")
		private String laboratoryName;	 	
		
	}

}
