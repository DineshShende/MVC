package com.projectx.mvc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static com.projectx.mvc.controller.fixtues.CustomerQuickRegisterDataFixture.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.projectx.mvc.config.Application;
import com.projectx.mvc.domain.CustomerQuickRegisterMVCDTO;
import com.projectx.mvc.services.CustomerQuickRegisterService;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;
import com.projectx.rest.domain.CustomerQuickRegisterSavedEntityDTO;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
//@ActiveProfiles("Test")
public class CustomerQuickRegisterControllerIntegrationTest {

@Autowired
	
	private WebApplicationContext wac;

	MockMvc mockMvc;
	
	@Autowired
	CustomerQuickRegisterService customerQuickRegisterService;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		customerQuickRegisterService.clearTestData();
		
		//customerQuickRegisterService=Mockito.mock(CustomerQuickRegisterService.class);
		
		//Mockito.when(customerQuickRegisterService.checkIfAlreadyExist(standardEmailCustomerDTO())).thenReturn(REGISTER_NOT_REGISTERED);
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

	
	@Test
	public void verifyMobilePin() throws Exception
	{
		CustomerQuickRegisterDTO savedEntity=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
				this.mockMvc.perform(
						post("/customer/quickregister/verifyMobilePin").param("customerId",Long.toString(savedEntity.getCustomerId()))
													   .param("mobilePin", Integer.toString(savedEntity.getMobilePin()))
													   
													)
				.andDo(print())
				.andExpect(model().attribute("mobileVerificationStatus", "Mobile Verification Sucess"))
				//.andExpect(model().attribute("customerQuickRegisterDTO", isA(CustomerQuickRegisterMVCDTO.class)))
				.andExpect(view().name("verifyEmailMobile"));		
	}
	
	
	@Test
	public void reSendMobilePin() throws Exception
	{
		CustomerQuickRegisterDTO savedEntity=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		System.out.println(savedEntity.getMobilePin());
		
		this.mockMvc.perform(
				post("/customer/quickregister/resendMobilePin").param("customerId",Long.toString(savedEntity.getCustomerId()))
											   
											   
											)
		.andDo(print())
		.andExpect(model().attribute("mobileVerificationStatus", "Mobile Pin is sent.Please Enter that code"))
		.andExpect(view().name("verifyEmailMobile"));
	}
	
	
	@Test
	public void verifyEmailHash() throws Exception
	{
		CustomerQuickRegisterDTO savedEntity=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		this.mockMvc.perform(get("/customer/quickregister/verifyEmailHash/"+savedEntity.getCustomerId()+"/"+savedEntity.getEmailHash()+""))
		
		.andDo(print())
		.andExpect(model().attribute("emailVerificationStatus", "Email Verification Sucess"))
		.andExpect(view().name("verifyEmailMobile"));
	}
	
	@Test
	public void reSendEmailHash() throws Exception
	{
		CustomerQuickRegisterDTO savedEntity=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		System.out.println(savedEntity.getEmailHash());
		
		this.mockMvc.perform(
				post("/customer/quickregister/resendEmailHash").param("customerId",Long.toString(savedEntity.getCustomerId()))
					)
		.andDo(print())
		.andExpect(model().attribute("emailVerificationStatus", "Verification Email Sent"))
		.andExpect(view().name("verifyEmailMobile"));
	}
	
	
	
}
