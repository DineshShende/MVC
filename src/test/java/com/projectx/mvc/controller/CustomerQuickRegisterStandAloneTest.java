package com.projectx.mvc.controller;

import static com.projectx.mvc.controller.fixtues.CustomerQuickRegisterDataFixture.*;
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
import com.projectx.mvc.servicefixtures.CustomerQuickRegisterFixture;

public class CustomerQuickRegisterStandAloneTest {

	@InjectMocks
	CustomerQuickRegisterController customerQuickRegisterController;
	
	@Mock
	CustomerQuickRegisterFixture customerQuickRegisterService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = standaloneSetup(customerQuickRegisterController)
	    		.build();
	    
	}

	
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
	

}
