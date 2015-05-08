package com.projectx.mvc.controller.completeregister;



import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.mvc.fixtures.completeregister.VendorDetailsDataFixture.*;
import static com.projectx.mvc.fixtures.completeregister.AddressDataFixture.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.projectx.mvc.config.Application;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.VerifyMobileDTO;

import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CustomerVendorDetailsWACTest {

	@Autowired
	private WebApplicationContext wac;

	MockMvc mockMvc;
	
	@Autowired
	CustomerDetailsService customerDetailsService;

	@Autowired
	VendorDetailsService vendorDetailsService;

	@Autowired
	QuickRegisterService customerQuickRegisterService;
	
	@Autowired
	SimpleDateFormat simpleDateFormat;
	
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		customerDetailsService.clearTestData();
		customerQuickRegisterService.clearTestData();
		vendorDetailsService.clearTestData();
	}

	
	@Test
	public void environmentTest() {
		
		
	}

	
	@Test
	public void datesubmit() throws Exception
	{
		System.out.println("{\"name\":\"abc\",\"date\":"+new Date().getTime()+"}");
		
		this.mockMvc.perform(
				post("/customer/datesubmit")
				.content("{\"name\":\"abc\",\"date\":"+new Date().getTime()+"}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk());	
		
	}
	
	@Test
	public void saveCustomerEntity() throws Exception
	{
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
	
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
		
		
		
		
		this.mockMvc.perform(
				post("/entity/save")
				.content("{\"customerId\":"+savedEntity.getCustomerId()+",\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"dateOfBirth\":"+new Date().getTime()+",\"homeAddressId\":{\"customerType\":1,\"addressLine\":\"AT-GHADGE\",\"city\":\"Baramati\",\"district\":\"Pune\",\"state\":\"Maharashtra\",\"pincode\":413133,\"updatedBy\":1,\"updatedById\":1},\"mobile\":9960821869,\"isMobileVerified\":false,\"email\":\"dineshshe@gmail.com\",\"isEmailVerified\":false,\"language\":\"Marathi\",\"businessDomain\":\"TRANSPORT\",\"nameOfFirm\":\"ABC TRANSPORT\",\"firmAddressId\":{\"customerType\":1,\"addressLine\":\"AT-GHADGE\",\"city\":\"Baramati\",\"district\":\"Pune\",\"state\":\"Maharashtra\",\"pincode\":413133,\"updatedBy\":1,\"updatedById\":1},\"secondaryMobile\":8598058044,\"isSecondaryMobileVerified\":false,\"secondaryEmail\":\"dineshshende@gmail.com\",\"requestedBy\":1,\"requestedById\":1,\"entityType\":1}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.result").value("sucess"))
    .andExpect(jsonPath("$.errorMessage").value(""));
		
		
	}
	
	@Test
	public void saveVendor() throws Exception
	{
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardCustomerQuickRegisterEntityVendor());
	
		
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
		
		VendorDetailsDTO savedEntity=vendorDetailsService
				.getCustomerDetailsById(quickRegisterSavedEntityDTO.getCustomer().getCustomerId());
		
		
		
		
		this.mockMvc.perform(
				post("/entity/save")
				.content("{\"customerId\":"+savedEntity.getVendorId()+",\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"dateOfBirth\":"+new Date().getTime()+",\"homeAddressId\":{\"customerType\":1,\"addressLine\":\"AT-GHADGE\",\"city\":\"Baramati\",\"district\":\"Pune\",\"state\":\"Maharashtra\",\"pincode\":413133,\"updatedBy\":1,\"updatedById\":1},\"mobile\":9960821869,\"isMobileVerified\":false,\"email\":\"dineshshe@gmail.com\",\"isEmailVerified\":false,\"language\":\"Marathi\",\"businessDomain\":\"TRANSPORT\",\"nameOfFirm\":\"ABC TRANSPORT\",\"firmAddressId\":{\"customerType\":1,\"addressLine\":\"AT-GHADGE\",\"city\":\"Baramati\",\"district\":\"Pune\",\"state\":\"Maharashtra\",\"pincode\":413133,\"updatedBy\":1,\"updatedById\":1},\"secondaryMobile\":8598058044,\"isSecondaryMobileVerified\":false,\"secondaryEmail\":\"dineshshende@gmail.com\",\"requestedBy\":1,\"requestedById\":1,\"entityType\":2}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.result").value("sucess"))
    .andExpect(jsonPath("$.errorMessage").value(""));
		
		
	}
	
	@Test
	public void getByIdVendor() throws Exception
	{
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileVendorDTO());
	
				
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
		
		VendorDetailsDTO savedEntity=vendorDetailsService
				.getCustomerDetailsById(quickRegisterSavedEntityDTO.getCustomer().getCustomerId());
		
		
		
		
		this.mockMvc.perform(
				post("/entity/save")
				.content("{\"customerId\":"+savedEntity.getVendorId()+",\"firstName\":\""+savedEntity.getFirstName()+"\",\"lastName\":\""+savedEntity.getLastName()+"\",\"dateOfBirth\":"+new Date().getTime()+",\"homeAddressId\":{\"customerType\":2,\"addressLine\":\"AT-GHADGE\",\"city\":\"Baramati\",\"district\":\"Pune\",\"state\":\"Maharashtra\",\"pincode\":413133,\"updatedBy\":1,\"updatedById\":1},\"mobile\":"+savedEntity.getMobile()+",\"isMobileVerified\":false,\"email\":\""+savedEntity.getEmail()+"\",\"isEmailVerified\":false,\"language\":\"English\",\"businessDomain\":\"TRANSPORT\",\"nameOfFirm\":\"ABC TRANSPORT\",\"firmAddressId\":{\"customerType\":2,\"addressLine\":\"AT-GHADGE\",\"city\":\"Baramati\",\"district\":\"Pune\",\"state\":\"Maharashtra\",\"pincode\":413133,\"updatedBy\":1,\"updatedById\":1},\"secondaryMobile\":"+savedEntity.getSecondaryMobile()+",\"isSecondaryMobileVerified\":false,\"secondaryEmail\":\"dineshshende@gmail.com\",\"requestedBy\":1,\"requestedById\":1,\"entityType\":2}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	//.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.result").value("sucess"))
    .andExpect(jsonPath("$.errorMessage").value(""));
		
		
		this.mockMvc.perform(
				post("/entity/getById")
					.content(standardJsonEntityIdDTO(standardEntityIdDTO(savedEntity.getVendorId(),ENTITY_TYPE_VENDOR)))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result.firstName").value(standardVendor(savedEntity.getVendorId()).getFirstName()))
			   .andExpect(jsonPath("$.result.lastName").value(standardVendor(savedEntity.getVendorId()).getLastName()))
			    .andExpect(jsonPath("$.result.homeAddressId").exists())
			    .andExpect(jsonPath("$.result.mobile").value(standardVendor(savedEntity.getVendorId()).getMobile()))
			    .andExpect(jsonPath("$.result.email").value(standardVendor(savedEntity.getVendorId()).getEmail()))
			    .andExpect(jsonPath("$.result.isMobileVerified").value(standardVendor(savedEntity.getVendorId()).getIsMobileVerified()))
			    .andExpect(jsonPath("$.result.isEmailVerified").value(standardVendor(savedEntity.getVendorId()).getIsEmailVerified()))
			    .andExpect(jsonPath("$.result.language").value(standardVendor(savedEntity.getVendorId()).getLaguage()))
			    //.andExpect(jsonPath("$.businessDomain").value(standardVendor(savedEntity.getVendorId()).getBusinessDomain()))
			    //.andExpect(jsonPath("$.nameOfFirm").value(standardVendor(savedEntity.getVendorId()).getFirmName()))
			    .andExpect(jsonPath("$.result.firmAddressId").exists())
			    .andExpect(jsonPath("$.result.secondaryMobile").doesNotExist())
			    .andExpect(jsonPath("$.result.isSecondaryMobileVerified").value(false))
			    //.andExpect(jsonPath("$.result.secondaryEmail").value(standardVendor(savedEntity.getVendorId()).getSecondaryEmail()))
			    .andExpect(jsonPath("$.result.dateOfBirth").exists())
			    .andExpect(jsonPath("$.result.insertTime").exists())
				.andExpect(jsonPath("$.result.updateTime").exists());
				//.andExpect(jsonPath("$.requestedBy").value(standardVendor(savedEntity.getVendorId())).getUpdatedBy()));
			
		
	}
	
	
	@Test
	public void getById() throws Exception
	{
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
	
		
		
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
		
		
		
		this.mockMvc.perform(
				post("/entity/save")
				.content("{\"customerId\":"+savedEntity.getCustomerId()+",\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"dateOfBirth\":"+new Date().getTime()+",\"homeAddressId\":{\"customerType\":1,\"addressLine\":\"AT-GHADGE\",\"city\":\"Baramati\",\"district\":\"Pune\",\"state\":\"Maharashtra\",\"pincode\":413133,\"updatedBy\":1,\"updatedById\":1},\"mobile\":9960821869,\"isMobileVerified\":false,\"email\":\"dineshshe@gmail.com\",\"isEmailVerified\":false,\"language\":\"Marathi\",\"businessDomain\":\"TRANSPORT\",\"nameOfFirm\":\"ABC TRANSPORT\",\"firmAddressId\":{\"customerType\":1,\"addressLine\":\"AT-GHADGE\",\"city\":\"Baramati\",\"district\":\"Pune\",\"state\":\"Maharashtra\",\"pincode\":413133,\"updatedBy\":1,\"updatedById\":1},\"secondaryMobile\":8598058044,\"isSecondaryMobileVerified\":false,\"secondaryEmail\":\"dineshshende@gmail.com\",\"requestedBy\":1,\"requestedById\":1,\"entityType\":1}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
				post("/entity/getById")
				.content(standardJsonEntityIdDTO(standardEntityIdDTO(savedEntity.getCustomerId(),ENTITY_TYPE_CUSTOMER)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("$.result.firstName").value(standardCustomerDetails(savedEntity.getCustomerId()).getFirstName()))
			    .andExpect(jsonPath("$.result.lastName").value(standardCustomerDetails(savedEntity.getCustomerId()).getLastName()))
			    .andExpect(jsonPath("$.result.homeAddressId").exists())
			    .andExpect(jsonPath("$.result.mobile").value(standardCustomerDetails(savedEntity.getCustomerId()).getMobile()))
			    .andExpect(jsonPath("$.result.email").value(standardCustomerDetails(savedEntity.getCustomerId()).getEmail()))
			    .andExpect(jsonPath("$.result.isMobileVerified").value(standardCustomerDetails(savedEntity.getCustomerId()).getIsMobileVerified()))
			    .andExpect(jsonPath("$.result.isEmailVerified").value(standardCustomerDetails(savedEntity.getCustomerId()).getIsEmailVerified()))
			    .andExpect(jsonPath("$.result.language").value(standardCustomerDetails(savedEntity.getCustomerId()).getLanguage()))
			    .andExpect(jsonPath("$.result.businessDomain").value(standardCustomerDetails(savedEntity.getCustomerId()).getBusinessDomain()))
			    .andExpect(jsonPath("$.result.nameOfFirm").value(standardCustomerDetails(savedEntity.getCustomerId()).getNameOfFirm()))
			    .andExpect(jsonPath("$.result.firmAddressId").exists())
			    .andExpect(jsonPath("$.result.secondaryMobile").doesNotExist())
			    .andExpect(jsonPath("$.result.isSecondaryMobileVerified").value(false))
			    .andExpect(jsonPath("$.result.secondaryEmail").value(standardCustomerDetails(savedEntity.getCustomerId()).getSecondaryEmail()))
			    .andExpect(jsonPath("$.result.dateOfBirth").exists())
			    .andExpect(jsonPath("$.result.insertTime").exists())
				.andExpect(jsonPath("$.result.updateTime").exists())
				.andExpect(jsonPath("$.result.requestedBy").value(standardCustomerDetails(savedEntity.getCustomerId()).getUpdatedBy()));
				
	
	}
	
}
