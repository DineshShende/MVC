package com.projectx.mvc.controller.quickregister;

import static com.projectx.mvc.fixtures.quickregister.AuthenticationDetailsDataFixtures.standardCustomerEmailMobileAuthenticationDetails;
import static com.projectx.mvc.fixtures.quickregister.AuthenticationDetailsDataFixtures.standardCustomerEmailMobileAuthenticationDetailsWithNewPassword;
import static com.projectx.mvc.fixtures.quickregister.AuthenticationDetailsDataFixtures.standardLoginVerificationWithEmail;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.projectx.mvc.config.Application;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.VerifyMobileDTO;

import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.mvc.fixtures.completeregister.AddressDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
//@ActiveProfiles("Test")
public class QuickRegisterControllerWACTest {

	@Autowired
	private WebApplicationContext wac;

	MockMvc mockMvc;
	
	@Autowired
	QuickRegisterService customerQuickRegisterService;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		customerQuickRegisterService.clearTestData();
				
	}
	
	
	@Test
	public void thatCustomerQuickRegistrationWithEmailMobileViewRedirect() throws Exception
	{
		this.mockMvc.perform(
								post("/customer/quickregister/").param("firstName",CUST_FIRSTNAME)
															   .param("lastName", CUST_LASTNAME)
															   .param("email",CUST_EMAIL)
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pin",Integer.toString(CUST_PIN))
															   
															  
															)
			.andDo(print())												
			.andExpect(view().name("verifyEmailMobile"));
	//		.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("firstName", is(CUST_FIRSTNAME)))));
			
			
	}
	
	@Test
	public void thatCustomerQuickRegistrationWithMobileViewRedirect() throws Exception
	{
		this.mockMvc.perform(
								post("/customer/quickregister/").param("firstName",CUST_FIRSTNAME)
															   .param("lastName", CUST_LASTNAME)
															   .param("email","")
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pin",Integer.toString(CUST_PIN))
															   
															  
															)
			.andDo(print())												
			.andExpect(view().name("verifyEmailMobile"));
	//		.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("firstName", is(CUST_FIRSTNAME)))));
			
			
	}

	
	@Test
	public void thatCustomerQuickRegistrationWithAlreadyPresnetEmail() throws Exception
	{
		customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		this.mockMvc.perform(
								post("/customer/quickregister").param("firstName",CUST_FIRSTNAME)
															   .param("lastName", CUST_LASTNAME)
															   .param("email","")
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pin",Integer.toString(CUST_PIN))
															  
															)
			.andExpect(view().name("alreadyRegistered"))
			.andDo(print());
			
	}



	@Test
	public void verifyMobilePin() throws Exception
	{
		QuickRegisterDTO savedEntity=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntity.getCustomerId(),CUST_TYPE, savedEntity.getMobile());
		
				this.mockMvc.perform(
						post("/customer/quickregister/verifyMobilePin").param("customerId",Long.toString(savedEntity.getCustomerId()))
													   .param("mobilePin", Integer.toString(mobileVerificationDetailsDTO.getMobilePin()))
													   
													)
				.andDo(print())
			//	.andExpect(model().attribute("mobileVerificationStatus", "Mobile Verification Sucess"))
				//.andExpect(model().attribute("customerQuickRegisterDTO", isA(CustomerQuickRegisterMVCDTO.class)))
				.andExpect(view().name("verifyEmailMobile"));		
	}
	
	
	@Test
	public void reSendMobilePin() throws Exception
	{
		QuickRegisterDTO savedEntity=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntity.getCustomerId(),CUST_TYPE, savedEntity.getMobile());
		
		
		System.out.println(mobileVerificationDetailsDTO.getMobilePin());
		
		this.mockMvc.perform(
				post("/customer/quickregister/resendMobilePin").param("customerId",Long.toString(savedEntity.getCustomerId()))
											   
											   
											)
		.andDo(print())
		//.andExpect(model().attribute("mobileVerificationStatus", "Mobile Pin is sent.Please Enter that code"))
		.andExpect(view().name("verifyEmailMobile"));
	}
	
	
	@Test
	public void verifyEmailHash() throws Exception
	{
		QuickRegisterDTO savedEntity=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		EmailVerificationDetailsDTO emailVerificationDetailsDTO=customerQuickRegisterService
				.getEmailVerificationDetailsByCustomerIdTypeAndEmail(savedEntity.getCustomerId(),CUST_TYPE, savedEntity.getEmail());
		
		
		this.mockMvc.perform(get("/customer/quickregister/verifyEmailHash/"+savedEntity.getCustomerId()+"/"+emailVerificationDetailsDTO.getEmailHash()+""))
		
		.andDo(print());
		//.andExpect(model().attribute("emailVerificationStatus", "Email Verification Sucess"))
		//.andExpect(view().name("verifyEmailMobile"));
	}
	
	
	@Test
	public void reSendEmailHash() throws Exception
	{
		QuickRegisterDTO savedEntity=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		EmailVerificationDetailsDTO emailVerificationDetailsDTO=customerQuickRegisterService
				.getEmailVerificationDetailsByCustomerIdTypeAndEmail(savedEntity.getCustomerId(),CUST_TYPE, savedEntity.getEmail());
		
		System.out.println(emailVerificationDetailsDTO.getEmailHash());
		
		
		this.mockMvc.perform(
				post("/customer/quickregister/resendEmailHash").param("customerId",Long.toString(savedEntity.getCustomerId()))
					)
		.andDo(print())
	//	.andExpect(model().attribute("emailVerificationStatus", "Verification Email Sent"))
		.andExpect(view().name("verifyEmailMobile"));
	}
	

	
	@Test
	public void verifyLoginDetailsEmailAsUserIdProceedToForcefulPasswordChange() throws Exception
	{
		QuickRegisterSavedEntityDTO quickRegisterDTO=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=
				customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType(),
																		quickRegisterDTO.getCustomer().getMobile());
		
		customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType(),
																		quickRegisterDTO.getCustomer().getMobile(),mobileVerificationDetailsDTO.getMobilePin()));
		
		AuthenticationDetailsDTO authenticationDetailsDTO=customerQuickRegisterService
				.getAuthenticationDetailsByCustomerIdType(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType());
		
		//customerQuickRegisterService.updatePassword(updatePasswordDTO)
		
		
		this.mockMvc.perform(
				post("/customer/quickregister/verifyLoginDetails").param("entity",quickRegisterDTO.getCustomer().getEmail())
											   .param("password", authenticationDetailsDTO.getPassword())
											   											  
											)
				.andDo(print())
				.andExpect(view().name("forcePasswordChange"))
				.andExpect(model().attributeExists("loginDetails"))	
				.andExpect(model().attribute("loginDetails",Matchers.allOf(
			//	hasProperty("key", equalTo(standardCustomerEmailMobileAuthenticationDetails().getKey())),
				hasProperty("email", equalTo(standardCustomerEmailMobileAuthenticationDetails().getEmail())),
				hasProperty("mobile", equalTo(standardCustomerEmailMobileAuthenticationDetails().getMobile())),
				hasProperty("password",equalTo(authenticationDetailsDTO.getPassword())),
				hasProperty("passwordType", equalTo(standardCustomerEmailMobileAuthenticationDetails().getPasswordType()))
					)));
			
	
	}
	
	
	@Test
	public void verifyLoginDetailsEmailAsUserIdProceedToCompleteRegistration() throws Exception
	{
		QuickRegisterSavedEntityDTO quickRegisterDTO=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=
				customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType(),
																		quickRegisterDTO.getCustomer().getMobile());
		
		customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType(),
																		quickRegisterDTO.getCustomer().getMobile(),mobileVerificationDetailsDTO.getMobilePin()));
		
		AuthenticationDetailsDTO authenticationDetailsDTO=customerQuickRegisterService
				.getAuthenticationDetailsByCustomerIdType(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType());
		
		customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(authenticationDetailsDTO.getKey(), CUST_PASSWORD_CHANGED));
		
		
		this.mockMvc.perform(
				post("/customer/quickregister/verifyLoginDetails").param("entity",quickRegisterDTO.getCustomer().getEmail())
											   .param("password", CUST_PASSWORD_CHANGED)
											   											  
											)
				.andDo(print())
				.andExpect(view().name("customerDetailsForm"))
				.andExpect(model().attributeExists("customerDetails"))
				.andExpect(model().attribute("customerDetails",hasProperty("firstName", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getFirstName()))))
				.andExpect(model().attribute("customerDetails",hasProperty("lastName", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLastName()))))
				.andExpect(model().attribute("customerDetails",hasProperty("email", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getEmail()))))
				.andExpect(model().attribute("customerDetails",hasProperty("mobile", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getMobile()))))
				
				.andExpect(model().attribute("customerDetails",hasProperty("isEmailVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsEmailVerified()))))
				.andExpect(model().attribute("customerDetails",hasProperty("isMobileVerified", is(true))))
				.andExpect(model().attribute("customerDetails",hasProperty("isSecondaryMobileVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsSecondaryMobileVerified()))))
				.andExpect(model().attribute("customerDetails",hasProperty("homeAddressId", isEmptyOrNullString())))
				.andExpect(model().attribute("customerDetails",hasProperty("language", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getLanguage()))))
				.andExpect(model().attribute("customerDetails",hasProperty("businessDomain", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getBusinessDomain()))))
				.andExpect(model().attribute("customerDetails",hasProperty("nameOfFirm", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getNameOfFirm()))))
				.andExpect(model().attribute("customerDetails",hasProperty("firmAddressId", isEmptyOrNullString())))
				.andExpect(model().attribute("customerDetails",hasProperty("secondaryMobile", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getSecondaryMobile()))))
				.andExpect(model().attribute("customerDetails",hasProperty("isSecondaryMobileVerified", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getIsSecondaryMobileVerified()))))
				.andExpect(model().attribute("customerDetails",hasProperty("secondaryEmail", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getSecondaryEmail()))));
	}
	
	
	@Test
	public void verifyLoginDetailsEmailAsUserIdProceedToShowCustomerDetails() throws Exception
	{
		QuickRegisterSavedEntityDTO quickRegisterDTO=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=
				customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType(),
																		quickRegisterDTO.getCustomer().getMobile());
		
		customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType(),
																		quickRegisterDTO.getCustomer().getMobile(),mobileVerificationDetailsDTO.getMobilePin()));
		
		AuthenticationDetailsDTO authenticationDetailsDTO=customerQuickRegisterService
				.getAuthenticationDetailsByCustomerIdType(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType());
		
		customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(authenticationDetailsDTO.getKey(), CUST_PASSWORD_CHANGED));
		
		
		this.mockMvc.perform(
				post("/customer/quickregister/verifyLoginDetails").param("entity",quickRegisterDTO.getCustomer().getEmail())
											   .param("password", CUST_PASSWORD_CHANGED)
											   											  
											);
		
		this.mockMvc.perform(
				post("/customer/quickregister/verifyLoginDetails").param("entity",quickRegisterDTO.getCustomer().getEmail())
											   .param("password", CUST_PASSWORD_CHANGED)
											   											  
											)
				.andDo(print())
				.andExpect(view().name("showCustomerDetails"))
				.andExpect(model().attributeExists("customerDetails"))
				.andExpect(model().attribute("customerDetails",hasProperty("firstName", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getFirstName()))))
				.andExpect(model().attribute("customerDetails",hasProperty("lastName", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLastName()))))
				.andExpect(model().attribute("customerDetails",hasProperty("email", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getEmail()))))
				.andExpect(model().attribute("customerDetails",hasProperty("mobile", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getMobile()))))
				
				.andExpect(model().attribute("customerDetails",hasProperty("isEmailVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsEmailVerified()))))
				.andExpect(model().attribute("customerDetails",hasProperty("isMobileVerified", is(true))))
				.andExpect(model().attribute("customerDetails",hasProperty("isSecondaryMobileVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsSecondaryMobileVerified()))))
				.andExpect(model().attribute("customerDetails",hasProperty("homeAddressId", isEmptyOrNullString())))
				.andExpect(model().attribute("customerDetails",hasProperty("language", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getLanguage()))))
				.andExpect(model().attribute("customerDetails",hasProperty("businessDomain", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getBusinessDomain()))))
				.andExpect(model().attribute("customerDetails",hasProperty("nameOfFirm", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getNameOfFirm()))))
				.andExpect(model().attribute("customerDetails",hasProperty("firmAddressId", isEmptyOrNullString())))
				.andExpect(model().attribute("customerDetails",hasProperty("secondaryMobile", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getSecondaryMobile()))))
				.andExpect(model().attribute("customerDetails",hasProperty("isSecondaryMobileVerified", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getIsSecondaryMobileVerified()))))
				.andExpect(model().attribute("customerDetails",hasProperty("secondaryEmail", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getSecondaryEmail()))));
		
	}
	
	/*
	@Test
	public void thatCustomerQuickRegistrationWithEmailViewRedirect() throws Exception
	{
		this.mockMvc.perform(
								post("/customer/quickregister").param("firstName",CUSTOMER_FIRSTNAME)
															   .param("lastName", CUSTOMER_LASTNAME)
															   .param("email",CUSTOMER_EMAIL)
															   .param("mobile","")
															   .param("pin",CUSTOMER_PIN)
															  
															)
			.andExpect(view().name("verifyEmail"))
			.andDo(print());
			
	}
	*/

	
}
