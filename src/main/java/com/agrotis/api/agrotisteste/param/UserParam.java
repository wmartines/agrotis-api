package com.agrotis.api.agrotisteste.param;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Instantiates a new farmer param.
 *
 * @param name the name
 * @param startDate the start date
 * @param finalDate the final date
 * @param documentId the document id
 * @param propertyInformationParam the property information param
 * @param laboratoryInformationParam the laboratory information param
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserParam  implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3277184751442747704L;
	
	/** The id. */
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
	
	/** The property information param. */
	@JsonProperty("infosPropriedade")
	private PropertyInformationParam propertyInformationParam;
	
	/** The laboratory. */
	@JsonProperty("laboratorio")
	private LaboratoryInformationParam laboratoryInformationParam;
	
	@JsonProperty("observacoes")
	private String observation;
	
	/**
	 * Instantiates a new property information param.
	 *
	 * @param name the property name
	 * @param id the property id
	 * @param propertyInformationParam the property information param
	 */
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static final class PropertyInformationParam implements Serializable {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 6765667818628756453L;
		
		/** The id. */
		private Integer id;
		
		/** The property name. */
		@JsonProperty("nome")
		private String propertyName;	 	
		
	}
	
	/**
	 * Instantiates a new laboratory information param.
	 *
	 * @param name the laboratory name
	 * @param id the laboratory id
	 * @param laboratoryInformationParam the laboratory information param
	 */
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static final class LaboratoryInformationParam implements Serializable {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 5329812352198953453L;

		/** The id. */
		private Integer id;
		
		/** The laboratory name. */
		@JsonProperty("nome")
		private String laboratoryName;	 	
		
	}
}
