package com.projectx.mvc.services;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
import static com.projectx.mvc.controller.fixtues.CustomerQuickRegisterDataFixture.*;
import static com.projectx.mvc.fixture.CustomerQuickRegisterDataFixture.REGISTER_EMAIL_ALREADY_REGISTERED;
import static com.projectx.mvc.fixture.CustomerQuickRegisterDataFixture.REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED;
import static com.projectx.mvc.fixture.CustomerQuickRegisterDataFixture.REGISTER_MOBILE_ALREADY_REGISTERED;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.mvc.config.BasicConfig;
import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.domain.CustomerIdDTO;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;
import com.projectx.rest.domain.CustomerQuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.VerifyEmailDTO;
import com.projectx.rest.domain.VerifyMobileDTO;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicConfig.class)
@ActiveProfiles(value="Dev")

public class CustomerQuickRegisterTest {

	@Autowired
	CustomerQuickRegisterService customerQuickRegisterService; 
	
	
	
	@Before
	public void setUp()
	{
		customerQuickRegisterService.clearTestData();
	}
	
	
	@Test
	public void checkIfExist() throws Exception {
		
		String status=customerQuickRegisterService.checkIfAlreadyExist(standardEmailMobileCustomerDTO());
		
		assertEquals(REGISTER_NOT_REGISTERED, status);
	}
	
	@Test
	public void checkIfExistWithExistingEmailMobileCustomer()
	{
		customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		String status=customerQuickRegisterService.checkIfAlreadyExist(standardEmailMobileCustomerDTO());
		
		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED, status);
	}

	@Test
	public void checkIfExistWithExistingEmailCustomer()
	{
		customerQuickRegisterService.addNewCustomer(standardEmailCustomerDTO());
		
		String status=customerQuickRegisterService.checkIfAlreadyExist(standardEmailMobileCustomerDTO());
		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED, status);
	}

	
	@Test
	public void checkIfExistWithExistingMobileCustomer()
	{
		customerQuickRegisterService.addNewCustomer(standardMobileCustomerDTO());
		
		String status=customerQuickRegisterService.checkIfAlreadyExist(standardEmailMobileCustomerDTO());
		
		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED, status);
	}
	
	
	@Test
	public void populateStatusForDuplicateFields()
	{
		assertEquals("Provided Email Already Registered", customerQuickRegisterService.populateMessageForDuplicationField(REGISTER_EMAIL_ALREADY_REGISTERED));
		
		assertEquals("Provided Mobile Already Registered", customerQuickRegisterService.populateMessageForDuplicationField(REGISTER_MOBILE_ALREADY_REGISTERED));
		
		assertEquals("Provided Email And Mobile Already Registered", customerQuickRegisterService.populateMessageForDuplicationField(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED));
	}

	@Test
	public void addNewCustomerWithEmailMobileCustomer()
	{
		CustomerQuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());

		assertTrue( savedEntityResult.getStatus());
		
		assertNotNull(savedEntityResult.getCustomer());
		
	}

	@Test
	public void addNewCustomerWithMobileCustomer()
	{
		CustomerQuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardMobileCustomerDTO());

		assertTrue( savedEntityResult.getStatus());
		
		assertNotNull(savedEntityResult.getCustomer());
		
	}
	
	
	@Test
	public void getCustomerById()
	{
		CustomerQuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		CustomerQuickRegisterDTO entity=customerQuickRegisterService.getByCustomerId(new CustomerIdDTO(savedEntityResult.getCustomer().getCustomerId()));
		
		assertNotNull(entity);
	}
	
	
	@Test
	public void verifyMobileWithEmailMobileCustomer()
	{
		CustomerQuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		assertTrue(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomer().getCustomerId(),
				savedEntityResult.getCustomer().getMobilePin())));
		
		
		assertTrue(customerQuickRegisterService.verifyEmail(new VerifyEmailDTO(savedEntityResult.getCustomer().getCustomerId(),
				savedEntityResult.getCustomer().getEmailHash())));
		
		
	}
	
	
	@Test
	public void verifyEmailWithEmailMobileCustomer()
	{
		CustomerQuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		assertTrue(customerQuickRegisterService.verifyEmail(new VerifyEmailDTO(savedEntityResult.getCustomer().getCustomerId(),
				savedEntityResult.getCustomer().getEmailHash())));
		
		assertTrue(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomer().getCustomerId(),
				savedEntityResult.getCustomer().getMobilePin())));
		
	}
	
	
	@Test
	public void reSendMobilePin()
	{
		CustomerQuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
			
		assertFalse(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomer().getCustomerId(),
				101010)));
		
		CustomerQuickRegisterDTO entity=customerQuickRegisterService.getByCustomerId(new CustomerIdDTO(savedEntityResult.getCustomer().getCustomerId()));
		
		Integer oldMobilePin=entity.getMobilePin();
		
		assertEquals(1, entity.getMobileVerificationAttempts().intValue());
		
		assertTrue(customerQuickRegisterService.reSendMobilePin(new CustomerIdDTO(savedEntityResult.getCustomer().getCustomerId())));
		
		CustomerQuickRegisterDTO updatedEntity=customerQuickRegisterService.getByCustomerId(new CustomerIdDTO(savedEntityResult.getCustomer().getCustomerId()));
		
		assertNotEquals(oldMobilePin, updatedEntity.getMobilePin());
	}
	
	

	@Test
	public void reSendEmailHash()
	{
		CustomerQuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
			
		CustomerQuickRegisterDTO entity=customerQuickRegisterService.getByCustomerId(new CustomerIdDTO(savedEntityResult.getCustomer().getCustomerId()));
		
		String oldEmailHash=entity.getEmailHash();
		
		assertTrue(customerQuickRegisterService.reSendEmailHash(new CustomerIdDTO(savedEntityResult.getCustomer().getCustomerId())));
		
		CustomerQuickRegisterDTO updatedEntity=customerQuickRegisterService.getByCustomerId(new CustomerIdDTO(savedEntityResult.getCustomer().getCustomerId()));
		
		assertNotEquals(oldEmailHash, updatedEntity.getEmailHash());
	}
	
	
	//@Test
	//public void reSend
	
}
