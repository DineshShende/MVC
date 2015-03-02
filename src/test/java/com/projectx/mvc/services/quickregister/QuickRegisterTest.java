package com.projectx.mvc.services.quickregister;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
import static com.projectx.mvc.fixtures.quickregister.AuthenticationDetailsDataFixtures.*;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.mvc.fixtures.completeregister.DocumentDetailsDataFixture.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import com.projectx.mvc.config.BasicConfig;
import com.projectx.mvc.domain.quickregister.CustomerDocumetDTO;
import com.projectx.mvc.domain.quickregister.QuickRegisterEntity;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.exception.repository.quickregister.AuthenticationDetailsNotFoundException;
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.completeregister.DocumentDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterStatusDTO;
import com.projectx.rest.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailDTO;
import com.projectx.rest.domain.quickregister.VerifyMobileDTO;

import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.*;

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
	
	@Before
	public void setUp()
	{
		customerQuickRegisterService.clearTestData();
		customerDetailsService.clearTestData();
	}

	
	@Test
	public void checkIfExist() throws Exception {
		
		QuickRegisterStatusDTO status=customerQuickRegisterService.checkIfAlreadyExist(standardEmailMobileCustomerDTO());
		
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
				mobileVerificationDetails.getMobilePin(),CUST_UPDATED_BY)));
		
		
		assertTrue(customerQuickRegisterService.verifyEmail(new VerifyEmailDTO(savedEntityResult.getCustomer().getCustomerId(),ENTITY_TYPE_CUSTOMER,
				ENTITY_TYPE_PRIMARY,emailVerificationDetails.getEmailHash(),CUST_UPDATED_BY)));
		
		
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
				ENTITY_TYPE_PRIMARY,emailVerificationDetails.getEmailHash(),CUST_UPDATED_BY)));
		
		assertTrue(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomer().getCustomerId(),ENTITY_TYPE_CUSTOMER,
				ENTITY_TYPE_PRIMARY,mobileVerificationDetails.getMobilePin(),CUST_UPDATED_BY)));
		
	}
		
	
	
	@Test
	public void reSendMobilePin()
	{
		QuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
			
		assertFalse(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,
				savedEntityResult.getCustomerType(),101010,CUST_UPDATED_BY)));
		
		MobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY);		
		
		
		Integer oldMobilePin=mobileVerificationDetails.getMobilePin();
		
		assertEquals(1, mobileVerificationDetails.getMobileVerificationAttempts().intValue());
		
		assertTrue(customerQuickRegisterService.reSendMobilePin(new CustomerIdTypeMobileTypeDTO(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY)));
		
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
		
		assertTrue(customerQuickRegisterService.reSendEmailHash(new CustomerIdTypeEmailTypeDTO(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY)));
		
		emailVerificationDetails=customerQuickRegisterService.
				getEmailVerificationDetailsByCustomerIdTypeAndEmail(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY);
		
		assertEquals(oldEmailHash, emailVerificationDetails.getEmailHash());
	}

	
	
	@Test
	public void reSetMobilePin()
	{
		QuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
			
		assertFalse(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,
				ENTITY_TYPE_PRIMARY,101010,CUST_UPDATED_BY)));
		
		MobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService.
				getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY);		
		
		
		Integer oldMobilePin=mobileVerificationDetails.getMobilePin();
		
		assertEquals(1, mobileVerificationDetails.getMobileVerificationAttempts().intValue());
		
		assertTrue(customerQuickRegisterService.reSetMobilePin(new CustomerIdTypeMobileTypeDTO(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY)));
		
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
		
		assertTrue(customerQuickRegisterService.reSetEmailHash(new CustomerIdTypeEmailTypeDTO(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY)));
		
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
		
		assertTrue(customerQuickRegisterService.verifyEmail(new VerifyEmailDTO(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY,emailVerificationDetails.getEmailHash(),CUST_UPDATED_BY)));
		
		AuthenticationDetailsDTO authenticationDetailsDTO=customerQuickRegisterService
				.getAuthenticationDetailsByCustomerIdType(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER);
		
		
		
		assertNotNull(customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(savedEntityResult.getEmail(),authenticationDetailsDTO.getPassword())).getKey().getCustomerId());
		
		assertNotNull(customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(Long.toString(savedEntityResult.getMobile()), authenticationDetailsDTO.getPassword())).getKey().getCustomerId());
		
	}

	
	
	@Test
	public void updatePassword()
	{
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		assertTrue(customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(new AuthenticationDetailsKey(savedEntityResult.getCustomer().getCustomerId(),ENTITY_TYPE_CUSTOMER),
																									"654321")));
	}


	
	@Test 
	public void resetPassword()
	{
		
		QuickRegisterDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		MobileVerificationDetailsDTO mobileVerificationDetails=customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY);
		
		
		assertTrue(customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY,
				mobileVerificationDetails.getMobilePin(),CUST_UPDATED_BY)));
		
		assertTrue(customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(new AuthenticationDetailsKey(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER),
				CUST_PASSWORD_CHANGED)));
		
		assertNotNull(customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(savedEntityResult.getEmail(),
				CUST_PASSWORD_CHANGED)).getKey().getCustomerId());
		
		assertTrue(customerQuickRegisterService.resetPassword(savedEntityResult.getCustomerId(),ENTITY_TYPE_CUSTOMER));
		
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
					
		assertNotNull(customerQuickRegisterService.resetPasswordRedirect(CUST_EMAIL).getCustomerId());
		
		assertNotNull(customerQuickRegisterService.resetPasswordRedirect(Long.toString(CUST_MOBILE)).getCustomerId());
	}
	
	
	@Test
	public void populateCompleteRegisterRedirect()
	{
		QuickRegisterSavedEntityDTO savedEntityResult=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		AuthenticationDetailsDTO result=customerQuickRegisterService
				.getAuthenticationDetailsByCustomerIdType(savedEntityResult.getCustomer().getCustomerId(), savedEntityResult.getCustomer().getCustomerType());
		
		documentDetailsService.saveDocument(standardDocumentDetails(savedEntityResult.getCustomer().getCustomerId()));
		
		assertNotNull(result.getKey());
		
		ModelAndView resultModelAndView=customerQuickRegisterService.populateCompleteRegisterRedirect(result);
		
		//System.out.println(resultModelAndView.getModel().get("customerDetails"));
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), resultModelAndView.getModel().get("customerDetails"));
		
		assertEquals("completeregister/customerDetailsForm",resultModelAndView.getViewName());
		
		///standCu
		
		resultModelAndView=customerQuickRegisterService.populateCompleteRegisterRedirect(result);
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), resultModelAndView.getModel().get("customerDetails"));
		
		assertEquals("completeregister/showCustomerDetails",resultModelAndView.getViewName());
		
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
