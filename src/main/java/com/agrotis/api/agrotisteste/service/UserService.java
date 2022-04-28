package com.agrotis.api.agrotisteste.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agrotis.api.agrotisteste.document.UserDocument;
import com.agrotis.api.agrotisteste.document.UserDocument.LaboratoryInformationDocument;
import com.agrotis.api.agrotisteste.document.UserDocument.PropertyInformationDocument;
import com.agrotis.api.agrotisteste.param.UserFilterParam;
import com.agrotis.api.agrotisteste.param.UserParam;
import com.agrotis.api.agrotisteste.param.UserParam.LaboratoryInformationParam;
import com.agrotis.api.agrotisteste.param.UserParam.PropertyInformationParam;
import com.agrotis.api.agrotisteste.repository.UserRepository;
import com.agrotis.api.agrotisteste.resource.ApplicationRuntimeException;

// TODO: Auto-generated Javadoc
/**
 * The Class UserService.
 */
@Service
public class UserService {
	
	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Find all.
	 *
	 * @param param the param
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<UserDocument> findAll(UserFilterParam param, Pageable pageable) {
		return userRepository.findAllUsers(param, pageable);
	}
	
	/**
	 * Save.
	 *
	 * @param parameter the parameter
	 * @return the user document
	 */
	public UserDocument save(UserParam parameter) {
		
		return Optional.ofNullable(parameter)
				// convert to save
				.map(this::convertToSaveDocument)
				// save user
				.map(userRepository::save)
				// throw exception
				.orElseThrow(() -> new ApplicationRuntimeException("Ocorreu um erro ao salvar usuário: " + parameter.getName()));

	}
	
	/**
	 * Update.
	 *
	 * @param parameter the parameter
	 * @return the user document
	 */
	public UserDocument update(UserParam parameter) {

		return Optional.ofNullable(parameter)
				// convert and save user
				.map(this::convertToUpdateDocument)
				// save
				.map(userRepository::save)
				// throw exception
				.orElseThrow(() -> new ApplicationRuntimeException("Ocorreu um erro ao atualizar usuário: " + parameter.getId()));

	}
	
	/**
	 * Convert to update document.
	 *
	 * @param param the param
	 * @return the user document
	 */
	private UserDocument convertToUpdateDocument(UserParam param) {

		return Optional.ofNullable(param.getId())
				// find by id
				.map(userRepository::findById)
				// get from optional
				.map(Optional<UserDocument>::get)
				// update document
				.map(document -> {
					document.setName(param.getName());
					document.setDtStart(param.getStartDate());
					document.setDtEnd(param.getFinalDate());
					document.setDocumentId(param.getDocumentId());
					document.setDsObservation(param.getObservation());
					document.setPropertyInformationDocument(convertPropertyInformationToDocument(param.getPropertyInformationParam()));
					document.setLaboratoryInformationDocument(convertLaboratoryInformationToDocument(param.getLaboratoryInformationParam()));
					
					return document;
					
		})// throw exception
		.orElseThrow(() -> new ApplicationRuntimeException("Ocorreu um erro ao atualizar informações do usuário: " + param.getId()));
	}
	
	/**
	 * Convert to save document.
	 *
	 * @param parameter the parameter
	 * @return the user document
	 */
	private UserDocument convertToSaveDocument(UserParam parameter) {

		return Optional.ofNullable(parameter)
				.map(param -> {
					return UserDocument.builder()
							.name(param.getName())
							.dtStart(param.getStartDate())
							.dtEnd(param.getFinalDate())
							.documentId(param.getDocumentId())
							.dsObservation(param.getObservation())
							.propertyInformationDocument(convertPropertyInformationToDocument(param.getPropertyInformationParam()))
							.laboratoryInformationDocument(convertLaboratoryInformationToDocument(param.getLaboratoryInformationParam()))
							.build();

		})// throw exception
		.orElseThrow(() -> new ApplicationRuntimeException("Ocorreu um erro ao converter informações do usuário: " + parameter.getName()));
	}
	
	/**
	 * Convert property information to document.
	 *
	 * @param param the param
	 * @return the property information document
	 */
	private PropertyInformationDocument convertPropertyInformationToDocument(PropertyInformationParam param) {

		return PropertyInformationDocument.builder()
				.id(param.getId())
				.propertyName(param.getPropertyName())
				.build();

	}
	
	/**
	 * Convert laboratory information to document.
	 *
	 * @param param the param
	 * @return the laboratory information document
	 */
	private LaboratoryInformationDocument convertLaboratoryInformationToDocument(LaboratoryInformationParam param) {

		return LaboratoryInformationDocument.builder()
				.id(param.getId())
				.laboratoryName(param.getLaboratoryName())
				.build();

	}
}
