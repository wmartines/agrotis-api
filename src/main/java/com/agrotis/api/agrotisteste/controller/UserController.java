package com.agrotis.api.agrotisteste.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agrotis.api.agrotisteste.document.UserDocument;
import com.agrotis.api.agrotisteste.document.UserDocument.LaboratoryInformationDocument;
import com.agrotis.api.agrotisteste.document.UserDocument.PropertyInformationDocument;
import com.agrotis.api.agrotisteste.param.UserFilterParam;
import com.agrotis.api.agrotisteste.param.UserParam;
import com.agrotis.api.agrotisteste.presenter.UserPresenter;
import com.agrotis.api.agrotisteste.presenter.UserPresenter.LaboratoryInformationPresenter;
import com.agrotis.api.agrotisteste.presenter.UserPresenter.PropertyInformationPresenter;
import com.agrotis.api.agrotisteste.repository.UserRepository;
import com.agrotis.api.agrotisteste.resource.ApplicationRuntimeException;
import com.agrotis.api.agrotisteste.service.UserService;

/**
 * The Class UserController.
 */
@RestController
@RequestMapping(value = "users")
public class UserController {
	
	/** The user service. */
	@Autowired
	private UserService userService;
	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Find all.
	 *
	 * @param name the name
	 * @param documentId the document id
	 * @param initialStartDate the initial start date
	 * @param finalStartDate the final start date
	 * @param pageable the pageable
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(value = "nome", required = false) String name,
			@RequestParam(value = "cnpj", required = false) String documentId,
			@RequestParam(value = "inicioDataInicial", required = false) String initialStartDate,
			@RequestParam(value = "finalDataInicial", required = false) String finalStartDate,
			Pageable pageable) throws Exception{
		
		// assign parameters
		UserFilterParam param =UserFilterParam.builder()
			.name(name)
			.documentId(documentId)
			.initialStartDate(initialStartDate)
			.finalStartDate(finalStartDate)
			.build();
		
		return new ResponseEntity<>(Optional.of(param)
                // find users
                .map(parameter -> userService.findAll(param, pageable))
                // convert to presenter
                .map(users -> new HashSet<>(users.stream().map(this::convertToPresenter).collect(Collectors.toList())))
                // throw exception
                .orElseThrow(ApplicationRuntimeException::new), HttpStatus.OK);
	}
	
	/**
	 * Save.
	 *
	 * @param param the param
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@PostMapping
	public ResponseEntity<?> save(@RequestBody UserParam param) throws Exception{
		
		return new ResponseEntity<>(Optional.of(param)
                .map(parameter ->{
                	// parameters validarion
                	validation(parameter);
                	return parameter;
                })
                // save user
                .map(userService::save)
                // convert document to presenter
                .map(this::convertToPresenter)
                // throw exception
                .orElseThrow(), HttpStatus.OK);
	}
	
	/**
	 * Update.
	 *
	 * @param param the param
	 * @param id the id
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody UserParam param, @PathVariable String id) throws Exception {

		return new ResponseEntity<>(Optional.of(param)
				.map(parameter -> {
					// parameters validarion
					validation(parameter);
					// set id to update
					parameter.setId(id);
					return parameter;
				})
				// save user
				.map(userService::update)
				// convert document to presenter
				.map(this::convertToPresenter)
				// throw exception
				.orElseThrow(), HttpStatus.OK);
	}
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public  ResponseEntity<?> delete(@PathVariable String id) {
		
		userRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Validation.
	 *
	 * @param param the param
	 */
	public void validation(UserParam param) {
		Assert.isTrue(StringUtils.isNotEmpty(param.getPropertyInformationParam().getPropertyName()), "Preencha os campos obrigatórios");
		Assert.isTrue(StringUtils.isNotEmpty(param.getLaboratoryInformationParam().getLaboratoryName()), "Preencha os campos obrigatórios");
	}
	
	/**
	 * Convert to presenter.
	 *
	 * @param document the document
	 * @return the user presenter
	 */
	private UserPresenter convertToPresenter(UserDocument document) {

		return Optional.ofNullable(document)
				.map(param -> {
					return UserPresenter.builder()
							.id(document.getId())
							.name(document.getName())
							.startDate(document.getDtStart())
							.finalDate(document.getDtEnd())
							.documentId(document.getDocumentId())
							.observation(document.getDsObservation())
							.propertyInformationPresenter(convertPropertyInformationToDocument(document.getPropertyInformationDocument()))
							.laboratoryInformationPresenter(convertLaboratoryInformationToDocument(document.getLaboratoryInformationDocument()))
							.build();

		})// throw exception
		.orElseThrow(() -> new ApplicationRuntimeException("Ocorreu um erro ao converter usuário para presenter"));
	}
	
	/**
	 * Convert property information to document.
	 *
	 * @param document the document
	 * @return the property information presenter
	 */
	private PropertyInformationPresenter convertPropertyInformationToDocument(PropertyInformationDocument document) {

		return PropertyInformationPresenter.builder()
				.id(document.getId())
				.propertyName(document.getPropertyName())
				.build();
	}
	
	/**
	 * Convert laboratory information to document.
	 *
	 * @param document the document
	 * @return the laboratory information presenter
	 */
	private LaboratoryInformationPresenter convertLaboratoryInformationToDocument(LaboratoryInformationDocument document) {

		return LaboratoryInformationPresenter.builder()
				.id(document.getId())
				.laboratoryName(document.getLaboratoryName())
				.build();
	}
}
