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
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.VerifyMobileDTO;

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
	QuickRegisterService customerQuickRegisterService;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		customerDetailsService.clearTestData();
		customerQuickRegisterService.clearTestData();
	}
	
	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void save() throws Exception
	{
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO= customerQuickRegisterService.addNewCustomer(standardCustomerQuickRegisterEntity());
		
		MockMultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "some xml".getBytes());
		
		
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY);
		
		customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
				quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY,
				mobileVerificationDetailsDTO.getMobilePin(), CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId()));
		
		
		AuthenticationDetails authenticationDetails=
				customerQuickRegisterService.getAuthenticationDetailsByCustomerIdType(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType());
		
		assertTrue(customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(new AuthenticationDetailsKey(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType()), "password", authenticationDetails.getPassword(),
						true, CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId())));
		
		
		AuthenticationDetailsDTO authenticationDetailsDTO=customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(quickRegisterSavedEntityDTO.getCustomer().getEmail(),
				"password"));
		
		assertFalse(authenticationDetailsDTO.getIsCompleteRegisterCompleted());
		
		authenticationDetailsDTO=customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(quickRegisterSavedEntityDTO.getCustomer().getEmail(),
				"password"));
	
		assertTrue(authenticationDetailsDTO.getIsCompleteRegisterCompleted());
		
		CustomerDetailsDTO savedEntity=customerDetailsService
				.getCustomerDetailsById(quickRegisterSavedEntityDTO.getCustomer().getCustomerId());
		
	
		
		customerDetailsService.merge(standardCustomerDetails(savedEntity));
		
		this.mockMvc.perform(
				fileUpload("/document/save")
					.file(file)
					.param("customerId", Long.toString(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()))
					.param("customerType", Long.toString(ENTITY_TYPE_CUSTOMER))
					.param("documentName", "DrivingLicense")						   
											  
											)
			 .andDo(print())												
	         .andExpect(view().name("completeregister/showCustomerDetails"))
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
