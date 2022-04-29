package com.agrotis.api.agrotisteste.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.agrotis.api.agrotisteste.exception.NotFoundException;
import com.agrotis.api.agrotisteste.param.UserFilterParam;
import com.agrotis.api.agrotisteste.param.UserParam;
import com.agrotis.api.agrotisteste.param.UserParam.LaboratoryInformationParam;
import com.agrotis.api.agrotisteste.param.UserParam.PropertyInformationParam;
import com.agrotis.api.agrotisteste.repository.UserRepository;
import com.agrotis.api.agrotisteste.service.UserService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class UserServiceUnitTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	public static UserParam param;
	
	public static String id;
	
	@Test
	public void testA_shouldSaveUser() {
		
		// create parameters
		var propertyInformationParam = PropertyInformationParam.builder()
				.id(123456)
				.propertyName("Nome Exemplo da fazenda")
				.build();
		var laboratoryInformationParam = LaboratoryInformationParam.builder()
				.id(123456)
				.laboratoryName("Nome Exemplo do laboratório")
				.build();
		 param = UserParam.builder()
							 .name("Claudio")
							 .documentId("86.750.253/0001-06")
							 .startDate("2022-05-02T17:41:44Z")
							 .finalDate("2022-05-02T17:41:44Z")
							 .observation("Observacao exemplo de teste")
							 .laboratoryInformationParam(laboratoryInformationParam)
							 .propertyInformationParam(propertyInformationParam)
							 .build();
		// save user
		var user = userService.save(param);
		
		// validation
		id = user.getId();
		assertNotNull(id);
		assertEquals(user.getName(), param.getName());
		assertEquals(user.getDocumentId(), param.getDocumentId());
		assertEquals(user.getDtStart(), param.getStartDate());
		assertEquals(user.getDtEnd(), param.getFinalDate());
		assertEquals(user.getDsObservation(), param.getObservation());
		assertEquals(user.getLaboratoryInformationDocument().getLaboratoryName(), param.getLaboratoryInformationParam().getLaboratoryName());
		assertEquals(user.getLaboratoryInformationDocument().getId(), param.getLaboratoryInformationParam().getId());
		assertEquals(user.getPropertyInformationDocument().getPropertyName(), param.getPropertyInformationParam().getPropertyName());
		assertEquals(user.getPropertyInformationDocument().getId(), param.getPropertyInformationParam().getId());
		
	}
	
	@Test
	public void testB_shouldFindUserById() {
		
		// find user by id
		var user = userService.findById(id);
		
		// validation
		assertNotNull(user);
		assertEquals(user.getName(), param.getName());
		assertEquals(user.getDocumentId(), param.getDocumentId());
		assertEquals(user.getDtStart(), param.getStartDate());
		assertEquals(user.getDtEnd(), param.getFinalDate());
	}
	
	@Test
	public void testC_shouldFilterByName() {
		
		// parameters to filter
		var filterParam = UserFilterParam.builder()
							.name("Claudio")
							.build();
		
		// filter by name
		var users = userService.findAll(filterParam, null);
		
		// validation
		assertTrue(users.stream().anyMatch(user -> user.getName().equals(param.getName())));
	}
	
	@Test
	public void testD_shouldFilterByDocumentId() {
		
		// paraters to filter
		var filterParam = UserFilterParam.builder()
							.documentId("86.750.253/0001-06")
							.build();

		// filter by document id
		var users = userService.findAll(filterParam, null);
		
		// validation
		assertTrue(users.stream().anyMatch(user -> user.getDocumentId().equals(param.getDocumentId())));
	}
	
	@Test
	public void testD_shouldUpdateUser() {
		
		// create parameters to update
		var propertyInformationParam = PropertyInformationParam.builder()
				.id(98765)
				.propertyName("Nome Exemplo da fazenda update")
				.build();
		var laboratoryInformationParam = LaboratoryInformationParam.builder()
				.id(98765)
				.laboratoryName("Nome Exemplo do laboratório update")
				.build();
		 param = null;
		 param = UserParam.builder()
				 			 .id(id)
							 .name("Claudio update")
							 .documentId("86.750.253/0001-06")
							 .startDate("2022-07-02T17:41:44Z")
							 .finalDate("2022-12-02T17:41:44Z")
							 .observation("Observacao exemplo de teste update")
							 .laboratoryInformationParam(laboratoryInformationParam)
							 .propertyInformationParam(propertyInformationParam)
							 .build();
		// update user
		var user = userService.update(param);
		
		// validation
		id = user.getId();
		assertNotNull(id);
		assertEquals(user.getName(), param.getName());
		assertEquals(user.getDocumentId(), param.getDocumentId());
		assertEquals(user.getDtStart(), param.getStartDate());
		assertEquals(user.getDtEnd(), param.getFinalDate());
		assertEquals(user.getDsObservation(), param.getObservation());
		assertEquals(user.getLaboratoryInformationDocument().getLaboratoryName(), param.getLaboratoryInformationParam().getLaboratoryName());
		assertEquals(user.getLaboratoryInformationDocument().getId(), param.getLaboratoryInformationParam().getId());
		assertEquals(user.getPropertyInformationDocument().getPropertyName(), param.getPropertyInformationParam().getPropertyName());
		assertEquals(user.getPropertyInformationDocument().getId(), param.getPropertyInformationParam().getId());
		
	}
	
	@Test
	public void testF_shouldDeleteUser() {
		
		var exception = assertThrows(NotFoundException.class, () -> {
			
			// delete user
			userRepository.deleteById(id);
			userService.findById(id);
		});
		
		// validation
	    String expectedMessage = "Usuário não encontrado com id: " + id;
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}

}
