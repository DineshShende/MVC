package com.projectx.mvc.controller;

import static com.projectx.mvc.controller.fixtues.CustomerQuickRegisterDataFixture.*;
import static com.projectx.mvc.controller.fixtues.CustomerAuthenticationDetailsDataFixtures.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.mvc.servicefixtures.CustomerQuickRegisterServiceFixture;

public class CustomerQuickRegisterStandAloneTest {

	@InjectMocks
	CustomerQuickRegisterController customerQuickRegisterController;
	
	@Mock
	CustomerQuickRegisterServiceFixture customerQuickRegisterService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = standaloneSetup(customerQuickRegisterController)
	    		.build();
	    
	}
	
	
	@Test
	public void verifyLoginDetailsEmailAsUserId() throws Exception
	{
		when(customerQuickRegisterService.verifyLoginDetails(standardLoginVerificationWithEmail())).thenReturn(standardCustomerEmailMobileAuthenticationDetails());
		
		this.mockMvc.perform(
				post("/customer/quickregister/verifyLoginDetails").param("entity",CUST_EMAIL)
											   .param("password", CUST_PASSWORD_DEFAULT)
											   											  
											)
				.andDo(print())
				.andExpect(view().name("forcePasswordChange"))
				.andExpect(model().attributeExists("loginDetails"))	
				.andExpect(model().attribute("loginDetails",Matchers.allOf(
				hasProperty("customerId", equalTo(standardCustomerEmailMobileAuthenticationDetails().getCustomerId())),
				hasProperty("email", equalTo(standardCustomerEmailMobileAuthenticationDetails().getEmail())),
				hasProperty("mobile", equalTo(standardCustomerEmailMobileAuthenticationDetails().getMobile())),
				hasProperty("password",equalTo(standardCustomerEmailMobileAuthenticationDetails().getPassword())),
				hasProperty("passwordType", equalTo(standardCustomerEmailMobileAuthenticationDetails().getPasswordType()))
					)));
			
	
	}

	
	@Test
	public void verifyLoginDetailsMobileAsUserId() throws Exception
	{
		when(customerQuickRegisterService.verifyLoginDetails(standardLoginVerificationWithMobile())).thenReturn(standardCustomerEmailMobileAuthenticationDetails());
		
		this.mockMvc.perform(
				post("/customer/quickregister/verifyLoginDetails").param("entity",Long.toString(CUST_MOBILE))
											   .param("password", CUST_PASSWORD_DEFAULT)
											   											  
											)
				.andDo(print())
				.andExpect(view().name("forcePasswordChange"))
				.andExpect(model().attributeExists("loginDetails"))	
				.andExpect(model().attribute("loginDetails",Matchers.allOf(
				hasProperty("customerId", equalTo(standardCustomerEmailMobileAuthenticationDetails().getCustomerId())),
				hasProperty("email", equalTo(standardCustomerEmailMobileAuthenticationDetails().getEmail())),
				hasProperty("mobile", equalTo(standardCustomerEmailMobileAuthenticationDetails().getMobile())),
				hasProperty("password",equalTo(standardCustomerEmailMobileAuthenticationDetails().getPassword())),
				hasProperty("passwordType", equalTo(standardCustomerEmailMobileAuthenticationDetails().getPasswordType()))
					)));
			
	
	}

	@Test
	public void updatePassword() throws Exception
	{
		when(customerQuickRegisterService.updatePassword(standardUpdatePassword())).thenReturn(true);
		
		this.mockMvc.perform(
				post("/customer/quickregister/updatePassword").param("customerId",Long.toString(CUST_ID))
											   .param("password", CUST_PASSWORD_CHANGED)
											   											  
											)
				.andDo(print())
				.andExpect(view().name("loginForm"));
		
	}

	/*
	@Test
	public void resetPasswordRedirect() throws Exception
	{
		when(customerQuickRegisterService.resetPasswordRedirect(CUST_EMAIL)).thenReturn(standardEmailMobileCustomer());
		
		this.mockMvc.perform(
				post("/customer/quickregister/resetPasswordRedirect").param("entity",CUST_EMAIL)
											   											   											  
											)
				.andDo(print())
				.andExpect(view().name("alreadyRegistered"));

	}
	*/
	
/*	
	@Test
	public void thatNewCustomerNotRegisteredAlready() throws Exception {
		
		when(customerQuickRegisterService.checkIfAlreadyExist(any(CustomerQuickRegisterEntity.class))).thenReturn(REGISTER_NOT_REGISTERED);
		
		when(customerQuickRegisterService.addNewCustomer(any(CustomerQuickRegisterEntity.class))).thenReturn(standardEmailCustomer());
		
		this.mockMvc.perform(
				post("/customer/quickregister").param("firstName",CUST_FIRSTNAME)
											   .param("lastName", CUST_LASTNAME)
											   .param("email",CUST_EMAIL)
											   .param("mobile",Long.toString(CUST_MOBILE))
											   .param("pin",Integer.toString(CUST_PIN))
											   .param("status", "")
											   
											  
											)
				.andExpect(view().name("verifyEmail"))
                .andDo(print());
		
		
	}
	
	@Test
	public void thatNewCustomerEmailRegisteredAlready() throws Exception {
		
		when(customerQuickRegisterService.checkIfAlreadyExist(any(CustomerQuickRegisterEntity.class))).thenReturn(REGISTER_EMAIL_ALREADY_REGISTERED);
		
		this.mockMvc.perform(
				post("/customer/quickregister").param("firstName",CUST_FIRSTNAME)
											   .param("lastName", CUST_LASTNAME)
											   .param("email",CUST_EMAIL)
											   .param("mobile",Long.toString(CUST_MOBILE))
											   .param("pin",Integer.toString(CUST_PIN))
											   .param("status", CUST_STATUS_EMAILMOBILE)
											   
											  
											)
				.andExpect(view().name("customerQuickRegister"))
				.andExpect(model().attribute("statusMessage", "Email Already Registered"))
				.andDo(print());
	
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void thatNewCustomerWithEmailMobileCustomer() throws Exception {
		
		when(customerQuickRegisterService.checkIfAlreadyExist(any(CustomerQuickRegisterEntity.class))).thenReturn(REGISTER_NOT_REGISTERED);
		
		when(customerQuickRegisterService.addNewCustomer(any(CustomerQuickRegisterEntity.class))).thenReturn(standardEmailMobileCustomer());
		
		this.mockMvc.perform(
				post("/customer/quickregister").param("firstName",CUST_FIRSTNAME)
											   .param("lastName", CUST_LASTNAME)
											   .param("email",CUST_EMAIL)
											   .param("mobile",Long.toString(CUST_MOBILE))
											   .param("pin",Integer.toString(CUST_PIN))
											   											  
											)
				.andDo(print())
				.andExpect(view().name("verifyEmailMobile"))
				.andExpect(model().attributeExists("newAddedCustomer"))	
				.andExpect(model().attribute("newAddedCustomer",Matchers.allOf(
				hasProperty("firstName", equalTo(CUST_FIRSTNAME)),
				hasProperty("lastName", equalTo(CUST_LASTNAME)),
				hasProperty("email", equalTo(CUST_EMAIL)),
				hasProperty("mobile",equalTo(CUST_MOBILE)),
				hasProperty("pin", equalTo(CUST_PIN)),
				hasProperty("status", equalTo(CUST_STATUS_EMAILMOBILE)),
				hasProperty("emailHash",equalTo(1010101010L)),
				hasProperty("mobilePin",equalTo(101010))
					)));
			
	
		
	}
	*/

}
