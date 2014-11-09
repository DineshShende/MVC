package com.projectx.mvc.services;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
import static com.projectx.mvc.controller.fixtues.CustomerQuickRegisterDataFixture.*;
import static com.projectx.mvc.controller.fixtues.CustomerAuthenticationDetailsDataFixtures.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.mvc.config.BasicConfig;
import com.projectx.mvc.domain.CustomerDocumetDTO;
import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.mvc.domain.UpdatePasswordDTO;
import com.projectx.rest.domain.CustomerAuthenticationDetailsDTO;
import com.projectx.rest.domain.CustomerIdDTO;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;
import com.projectx.rest.domain.CustomerQuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.CustomerQuickRegisterStringStatusDTO;
import com.projectx.rest.domain.LoginVerificationDTO;
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
		
		CustomerQuickRegisterStringStatusDTO status=customerQuickRegisterService.checkIfAlreadyExist(standardEmailMobileCustomerDTO());
		
		assertEquals(REGISTER_NOT_REGISTERED, status.getStatus());
	}
	
	@Test
	public void checkIfExistWithExistingEmailMobileCustomer()
	{
		customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		String status=customerQuickRegisterService.checkIfAlreadyExist(standardEmailMobileCustomerDTO()).getStatus();
		
		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED, status);
	}

	@Test
	public void checkIfExistWithExistingEmailCustomer()
	{
		customerQuickRegisterService.addNewCustomer(standardEmailCustomerDTO());
		
		String status=customerQuickRegisterService.checkIfAlreadyExist(standardEmailMobileCustomerDTO()).getStatus();
		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED, status);
	}

	
	@Test
	public void checkIfExistWithExistingMobileCustomer()
	{
		customerQuickRegisterService.addNewCustomer(standardMobileCustomerDTO());
		
		String status=customerQuickRegisterService.checkIfAlreadyExist(standardEmailMobileCustomerDTO()).getStatus();
		
		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED, status);
	}
	
	
	@Test
	public void populateStatusForDuplicateFields()
	{
		assertEquals(MSG_REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED, customerQuickRegisterService.populateMessageForDuplicationField(REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED));
		
		assertEquals(MSG_REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED, customerQuickRegisterService.populateMessageForDuplicationField(REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED));
		
		assertEquals(MSG_REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED, customerQuickRegisterService.populateMessageForDuplicationField(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED));
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


	@Test
	public void verifyLoginDetails()
	{
		assertNotNull(customerQuickRegisterService.verifyLoginDetails(standardLoginVerificationWithEmail()));
		
		assertNotNull(customerQuickRegisterService.verifyLoginDetails(standardLoginVerificationWithMobile()));
		
	//	CustomerQuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
	//	assertTrue(customerQuickRegisterService.verifyEmail(new VerifyEmailDTO(savedEntityResult.getCustomer().getCustomerId(), savedEntityResult.getCustomer().getEmailHash())));
		
		//customerQuickRegisterService.
		
		//assertNotNull(customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(savedEntityResult.getCustomer().getEmail(), null, savedEntityResult.getCustomer().getPassword())).getCustomerId());
		
		//assertNotNull(customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(null, savedEntityResult.getCustomer().getMobile(), savedEntityResult.getCustomer().getPassword())).getCustomerId());
		
	}
	
	@Test
	public void updatePassword()
	{
		CustomerQuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		assertTrue(customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(savedEntityResult.getCustomer().getCustomerId(),
																									"654321")));
	}
	
	
	@Test
	public void saveCustomerDocumentI()
	{
	
		CustomerDocumetDTO customerDocumetDTO=new CustomerDocumetDTO(3L, "dsjgfjegufgjvdfjv".getBytes());
		
		assertEquals(customerDocumetDTO,customerQuickRegisterService.saveCustomerDocumet(customerDocumetDTO));
		
		assertEquals(customerDocumetDTO,customerQuickRegisterService.getCustomerDocumetById(customerDocumetDTO.getCustomerId()));
		
	}

	
	@Test 
	public void resetPassword()
	{
		
		CustomerQuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		assertTrue(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomer().getCustomerId(),
				savedEntityResult.getCustomer().getMobilePin())));
		
		assertTrue(customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(savedEntityResult.getCustomer().getCustomerId(),
				CUST_PASSWORD_CHANGED)));
		
		assertNotNull(customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(savedEntityResult.getCustomer().getEmail(),
				CUST_PASSWORD_CHANGED)).getCustomerId());
		
		assertTrue(customerQuickRegisterService.resetPassword(savedEntityResult.getCustomer().getCustomerId()));
		
		assertNull(customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(savedEntityResult.getCustomer().getEmail(),
				CUST_PASSWORD_CHANGED)).getCustomerId());
		
	}
		
	
	@Test
	public void resetPasswordRedirect()
	{
		CustomerQuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
					
		assertNotNull(customerQuickRegisterService.resetPasswordRedirect(CUST_EMAIL).getCustomerId());
		
		assertNotNull(customerQuickRegisterService.resetPasswordRedirect(Long.toString(CUST_MOBILE)).getCustomerId());
	}
	
	
}
