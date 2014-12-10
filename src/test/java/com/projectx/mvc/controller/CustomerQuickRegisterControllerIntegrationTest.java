package com.projectx.mvc.controller;

import static com.projectx.mvc.controller.fixtues.CustomerQuickRegisterDataFixture.CUST_EMAIL;
import static com.projectx.mvc.controller.fixtues.CustomerQuickRegisterDataFixture.CUST_FIRSTNAME;
import static com.projectx.mvc.controller.fixtues.CustomerQuickRegisterDataFixture.CUST_LASTNAME;
import static com.projectx.mvc.controller.fixtues.CustomerQuickRegisterDataFixture.CUST_MOBILE;
import static com.projectx.mvc.controller.fixtues.CustomerQuickRegisterDataFixture.CUST_PIN;
import static com.projectx.mvc.controller.fixtues.CustomerQuickRegisterDataFixture.standardEmailMobileCustomerDTO;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import com.projectx.mvc.services.CustomerQuickRegisterService;
import com.projectx.rest.domain.CustomerEmailVerificationDetailsDTO;
import com.projectx.rest.domain.CustomerMobileVerificationDetailsDTO;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;


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
		CustomerQuickRegisterDTO savedEntity=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		CustomerMobileVerificationDetailsDTO mobileVerificationDetailsDTO=customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdAndMobile(savedEntity.getCustomerId(), savedEntity.getMobile());
		
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
		CustomerQuickRegisterDTO savedEntity=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		CustomerMobileVerificationDetailsDTO mobileVerificationDetailsDTO=customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdAndMobile(savedEntity.getCustomerId(), savedEntity.getMobile());
		
		
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
		CustomerQuickRegisterDTO savedEntity=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		CustomerEmailVerificationDetailsDTO emailVerificationDetailsDTO=customerQuickRegisterService
				.getEmailVerificationDetailsByCustomerIdAndEmail(savedEntity.getCustomerId(), savedEntity.getEmail());
		
		
		this.mockMvc.perform(get("/customer/quickregister/verifyEmailHash/"+savedEntity.getCustomerId()+"/"+emailVerificationDetailsDTO.getEmailHash()+""))
		
		.andDo(print());
		//.andExpect(model().attribute("emailVerificationStatus", "Email Verification Sucess"))
		//.andExpect(view().name("verifyEmailMobile"));
	}
	
	
	@Test
	public void reSendEmailHash() throws Exception
	{
		CustomerQuickRegisterDTO savedEntity=customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		CustomerEmailVerificationDetailsDTO emailVerificationDetailsDTO=customerQuickRegisterService
				.getEmailVerificationDetailsByCustomerIdAndEmail(savedEntity.getCustomerId(), savedEntity.getEmail());
		
		System.out.println(emailVerificationDetailsDTO.getEmailHash());
		
		
		this.mockMvc.perform(
				post("/customer/quickregister/resendEmailHash").param("customerId",Long.toString(savedEntity.getCustomerId()))
					)
		.andDo(print())
	//	.andExpect(model().attribute("emailVerificationStatus", "Verification Email Sent"))
		.andExpect(view().name("verifyEmailMobile"));
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
