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
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;

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
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		customerDetailsService.clearTestData();
				
	}

	
	@Test
	public void environmentTest() {
		
		
	}
	
	@Test
	public void createCustomerDetailsFromQuickRegisterEntity() throws Exception
	{
		this.mockMvc.perform(
								post("/customer/createCustomerDetailsFromQuickRegisterEntity").param("customerId", Long.toString(CUST_ID))
															.param("firstName",CUST_FIRSTNAME)
															   .param("lastName", CUST_LASTNAME)
															   .param("email",CUST_EMAIL)
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pincode",Integer.toString(CUST_PIN))
															   .param("customerType", Integer.toString(CUST_TYPE))
															   .param("isEmailVerified", "true")
															   .param("isMobileVerified", "true")
															   
															  
															)
			.andDo(print())												
			.andExpect(view().name("customerDetails"));
	//		.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("firstName", is(CUST_FIRSTNAME)))));
			
			
	}
	
	
	@Test
	public void save() throws Exception
	{
		
		this.mockMvc.perform(
				post("/customer/createCustomerDetailsFromQuickRegisterEntity").param("customerId", Long.toString(CUST_ID))
											.param("firstName",CUST_FIRSTNAME)
											   .param("lastName", CUST_LASTNAME)
											   .param("email",CUST_EMAIL)
											   .param("mobile",Long.toString(CUST_MOBILE))
											   .param("pincode",Integer.toString(CUST_PIN))
											   .param("customerType", Integer.toString(CUST_TYPE))
											   .param("isEmailVerified", "true")
											   .param("isMobileVerified", "true")
											   
											  
											);
		
		this.mockMvc.perform(
								post("/customer/save").param("customerId", Long.toString(CUST_ID))
															.param("firstName",CUST_FIRSTNAME)
															   .param("lastName", CUST_LASTNAME)
															   .param("email",CUST_EMAIL)
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pincode",Integer.toString(CUST_PIN))
															   .param("customerType", Integer.toString(CUST_TYPE))
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
			.andExpect(view().name("showCustomerDetails"))
			.andExpect(model().size(2))
			.andExpect(model().attributeExists("customerdetails"))
			.andExpect(model().attribute("customerdetails",hasProperty("customerId", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getCustomerId()))))
			.andExpect(model().attribute("customerdetails",hasProperty("firstName", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getFirstName()))))
			.andExpect(model().attribute("customerdetails",hasProperty("lastName", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLastName()))))
			.andExpect(model().attribute("customerdetails",hasProperty("email", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getEmail()))))
			.andExpect(model().attribute("customerdetails",hasProperty("mobile", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getMobile()))))
			
			.andExpect(model().attribute("customerdetails",hasProperty("isEmailVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsEmailVerified()))))
			.andExpect(model().attribute("customerdetails",hasProperty("isMobileVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsMobileVerified()))))
			.andExpect(model().attribute("customerdetails",hasProperty("isSecondaryMobileVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsSecondaryMobileVerified()))))
			.andExpect(model().attribute("customerdetails",hasProperty("homeAddressId", is(standardAddress()))))
			.andExpect(model().attribute("customerdetails",hasProperty("language", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLanguage()))))
			.andExpect(model().attribute("customerdetails",hasProperty("businessDomain", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getBusinessDomain()))))
			.andExpect(model().attribute("customerdetails",hasProperty("nameOfFirm", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getNameOfFirm()))))
			.andExpect(model().attribute("customerdetails",hasProperty("firmAddressId", is(standardAddress()))))
			.andExpect(model().attribute("customerdetails",hasProperty("secondaryMobile", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryMobile()))))
			.andExpect(model().attribute("customerdetails",hasProperty("isSecondaryMobileVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsSecondaryMobileVerified()))))
			.andExpect(model().attribute("customerdetails",hasProperty("secondaryEmail", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryEmail()))));
						
	}
	
	
	@Test
	public void verifyMobileDetails() throws Exception
	{
		
		this.mockMvc.perform(
								post("/customer/save").param("customerId", Long.toString(CUST_ID))
															.param("firstName",CUST_FIRSTNAME)
															   .param("lastName", CUST_LASTNAME)
															   .param("email",CUST_EMAIL)
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pincode",Integer.toString(CUST_PIN))
															   .param("customerType", Integer.toString(CUST_TYPE))
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

		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=
				quickRegisterService.getMobileVerificationDetailsByCustomerIdTypeAndMobile(CUST_ID, 1, standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getMobile());
		
		this.mockMvc.perform(
				post("/customer/verifyMobileDetails").param("customerId", Long.toString(CUST_ID))
											.param("customerType",Integer.toString(1))
											   .param("mobile", Long.toString(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getMobile()))
											   .param("mobileType",Integer.toString(1))
											   .param("mobilePin",Integer.toString(mobileVerificationDetailsDTO.getMobilePin())))
			.andDo(print())
			.andExpect(model().attribute("mobileVrificationStatus",is("sucess")))
			.andExpect(model().attributeExists("customerdetails"))
			.andExpect(model().attribute("customerdetails",hasProperty("isMobileVerified", is(true))));
											   
		
			
	}

	@Test
	public void verifySecondaryMobileDetails() throws Exception
	{
		
		this.mockMvc.perform(
								post("/customer/save").param("customerId", Long.toString(CUST_ID))
															.param("firstName",CUST_FIRSTNAME)
															   .param("lastName", CUST_LASTNAME)
															   .param("email",CUST_EMAIL)
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pincode",Integer.toString(CUST_PIN))
															   .param("customerType", Integer.toString(CUST_TYPE))
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

		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=
				quickRegisterService.getMobileVerificationDetailsByCustomerIdTypeAndMobile(CUST_ID, 1, standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryMobile());
		
		this.mockMvc.perform(
				post("/customer/verifyMobileDetails").param("customerId", Long.toString(CUST_ID))
											.param("customerType",Integer.toString(1))
											   .param("mobile", Long.toString(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryMobile()))
											   .param("mobileType",Integer.toString(2))
											   .param("mobilePin",Integer.toString(mobileVerificationDetailsDTO.getMobilePin())))
			.andDo(print())
			.andExpect(model().attribute("mobileVrificationStatus",is("sucess")))
			.andExpect(model().attributeExists("customerdetails"))
			.andExpect(model().attribute("customerdetails",hasProperty("isSecondaryMobileVerified", is(true))));
											   
		
			
	}
	
	
	@Test
	public void sendMobileVerificationDetails() throws Exception
	{
		this.mockMvc.perform(
				post("/customer/save").param("customerId", Long.toString(CUST_ID))
											.param("firstName",CUST_FIRSTNAME)
											   .param("lastName", CUST_LASTNAME)
											   .param("email",CUST_EMAIL)
											   .param("mobile",Long.toString(CUST_MOBILE))
											   .param("pincode",Integer.toString(CUST_PIN))
											   .param("customerType", Integer.toString(CUST_TYPE))
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
											.param("customerType",Integer.toString(1))
											  .param("mobile", Long.toString(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getMobile()))
											  )
			.andDo(print())
			.andExpect(model().attribute("sendMobileVerificationStatus",is("sucess")))
			.andExpect(model().attributeExists("customerdetails"));
											   
		
	}
	
	
	
	@Test
	public void verifyEmailDetails() throws Exception
	{
		
		this.mockMvc.perform(
								post("/customer/save").param("customerId", Long.toString(CUST_ID))
															.param("firstName",CUST_FIRSTNAME)
															   .param("lastName", CUST_LASTNAME)
															   .param("email",CUST_EMAIL)
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pincode",Integer.toString(CUST_PIN))
															   .param("customerType", Integer.toString(CUST_TYPE))
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

		EmailVerificationDetailsDTO emailVerificationDetailsDTO=
				quickRegisterService.getEmailVerificationDetailsByCustomerIdTypeAndEmail(CUST_ID, 1, standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getEmail());
		
		this.mockMvc.perform(
				get("/customer/verifyEmailDetails/"+CUST_ID+"/"+CUST_TYPE+"/"+CUST_EMAIL+"/"+emailVerificationDetailsDTO.getEmailHash()))
			.andDo(print())
			.andExpect(model().attribute("emailVrificationStatus",is("sucess")))
			.andExpect(model().attributeExists("customerdetails"))
			.andExpect(model().attribute("customerdetails",hasProperty("isEmailVerified", is(true))));
											   
		
			
	}
	
	
	@Test
	public void sendEmailVerificationDetails() throws Exception
	{
		this.mockMvc.perform(
				post("/customer/save").param("customerId", Long.toString(CUST_ID))
											.param("firstName",CUST_FIRSTNAME)
											   .param("lastName", CUST_LASTNAME)
											   .param("email",CUST_EMAIL)
											   .param("mobile",Long.toString(CUST_MOBILE))
											   .param("pincode",Integer.toString(CUST_PIN))
											   .param("customerType", Integer.toString(CUST_TYPE))
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
											.param("customerType",Integer.toString(1))
											  .param("email", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getEmail())
											  )
			.andDo(print())
			.andExpect(model().attribute("sendEmailVerificationStatus",is("sucess")))
			.andExpect(model().attributeExists("customerdetails"));
											   
		
	}
	
	
}
