package com.projectx.mvc.controller.completeregister;

import static com.projectx.mvc.fixtures.completeregister.AddressDataFixture.standardAddress;
import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.standardCustomerDetails;
import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.standardCustomerDetailsCopiedFromQuickRegisterEntity;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.projectx.mvc.config.Application;
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;

import static com.projectx.mvc.fixtures.completeregister.DocumentDetailsDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class DocumentDetailsWACTest {

	@Autowired
	private WebApplicationContext wac;

	MockMvc mockMvc;

	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		customerDetailsService.clearTestData();
		quickRegisterService.clearTestData();
	}
	
	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void save() throws Exception
	{
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO= quickRegisterService.addNewCustomer(standardCustomerQuickRegisterEntity());
		
		MockMultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "some xml".getBytes());
		
		customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer());
		
		customerDetailsService.merge(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()));
		
		this.mockMvc.perform(
				fileUpload("/document/save")
					.file(file)
					.param("customerId", Long.toString(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()))
					.param("customerType", Long.toString(ENTITY_TYPE_CUSTOMER))
					.param("documentName", "DrivingLicense")						   
											  
											)
			 .andDo(print())												
	         .andExpect(view().name("showCustomerDetails"))
	         .andExpect(model().attributeExists("documentDetails"))
			// .andExpect(model().attribute("documentDetails",hasProperty("key", is(standardDocumentKey()))))
			 .andExpect(model().attribute("documentDetails",hasProperty("document", is("some xml".getBytes()))))
			 .andExpect(model().attribute("documentDetails",hasProperty("contentType", is("text/plain"))))
			 .andExpect(model().attribute("documentDetails",hasProperty("verificationStatus", is(standardDocumentDetails().getVerificationStatus()))))
			 .andExpect(model().attribute("documentDetails",hasProperty("verificationRemark", is(standardDocumentDetails().getVerificationRemark()))))
			 .andExpect(model().attribute("documentDetails",hasProperty("updatedBy",is(standardDocumentDetails().getUpdatedBy()))))
			 
			 
			 .andExpect(model().attributeExists("customerDetails"))
			//.andExpect(model().attribute("customerDetails",hasProperty("customerId", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getCustomerId()))))
			.andExpect(model().attribute("customerDetails",hasProperty("firstName", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getFirstName()))))
			.andExpect(model().attribute("customerDetails",hasProperty("lastName", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLastName()))))
			.andExpect(model().attribute("customerDetails",hasProperty("email", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getEmail()))))
			.andExpect(model().attribute("customerDetails",hasProperty("mobile", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getMobile()))))
			
			.andExpect(model().attribute("customerDetails",hasProperty("isEmailVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsEmailVerified()))))
			.andExpect(model().attribute("customerDetails",hasProperty("isMobileVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsMobileVerified()))))
			.andExpect(model().attribute("customerDetails",hasProperty("isSecondaryMobileVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsSecondaryMobileVerified()))));
		//	.andExpect(model().attribute("customerDetails",hasProperty("homeAddressId", is(standardAddress()))))
		//	.andExpect(model().attribute("customerDetails",hasProperty("language", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLanguage()))))
		//	.andExpect(model().attribute("customerDetails",hasProperty("businessDomain", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getBusinessDomain()))))
		//	.andExpect(model().attribute("customerDetails",hasProperty("nameOfFirm", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getNameOfFirm()))))
		//	.andExpect(model().attribute("customerDetails",hasProperty("firmAddressId", is(standardAddress()))))
		//	.andExpect(model().attribute("customerDetails",hasProperty("secondaryMobile", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryMobile()))))
		//	.andExpect(model().attribute("customerDetails",hasProperty("isSecondaryMobileVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsSecondaryMobileVerified()))))
		//	.andExpect(model().attribute("customerDetails",hasProperty("secondaryEmail", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryEmail()))));
			
			 
	}

}
