package com.projectx.mvc.controller.completeregister;

import static com.projectx.mvc.fixtures.completeregister.AddressDataFixture.*;
import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.*;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.text.SimpleDateFormat;

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
	QuickRegisterService customerQuickRegisterService;
	
	@Autowired
	SimpleDateFormat simpleDateFormat;
	
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		vendorDetailsService.clearTestData();
		customerQuickRegisterService.clearTestData();
	}

	
	@Test
	public void environmentTest() {
		
		
	}

	@Test
	public void save() throws Exception
	{
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileVendorDTO());
	

		customerQuickRegisterService.sendMobilePin(new CustomerIdTypeMobileTypeRequestedByDTO(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
				quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY, CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId()));
		
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
		
	
		assertEquals(standardVendorCreatedFromQuickRegisterWithDefaultHomeAddMobileVerified(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()),savedEntity);
	
		
		this.mockMvc.perform(
				post("/vendor/save")
				.content(standardJsonVendor(standardVendorComplete(savedEntity.getVendorId())))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.firstName").value(standardVendorComplete(savedEntity.getVendorId()).getFirstName()))
	.andExpect(jsonPath("$.lastName").value(standardVendorComplete(savedEntity.getVendorId()).getLastName()))
    .andExpect(jsonPath("$.homeAddress").exists())
    .andExpect(jsonPath("$.phoneNumber").exists())
    .andExpect(jsonPath("$.mobile").value(standardVendorComplete(savedEntity.getVendorId()).getMobile()))
    .andExpect(jsonPath("$.isMobileVerified").value(standardVendorComplete(savedEntity.getVendorId()).getIsMobileVerified()))
    .andExpect(jsonPath("$.isEmailVerified").value(standardVendorComplete(savedEntity.getVendorId()).getIsEmailVerified()))
    .andExpect(jsonPath("$.laguage").value(standardVendorComplete(savedEntity.getVendorId()).getLaguage()))
    .andExpect(jsonPath("$.firmName").value(standardVendorComplete(savedEntity.getVendorId()).getFirmName()))
    .andExpect(jsonPath("$.firmAddress").exists())
    .andExpect(jsonPath("$.secondaryMobile").value(standardVendorComplete(savedEntity.getVendorId()).getSecondaryMobile()))
    .andExpect(jsonPath("$.isSecondaryMobileVerified").value(false))
    .andExpect(jsonPath("$.dateOfBirth").exists())
    .andExpect(jsonPath("$.insertTime").exists())
	.andExpect(jsonPath("$.updateTime").exists())
	.andExpect(jsonPath("$.updatedBy").value(standardVendorComplete(savedEntity.getVendorId()).getUpdatedBy()));
	
	
	}
	
	@Test
	public void getById() throws Exception
	{
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileVendorDTO());
	

		customerQuickRegisterService.sendMobilePin(new CustomerIdTypeMobileTypeRequestedByDTO(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
				quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY, CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId()));
		
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
	
		vendorDetailsService.update(standardVendorComplete(savedEntity.getVendorId()));
		
		
		
		this.mockMvc.perform(
				post("/vendor/getById")
				.content(standardJsonEntityIdDTO(standardEntityIdDTO(savedEntity.getVendorId(),ENTITY_TYPE_VENDOR)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.firstName").value(standardVendorComplete(savedEntity.getVendorId()).getFirstName()))
	.andExpect(jsonPath("$.lastName").value(standardVendorComplete(savedEntity.getVendorId()).getLastName()))
    .andExpect(jsonPath("$.homeAddressId").exists())
    .andExpect(jsonPath("$.phoneNumber").exists())
    .andExpect(jsonPath("$.mobile").value(standardVendorComplete(savedEntity.getVendorId()).getMobile()))
    .andExpect(jsonPath("$.isMobileVerified").value(standardVendorComplete(savedEntity.getVendorId()).getIsMobileVerified()))
    .andExpect(jsonPath("$.isEmailVerified").value(standardVendorComplete(savedEntity.getVendorId()).getIsEmailVerified()))
    .andExpect(jsonPath("$.language").value(standardVendorComplete(savedEntity.getVendorId()).getLaguage()))
    .andExpect(jsonPath("$.nameOfFirm").value(standardVendorComplete(savedEntity.getVendorId()).getFirmName()))
    .andExpect(jsonPath("$.firmAddressId").exists())
    .andExpect(jsonPath("$.secondaryMobile").value(standardVendorComplete(savedEntity.getVendorId()).getSecondaryMobile()))
    .andExpect(jsonPath("$.isSecondaryMobileVerified").value(false))
    .andExpect(jsonPath("$.dateOfBirth").exists())
    .andExpect(jsonPath("$.insertTime").exists())
	.andExpect(jsonPath("$.updateTime").exists())
	.andExpect(jsonPath("$.requestedBy").value(standardVendorComplete(savedEntity.getVendorId()).getUpdatedBy()));
	
	
	}
}
