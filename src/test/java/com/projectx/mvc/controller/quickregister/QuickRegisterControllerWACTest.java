package com.projectx.mvc.controller.quickregister;


import static com.projectx.mvc.fixtures.quickregister.AuthenticationDetailsDataFixtures.*;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.mvc.fixtures.completeregister.VendorDetailsDataFixture.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.*;

import javax.servlet.http.HttpSession;

import org.hamcrest.Matchers;
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
import com.projectx.mvc.domain.quickregister.QuickRegisterMVCDTO;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.completeregister.DocumentDetailsService;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.VerifyMobileDTO;

import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.mvc.fixtures.completeregister.AddressDataFixture.*;
import static com.projectx.mvc.fixtures.completeregister.DocumentDetailsDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
//@ActiveProfiles("Test")
public class QuickRegisterControllerWACTest {

	@Autowired
	private WebApplicationContext wac;

	MockMvc mockMvc;
	
	@Autowired
	QuickRegisterService quickRegisterService;

	@Autowired
	DocumentDetailsService documentDetailsService;
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
	}
	
	@Before
	public void clearTestData()
	{
		quickRegisterService.clearTestData();
		customerDetailsService.clearTestData();
		vendorDetailsService.clearTestData();
	}
	
	

	@Test
	public void thatCustomerQuickRegistrationWithEmailMobileViewRedirect() throws Exception
	{
		this.mockMvc.perform(
								post("/quickregister/").param("firstName",CUST_FIRSTNAME)
															   .param("lastName", CUST_LASTNAME)
															   .param("email",CUST_EMAIL)
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pin",Integer.toString(CUST_PIN))
															   .param("customerType", Integer.toString(1))										   
															   .param("requestBy", "CUST_ONLINE")
															)
			.andDo(print())												
			.andExpect(view().name("quickregister/verifyEmailMobile"))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("firstName", is(CUST_FIRSTNAME)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("lastName", is(CUST_LASTNAME)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("email", is(CUST_EMAIL)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("mobile", is(CUST_MOBILE)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("pincode", is(CUST_PIN)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("customerType", is(CUST_TYPE_CUSTOMER)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("isEmailVerified", is(false)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("isMobileVerified", is(false)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("updatedBy", is(CUST_UPDATED_BY)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("insertTime", notNullValue()))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("updateTime", notNullValue()))));
			
			
	}
	

	@Test
	public void thatCustomerQuickRegistrationWithMobileViewRedirect() throws Exception
	{
		this.mockMvc.perform(
								post("/quickregister/").param("firstName",CUST_FIRSTNAME)
															   .param("lastName", CUST_LASTNAME)
															   .param("email","")
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pin",Integer.toString(CUST_PIN))
															   .param("customerType", Integer.toString(1))
															   .param("requestBy", "CUST_ONLINE")
															  
															)
			.andDo(print())												
			.andExpect(view().name("quickregister/verifyEmailMobile"))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("firstName", is(CUST_FIRSTNAME)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("lastName", is(CUST_LASTNAME)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("email", nullValue()))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("mobile", is(CUST_MOBILE)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("pincode", is(CUST_PIN)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("customerType", is(CUST_TYPE_CUSTOMER)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("isEmailVerified", nullValue()))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("isMobileVerified", is(false)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("updatedBy", is(CUST_UPDATED_BY)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("insertTime", notNullValue()))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("updateTime", notNullValue()))));
			
			
	}

	
	@Test
	public void thatCustomerQuickRegistrationWithMobileAlreadyPresent() throws Exception
	{
		quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		this.mockMvc.perform(
								post("/quickregister").param("firstName",CUST_FIRSTNAME)
															   .param("lastName", CUST_LASTNAME)
															   .param("email","")
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pin",Integer.toString(CUST_PIN))
															   .param("customerType", Integer.toString(1))
															   .param("requestBy", "CUST_ONLINE")
															)
			.andDo(print())
			.andExpect(view().name("quickregister/alreadyRegistered"))
						.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("firstName", is(CUST_FIRSTNAME)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("firstName", is(CUST_FIRSTNAME)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("lastName", is(CUST_LASTNAME)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("email", is(CUST_EMAIL)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("mobile", is(CUST_MOBILE)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("pincode", is(CUST_PIN)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("customerType", is(CUST_TYPE_CUSTOMER)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("isEmailVerified", is(false)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("isMobileVerified", is(false)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("updatedBy", is(CUST_UPDATED_BY)))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("insertTime", notNullValue()))))
			.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("updateTime", notNullValue()))))

			.andExpect(model().attribute("message", "Provided Mobile Already Registered. Click Here to verify mobile."));
			
	}



	@Test
	public void verifyMobilePin() throws Exception
	{
		
		
		QuickRegisterDTO savedEntity=quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		
		
		MockHttpSession httpSession=new MockHttpSession();
		
		httpSession.setAttribute("customerQuickRegisterDTO", new QuickRegisterMVCDTO(savedEntity));
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=quickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntity.getCustomerId(),ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);
		
				this.mockMvc.perform(
						post("/quickregister/verifyMobilePin").param("customerId",Long.toString(savedEntity.getCustomerId()))
														.param("customerType",Integer.toString(ENTITY_TYPE_CUSTOMER))
														.param("mobileType",Integer.toString(ENTITY_TYPE_PRIMARY))
													   .param("mobilePin", Integer.toString(mobileVerificationDetailsDTO.getMobilePin()))
													   .param("requestBy", "CUST_ONLINE")
													   .session(httpSession)
													   
													)
				.andDo(print())
			//	.andExpect(model().attribute("mobileVerificationStatus", "Mobile Verification Sucess"))
				//.andExpect(model().attribute("customerQuickRegisterDTO", isA(CustomerQuickRegisterMVCDTO.class)))
				.andExpect(view().name("quickregister/loginForm"));		
	}
	
	
	
	
	
	@Test
	public void verifyEmailHash() throws Exception
	{
		QuickRegisterDTO savedEntity=quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		EmailVerificationDetailsDTO emailVerificationDetailsDTO=quickRegisterService
				.getEmailVerificationDetailsByCustomerIdTypeAndEmail(savedEntity.getCustomerId(),ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);
		
		
		this.mockMvc.perform(get("/quickregister/verifyEmailHash/"+savedEntity.getCustomerId()+"/"+ENTITY_TYPE_CUSTOMER+"/"
						+ENTITY_TYPE_CUSTOMER+"/"+"UPDATEDBYCUST"+"/"+emailVerificationDetailsDTO.getEmailHash()))
		
		.andDo(print())
		.andExpect(model().attribute("emailVerificationStatus", "Email Verification Sucess"))
		.andExpect(view().name("quickregister/loginForm"));
	}
	
	@Test
	public void reSendMobilePin() throws Exception
	{
		QuickRegisterDTO savedEntity=quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
		this.mockMvc.perform(
				post("/quickregister/resendMobilePin").param("customerId",Long.toString(savedEntity.getCustomerId())														)
																.param("customerType", Integer.toString(ENTITY_TYPE_CUSTOMER))
																.param("mobileType",Integer.toString(ENTITY_TYPE_PRIMARY))
																.param("updatedBy", "CUST_ONLINE")
											   
											)
		.andDo(print())
		.andExpect(content().string("Sucess"));
	}
	
	
	
	@Test
	public void reSendEmailHash() throws Exception
	{
		QuickRegisterDTO savedEntity=quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
		
				
		
		this.mockMvc.perform(
				post("/quickregister/resendEmailHash").param("customerId",Long.toString(savedEntity.getCustomerId()))
																.param("customerType", Integer.toString(ENTITY_TYPE_CUSTOMER))
																.param("emailType",Integer.toString(ENTITY_TYPE_PRIMARY))
																.param("updatedBy","CUST_ONLINE")
					)
		.andDo(print())
		.andExpect(content().string("Sucess"));
	}
	

	
	@Test
	public void verifyLoginDetailsEmailAsUserIdProceedToForcefulPasswordChange() throws Exception
	{
		QuickRegisterSavedEntityDTO quickRegisterDTO=quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=
				quickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType(),
						ENTITY_TYPE_PRIMARY);
		
		quickRegisterService.verifyMobile(new VerifyMobileDTO(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType(),
				ENTITY_TYPE_PRIMARY,mobileVerificationDetailsDTO.getMobilePin(),CUST_UPDATED_BY));
		
		AuthenticationDetailsDTO authenticationDetailsDTO=quickRegisterService
				.getAuthenticationDetailsByCustomerIdType(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType());
		
	
		//TODO uncomment following 
		
		/*
		this.mockMvc.perform(
				post("/quickregister/verifyLoginDetails").param("entity",quickRegisterDTO.getCustomer().getEmail())
											   .param("password", authenticationDetailsDTO.getPassword())
											   											  
											)
				.andDo(print())
				.andExpect(view().name("quickregister/forcePasswordChange"))
				.andExpect(model().attributeExists("loginDetails"))	
				.andExpect(model().attribute("loginDetails",Matchers.allOf(
			//	hasProperty("key", equalTo(standardCustomerEmailMobileAuthenticationDetails().getKey())),
				hasProperty("email", equalTo(standardCustomerEmailMobileAuthenticationDetails().getEmail())),
				hasProperty("mobile", equalTo(standardCustomerEmailMobileAuthenticationDetails().getMobile())),
			//	hasProperty("password",equalTo(authenticationDetailsDTO.getPassword())),
				hasProperty("passwordType", equalTo(standardCustomerEmailMobileAuthenticationDetails().getPasswordType()))
					)));
		*/	
	
	}
	
	
	@Test
	public void verifyLoginDetailsEmailAsUserIdProceedToCompleteRegistration() throws Exception
	{
		QuickRegisterSavedEntityDTO quickRegisterDTO=quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=
				quickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType(),
						ENTITY_TYPE_PRIMARY);
		
		quickRegisterService.verifyMobile(new VerifyMobileDTO(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType(),
				ENTITY_TYPE_PRIMARY,mobileVerificationDetailsDTO.getMobilePin(),CUST_UPDATED_BY));
		
		AuthenticationDetailsDTO authenticationDetailsDTO=quickRegisterService
				.getAuthenticationDetailsByCustomerIdType(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType());
		
		quickRegisterService.updatePassword(new UpdatePasswordDTO(authenticationDetailsDTO.getKey(), CUST_PASSWORD_CHANGED,true,CUST_UPDATED_BY));
		
		
		this.mockMvc.perform(
				post("/quickregister/verifyLoginDetails").param("entity",quickRegisterDTO.getCustomer().getEmail())
											   .param("password", CUST_PASSWORD_CHANGED)
											   											  
											)
				.andDo(print())
				.andExpect(view().name("completeregister/customerDetailsForm"))
				.andExpect(model().attributeExists("customerDetails"))
				.andExpect(model().attribute("customerDetails",hasProperty("firstName", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getFirstName()))))
				.andExpect(model().attribute("customerDetails",hasProperty("lastName", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLastName()))))
				.andExpect(model().attribute("customerDetails",hasProperty("email", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getEmail()))))
				.andExpect(model().attribute("customerDetails",hasProperty("mobile", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getMobile()))))
				
				.andExpect(model().attribute("customerDetails",hasProperty("isEmailVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsEmailVerified()))))
				.andExpect(model().attribute("customerDetails",hasProperty("isMobileVerified", is(true))))
				.andExpect(model().attribute("customerDetails",hasProperty("isSecondaryMobileVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsSecondaryMobileVerified()))))
				.andExpect(model().attribute("customerDetails",hasProperty("homeAddressId", isEmptyOrNullString())))
				.andExpect(model().attribute("customerDetails",hasProperty("language", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getLanguage()))))
				.andExpect(model().attribute("customerDetails",hasProperty("businessDomain", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getBusinessDomain()))))
				.andExpect(model().attribute("customerDetails",hasProperty("nameOfFirm", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getNameOfFirm()))))
				.andExpect(model().attribute("customerDetails",hasProperty("firmAddressId", isEmptyOrNullString())))
				.andExpect(model().attribute("customerDetails",hasProperty("secondaryMobile", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getSecondaryMobile()))))
				.andExpect(model().attribute("customerDetails",hasProperty("isSecondaryMobileVerified", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getIsSecondaryMobileVerified()))))
				.andExpect(model().attribute("customerDetails",hasProperty("secondaryEmail", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getSecondaryEmail()))));
	}
	
	
	@Test
	public void verifyLoginDetailsEmailAsUserIdProceedToShowCustomerDetails() throws Exception
	{
		QuickRegisterSavedEntityDTO quickRegisterDTO=quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());

		documentDetailsService.saveDocument(new DocumentDetails(new DocumentKey(quickRegisterDTO.getCustomer().getCustomerId(),
				1, "DrivingLicense"),
						documentDummy(),standardDocumentDetails().getContentType() , standardDocumentDetails().getVerificationStatus(),
						standardDocumentDetails().getVerificationRemark(), standardDocumentDetails().getInsertTime(),
						standardDocumentDetails().getUpdateTime(), standardDocumentDetails().getUpdatedBy()));
		

		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=
				quickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType(),
						ENTITY_TYPE_PRIMARY);
		
		quickRegisterService.verifyMobile(new VerifyMobileDTO(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType(),
				ENTITY_TYPE_PRIMARY,mobileVerificationDetailsDTO.getMobilePin(),CUST_UPDATED_BY));
		
		AuthenticationDetailsDTO authenticationDetailsDTO=quickRegisterService
				.getAuthenticationDetailsByCustomerIdType(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType());
		
		quickRegisterService.updatePassword(new UpdatePasswordDTO(authenticationDetailsDTO.getKey(), CUST_PASSWORD_CHANGED,true
				,CUST_UPDATED_BY));
		
		
		this.mockMvc.perform(
				post("/quickregister/verifyLoginDetails").param("entity",quickRegisterDTO.getCustomer().getEmail())
											   .param("password", CUST_PASSWORD_CHANGED)
											   											  
											);
		
		this.mockMvc.perform(
				post("/quickregister/verifyLoginDetails").param("entity",quickRegisterDTO.getCustomer().getEmail())
											   .param("password", CUST_PASSWORD_CHANGED)
											   											  
											)
				.andDo(print())
				.andExpect(view().name("completeregister/showCustomerDetails"))
				.andExpect(model().attributeExists("customerDetails"))
				.andExpect(model().attribute("customerDetails",hasProperty("firstName", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getFirstName()))))
				.andExpect(model().attribute("customerDetails",hasProperty("lastName", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLastName()))))
				.andExpect(model().attribute("customerDetails",hasProperty("email", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getEmail()))))
				.andExpect(model().attribute("customerDetails",hasProperty("mobile", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getMobile()))))
				
				.andExpect(model().attribute("customerDetails",hasProperty("isEmailVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsEmailVerified()))))
				.andExpect(model().attribute("customerDetails",hasProperty("isMobileVerified", is(true))))
				.andExpect(model().attribute("customerDetails",hasProperty("isSecondaryMobileVerified", is(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsSecondaryMobileVerified()))))
				.andExpect(model().attribute("customerDetails",hasProperty("homeAddressId", isEmptyOrNullString())))
				.andExpect(model().attribute("customerDetails",hasProperty("language", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getLanguage()))))
				.andExpect(model().attribute("customerDetails",hasProperty("businessDomain", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getBusinessDomain()))))
				.andExpect(model().attribute("customerDetails",hasProperty("nameOfFirm", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getNameOfFirm()))))
				.andExpect(model().attribute("customerDetails",hasProperty("firmAddressId", isEmptyOrNullString())))
				.andExpect(model().attribute("customerDetails",hasProperty("secondaryMobile", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getSecondaryMobile()))))
				.andExpect(model().attribute("customerDetails",hasProperty("isSecondaryMobileVerified", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getIsSecondaryMobileVerified()))))
				.andExpect(model().attribute("customerDetails",hasProperty("secondaryEmail", is(standardCustomerDetailsCopiedFromQuickRegisterEntity().getSecondaryEmail()))));
		
	}
	

	@Test
	public void verifyLoginDetailsEmailAsUserIdProceedToVendorCompleteRegistration() throws Exception
	{
		QuickRegisterSavedEntityDTO quickRegisterDTO=quickRegisterService.addNewCustomer(standardEmailMobileVendorDTO());
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=
				quickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType(),
						ENTITY_TYPE_PRIMARY);
		
		quickRegisterService.verifyMobile(new VerifyMobileDTO(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType(),
				ENTITY_TYPE_PRIMARY,mobileVerificationDetailsDTO.getMobilePin(),CUST_UPDATED_BY));
		
		AuthenticationDetailsDTO authenticationDetailsDTO=quickRegisterService
				.getAuthenticationDetailsByCustomerIdType(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType());
		
		quickRegisterService.updatePassword(new UpdatePasswordDTO(authenticationDetailsDTO.getKey(), CUST_PASSWORD_CHANGED,true,CUST_UPDATED_BY));
		
		
		this.mockMvc.perform(
				post("/quickregister/verifyLoginDetails").param("entity",quickRegisterDTO.getCustomer().getEmail())
											   .param("password", CUST_PASSWORD_CHANGED)
											   											  
											)
				.andDo(print())
				.andExpect(view().name("completeregister/vendorDetailsForm"))
				.andExpect(model().attributeExists("vendorDetails"))
				.andExpect(model().attribute("vendorDetails",hasProperty("vendorId", is(quickRegisterDTO.getCustomer().getCustomerId()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("firstName", is(standardVendor(standardVendorCreatedFromQuickRegister()).getFirstName()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("lastName", is(standardVendor(standardVendorCreatedFromQuickRegister()).getLastName()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("email", is(standardVendor(standardVendorCreatedFromQuickRegister()).getEmail()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("mobile", is(standardVendor(standardVendorCreatedFromQuickRegister()).getMobile()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("isEmailVerified", is(standardVendor(standardVendorCreatedFromQuickRegister()).getIsEmailVerified()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("isMobileVerified", is(true))))
				.andExpect(model().attribute("vendorDetails",hasProperty("laguage", nullValue())))
				.andExpect(model().attribute("vendorDetails",hasProperty("firmAddress", nullValue())));
				
	}

	
	@Test
	public void verifyLoginDetailsEmailAsUserIdProceedToShowVendorDetails() throws Exception
	{
		QuickRegisterSavedEntityDTO quickRegisterDTO=quickRegisterService.addNewCustomer(standardEmailMobileVendorDTO());

		documentDetailsService.saveDocument(new DocumentDetails(new DocumentKey(quickRegisterDTO.getCustomer().getCustomerId(),
				ENTITY_TYPE_VENDOR, "DrivingLicense"),
						documentDummy(),standardDocumentDetails().getContentType() , standardDocumentDetails().getVerificationStatus(),
						standardDocumentDetails().getVerificationRemark(), standardDocumentDetails().getInsertTime(),
						standardDocumentDetails().getUpdateTime(), standardDocumentDetails().getUpdatedBy()));
		

		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=
				quickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType(),
						ENTITY_TYPE_PRIMARY);
		
		quickRegisterService.verifyMobile(new VerifyMobileDTO(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType(),
				ENTITY_TYPE_PRIMARY,mobileVerificationDetailsDTO.getMobilePin(),CUST_UPDATED_BY));
		
		AuthenticationDetailsDTO authenticationDetailsDTO=quickRegisterService
				.getAuthenticationDetailsByCustomerIdType(quickRegisterDTO.getCustomer().getCustomerId(), quickRegisterDTO.getCustomer().getCustomerType());
		
		quickRegisterService.updatePassword(new UpdatePasswordDTO(authenticationDetailsDTO.getKey(), CUST_PASSWORD_CHANGED,true,CUST_UPDATED_BY));
		
		
		this.mockMvc.perform(
				post("/quickregister/verifyLoginDetails").param("entity",quickRegisterDTO.getCustomer().getEmail())
											   .param("password", CUST_PASSWORD_CHANGED)
											   											  
											);
		
		this.mockMvc.perform(
				post("/quickregister/verifyLoginDetails").param("entity",quickRegisterDTO.getCustomer().getEmail())
											   .param("password", CUST_PASSWORD_CHANGED)
											   											  
											)
				.andDo(print())
				.andExpect(view().name("completeregister/showVendorDetails"))
				.andExpect(model().attributeExists("vendorDetails"))
				.andExpect(model().attribute("vendorDetails",hasProperty("vendorId", is(quickRegisterDTO.getCustomer().getCustomerId()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("firstName", is(standardVendor(standardVendorCreatedFromQuickRegister()).getFirstName()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("lastName", is(standardVendor(standardVendorCreatedFromQuickRegister()).getLastName()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("email", is(standardVendor(standardVendorCreatedFromQuickRegister()).getEmail()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("mobile", is(standardVendor(standardVendorCreatedFromQuickRegister()).getMobile()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("isEmailVerified", is(standardVendor(standardVendorCreatedFromQuickRegister()).getIsEmailVerified()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("isMobileVerified", is(true))))
				.andExpect(model().attribute("vendorDetails",hasProperty("laguage", nullValue())))
				.andExpect(model().attribute("vendorDetails",hasProperty("firmAddress", nullValue())));
		
	}

	
	@Test
	public void verifyEmailDetailsWithCustomer() throws Exception
	{

		MockHttpSession httpSession=new MockHttpSession();
		
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
															   .session(httpSession)
															  
															);

		quickRegisterService.reSendEmailHash(new CustomerIdTypeEmailTypeUpdatedByDTO(CUST_ID, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY,CUST_UPDATED_BY));
		
		EmailVerificationDetailsDTO emailVerificationDetailsDTO=
				quickRegisterService.getEmailVerificationDetailsByCustomerIdTypeAndEmail(CUST_ID, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);
		
		this.mockMvc.perform(
				get("/quickregister/verifyEmailHash/"+CUST_ID+"/"+ENTITY_TYPE_CUSTOMER+"/"+ENTITY_TYPE_PRIMARY+"/CUST_ONLINE/"+emailVerificationDetailsDTO.getEmailHash())
				.session(httpSession))
			.andDo(print())
			.andExpect(view().name("quickregister/loginForm"))
			.andExpect(model().attribute("emailVerificationStatus",is("Email Verification Sucess")))
			;
											   
		
			
	}

	
	@Test
	public void verifyEmailDetailsWithVendor() throws Exception
	{
		
		this.mockMvc.perform(
				post("/vendor/save").param("vendorId", Long.toString(VENDOR_ID))
											    .param("firstName",VENDER_FIRSTNAME)
											   .param("lastName", VENDER_LASTNAME)
											   .param("dateOfBirth", "01/01/1990")
											   .param("email",VENDOR_EMAIL)
											   .param("mobile",Long.toString(VENDOR_MOBILE))
											   .param("customerType", Integer.toString(ENTITY_TYPE_CUSTOMER))
											   .param("isEmailVerified", "false")
											   .param("isMobileVerified", "false")
											   
				
										    // .param("dateOfBirth", standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getDateOfBirth().toString())
											   
											   .param("laguage", standardVendor(standardVendorCreatedFromQuickRegister()).getLaguage())
											   .param("firmAddress.customerType", Integer.toString(standardAddress().getCustomerType()))
											   .param("firmAddress.addressLine", standardAddress().getAddressLine())
											   .param("firmAddress.city", standardAddress().getCity())
											   .param("firmAddress.district", standardAddress().getDistrict())
											   .param("firmAddress.state", standardAddress().getState())
											   .param("firmAddress.pincode", Integer.toString(standardAddress().getPincode()))
											   
											   
											  
											);
		
		quickRegisterService.reSendEmailHash(new CustomerIdTypeEmailTypeUpdatedByDTO(VENDOR_ID, ENTITY_TYPE_VENDOR,
				ENTITY_TYPE_PRIMARY,CUST_UPDATED_BY));
		
		EmailVerificationDetailsDTO emailVerificationDetailsDTO=
				quickRegisterService.getEmailVerificationDetailsByCustomerIdTypeAndEmail(VENDOR_ID, ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY);
		
		
		
		
		this.mockMvc.perform(
				get("/quickregister/verifyEmailHash/"+VENDOR_ID+"/"+ENTITY_TYPE_VENDOR+"/"+ENTITY_TYPE_PRIMARY+"/CUST_ONLINE/"+emailVerificationDetailsDTO.getEmailHash()))
			.andDo(print())
			.andExpect(model().attribute("emailVerificationStatus",is("Email Verification Sucess")))
			.andExpect(view().name("quickregister/loginForm"));
			//.andExpect(model().attributeExists("vendorDetails"))
			//.andExpect(model().attribute("vendorDetails",hasProperty("isEmailVerified", is(true))));
					
		
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
