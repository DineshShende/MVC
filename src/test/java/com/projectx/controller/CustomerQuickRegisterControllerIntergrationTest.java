package com.projectx.controller;

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

import com.projectx.config.Application;
import com.projectx.domain.Email;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@ActiveProfiles("Test")
public class CustomerQuickRegisterControllerIntergrationTest {

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
						post("/email/addemail").param("name", "dinesh").param(
								"email", "dineshshe@gmail.com"))
				.andDo(print())
				.andExpect(
						model().attribute(
								"email",
								Matchers.allOf(
										org.hamcrest.Matchers
												.<Email> hasProperty("name",
														equalTo("dinesh")),
										org.hamcrest.Matchers
												.<Email> hasProperty(
														"email",
														equalTo("dineshshe@gmail.com")),
										org.hamcrest.Matchers
												.<Email> hasProperty(
														"message",
														equalTo("Email added sucesssfully")))));

	}

	@Test
	public void thatEmailAddedSucessfullyRedirectCorrectViev() throws Exception {
		this.mockMvc
				.perform(
						post("/email/addemail").param("name", "dinesh").param(
								"email", "dineshshe@gmail.com")).andDo(print())
				.andExpect(view().name("emailForm"));
	}

}
