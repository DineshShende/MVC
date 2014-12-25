package com.projectx.mvc.services.quickregister;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
import static com.projectx.mvc.fixtures.quickregister.AuthenticationDetailsDataFixtures.*;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.mvc.config.BasicConfig;
import com.projectx.mvc.domain.quickregister.CustomerDocumetDTO;
import com.projectx.mvc.domain.quickregister.QuickRegisterEntity;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterStringStatusDTO;
import com.projectx.rest.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailDTO;
import com.projectx.rest.domain.quickregister.VerifyMobileDTO;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicConfig.class)
@ActiveProfiles(value="Dev")

public class CustomerQuickRegisterTest {

	@Autowired
	QuickRegisterService customerQuickRegisterService; 
	
	
	
	@Before
	public void setUp()
	{
		customerQuickRegisterService.clearTestData();
	}

	
	@Test
	public void checkIfExist() throws Exception {
		
		QuickRegisterStringStatusDTO status=customerQuickRegisterService.checkIfAlreadyExist(standardEmailMobileCustomerDTO());
		
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
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());

		assertTrue( savedEntityResult.getStatus());
		
		assertNotNull(savedEntityResult.getCustomer());
		
	}

	@Test
	public void addNewCustomerWithMobileCustomer()
	{
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardMobileCustomerDTO());

		assertTrue( savedEntityResult.getStatus());
		
		assertNotNull(savedEntityResult.getCustomer());
		
	}
	
	
	@Test
	public void getCustomerById()
	{
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		QuickRegisterDTO entity=customerQuickRegisterService.getByCustomerIdType(new CustomerIdTypeDTO(savedEntityResult.getCustomer().getCustomerId(),CUST_TYPE));
		
		assertNotNull(entity);
	}
	

	
	@Test
	public void verifyEmailAndMobileWithEmailMobileCustomer()
	{
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());

		EmailVerificationDetailsDTO emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdTypeAndEmail(savedEntityResult.getCustomer().getCustomerId(),CUST_TYPE,savedEntityResult.getCustomer().getEmail());
				
		MobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomer().getCustomerId(),CUST_TYPE, savedEntityResult.getCustomer().getMobile());		
		
		
		assertTrue(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomer().getCustomerId(),CUST_TYPE,savedEntityResult.getCustomer().getMobile(),
				mobileVerificationDetails.getMobilePin())));
		
		
		assertTrue(customerQuickRegisterService.verifyEmail(new VerifyEmailDTO(savedEntityResult.getCustomer().getCustomerId(),CUST_TYPE,
				savedEntityResult.getCustomer().getEmail(),emailVerificationDetails.getEmailHash())));
		
		
	}
	

	
	@Test
	public void verifyEmailWithEmailMobileCustomer()
	{
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		EmailVerificationDetailsDTO emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdTypeAndEmail(savedEntityResult.getCustomer().getCustomerId(),CUST_TYPE,savedEntityResult.getCustomer().getEmail());
				
		MobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomer().getCustomerId(),CUST_TYPE, savedEntityResult.getCustomer().getMobile());		
		
		
	
		
		assertTrue(customerQuickRegisterService.verifyEmail(new VerifyEmailDTO(savedEntityResult.getCustomer().getCustomerId(),CUST_TYPE,
				savedEntityResult.getCustomer().getEmail(),emailVerificationDetails.getEmailHash())));
		
		assertTrue(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomer().getCustomerId(),CUST_TYPE,
				savedEntityResult.getCustomer().getMobile(),mobileVerificationDetails.getMobilePin())));
		
	}
		
	
	
	@Test
	public void reSendMobilePin()
	{
		QuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
			
		assertFalse(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomerId(),CUST_TYPE,
				savedEntityResult.getMobile(),101010)));
		
		MobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomerId(),CUST_TYPE,savedEntityResult.getMobile());		
		
		
		Integer oldMobilePin=mobileVerificationDetails.getMobilePin();
		
		assertEquals(1, mobileVerificationDetails.getMobileVerificationAttempts().intValue());
		
		assertTrue(customerQuickRegisterService.reSendMobilePin(new CustomerIdTypeMobileDTO(savedEntityResult.getCustomerId(),CUST_TYPE,savedEntityResult.getMobile())));
		
		mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomerId(),CUST_TYPE,savedEntityResult.getMobile());
		
		assertEquals(oldMobilePin, mobileVerificationDetails.getMobilePin());
	}
	
	
	
	@Test
	public void reSendEmailHash()
	{
		QuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		EmailVerificationDetailsDTO emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdTypeAndEmail(savedEntityResult.getCustomerId(),CUST_TYPE,savedEntityResult.getEmail());
		
		String oldEmailHash=emailVerificationDetails.getEmailHash();
		
		assertTrue(customerQuickRegisterService.reSendEmailHash(new CustomerIdTypeEmailDTO(savedEntityResult.getCustomerId(),CUST_TYPE,savedEntityResult.getEmail())));
		
		emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdTypeAndEmail(savedEntityResult.getCustomerId(),CUST_TYPE,savedEntityResult.getEmail());
		
		assertEquals(oldEmailHash, emailVerificationDetails.getEmailHash());
	}

	
	
	@Test
	public void reSetMobilePin()
	{
		QuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
			
		assertFalse(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomerId(),CUST_TYPE,
				savedEntityResult.getMobile(),101010)));
		
		MobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomerId(),CUST_TYPE,savedEntityResult.getMobile());		
		
		
		Integer oldMobilePin=mobileVerificationDetails.getMobilePin();
		
		assertEquals(1, mobileVerificationDetails.getMobileVerificationAttempts().intValue());
		
		assertTrue(customerQuickRegisterService.reSetMobilePin(new CustomerIdTypeMobileDTO(savedEntityResult.getCustomerId(),CUST_TYPE,savedEntityResult.getMobile())));
		
		mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomerId(),CUST_TYPE,savedEntityResult.getMobile());
		
		assertNotEquals(oldMobilePin, mobileVerificationDetails.getMobilePin());
	}
	
		
	@Test
	public void reSetEmailHash()
	{
		QuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		EmailVerificationDetailsDTO emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdTypeAndEmail(savedEntityResult.getCustomerId(),CUST_TYPE,savedEntityResult.getEmail());
		
		String oldEmailHash=emailVerificationDetails.getEmailHash();
		
		assertTrue(customerQuickRegisterService.reSetEmailHash(new CustomerIdTypeEmailDTO(savedEntityResult.getCustomerId(),CUST_TYPE,savedEntityResult.getEmail())));
		
		emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdTypeAndEmail(savedEntityResult.getCustomerId(),CUST_TYPE,savedEntityResult.getEmail());
		
		assertNotEquals(oldEmailHash, emailVerificationDetails.getEmailHash());
	}

		
	@Test
	public void verifyLoginDetails()
	{
		assertNull(customerQuickRegisterService.verifyLoginDetails(standardLoginVerificationWithEmail()).getKey());
		
		assertNull(customerQuickRegisterService.verifyLoginDetails(standardLoginVerificationWithMobile()).getKey());
		
		QuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		EmailVerificationDetailsDTO emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdTypeAndEmail(savedEntityResult.getCustomerId(),CUST_TYPE,savedEntityResult.getEmail());
		
		assertTrue(customerQuickRegisterService.verifyEmail(new VerifyEmailDTO(savedEntityResult.getCustomerId(),CUST_TYPE, savedEntityResult.getEmail(),emailVerificationDetails.getEmailHash())));
		
		AuthenticationDetailsDTO authenticationDetailsDTO=customerQuickRegisterService
				.getAuthenticationDetailsByCustomerIdType(savedEntityResult.getCustomerId(),CUST_TYPE);
		
		
		
		assertNotNull(customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(savedEntityResult.getEmail(),authenticationDetailsDTO.getPassword())).getKey().getCustomerId());
		
		assertNotNull(customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(Long.toString(savedEntityResult.getMobile()), authenticationDetailsDTO.getPassword())).getKey().getCustomerId());
		
	}

	
	
	@Test
	public void updatePassword()
	{
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		assertTrue(customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(savedEntityResult.getCustomer().getCustomerId(),CUST_TYPE,
																									"654321")));
	}

	
	@Test 
	public void resetPassword()
	{
		
		QuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		MobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomerId(),CUST_TYPE,savedEntityResult.getMobile());
		
		
		assertTrue(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomerId(),CUST_TYPE,savedEntityResult.getMobile(),
				mobileVerificationDetails.getMobilePin())));
		
		assertTrue(customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(savedEntityResult.getCustomerId(),CUST_TYPE,
				CUST_PASSWORD_CHANGED)));
		
		assertNotNull(customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(savedEntityResult.getEmail(),
				CUST_PASSWORD_CHANGED)).getKey().getCustomerId());
		
		assertTrue(customerQuickRegisterService.resetPassword(savedEntityResult.getCustomerId(),CUST_TYPE));
		
		assertNull(customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(savedEntityResult.getEmail(),
				CUST_PASSWORD_CHANGED)).getKey());
		
	}
		
	
	@Test
	public void resetPasswordRedirect()
	{
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
					
		assertNotNull(customerQuickRegisterService.resetPasswordRedirect(CUST_EMAIL).getCustomerId());
		
		assertNotNull(customerQuickRegisterService.resetPasswordRedirect(Long.toString(CUST_MOBILE)).getCustomerId());
	}
	
	/*
	@Test
	public void saveCustomerDocumentI()
	{
	
		CustomerDocumetDTO customerDocumetDTO=new CustomerDocumetDTO(3L, "dsjgfjegufgjvdfjv".getBytes());
		
		assertEquals(customerDocumetDTO,customerQuickRegisterService.saveCustomerDocumet(customerDocumetDTO));
		
		assertEquals(customerDocumetDTO,customerQuickRegisterService.getCustomerDocumetById(customerDocumetDTO.getCustomerId()));
		
	}
	*/
}
