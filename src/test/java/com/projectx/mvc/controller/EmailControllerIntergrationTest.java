package com.projectx.mvc.controller;

import static com.projectx.mvc.controller.fixtues.EmailDataFixtures.EMAIL_EMAIL;
import static com.projectx.mvc.controller.fixtues.EmailDataFixtures.EMAIL_MSG;
import static com.projectx.mvc.controller.fixtues.EmailDataFixtures.EMAIL_NAME;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.projectx.mvc.config.Application;
import com.projectx.mvc.domain.Email;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("Test")
public class EmailControllerIntergrationTest {

	
	
	@Autowired
	
	private WebApplicationContext wac;

	MockMvc mockMvc;

	@Mock
	private Email email;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void thatEmailAddedSucessfullyWithCorrectObject() throws Exception {

		this.mockMvc
				.perform(
						post("/email/addemail").param("name", EMAIL_NAME).param(
								"email", EMAIL_EMAIL))
				.andDo(print())
				.andExpect(
						model().attribute(
								"email",
								Matchers.allOf(
										org.hamcrest.Matchers
												.<Email> hasProperty("name",
														equalTo(EMAIL_NAME)),
										org.hamcrest.Matchers
												.<Email> hasProperty(
														"email",
														equalTo(EMAIL_EMAIL)),
										org.hamcrest.Matchers
												.<Email> hasProperty(
														"message",
														equalTo(EMAIL_MSG)))));

	}

	@Test
	public void thatEmailAddedSucessfullyRedirectCorrectView() throws Exception {
		this.mockMvc
				.perform(
						post("/email/addemail").param("name", EMAIL_NAME).param(
								"email", EMAIL_EMAIL)).andDo(print())
				.andExpect(view().name("emailForm"));
	}
	

}
