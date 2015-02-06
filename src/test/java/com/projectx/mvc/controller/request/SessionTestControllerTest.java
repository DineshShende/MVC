package com.projectx.mvc.controller.request;

import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.CUST_EMAIL;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.CUST_FIRSTNAME;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.CUST_LASTNAME;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.CUST_MOBILE;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.CUST_PIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.projectx.mvc.config.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class SessionTestControllerTest {

	@Autowired
	private WebApplicationContext wac;

	MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
				
	}
	
	
	@Test
	public void test() throws Exception
	{
		MockHttpSession mockHttpSession=new MockHttpSession();
		
		this.mockMvc.perform(
				post("/sessionTest").param("name","dinesh")
											   .param("password", "123")
											   .session(mockHttpSession)											   
											  
											)
		.andDo(print())												
		.andExpect(view().name("showSession"));
	
		
		this.mockMvc.perform(
				get("/sessionTest/redirect")
				.session(mockHttpSession)
				)
		.andDo(print());
		
				
		this.mockMvc.perform(
				get("/sessionTest/redirect")
				.session(mockHttpSession)
				)
		.andDo(print());
	}
	
}
