package com.projectx.mvc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static com.projectx.mvc.controller.fixtues.CustomerQuickRegisterDataFixture.*;

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
import com.projectx.mvc.services.CustomerQuickRegisterService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("Test")
public class CustomerQuickRegisterControllerIntegrationTest {

@Autowired
	
	private WebApplicationContext wac;

	MockMvc mockMvc;
	
	CustomerQuickRegisterService customerQuickRegisterService;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		customerQuickRegisterService=Mockito.mock(CustomerQuickRegisterService.class);
		
		Mockito.when(customerQuickRegisterService.checkIfAlreadyExist(standardEmailCustomerDTO())).thenReturn(REGISTER_NOT_REGISTERED);
	}
	
	
	@Test
	public void thatCustomerQuickRegistrationWithEmailMobileViewRedirect() throws Exception
	{
		this.mockMvc.perform(
								post("/customer/quickregister").param("firstName",CUST_FIRSTNAME)
															   .param("lastName", CUST_LASTNAME)
															   .param("email",CUST_EMAIL)
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pin",Integer.toString(CUST_PIN))
															   
															  
															)
			.andExpect(view().name("verifyEmailMobile"))
			.andDo(print());
			
	}
	
	/*

	@Test
	public void thatCustomerQuickRegistrationWithMobileViewRedirect() throws Exception
	{
		this.mockMvc.perform(
								post("/customer/quickregister").param("firstName",CUSTOMER_FIRSTNAME)
															   .param("lastName", CUSTOMER_LASTNAME)
															   .param("email","")
															   .param("mobile",CUSTOMER_MOBILE)
															   .param("pin",CUSTOMER_PIN)
															  
															)
			.andExpect(view().name("verifyMobile"))
			.andDo(print());
			
	}
	
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
