package com.projectx.mvc.controller.completeregister;

import static com.projectx.mvc.fixtures.completeregister.AddressDataFixture.*;
import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.standardCustomerDetailsCopiedFromQuickRegisterEntity;
import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.standardJsonCustomerDetails;
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
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeRequestedByDTO;
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
	public void save() throws Exception
	{
		QuickRegisterDTO quickRegisterSavedEntityDTO=
				quickRegisterService.addNewCustomer(standardEmailMobileVendorDTO()).getCustomer();
	
		vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomerId());
		
		this.mockMvc.perform(
				post("/vendor/save")
				.content(standardJsonVendor(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId())))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.firstName").value(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId()).getFirstName()))
	.andExpect(jsonPath("$.lastName").value(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId()).getLastName()))
    .andExpect(jsonPath("$.homeAddress").exists())
    .andExpect(jsonPath("$.phoneNumber").exists())
    .andExpect(jsonPath("$.mobile").value(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId()).getMobile()))
    .andExpect(jsonPath("$.isMobileVerified").value(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId()).getIsMobileVerified()))
    .andExpect(jsonPath("$.isEmailVerified").value(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId()).getIsEmailVerified()))
    .andExpect(jsonPath("$.laguage").value(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId()).getLaguage()))
    .andExpect(jsonPath("$.firmName").value(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId()).getFirmName()))
    .andExpect(jsonPath("$.firmAddress").exists())
    .andExpect(jsonPath("$.secondaryMobile").value(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId()).getSecondaryMobile()))
    .andExpect(jsonPath("$.isSecondaryMobileVerified").value(false))
    .andExpect(jsonPath("$.dateOfBirth").exists())
    .andExpect(jsonPath("$.insertTime").exists())
	.andExpect(jsonPath("$.updateTime").exists())
	.andExpect(jsonPath("$.updatedBy").value(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId()).getUpdatedBy()));
	
	
	}
	
	public void getById() throws Exception
	{
		QuickRegisterDTO quickRegisterSavedEntityDTO=
				quickRegisterService.addNewCustomer(standardEmailMobileVendorDTO()).getCustomer();
	
		vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomerId());
		
		this.mockMvc.perform(
				post("/vendor/save")
				.content(standardJsonVendor(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId())))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		
		this.mockMvc.perform(
				post("/vendor/getById")
				.content(standardJsonEntityIdDTO(standardEntityIdDTO(quickRegisterSavedEntityDTO.getCustomerId(),ENTITY_TYPE_VENDOR)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.firstName").value(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId()).getFirstName()))
	.andExpect(jsonPath("$.lastName").value(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId()).getLastName()))
    .andExpect(jsonPath("$.homeAddress").exists())
    .andExpect(jsonPath("$.phoneNumber").exists())
    .andExpect(jsonPath("$.mobile").value(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId()).getMobile()))
    .andExpect(jsonPath("$.isMobileVerified").value(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId()).getIsMobileVerified()))
    .andExpect(jsonPath("$.isEmailVerified").value(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId()).getIsEmailVerified()))
    .andExpect(jsonPath("$.laguage").value(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId()).getLaguage()))
    .andExpect(jsonPath("$.firmName").value(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId()).getFirmName()))
    .andExpect(jsonPath("$.firmAddress").exists())
    .andExpect(jsonPath("$.secondaryMobile").value(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId()).getSecondaryMobile()))
    .andExpect(jsonPath("$.isSecondaryMobileVerified").value(false))
    .andExpect(jsonPath("$.dateOfBirth").exists())
    .andExpect(jsonPath("$.insertTime").exists())
	.andExpect(jsonPath("$.updateTime").exists())
	.andExpect(jsonPath("$.updatedBy").value(standardVendorComplete(quickRegisterSavedEntityDTO.getCustomerId()).getUpdatedBy()));
	
	
	}
}
