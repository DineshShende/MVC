package com.projectx.mvc.controller.completeregister;

import static com.projectx.mvc.fixtures.completeregister.AddressDataFixture.*;
import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.standardCustomerDetails;
import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.standardCustomerDetailsCopiedFromQuickRegisterEntity;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.mvc.fixtures.completeregister.VendorDetailsDataFixture.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class VendorDetailsWACTest {

	@Autowired
	private WebApplicationContext wac;

	MockMvc mockMvc;
	
	@Autowired
	VendorDetailsService vendorDetailsService;


	@Autowired
	QuickRegisterService quickRegisterService;
	
	@Autowired
	SimpleDateFormat simpleDateFormat;
	
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		vendorDetailsService.clearTestData();
		quickRegisterService.clearTestData();
	}

	
	@Test
	public void environmentTest() {
		
		
	}

	@Test
	public void createCustomerDetailsFromQuickRegisterEntity() throws Exception
	{
		QuickRegisterDTO quickRegisterSavedEntityDTO=
				quickRegisterService.addNewCustomer(standardEmailMobileVendorDTO()).getCustomer();
	
		
		this.mockMvc.perform(
								post("/vendor/createCustomerDetailsFromQuickRegisterEntity").param("customerId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
															.param("firstName",CUST_FIRSTNAME)
															   .param("lastName", CUST_LASTNAME)
															   .param("email",CUST_EMAIL)
															   .param("mobile",Long.toString(CUST_MOBILE))
															   .param("pincode",Integer.toString(CUST_PIN))
															   .param("customerType", Integer.toString(ENTITY_TYPE_VENDOR))
															   .param("isEmailVerified", "true")
															   .param("isMobileVerified", "true")
															   
															  
															)
			.andDo(print())												
			.andExpect(view().name("completeregister/vendorDetailsForm"));
	//		.andExpect(model().attribute("customerQuickRegisterDTO", allOf(hasProperty("firstName", is(CUST_FIRSTNAME)))));
			
			
	}
	

	@Test
	public void save() throws Exception
	{
	
			QuickRegisterDTO quickRegisterSavedEntityDTO=
				quickRegisterService.addNewCustomer(standardEmailMobileVendorDTO()).getCustomer();
	
		
		this.mockMvc.perform(
				post("/vendor/createCustomerDetailsFromQuickRegisterEntity").param("customerId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
											.param("firstName",VENDER_FIRSTNAME)
											   .param("lastName", VENDER_LASTNAME)
											   .param("email",VENDOR_EMAIL)
											   .param("mobile",Long.toString(VENDOR_MOBILE))
											   .param("pincode",Integer.toString(ADDRESS_PINCODE))
											   .param("customerType", Integer.toString(ENTITY_TYPE_VENDOR))
											   .param("isEmailVerified", "true")
											   .param("isMobileVerified", "true")
											   
											  
											);
		
		this.mockMvc.perform(
								post("/vendor/save").param("vendorId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
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
															   
															   
															  
															)
			.andDo(print())											
			.andExpect(view().name("completeregister/documentUploadVendor"))
			.andExpect(model().size(2))
			.andExpect(model().attributeExists("vendorDetails"))
			.andExpect(model().attribute("vendorDetails",hasProperty("vendorId", is(quickRegisterSavedEntityDTO.getCustomerId()))))
			.andExpect(model().attribute("vendorDetails",hasProperty("firstName", is(standardVendor(standardVendorCreatedFromQuickRegister()).getFirstName()))))
			.andExpect(model().attribute("vendorDetails",hasProperty("lastName", is(standardVendor(standardVendorCreatedFromQuickRegister()).getLastName()))))
			.andExpect(model().attribute("vendorDetails",hasProperty("dateOfBirth", is(simpleDateFormat.parse("01-01-1990")))))
			.andExpect(model().attribute("vendorDetails",hasProperty("email", is(standardVendor(standardVendorCreatedFromQuickRegister()).getEmail()))))
			.andExpect(model().attribute("vendorDetails",hasProperty("mobile", is(standardVendor(standardVendorCreatedFromQuickRegister()).getMobile()))))
			.andExpect(model().attribute("vendorDetails",hasProperty("isEmailVerified", is(standardVendor(standardVendorCreatedFromQuickRegister()).getIsEmailVerified()))))
			.andExpect(model().attribute("vendorDetails",hasProperty("isMobileVerified", is(standardVendor(standardVendorCreatedFromQuickRegister()).getIsMobileVerified()))))
			.andExpect(model().attribute("vendorDetails",hasProperty("laguage", is(standardVendor(standardVendorCreatedFromQuickRegister()).getLaguage()))))
			.andExpect(model().attribute("vendorDetails",hasProperty("firmAddress", is(standardAddress()))));
			
	}

	@Test
	public void saveWithErrore() throws Exception
	{
	
			QuickRegisterDTO quickRegisterSavedEntityDTO=
				quickRegisterService.addNewCustomer(standardEmailMobileVendorDTO()).getCustomer();
	
		
		this.mockMvc.perform(
				post("/vendor/createCustomerDetailsFromQuickRegisterEntity").param("customerId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
											.param("firstName",VENDER_FIRSTNAME)
											   .param("lastName", VENDER_LASTNAME)
											   .param("email",VENDOR_EMAIL)
											   .param("mobile",Long.toString(VENDOR_MOBILE))
											   .param("pincode",Integer.toString(ADDRESS_PINCODE))
											   .param("customerType", Integer.toString(ENTITY_TYPE_VENDOR))
											   .param("isEmailVerified", "true")
											   .param("isMobileVerified", "true")
											   
											  
											);
		
		this.mockMvc.perform(
								post("/vendor/save").param("vendorId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
															    .param("firstName",VENDER_FIRSTNAME)
															   .param("lastName", VENDER_LASTNAME)
															   .param("dateOfBirth", "01/01/1990")
															   .param("email",VENDOR_EMAIL)
															   .param("mobile",Long.toString(100000000000L))
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
															   
															   
															  
															)
			.andDo(print())											
			.andExpect(view().name("completeregister/vendorDetailsForm"))
			.andExpect(model().size(2))
			.andExpect(model().attributeExists("vendorDetails"));
			
	}

	
	@Test
	public void updateWithErrors() throws Exception
	{
	
			QuickRegisterDTO quickRegisterSavedEntityDTO=
				quickRegisterService.addNewCustomer(standardEmailMobileVendorDTO()).getCustomer();
	
		
		this.mockMvc.perform(
				post("/vendor/createCustomerDetailsFromQuickRegisterEntity").param("customerId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
											.param("firstName",VENDER_FIRSTNAME)
											   .param("lastName", VENDER_LASTNAME)
											   .param("email",VENDOR_EMAIL)
											   .param("mobile",Long.toString(VENDOR_MOBILE))
											   .param("pincode",Integer.toString(ADDRESS_PINCODE))
											   .param("customerType", Integer.toString(ENTITY_TYPE_VENDOR))
											   .param("isEmailVerified", "true")
											   .param("isMobileVerified", "true")
											   
											  
											);
		
		this.mockMvc.perform(
								post("/vendor/save").param("vendorId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
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
	
		Address address=vendorDetailsService.getCustomerDetailsById(quickRegisterSavedEntityDTO.getCustomerId()).getFirmAddress();
		
		this.mockMvc.perform(
				post("/vendor/edit").param("vendorId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
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
											   .param("firmAddress.addressId", Long.toString(address.getAddressId()))
											   .param("firmAddress.customerType", Integer.toString(address.getCustomerType()))
											   .param("firmAddress.addressLine", standardAddressUpdated().getAddressLine())
											   .param("firmAddress.city", "")
											   .param("firmAddress.district", standardAddressUpdated().getDistrict())
											   .param("firmAddress.state", standardAddressUpdated().getState())
											   .param("firmAddress.pincode", Integer.toString(standardAddressUpdated().getPincode()))
											   
											   
											  
															)
				.andDo(print())											
				.andExpect(view().name("completeregister/vendorDetailsForm"))
				.andExpect(model().size(2))
				.andExpect(model().attributeExists("vendorDetails"));
		
		
			
	}

	
	
	
	@Test
	public void update() throws Exception
	{
	
			QuickRegisterDTO quickRegisterSavedEntityDTO=
				quickRegisterService.addNewCustomer(standardEmailMobileVendorDTO()).getCustomer();
	
		
		this.mockMvc.perform(
				post("/vendor/createCustomerDetailsFromQuickRegisterEntity").param("customerId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
											.param("firstName",VENDER_FIRSTNAME)
											   .param("lastName", VENDER_LASTNAME)
											   .param("email",VENDOR_EMAIL)
											   .param("mobile",Long.toString(VENDOR_MOBILE))
											   .param("pincode",Integer.toString(ADDRESS_PINCODE))
											   .param("customerType", Integer.toString(ENTITY_TYPE_VENDOR))
											   .param("isEmailVerified", "true")
											   .param("isMobileVerified", "true")
											   
											  
											);
		
		this.mockMvc.perform(
								post("/vendor/save").param("vendorId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
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
	
		Address address=vendorDetailsService.getCustomerDetailsById(quickRegisterSavedEntityDTO.getCustomerId()).getFirmAddress();
		
		this.mockMvc.perform(
				post("/vendor/edit").param("vendorId", Long.toString(quickRegisterSavedEntityDTO.getCustomerId()))
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
											   .param("firmAddress.addressId", Long.toString(address.getAddressId()))
											   .param("firmAddress.customerType", Integer.toString(address.getCustomerType()))
											   .param("firmAddress.addressLine", standardAddressUpdated().getAddressLine())
											   .param("firmAddress.city", standardAddressUpdated().getCity())
											   .param("firmAddress.district", standardAddressUpdated().getDistrict())
											   .param("firmAddress.state", standardAddressUpdated().getState())
											   .param("firmAddress.pincode", Integer.toString(standardAddressUpdated().getPincode()))
											   
											   
											  
															)
				.andDo(print())											
				.andExpect(view().name("completeregister/showVendorDetails"))
				.andExpect(model().size(4))
				.andExpect(model().attributeExists("vendorDetails"))
				.andExpect(model().attribute("vendorDetails",hasProperty("vendorId", is(quickRegisterSavedEntityDTO.getCustomerId()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("firstName", is(standardVendor(standardVendorCreatedFromQuickRegister()).getFirstName()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("lastName", is(standardVendor(standardVendorCreatedFromQuickRegister()).getLastName()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("dateOfBirth", is(simpleDateFormat.parse("01-01-1990")))))
				.andExpect(model().attribute("vendorDetails",hasProperty("email", is(standardVendor(standardVendorCreatedFromQuickRegister()).getEmail()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("mobile", is(standardVendor(standardVendorCreatedFromQuickRegister()).getMobile()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("isEmailVerified", is(standardVendor(standardVendorCreatedFromQuickRegister()).getIsEmailVerified()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("isMobileVerified", is(standardVendor(standardVendorCreatedFromQuickRegister()).getIsMobileVerified()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("laguage", is(standardVendor(standardVendorCreatedFromQuickRegister()).getLaguage()))))
				.andExpect(model().attribute("vendorDetails",hasProperty("firmAddress", is(standardAddressUpdated()))));
	
		
		
			
	}

	
	@Test
	public void verifyMobileDetails() throws Exception
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
		
		quickRegisterService.reSendMobilePin(new CustomerIdTypeMobileTypeDTO(VENDOR_ID, ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY));
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=
				quickRegisterService.getMobileVerificationDetailsByCustomerIdTypeAndMobile(VENDOR_ID, ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY);
		
		
		
		
		this.mockMvc.perform(
				post("/vendor/verifyMobileDetails").param("entityId", Long.toString(VENDOR_ID))
											.param("entityType",Integer.toString(ENTITY_TYPE_VENDOR))
											   //.param("mobile", Long.toString(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getMobile()))
											   .param("mobileType",Integer.toString(ENTITY_TYPE_PRIMARY))
											   .param("mobilePin",Integer.toString(mobileVerificationDetailsDTO.getMobilePin())))
			.andDo(print())
			.andExpect(content().string("true"));
			
											
		
	}

	
	@Test
	public void sendMobileVerificationDetails() throws Exception
	{
		
		this.mockMvc.perform(
				post("/vendor/save").param("vendorId", Long.toString(VENDOR_ID))
											    .param("firstName",VENDER_FIRSTNAME)
											   .param("lastName", VENDER_LASTNAME)
											   .param("dateOfBirth", "01/01/1990")
											   .param("email",VENDOR_EMAIL)
											   .param("mobile",Long.toString(VENDOR_MOBILE))
											   .param("customerType", Integer.toString(ENTITY_TYPE_VENDOR))
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
	
		this.mockMvc.perform(
				post("/vendor/sendMobileVerificationDetails").param("customerId", Long.toString(VENDOR_ID))
											.param("customerType",Integer.toString(ENTITY_TYPE_VENDOR))
											  .param("mobileType", Integer.toString(ENTITY_TYPE_PRIMARY))
											  )
			.andDo(print())
			.andExpect(content().string("true"));
			
											   
	}
	
	@Test
	public void sendEmailVerificationDetails() throws Exception
	{
		
		this.mockMvc.perform(
				post("/vendor/save").param("vendorId", Long.toString(VENDOR_ID))
											    .param("firstName",VENDER_FIRSTNAME)
											   .param("lastName", VENDER_LASTNAME)
											   .param("dateOfBirth", "01/01/1990")
											   .param("email",VENDOR_EMAIL)
											   .param("mobile",Long.toString(VENDOR_MOBILE))
											   .param("customerType", Integer.toString(ENTITY_TYPE_VENDOR))
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
	
		this.mockMvc.perform(
				post("/vendor/sendEmailVerificationDetails").param("customerId", Long.toString(VENDOR_ID))
											.param("customerType",Integer.toString(ENTITY_TYPE_VENDOR))
											  .param("emailType", Integer.toString(ENTITY_TYPE_PRIMARY))
											  )
			.andDo(print())
			.andExpect(content().string("true"));
											   
	}
	
}
