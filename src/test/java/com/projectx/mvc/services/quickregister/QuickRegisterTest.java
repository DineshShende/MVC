package com.projectx.mvc.services.quickregister;

import static com.projectx.mvc.fixtures.completeregister.VendorDetailsDataFixture.*;
import static com.projectx.mvc.fixtures.quickregister.AuthenticationDetailsDataFixtures.standardLoginVerificationWithEmail;
import static com.projectx.mvc.fixtures.quickregister.AuthenticationDetailsDataFixtures.standardLoginVerificationWithMobile;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.*;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import com.projectx.mvc.config.BasicConfig;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.exception.repository.quickregister.AuthenticationDetailsNotFoundException;
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.completeregister.DocumentDetailsService;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailDTO;
import com.projectx.rest.domain.quickregister.VerifyMobileDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicConfig.class)
@ActiveProfiles(value="Dev")

public class QuickRegisterTest {

	@Autowired
	QuickRegisterService customerQuickRegisterService; 
	
	@Autowired
	DocumentDetailsService documentDetailsService; 
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	

	
	private Integer EMAIL_REQ=1;
	
	private Integer MOBILE_REQ=2;
	
	@Before
	public void setUp()
	{
		customerQuickRegisterService.clearTestData();
		customerDetailsService.clearTestData();
		vendorDetailsService.clearTestData();
	}

	
	@Test
	public void quickRegisterSucessfull() throws Exception {
		
	
		
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		assertEquals(REGISTER_REGISTERED_SUCESSFULLY ,savedEntityResult.getStatus());
		
	
	}
	
	
	
	@Test
	public void quickRegisterWithExistingEmailMobileCustomer()
	{
		customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED ,savedEntityResult.getStatus());
		
	}

	
	@Test
	public void quickRegisterWithExistingEmailCustomer()
	{
		customerQuickRegisterService.addNewCustomer(standardEmailCustomerDTO());
		
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED ,savedEntityResult.getStatus());
		
	}

	
	@Test
	public void quickRegisterWithExistingMobileCustomer()
	{
		customerQuickRegisterService.addNewCustomer(standardMobileCustomerDTO());
		
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED ,savedEntityResult.getStatus());
		
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

		assertEquals(REGISTER_REGISTERED_SUCESSFULLY ,savedEntityResult.getStatus());
		
		assertNotNull(savedEntityResult.getCustomer());
		
	}

	
	@Test
	public void addNewCustomerWithMobileCustomer()
	{
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardMobileCustomerDTO());

		assertEquals(REGISTER_REGISTERED_SUCESSFULLY ,savedEntityResult.getStatus());
		
		assertNotNull(savedEntityResult.getCustomer());
		
	}
	
	
	@Test
	public void getCustomerById()
	{
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		QuickRegisterDTO entity=customerQuickRegisterService.getByCustomerIdType(new CustomerIdTypeDTO(savedEntityResult.getCustomer().getCustomerId(),ENTITY_TYPE_CUSTOMER));
		
		assertNotNull(entity);
	}
	

	
	@Test
	public void verifyEmailAndMobileWithEmailMobileCustomer()
	{
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());

		EmailVerificationDetailsDTO emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdTypeAndEmail(savedEntityResult.getCustomer().getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY);
				
		MobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomer().getCustomerId(),ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);		
		
		
		assertTrue(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomer().getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY,
				mobileVerificationDetails.getMobilePin(),CUST_UPDATED_BY,savedEntityResult.getCustomer().getCustomerId())));
		
		
		assertTrue(customerQuickRegisterService.verifyEmail(new VerifyEmailDTO(savedEntityResult.getCustomer().getCustomerId(),ENTITY_TYPE_CUSTOMER,
				ENTITY_TYPE_PRIMARY,emailVerificationDetails.getEmailHash(),CUST_UPDATED_BY,savedEntityResult.getCustomer().getCustomerId())));
		
		
	}
	

	
	@Test
	public void verifyEmailWithEmailMobileCustomer()
	{
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		EmailVerificationDetailsDTO emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdTypeAndEmail(savedEntityResult.getCustomer().getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY);
				
		MobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomer().getCustomerId(),ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);		
		
		
	
		
		assertTrue(customerQuickRegisterService.verifyEmail(new VerifyEmailDTO(savedEntityResult.getCustomer().getCustomerId(),ENTITY_TYPE_CUSTOMER,
				ENTITY_TYPE_PRIMARY,emailVerificationDetails.getEmailHash(),CUST_UPDATED_BY,savedEntityResult.getCustomer().getCustomerId())));
		
		assertTrue(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomer().getCustomerId(),ENTITY_TYPE_CUSTOMER,
				ENTITY_TYPE_PRIMARY,mobileVerificationDetails.getMobilePin(),CUST_UPDATED_BY,savedEntityResult.getCustomer().getCustomerId())));
		
	}
		
	
	
	@Test
	public void reSendMobilePin()
	{
		QuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
			
		assertFalse(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,
				savedEntityResult.getCustomerType(),101010,CUST_UPDATED_BY,savedEntityResult.getCustomerId())));
		
		MobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY);		
		
		
		Integer oldMobilePin=mobileVerificationDetails.getMobilePin();
		
		assertEquals(1, mobileVerificationDetails.getMobileVerificationAttempts().intValue());
		
		assertTrue(customerQuickRegisterService.reSendMobilePin(new CustomerIdTypeMobileTypeRequestedByDTO
				(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY,CUST_UPDATED_BY,savedEntityResult.getCustomerId())));
		
		mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY);
		
		assertEquals(oldMobilePin, mobileVerificationDetails.getMobilePin());
	}
	
	
	
	@Test
	public void reSendEmailHash()
	{
		QuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		EmailVerificationDetailsDTO emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdTypeAndEmail(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY);
		
		String oldEmailHash=emailVerificationDetails.getEmailHash();
		
		assertTrue(customerQuickRegisterService.reSendEmailHash(new CustomerIdTypeEmailTypeUpdatedByDTO(savedEntityResult.getCustomerId(),
				ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY,CUST_UPDATED_BY,savedEntityResult.getCustomerId())));
		
		emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdTypeAndEmail(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY);
		
		assertEquals(oldEmailHash, emailVerificationDetails.getEmailHash());
	}

	
	
	@Test
	public void reSetMobilePin()
	{
		QuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
			
		assertFalse(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,
				ENTITY_TYPE_PRIMARY,101010,CUST_UPDATED_BY,savedEntityResult.getCustomerId())));
		
		MobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY);		
		
		
		Integer oldMobilePin=mobileVerificationDetails.getMobilePin();
		
		assertEquals(1, mobileVerificationDetails.getMobileVerificationAttempts().intValue());
		
		assertTrue(customerQuickRegisterService.reSetMobilePin(new CustomerIdTypeMobileTypeRequestedByDTO(savedEntityResult.getCustomerId(),
				ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY,CUST_UPDATED_BY,savedEntityResult.getCustomerId())));
		
		mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY);
		
		assertNotEquals(oldMobilePin, mobileVerificationDetails.getMobilePin());
	}
	
	
	
	@Test
	public void reSetEmailHash()
	{
		QuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		EmailVerificationDetailsDTO emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdTypeAndEmail(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY);
		
		String oldEmailHash=emailVerificationDetails.getEmailHash();
		
		assertTrue(customerQuickRegisterService.reSetEmailHash(new CustomerIdTypeEmailTypeUpdatedByDTO(savedEntityResult.getCustomerId(),
				ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY,CUST_UPDATED_BY,savedEntityResult.getCustomerId())));
		
		emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdTypeAndEmail(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY);
		
		assertNotEquals(oldEmailHash, emailVerificationDetails.getEmailHash());
	}

	
	
	@Test
	public void verifyLoginDetails()
	{
		AuthenticationDetailsDTO authenticationDetails=null;
		
		try{
			authenticationDetails=customerQuickRegisterService.verifyLoginDetails(standardLoginVerificationWithEmail());
		}catch(AuthenticationDetailsNotFoundException e)
		{
			assertNull(authenticationDetails);
		}
		
		try{
			authenticationDetails=customerQuickRegisterService.verifyLoginDetails(standardLoginVerificationWithMobile());
		}catch(AuthenticationDetailsNotFoundException e)
		{
			assertNull(authenticationDetails);
		}
		
		
		QuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		EmailVerificationDetailsDTO emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdTypeAndEmail(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY);
		
		assertTrue(customerQuickRegisterService.verifyEmail(new VerifyEmailDTO(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER, 
				ENTITY_TYPE_PRIMARY,emailVerificationDetails.getEmailHash(),CUST_UPDATED_BY,savedEntityResult.getCustomerId())));
		
		AuthenticationDetails authenticationDetailsDTO=customerQuickRegisterService
				.getAuthenticationDetailsByCustomerIdType(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER);
		

		AuthenticationDetailsDTO verifyLoginDetails=customerQuickRegisterService
				.verifyLoginDetails(new LoginVerificationDTO(authenticationDetailsDTO.getEmail(), authenticationDetailsDTO.getPassword()));
		
		assertEquals("Default", verifyLoginDetails.getPasswordType());
		assertFalse(verifyLoginDetails.getIsCompleteRegisterCompleted());
		
		
	}

	
	
	@Test
	public void updatePassword()
	{
		QuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		MobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY);
		
		
		assertTrue(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY,
				mobileVerificationDetails.getMobilePin(),CUST_UPDATED_BY,savedEntityResult.getCustomerId())));
		
		AuthenticationDetails authenticationDetails=customerQuickRegisterService
				.getAuthenticationDetailsByCustomerIdType(savedEntityResult.getCustomerId(),
						savedEntityResult.getCustomerType());
		
		assertTrue(customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(new AuthenticationDetailsKey(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER),
										"654321",authenticationDetails.getPassword(),true,CUST_UPDATED_BY,savedEntityResult.getCustomerId())));
	}


	
	@Test 
	public void resetPassword()
	{
		
		QuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		MobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY);
		
		
		assertTrue(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY,
				mobileVerificationDetails.getMobilePin(),CUST_UPDATED_BY,savedEntityResult.getCustomerId())));
		
		AuthenticationDetails authenticationDetails=customerQuickRegisterService
				.getAuthenticationDetailsByCustomerIdType(savedEntityResult.getCustomerId(),
						savedEntityResult.getCustomerType());
		
		assertTrue(customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(new AuthenticationDetailsKey(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER),
				CUST_PASSWORD_CHANGED,authenticationDetails.getPassword(),true,CUST_UPDATED_BY,savedEntityResult.getCustomerId())));
		
		assertNotNull(customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(savedEntityResult.getEmail(),
				CUST_PASSWORD_CHANGED)).getKey().getCustomerId());
		
		assertTrue(customerQuickRegisterService.resetPassword(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,
				MOBILE_REQ,CUST_UPDATED_BY,savedEntityResult.getCustomerId()));
		
		AuthenticationDetailsDTO authenticationDetailsDTO=null;
		
		try{
			authenticationDetailsDTO=customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(savedEntityResult.getEmail(),
					CUST_PASSWORD_CHANGED));
			
		}catch(AuthenticationDetailsNotFoundException e)
		{
			assertNull(authenticationDetailsDTO);
		}
		
	}
		
	
	@Test
	public void resetPasswordRedirect()
	{
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
					
		assertNotNull(customerQuickRegisterService.resetPasswordRedirect(CUST_EMAIL,CUST_UPDATED_BY,CUST_ID).getCustomerId());
		
		assertNotNull(customerQuickRegisterService.resetPasswordRedirect(Long.toString(CUST_MOBILE),CUST_UPDATED_BY,CUST_ID).getCustomerId());
	}
	

	@Test
	public void createCustomerDetailsAfterLoginVerification()
	{
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		MobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomer().getCustomerId(),
						ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY);
		
		
		assertTrue(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomer().getCustomerId(),
				ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY,mobileVerificationDetails.getMobilePin(),CUST_UPDATED_BY,
				savedEntityResult.getCustomer().getCustomerId())));
		
		
		AuthenticationDetails result=customerQuickRegisterService
				.getAuthenticationDetailsByCustomerIdType(savedEntityResult.getCustomer().getCustomerId(), savedEntityResult.getCustomer().getCustomerType());
		
		AuthenticationDetailsDTO verifyLoginDetails=customerQuickRegisterService
				.verifyLoginDetails(new LoginVerificationDTO(result.getMobile().toString(),result.getPassword() ));
		
		
		assertEquals("Default", verifyLoginDetails.getPasswordType());
		assertFalse(verifyLoginDetails.getIsCompleteRegisterCompleted());
		
		
		assertTrue(customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(new AuthenticationDetailsKey(savedEntityResult.getCustomer().getCustomerId(),
				ENTITY_TYPE_CUSTOMER),"654321",result.getPassword(),true,CUST_UPDATED_BY,savedEntityResult.getCustomer().getCustomerId())));
		

		try{
			
			customerDetailsService.getCustomerDetailsById(savedEntityResult.getCustomer().getCustomerId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		verifyLoginDetails=customerQuickRegisterService
				.verifyLoginDetails(new LoginVerificationDTO(result.getMobile().toString(), "654321"));
		
		assertEquals("Changed", verifyLoginDetails.getPasswordType());
		assertFalse(verifyLoginDetails.getIsCompleteRegisterCompleted());
		
		CustomerDetailsDTO customerDetailsDTO=customerDetailsService.getCustomerDetailsById(savedEntityResult.getCustomer().getCustomerId());
		
		assertEquals(savedEntityResult.getCustomer().getCustomerId(), customerDetailsDTO.getCustomerId());
		
		verifyLoginDetails=customerQuickRegisterService
				.verifyLoginDetails(new LoginVerificationDTO(result.getMobile().toString(), "654321"));
		
		assertEquals("Changed", verifyLoginDetails.getPasswordType());
		assertTrue(verifyLoginDetails.getIsCompleteRegisterCompleted());
			
	}
	
	@Test
	public void createVendorDetailsAfterLoginVerification()
	{
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileVendorDTO());
		
		MobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomer().getCustomerId(),
						ENTITY_TYPE_VENDOR,ENTITY_TYPE_PRIMARY);
		
		
		assertTrue(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomer().getCustomerId(),
				ENTITY_TYPE_VENDOR,ENTITY_TYPE_PRIMARY,mobileVerificationDetails.getMobilePin(),CUST_UPDATED_BY,
				savedEntityResult.getCustomer().getCustomerId())));
		
		
		AuthenticationDetails result=customerQuickRegisterService
				.getAuthenticationDetailsByCustomerIdType(savedEntityResult.getCustomer().getCustomerId(), savedEntityResult.getCustomer().getCustomerType());
		
		AuthenticationDetailsDTO verifyLoginDetails=customerQuickRegisterService
				.verifyLoginDetails(new LoginVerificationDTO(result.getMobile().toString(),result.getPassword() ));
		
		
		assertEquals("Default", verifyLoginDetails.getPasswordType());
		assertFalse(verifyLoginDetails.getIsCompleteRegisterCompleted());
		
		
		assertTrue(customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(new AuthenticationDetailsKey(savedEntityResult.getCustomer().getCustomerId(),
				ENTITY_TYPE_VENDOR),"654321",result.getPassword(),true,CUST_UPDATED_BY,savedEntityResult.getCustomer().getCustomerId())));
		

		try{
			
			vendorDetailsService.getCustomerDetailsById(savedEntityResult.getCustomer().getCustomerId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		verifyLoginDetails=customerQuickRegisterService
				.verifyLoginDetails(new LoginVerificationDTO(result.getMobile().toString(), "654321"));
		
		assertEquals("Changed", verifyLoginDetails.getPasswordType());
		assertFalse(verifyLoginDetails.getIsCompleteRegisterCompleted());
		
		VendorDetailsDTO vendorDetailsDTO=vendorDetailsService.getCustomerDetailsById(savedEntityResult.getCustomer().getCustomerId());
		
		assertEquals(savedEntityResult.getCustomer().getCustomerId(), vendorDetailsDTO.getVendorId());
		
		verifyLoginDetails=customerQuickRegisterService
				.verifyLoginDetails(new LoginVerificationDTO(result.getMobile().toString(), "654321"));
		
		assertEquals("Changed", verifyLoginDetails.getPasswordType());
		assertTrue(verifyLoginDetails.getIsCompleteRegisterCompleted());
		
		
	}
	
	
}
