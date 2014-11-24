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
import com.projectx.rest.domain.CustomerEmailVerificationDetailsDTO;
import com.projectx.rest.domain.CustomerIdDTO;
import com.projectx.rest.domain.CustomerIdEmailDTO;
import com.projectx.rest.domain.CustomerIdMobileDTO;
import com.projectx.rest.domain.CustomerMobileVerificationDetailsDTO;
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
	public void verifyEmailAndMobileWithEmailMobileCustomer()
	{
		CustomerQuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());

		CustomerEmailVerificationDetailsDTO emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdAndEmail(savedEntityResult.getCustomer().getCustomerId(),savedEntityResult.getCustomer().getEmail());
				
		CustomerMobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdAndMobile(savedEntityResult.getCustomer().getCustomerId(), savedEntityResult.getCustomer().getMobile());		
		
		
		assertTrue(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomer().getCustomerId(),savedEntityResult.getCustomer().getMobile(),
				mobileVerificationDetails.getMobilePin())));
		
		
		assertTrue(customerQuickRegisterService.verifyEmail(new VerifyEmailDTO(savedEntityResult.getCustomer().getCustomerId(),
				savedEntityResult.getCustomer().getEmail(),emailVerificationDetails.getEmailHash())));
		
		
	}
	
	
	
	@Test
	public void verifyEmailWithEmailMobileCustomer()
	{
		CustomerQuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		CustomerEmailVerificationDetailsDTO emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdAndEmail(savedEntityResult.getCustomer().getCustomerId(),savedEntityResult.getCustomer().getEmail());
				
		CustomerMobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdAndMobile(savedEntityResult.getCustomer().getCustomerId(), savedEntityResult.getCustomer().getMobile());		
		
		
	
		
		assertTrue(customerQuickRegisterService.verifyEmail(new VerifyEmailDTO(savedEntityResult.getCustomer().getCustomerId(),
				savedEntityResult.getCustomer().getEmail(),emailVerificationDetails.getEmailHash())));
		
		assertTrue(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomer().getCustomerId(),
				savedEntityResult.getCustomer().getMobile(),mobileVerificationDetails.getMobilePin())));
		
	}
	
	
	@Test
	public void reSendMobilePin()
	{
		CustomerQuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
			
		assertFalse(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomerId(),
				savedEntityResult.getMobile(),101010)));
		
		CustomerMobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdAndMobile(savedEntityResult.getCustomerId(),savedEntityResult.getMobile());		
		
		
		Integer oldMobilePin=mobileVerificationDetails.getMobilePin();
		
		assertEquals(1, mobileVerificationDetails.getMobileVerificationAttempts().intValue());
		
		assertTrue(customerQuickRegisterService.reSendMobilePin(new CustomerIdMobileDTO(savedEntityResult.getCustomerId(),savedEntityResult.getMobile())));
		
		mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdAndMobile(savedEntityResult.getCustomerId(),savedEntityResult.getMobile());
		
		assertEquals(oldMobilePin, mobileVerificationDetails.getMobilePin());
	}
	
	
	
	@Test
	public void reSendEmailHash()
	{
		CustomerQuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		CustomerEmailVerificationDetailsDTO emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdAndEmail(savedEntityResult.getCustomerId(),savedEntityResult.getEmail());
		
		String oldEmailHash=emailVerificationDetails.getEmailHash();
		
		assertTrue(customerQuickRegisterService.reSendEmailHash(new CustomerIdEmailDTO(savedEntityResult.getCustomerId(),savedEntityResult.getEmail())));
		
		emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdAndEmail(savedEntityResult.getCustomerId(),savedEntityResult.getEmail());
		
		assertEquals(oldEmailHash, emailVerificationDetails.getEmailHash());
	}


	@Test
	public void reSetMobilePin()
	{
		CustomerQuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
			
		assertFalse(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomerId(),
				savedEntityResult.getMobile(),101010)));
		
		CustomerMobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdAndMobile(savedEntityResult.getCustomerId(),savedEntityResult.getMobile());		
		
		
		Integer oldMobilePin=mobileVerificationDetails.getMobilePin();
		
		assertEquals(1, mobileVerificationDetails.getMobileVerificationAttempts().intValue());
		
		assertTrue(customerQuickRegisterService.reSetMobilePin(new CustomerIdMobileDTO(savedEntityResult.getCustomerId(),savedEntityResult.getMobile())));
		
		mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdAndMobile(savedEntityResult.getCustomerId(),savedEntityResult.getMobile());
		
		assertNotEquals(oldMobilePin, mobileVerificationDetails.getMobilePin());
	}
	
	
	
	@Test
	public void reSetEmailHash()
	{
		CustomerQuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		CustomerEmailVerificationDetailsDTO emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdAndEmail(savedEntityResult.getCustomerId(),savedEntityResult.getEmail());
		
		String oldEmailHash=emailVerificationDetails.getEmailHash();
		
		assertTrue(customerQuickRegisterService.reSetEmailHash(new CustomerIdEmailDTO(savedEntityResult.getCustomerId(),savedEntityResult.getEmail())));
		
		emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdAndEmail(savedEntityResult.getCustomerId(),savedEntityResult.getEmail());
		
		assertNotEquals(oldEmailHash, emailVerificationDetails.getEmailHash());
	}

	
	
	@Test
	public void verifyLoginDetails()
	{
		assertNull(customerQuickRegisterService.verifyLoginDetails(standardLoginVerificationWithEmail()).getCustomerId());
		
		assertNull(customerQuickRegisterService.verifyLoginDetails(standardLoginVerificationWithMobile()).getCustomerId());
		
		CustomerQuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		CustomerEmailVerificationDetailsDTO emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdAndEmail(savedEntityResult.getCustomerId(),savedEntityResult.getEmail());
		
		assertTrue(customerQuickRegisterService.verifyEmail(new VerifyEmailDTO(savedEntityResult.getCustomerId(), savedEntityResult.getEmail(),emailVerificationDetails.getEmailHash())));
		
		CustomerAuthenticationDetailsDTO authenticationDetailsDTO=customerQuickRegisterService.getAuthenticationDetailsByCustomerId(savedEntityResult.getCustomerId());
		
		
		
		assertNotNull(customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(savedEntityResult.getEmail(),authenticationDetailsDTO.getPassword())).getCustomerId());
		
		assertNotNull(customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(Long.toString(savedEntityResult.getMobile()), authenticationDetailsDTO.getPassword())).getCustomerId());
		
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
		
		CustomerQuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		CustomerMobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdAndMobile(savedEntityResult.getCustomerId(),savedEntityResult.getMobile());
		
		
		assertTrue(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomerId(),savedEntityResult.getMobile(),
				mobileVerificationDetails.getMobilePin())));
		
		assertTrue(customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(savedEntityResult.getCustomerId(),
				CUST_PASSWORD_CHANGED)));
		
		assertNotNull(customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(savedEntityResult.getEmail(),
				CUST_PASSWORD_CHANGED)).getCustomerId());
		
		assertTrue(customerQuickRegisterService.resetPassword(savedEntityResult.getCustomerId()));
		
		assertNull(customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(savedEntityResult.getEmail(),
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
