package com.agrotis.api.agrotisteste.document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class UserDocument {
	
	@Id
	private String id;
	/** The name. */
	private String name;
	/** The start date. */
	private String dtStart;
	/** The final date. */
	private String dtEnd;
	/** The document id. */
	private String documentId;
	/** The property information document. */
	private PropertyInformationDocument propertyInformationDocument;
	/** The laboratory. */
	private LaboratoryInformationDocument laboratoryInformationDocument;
	/** The observation. */
	private String dsObservation;
	
	/**
	 * Instantiates a new property information document.
	 *
	 * @param name the property name
	 * @param id the property id
	 * @param propertyInformationParam the property information document
	 */
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static final class PropertyInformationDocument {
		
		/** The id. */
		private Integer id;
		/** The property name. */
		private String propertyName;	 	
		
	}
	
	/**
	 * Instantiates a new laboratory information document.
	 *
	 * @param name the laboratory name
	 * @param id the laboratory id
	 * @param laboratoryInformationParam the laboratory information document
	 */
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static final class LaboratoryInformationDocument  {
		
		/** The id. */
		private Integer id;
		/** The laboratory name. */
		private String laboratoryName;	 	
		
	}

}
