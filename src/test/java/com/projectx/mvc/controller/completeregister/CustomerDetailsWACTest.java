package com.projectx.mvc.controller.completeregister;



import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.mvc.fixtures.completeregister.AddressDataFixture.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;

import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CustomerDetailsWACTest {

	@Autowired
	private WebApplicationContext wac;

	MockMvc mockMvc;
	
	@Autowired
	CustomerDetailsService customerDetailsService;


	@Autowired
	QuickRegisterService quickRegisterService;
	
	@Autowired
	SimpleDateFormat simpleDateFormat;
	
	
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
	public void createCustomerDetailsFromQuickRegisterEntity() throws Exception
	{
		QuickRegisterDTO quickRegisterSavedEntityDTO=
				quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
	
		
		this.mockMvc.perform(
								post("/customer/createCustomerDetailsFromQuickRegisterEntity").param("customerId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
															.param("firstName",CUST_FIRSTNAME)
															   .param("lastName", CUST_LASTNAME)
															   .param("email",CUST_EMAIL)
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pincode",Integer.toString(CUST_PIN))
															   .param("customerType", Integer.toString(ENTITY_TYPE_CUSTOMER))
															   .param("isEmailVerified", "true")
															   .param("isMobileVerified", "true")
															   
															  
															)
			.andDo(print())												
			.andExpect(view().name("customerDetailsForm"));
	//		.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("firstName", is(CUST_FIRSTNAME)))));
			
			
	}
	
	
	@Test
	public void save() throws Exception
	{
	
			QuickRegisterDTO quickRegisterSavedEntityDTO=
				quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
	
		
		this.mockMvc.perform(
				post("/customer/createCustomerDetailsFromQuickRegisterEntity").param("customerId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
											.param("firstName",CUST_FIRSTNAME)
											   .param("lastName", CUST_LASTNAME)
											   .param("email",CUST_EMAIL)
											   .param("mobile",Long.toString(CUST_MOBILE))
											   .param("pincode",Integer.toString(CUST_PIN))
											   .param("customerType", Integer.toString(ENTITY_TYPE_CUSTOMER))
											   .param("isEmailVerified", "true")
											   .param("isMobileVerified", "true")
											   
											  
											);
		
		this.mockMvc.perform(
								post("/customer/save").param("customerId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
															.param("firstName",CUST_FIRSTNAME)
															   .param("lastName", CUST_LASTNAME)
															   .param("dateOfBirth", "01/01/1990")
															   .param("email",CUST_EMAIL)
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pincode",Integer.toString(CUST_PIN))
															   .param("customerType", Integer.toString(ENTITY_TYPE_CUSTOMER))
															   .param("isEmailVerified", "false")
															   .param("isMobileVerified", "false")
															   .param("isSecondaryMobileVerified", "false")
								
														    // .param("dateOfBirth", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getDateOfBirth().toString())
															   .param("homeAddressId.customerType", Integer.toString(standardAddress().getCustomerType()))
															   .param("homeAddressId.addressLine", standardAddress().getAddressLine())
															   .param("homeAddressId.city", standardAddress().getCity())
															   .param("homeAddressId.district", standardAddress().getDistrict())
						
															    
															   .param("homeAddressId.state", standardAddress().getState())
															   .param("homeAddressId.pincode", Integer.toString(standardAddress().getPincode()))
															   
															   .param("language", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLanguage())
															   .param("businessDomain", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getBusinessDomain())
															   .param("nameOfFirm", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getNameOfFirm())
															   .param("firmAddressId.customerType", Integer.toString(standardAddress().getCustomerType()))
															   .param("firmAddressId.addressLine", standardAddress().getAddressLine())
															   .param("firmAddressId.city", standardAddress().getCity())
															   .param("firmAddressId.district", standardAddress().getDistrict())
															   .param("firmAddressId.state", standardAddress().getState())
															   .param("firmAddressId.pincode", Integer.toString(standardAddress().getPincode()))
															   
															   .param("secondaryMobile", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryMobile().toString())
															   .param("secondaryEmail", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryEmail())
															   
															  
															)
			.andDo(print())												
			.andExpect(view().name("documentUpload"))
			.andExpect(model().size(2))
			.andExpect(model().attributeExists("customerDetails"))
			.andExpect(model().attribute("customerDetails",hasProperty("customerId", is(quickRegisterSavedEntityDTO.getCustomerId()))))
			.andExpect(model().attribute("customerDetails",hasProperty("firstName", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getFirstName()))))
			.andExpect(model().attribute("customerDetails",hasProperty("lastName", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLastName()))))
			.andExpect(model().attribute("customerDetails",hasProperty("dateOfBirth", is(simpleDateFormat.parse("01-01-1990")))))
			.andExpect(model().attribute("customerDetails",hasProperty("email", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getEmail()))))
			.andExpect(model().attribute("customerDetails",hasProperty("mobile", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getMobile()))))
			
			.andExpect(model().attribute("customerDetails",hasProperty("isEmailVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsEmailVerified()))))
			.andExpect(model().attribute("customerDetails",hasProperty("isMobileVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsMobileVerified()))))
			.andExpect(model().attribute("customerDetails",hasProperty("isSecondaryMobileVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsSecondaryMobileVerified()))))
			.andExpect(model().attribute("customerDetails",hasProperty("homeAddressId", is(standardAddress()))))
			.andExpect(model().attribute("customerDetails",hasProperty("language", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLanguage()))))
			.andExpect(model().attribute("customerDetails",hasProperty("businessDomain", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getBusinessDomain()))))
			.andExpect(model().attribute("customerDetails",hasProperty("nameOfFirm", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getNameOfFirm()))))
			.andExpect(model().attribute("customerDetails",hasProperty("firmAddressId", is(standardAddress()))))
			.andExpect(model().attribute("customerDetails",hasProperty("secondaryMobile",isEmptyOrNullString() )))
			.andExpect(model().attribute("customerDetails",hasProperty("isSecondaryMobileVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsSecondaryMobileVerified()))))
			.andExpect(model().attribute("customerDetails",hasProperty("secondaryEmail", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryEmail()))));
						
	}
	
	
	@Test
	public void saveWithError() throws Exception
	{
	
			QuickRegisterDTO quickRegisterSavedEntityDTO=
				quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
	
		
		this.mockMvc.perform(
				post("/customer/createCustomerDetailsFromQuickRegisterEntity").param("customerId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
											.param("firstName",CUST_FIRSTNAME)
											   .param("lastName", CUST_LASTNAME)
											   .param("email",CUST_EMAIL)
											   .param("mobile",Long.toString(CUST_MOBILE))
											   .param("pincode",Integer.toString(CUST_PIN))
											   .param("customerType", Integer.toString(ENTITY_TYPE_CUSTOMER))
											   .param("isEmailVerified", "true")
											   .param("isMobileVerified", "true")
											   
											  
											);
		
		this.mockMvc.perform(
								post("/customer/save").param("customerId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
															.param("firstName","")
															   .param("lastName", CUST_LASTNAME)
															   .param("dateOfBirth", "01/01/1990")
															   .param("email",CUST_EMAIL)
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pincode",Integer.toString(CUST_PIN))
															   .param("customerType", Integer.toString(ENTITY_TYPE_CUSTOMER))
															   .param("isEmailVerified", "false")
															   .param("isMobileVerified", "false")
															   .param("isSecondaryMobileVerified", "false")
								
														    // .param("dateOfBirth", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getDateOfBirth().toString())
															   .param("homeAddressId.customerType", Integer.toString(standardAddress().getCustomerType()))
															   .param("homeAddressId.addressLine", standardAddress().getAddressLine())
															   .param("homeAddressId.city", standardAddress().getCity())
															   .param("homeAddressId.district", standardAddress().getDistrict())
						
															    
															   .param("homeAddressId.state", standardAddress().getState())
															   .param("homeAddressId.pincode", Integer.toString(standardAddress().getPincode()))
															   
															   .param("language", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLanguage())
															   .param("businessDomain", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getBusinessDomain())
															   .param("nameOfFirm", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getNameOfFirm())
															   .param("firmAddressId.customerType", Integer.toString(standardAddress().getCustomerType()))
															   .param("firmAddressId.addressLine", standardAddress().getAddressLine())
															   .param("firmAddressId.city", standardAddress().getCity())
															   .param("firmAddressId.district", standardAddress().getDistrict())
															   .param("firmAddressId.state", standardAddress().getState())
															   .param("firmAddressId.pincode", Integer.toString(standardAddress().getPincode()))
															   
															   .param("secondaryMobile", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryMobile().toString())
															   .param("secondaryEmail", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryEmail())
															   
															  
															)
			.andDo(print())												
			.andExpect(view().name("customerDetailsForm"))
			.andExpect(model().size(2))
			.andExpect(model().attributeExists("customerDetails"));			
	}
	
	
	@Test
	public void editWithErrors() throws Exception
	{
		QuickRegisterDTO quickRegisterSavedEntityDTO=
				quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
	
		
		this.mockMvc.perform(
				post("/customer/createCustomerDetailsFromQuickRegisterEntity").param("customerId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
											.param("firstName",CUST_FIRSTNAME)
											   .param("lastName", CUST_LASTNAME)
											   .param("email",CUST_EMAIL)
											   .param("mobile",Long.toString(CUST_MOBILE))
											   .param("pincode",Integer.toString(CUST_PIN))
											   .param("customerType", Integer.toString(ENTITY_TYPE_CUSTOMER))
											   .param("isEmailVerified", "true")
											   .param("isMobileVerified", "true")
											   
											  
											);
		
		this.mockMvc.perform(
								post("/customer/save").param("customerId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
															.param("firstName",CUST_FIRSTNAME)
															   .param("lastName", CUST_LASTNAME)
															   .param("dateOfBirth", "01/01/1990")
															   .param("email",CUST_EMAIL)
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pincode",Integer.toString(CUST_PIN))
															   .param("customerType", Integer.toString(ENTITY_TYPE_CUSTOMER))
															   .param("isEmailVerified", "false")
															   .param("isMobileVerified", "false")
															   .param("isSecondaryMobileVerified", "false")
								
														    // .param("dateOfBirth", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getDateOfBirth().toString())
															   .param("homeAddressId.customerType", Integer.toString(standardAddress().getCustomerType()))
															   .param("homeAddressId.addressLine", standardAddress().getAddressLine())
															   .param("homeAddressId.city", standardAddress().getCity())
															   .param("homeAddressId.district", standardAddress().getDistrict())
															   .param("homeAddressId.state", standardAddress().getState())
															   .param("homeAddressId.pincode", Integer.toString(standardAddress().getPincode()))
															   
															   .param("language", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLanguage())
															   .param("businessDomain", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getBusinessDomain())
															   .param("nameOfFirm", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getNameOfFirm())
															   .param("firmAddressId.customerType", Integer.toString(standardAddress().getCustomerType()))
															   .param("firmAddressId.addressLine", standardAddress().getAddressLine())
															   .param("firmAddressId.city", standardAddress().getCity())
															   .param("firmAddressId.district", standardAddress().getDistrict())
															   .param("firmAddressId.state", standardAddress().getState())
															   .param("firmAddressId.pincode", Integer.toString(standardAddress().getPincode()))
															   
															   .param("secondaryMobile", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryMobile().toString())
															   .param("secondaryEmail", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryEmail())
															   
															  
															);
		
		
		Address homeAddress=customerDetailsService.getCustomerDetailsById(quickRegisterSavedEntityDTO.getCustomerId()).getHomeAddressId();
		
		Address firmAddress=customerDetailsService.getCustomerDetailsById(quickRegisterSavedEntityDTO.getCustomerId()).getFirmAddressId();
								
		this.mockMvc.perform(
				post("/customer/edit").param("customerId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
											.param("firstName",CUST_FIRSTNAME)
											   .param("lastName", "")
											   .param("dateOfBirth", "01/01/1990")
											   .param("email",CUST_EMAIL)
											   .param("mobile",Long.toString(CUST_MOBILE))
											   .param("pincode",Integer.toString(CUST_PIN))
											   .param("customerType", Integer.toString(ENTITY_TYPE_CUSTOMER))
											   .param("isEmailVerified", "false")
											   .param("isMobileVerified", "false")
											   .param("isSecondaryMobileVerified", "false")
				
										    // .param("dateOfBirth", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getDateOfBirth().toString())
											   .param("homeAddressId.addressId", Long.toString(homeAddress.getAddressId()))
											   .param("homeAddressId.customerType", Integer.toString(standardAddressUpdated().getCustomerType()))
											   .param("homeAddressId.addressLine", standardAddressUpdated().getAddressLine())
											   .param("homeAddressId.city", standardAddressUpdated().getCity())
											   .param("homeAddressId.district", standardAddressUpdated().getDistrict())
											   .param("homeAddressId.state", standardAddressUpdated().getState())
											   .param("homeAddressId.pincode", Integer.toString(standardAddressUpdated().getPincode()))
											   
											   .param("language", standardCustomerDetailsNew(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLanguage())
											   .param("businessDomain", standardCustomerDetailsNew(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getBusinessDomain())
											   .param("nameOfFirm", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getNameOfFirm())
											   .param("firmAddressId.addressId", Long.toString(firmAddress.getAddressId()))
											   .param("firmAddressId.customerType", Integer.toString(standardAddressUpdated().getCustomerType()))
											   .param("firmAddressId.addressLine", standardAddressUpdated().getAddressLine())
											   .param("firmAddressId.city", standardAddressUpdated().getCity())
											   .param("firmAddressId.district", standardAddressUpdated().getDistrict())
											   .param("firmAddressId.state", standardAddressUpdated().getState())
											   .param("firmAddressId.pincode", Integer.toString(standardAddressUpdated().getPincode()))
											   
											   .param("secondaryMobile", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryMobile().toString())
											   .param("secondaryEmail", standardCustomerDetailsNew(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryEmail())
											   
											  
											)
					.andDo(print())												
					.andExpect(view().name("customerDetailsForm"))
					.andExpect(model().size(2))
					.andExpect(model().attributeExists("customerDetails"));
	}

	
	
	@Test
	public void edit() throws Exception
	{
		QuickRegisterDTO quickRegisterSavedEntityDTO=
				quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
	
		
		this.mockMvc.perform(
				post("/customer/createCustomerDetailsFromQuickRegisterEntity").param("customerId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
											.param("firstName",CUST_FIRSTNAME)
											   .param("lastName", CUST_LASTNAME)
											   .param("email",CUST_EMAIL)
											   .param("mobile",Long.toString(CUST_MOBILE))
											   .param("pincode",Integer.toString(CUST_PIN))
											   .param("customerType", Integer.toString(ENTITY_TYPE_CUSTOMER))
											   .param("isEmailVerified", "true")
											   .param("isMobileVerified", "true")
											   
											  
											);
		
		this.mockMvc.perform(
								post("/customer/save").param("customerId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
															.param("firstName",CUST_FIRSTNAME)
															   .param("lastName", CUST_LASTNAME)
															   .param("dateOfBirth", "01/01/1990")
															   .param("email",CUST_EMAIL)
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pincode",Integer.toString(CUST_PIN))
															   .param("customerType", Integer.toString(ENTITY_TYPE_CUSTOMER))
															   .param("isEmailVerified", "false")
															   .param("isMobileVerified", "false")
															   .param("isSecondaryMobileVerified", "false")
								
														    // .param("dateOfBirth", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getDateOfBirth().toString())
															   .param("homeAddressId.customerType", Integer.toString(standardAddress().getCustomerType()))
															   .param("homeAddressId.addressLine", standardAddress().getAddressLine())
															   .param("homeAddressId.city", standardAddress().getCity())
															   .param("homeAddressId.district", standardAddress().getDistrict())
															   .param("homeAddressId.state", standardAddress().getState())
															   .param("homeAddressId.pincode", Integer.toString(standardAddress().getPincode()))
															   
															   .param("language", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLanguage())
															   .param("businessDomain", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getBusinessDomain())
															   .param("nameOfFirm", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getNameOfFirm())
															   .param("firmAddressId.customerType", Integer.toString(standardAddress().getCustomerType()))
															   .param("firmAddressId.addressLine", standardAddress().getAddressLine())
															   .param("firmAddressId.city", standardAddress().getCity())
															   .param("firmAddressId.district", standardAddress().getDistrict())
															   .param("firmAddressId.state", standardAddress().getState())
															   .param("firmAddressId.pincode", Integer.toString(standardAddress().getPincode()))
															   
															   .param("secondaryMobile", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryMobile().toString())
															   .param("secondaryEmail", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryEmail())
															   
															  
															);
		
		
		Address homeAddress=customerDetailsService.getCustomerDetailsById(quickRegisterSavedEntityDTO.getCustomerId()).getHomeAddressId();
		
		Address firmAddress=customerDetailsService.getCustomerDetailsById(quickRegisterSavedEntityDTO.getCustomerId()).getFirmAddressId();
								
		this.mockMvc.perform(
				post("/customer/edit").param("customerId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
											.param("firstName",CUST_FIRSTNAME)
											   .param("lastName", CUST_LASTNAME)
											   .param("dateOfBirth", "01/01/1990")
											   .param("email",CUST_EMAIL)
											   .param("mobile",Long.toString(CUST_MOBILE))
											   .param("pincode",Integer.toString(CUST_PIN))
											   .param("customerType", Integer.toString(ENTITY_TYPE_CUSTOMER))
											   .param("isEmailVerified", "false")
											   .param("isMobileVerified", "false")
											   .param("isSecondaryMobileVerified", "false")
				
										    // .param("dateOfBirth", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getDateOfBirth().toString())
											   .param("homeAddressId.addressId", Long.toString(homeAddress.getAddressId()))
											   .param("homeAddressId.customerType", Integer.toString(standardAddressUpdated().getCustomerType()))
											   .param("homeAddressId.addressLine", standardAddressUpdated().getAddressLine())
											   .param("homeAddressId.city", standardAddressUpdated().getCity())
											   .param("homeAddressId.district", standardAddressUpdated().getDistrict())
											   .param("homeAddressId.state", standardAddressUpdated().getState())
											   .param("homeAddressId.pincode", Integer.toString(standardAddressUpdated().getPincode()))
											   
											   .param("language", standardCustomerDetailsNew(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLanguage())
											   .param("businessDomain", standardCustomerDetailsNew(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getBusinessDomain())
											   .param("nameOfFirm", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getNameOfFirm())
											   .param("firmAddressId.addressId", Long.toString(firmAddress.getAddressId()))
											   .param("firmAddressId.customerType", Integer.toString(standardAddressUpdated().getCustomerType()))
											   .param("firmAddressId.addressLine", standardAddressUpdated().getAddressLine())
											   .param("firmAddressId.city", standardAddressUpdated().getCity())
											   .param("firmAddressId.district", standardAddressUpdated().getDistrict())
											   .param("firmAddressId.state", standardAddressUpdated().getState())
											   .param("firmAddressId.pincode", Integer.toString(standardAddressUpdated().getPincode()))
											   
											   .param("secondaryMobile", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryMobile().toString())
											   .param("secondaryEmail", standardCustomerDetailsNew(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryEmail())
											   
											  
											)
					.andDo(print())												
					.andExpect(view().name("showCustomerDetails"))
					.andExpect(model().size(5))
					.andExpect(model().attributeExists("customerDetails"))
					.andExpect(model().attribute("customerDetails",hasProperty("customerId", is(quickRegisterSavedEntityDTO.getCustomerId()))))
					.andExpect(model().attribute("customerDetails",hasProperty("firstName", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getFirstName()))))
					.andExpect(model().attribute("customerDetails",hasProperty("lastName", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLastName()))))
					.andExpect(model().attribute("customerDetails",hasProperty("dateOfBirth", is(simpleDateFormat.parse("01-01-1990")))))
					.andExpect(model().attribute("customerDetails",hasProperty("email", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getEmail()))))
					.andExpect(model().attribute("customerDetails",hasProperty("mobile", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getMobile()))))
					
					.andExpect(model().attribute("customerDetails",hasProperty("isEmailVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsEmailVerified()))))
					.andExpect(model().attribute("customerDetails",hasProperty("isMobileVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsMobileVerified()))))
					.andExpect(model().attribute("customerDetails",hasProperty("isSecondaryMobileVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsSecondaryMobileVerified()))))
					.andExpect(model().attribute("customerDetails",hasProperty("homeAddressId", is(standardAddressUpdated()))))
					.andExpect(model().attribute("customerDetails",hasProperty("language", is(standardCustomerDetailsNew(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLanguage()))))
					.andExpect(model().attribute("customerDetails",hasProperty("businessDomain", is(standardCustomerDetailsNew(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getBusinessDomain()))))
					.andExpect(model().attribute("customerDetails",hasProperty("nameOfFirm", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getNameOfFirm()))))
					.andExpect(model().attribute("customerDetails",hasProperty("firmAddressId", is(standardAddressUpdated()))))
					.andExpect(model().attribute("customerDetails",hasProperty("secondaryMobile", isEmptyOrNullString())))
					.andExpect(model().attribute("customerDetails",hasProperty("isSecondaryMobileVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsSecondaryMobileVerified()))))
					.andExpect(model().attribute("customerDetails",hasProperty("secondaryEmail", is(standardCustomerDetailsNew(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryEmail()))));
	}
	
	
	@Test
	public void verifyMobileDetails() throws Exception
	{
		
		this.mockMvc.perform(
								post("/customer/save").param("customerId", Long.toString(CUST_ID))
															.param("firstName",CUST_FIRSTNAME)
															   .param("lastName", CUST_LASTNAME)
															   .param("dateOfBirth", "01/01/1990")
															   .param("email",CUST_EMAIL)
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pincode",Integer.toString(CUST_PIN))
															   .param("customerType", Integer.toString(ENTITY_TYPE_CUSTOMER))
															   .param("isEmailVerified", "false")
															   .param("isMobileVerified", "false")
															   .param("isSecondaryMobileVerified", "false")
								
														    // .param("dateOfBirth", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getDateOfBirth().toString())
															   .param("homeAddressId.customerType", Integer.toString(standardAddress().getCustomerType()))
															   .param("homeAddressId.addressLine", standardAddress().getAddressLine())
															   .param("homeAddressId.city", standardAddress().getCity())
															   .param("homeAddressId.district", standardAddress().getDistrict())
															   .param("homeAddressId.state", standardAddress().getState())
															   .param("homeAddressId.pincode", Integer.toString(standardAddress().getPincode()))
															   
															   .param("language", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLanguage())
															   .param("businessDomain", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getBusinessDomain())
															   .param("nameOfFirm", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getNameOfFirm())
															   .param("firmAddressId.customerType", Integer.toString(standardAddress().getCustomerType()))
															   .param("firmAddressId.addressLine", standardAddress().getAddressLine())
															   .param("firmAddressId.city", standardAddress().getCity())
															   .param("firmAddressId.district", standardAddress().getDistrict())
															   .param("firmAddressId.state", standardAddress().getState())
															   .param("firmAddressId.pincode", Integer.toString(standardAddress().getPincode()))
															   
															   .param("secondaryMobile", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryMobile().toString())
															   .param("secondaryEmail", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryEmail())
															   
															  
															);

		quickRegisterService.reSendMobilePin(new CustomerIdTypeMobileTypeDTO(CUST_ID, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY));
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=
				quickRegisterService.getMobileVerificationDetailsByCustomerIdTypeAndMobile(CUST_ID, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);
		
		
		
		
		this.mockMvc.perform(
				post("/customer/verifyMobileDetails").param("entityId", Long.toString(CUST_ID))
											.param("entityType",Integer.toString(ENTITY_TYPE_CUSTOMER))
											   //.param("mobile", Long.toString(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getMobile()))
											   .param("mobileType",Integer.toString(ENTITY_TYPE_PRIMARY))
											   .param("mobilePin",Integer.toString(mobileVerificationDetailsDTO.getMobilePin())))
			.andDo(print())
			.andExpect(model().attribute("mobileVrificationStatus",is("sucess")))
			.andExpect(model().attributeExists("customerDetails"))
			.andExpect(model().attribute("customerDetails",hasProperty("isMobileVerified", is(true))));
											   
		
			
	}



	
	@Test
	public void verifySecondaryMobileDetails() throws Exception
	{
		
		this.mockMvc.perform(
								post("/customer/save").param("customerId", Long.toString(CUST_ID))
															.param("firstName",CUST_FIRSTNAME)
															   .param("lastName", CUST_LASTNAME)
															   .param("dateOfBirth", "01/01/1990")
															   .param("email",CUST_EMAIL)
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pincode",Integer.toString(CUST_PIN))
															   .param("customerType", Integer.toString(ENTITY_TYPE_CUSTOMER))
															   .param("isEmailVerified", "false")
															   .param("isMobileVerified", "false")
															   .param("isSecondaryMobileVerified", "false")
								
														    // .param("dateOfBirth", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getDateOfBirth().toString())
															   .param("homeAddressId.customerType", Integer.toString(standardAddress().getCustomerType()))
															   .param("homeAddressId.addressLine", standardAddress().getAddressLine())
															   .param("homeAddressId.city", standardAddress().getCity())
															   .param("homeAddressId.district", standardAddress().getDistrict())
															   .param("homeAddressId.state", standardAddress().getState())
															   .param("homeAddressId.pincode", Integer.toString(standardAddress().getPincode()))
															   
															   .param("language", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLanguage())
															   .param("businessDomain", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getBusinessDomain())
															   .param("nameOfFirm", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getNameOfFirm())
															   .param("firmAddressId.customerType", Integer.toString(standardAddress().getCustomerType()))
															   .param("firmAddressId.addressLine", standardAddress().getAddressLine())
															   .param("firmAddressId.city", standardAddress().getCity())
															   .param("firmAddressId.district", standardAddress().getDistrict())
															   .param("firmAddressId.state", standardAddress().getState())
															   .param("firmAddressId.pincode", Integer.toString(standardAddress().getPincode()))
															   
															   .param("secondaryMobile", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryMobile().toString())
															   .param("secondaryEmail", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryEmail())
															   
															  
															);

		
		quickRegisterService.reSendMobilePin(new CustomerIdTypeMobileTypeDTO(CUST_ID, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY));
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=
				quickRegisterService.getMobileVerificationDetailsByCustomerIdTypeAndMobile(CUST_ID, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY);
		
		this.mockMvc.perform(
				post("/customer/verifyMobileDetails").param("entityId", Long.toString(CUST_ID))
											.param("entityType",Integer.toString(ENTITY_TYPE_CUSTOMER))
											   //.param("mobile", Long.toString(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryMobile()))
											   .param("mobileType",Integer.toString(ENTITY_TYPE_SECONDARY))
											   .param("mobilePin",Integer.toString(mobileVerificationDetailsDTO.getMobilePin())))
			.andDo(print())
			.andExpect(model().attribute("mobileVrificationStatus",is("sucess")))
			.andExpect(model().attributeExists("customerDetails"))
			.andExpect(model().attribute("customerDetails",hasProperty("isSecondaryMobileVerified", is(true))));
											   
		
			
	}

	
	
	@Test
	public void sendMobileVerificationDetails() throws Exception
	{
		this.mockMvc.perform(
				post("/customer/save").param("customerId", Long.toString(CUST_ID))
											.param("firstName",CUST_FIRSTNAME)
											   .param("lastName", CUST_LASTNAME)
											   .param("dateOfBirth", "01/01/1990")
											   .param("email",CUST_EMAIL)
											   .param("mobile",Long.toString(CUST_MOBILE))
											   .param("pincode",Integer.toString(CUST_PIN))
											   .param("customerType", Integer.toString(ENTITY_TYPE_CUSTOMER))
											   .param("isEmailVerified", "false")
											   .param("isMobileVerified", "false")
											   .param("isSecondaryMobileVerified", "false")
				
										    // .param("dateOfBirth", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getDateOfBirth().toString())
											   .param("homeAddressId.customerType", Integer.toString(standardAddress().getCustomerType()))
											   .param("homeAddressId.addressLine", standardAddress().getAddressLine())
											   .param("homeAddressId.city", standardAddress().getCity())
											   .param("homeAddressId.district", standardAddress().getDistrict())
											   .param("homeAddressId.state", standardAddress().getState())
											   .param("homeAddressId.pincode", Integer.toString(standardAddress().getPincode()))
											   
											   .param("language", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLanguage())
											   .param("businessDomain", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getBusinessDomain())
											   .param("nameOfFirm", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getNameOfFirm())
											   .param("firmAddressId.customerType", Integer.toString(standardAddress().getCustomerType()))
											   .param("firmAddressId.addressLine", standardAddress().getAddressLine())
											   .param("firmAddressId.city", standardAddress().getCity())
											   .param("firmAddressId.district", standardAddress().getDistrict())
											   .param("firmAddressId.state", standardAddress().getState())
											   .param("firmAddressId.pincode", Integer.toString(standardAddress().getPincode()))
											   
											   .param("secondaryMobile", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryMobile().toString())
											   .param("secondaryEmail", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryEmail())
											   
											  
											);
		
		this.mockMvc.perform(
				post("/customer/sendMobileVerificationDetails").param("customerId", Long.toString(CUST_ID))
											.param("customerType",Integer.toString(ENTITY_TYPE_CUSTOMER))
											  .param("mobileType", Integer.toString(ENTITY_TYPE_PRIMARY))
											  )
			.andDo(print())
			.andExpect(model().attribute("sendMobileVerificationStatus",is("sucess")))
			.andExpect(model().attributeExists("customerDetails"));
											   
		
	}
	
	
	
		
	
	@Test
	public void sendEmailVerificationDetails() throws Exception
	{
		this.mockMvc.perform(
				post("/customer/save").param("customerId", Long.toString(CUST_ID))
											.param("firstName",CUST_FIRSTNAME)
											   .param("lastName", CUST_LASTNAME)
											   .param("dateOfBirth", "01/01/1990")
											   .param("email",CUST_EMAIL)
											   .param("mobile",Long.toString(CUST_MOBILE))
											   .param("pincode",Integer.toString(CUST_PIN))
											   .param("customerType", Integer.toString(ENTITY_TYPE_CUSTOMER))
											   .param("isEmailVerified", "false")
											   .param("isMobileVerified", "false")
											   .param("isSecondaryMobileVerified", "false")
				
										    // .param("dateOfBirth", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getDateOfBirth().toString())
											   .param("homeAddressId.customerType", Integer.toString(standardAddress().getCustomerType()))
											   .param("homeAddressId.addressLine", standardAddress().getAddressLine())
											   .param("homeAddressId.city", standardAddress().getCity())
											   .param("homeAddressId.district", standardAddress().getDistrict())
											   .param("homeAddressId.state", standardAddress().getState())
											   .param("homeAddressId.pincode", Integer.toString(standardAddress().getPincode()))
											   
											   .param("language", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLanguage())
											   .param("businessDomain", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getBusinessDomain())
											   .param("nameOfFirm", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getNameOfFirm())
											   .param("firmAddressId.customerType", Integer.toString(standardAddress().getCustomerType()))
											   .param("firmAddressId.addressLine", standardAddress().getAddressLine())
											   .param("firmAddressId.city", standardAddress().getCity())
											   .param("firmAddressId.district", standardAddress().getDistrict())
											   .param("firmAddressId.state", standardAddress().getState())
											   .param("firmAddressId.pincode", Integer.toString(standardAddress().getPincode()))
											   
											   .param("secondaryMobile", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryMobile().toString())
											   .param("secondaryEmail", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryEmail())
											   
											  
											);
		
		this.mockMvc.perform(
				post("/customer/sendEmailVerificationDetails").param("customerId", Long.toString(CUST_ID))
											.param("customerType",Integer.toString(ENTITY_TYPE_CUSTOMER))
											  .param("emailType", Integer.toString(ENTITY_TYPE_PRIMARY))
											  )
			.andDo(print())
			.andExpect(model().attribute("sendEmailVerificationStatus",is("sucess")))
			.andExpect(model().attributeExists("customerDetails"));
											   
		
	}
	
	
}
