package com.projectx.mvc.controller.completeregister;



import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.*;
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
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeRequestedByDTO;
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
	public void save() throws Exception
	{
		QuickRegisterDTO quickRegisterSavedEntityDTO=
				quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
	
		customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomerId());
		
		
		this.mockMvc.perform(
				post("/customer/save")
				.content("{\"customerId\":"+quickRegisterSavedEntityDTO.getCustomerId()+",\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"dateOfBirth\":"+new Date().getTime()+",\"homeAddressId\":{\"customerType\":1,\"addressLine\":\"AT-GHADGE\",\"city\":\"Baramati\",\"district\":\"Pune\",\"state\":\"Maharashtra\",\"pincode\":413133,\"updatedBy\":1,\"updatedById\":1},\"mobile\":9960821869,\"isMobileVerified\":false,\"email\":\"dineshshe@gmail.com\",\"isEmailVerified\":false,\"language\":\"Marathi\",\"businessDomain\":\"TRANSPORT\",\"nameOfFirm\":\"ABC TRANSPORT\",\"firmAddressId\":{\"customerType\":1,\"addressLine\":\"AT-GHADGE\",\"city\":\"Baramati\",\"district\":\"Pune\",\"state\":\"Maharashtra\",\"pincode\":413133,\"updatedBy\":1,\"updatedById\":1},\"secondaryMobile\":8598058044,\"isSecondaryMobileVerified\":false,\"secondaryEmail\":\"dineshshende@gmail.com\",\"requestedBy\":1,\"requestedById\":1,\"entityType\":1}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.status").value("sucess"))
    .andExpect(jsonPath("$.errorMessage").value(""));
		
		
	}
	
	@Test
	public void getById() throws Exception
	{
		QuickRegisterDTO quickRegisterSavedEntityDTO=
				quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO()).getCustomer();
	
		customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomerId());
		
		this.mockMvc.perform(
				post("/customer/save")
				.content("{\"customerId\":"+quickRegisterSavedEntityDTO.getCustomerId()+",\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"dateOfBirth\":"+new Date().getTime()+",\"homeAddressId\":{\"customerType\":1,\"addressLine\":\"AT-GHADGE\",\"city\":\"Baramati\",\"district\":\"Pune\",\"state\":\"Maharashtra\",\"pincode\":413133,\"updatedBy\":1,\"updatedById\":1},\"mobile\":9960821869,\"isMobileVerified\":false,\"email\":\"dineshshe@gmail.com\",\"isEmailVerified\":false,\"language\":\"Marathi\",\"businessDomain\":\"TRANSPORT\",\"nameOfFirm\":\"ABC TRANSPORT\",\"firmAddressId\":{\"customerType\":1,\"addressLine\":\"AT-GHADGE\",\"city\":\"Baramati\",\"district\":\"Pune\",\"state\":\"Maharashtra\",\"pincode\":413133,\"updatedBy\":1,\"updatedById\":1},\"secondaryMobile\":8598058044,\"isSecondaryMobileVerified\":false,\"secondaryEmail\":\"dineshshende@gmail.com\",\"requestedBy\":1,\"requestedById\":1,\"entityType\":1}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
				post("/customer/getById")
				.content(standardJsonEntityIdDTO(standardEntityIdDTO(quickRegisterSavedEntityDTO.getCustomerId(),ENTITY_TYPE_CUSTOMER)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.firstName").value(standardCustomerDetails(quickRegisterSavedEntityDTO.getCustomerId()).getFirstName()))
			    .andExpect(jsonPath("$.lastName").value(standardCustomerDetails(quickRegisterSavedEntityDTO.getCustomerId()).getLastName()))
			    .andExpect(jsonPath("$.homeAddressId").exists())
			    .andExpect(jsonPath("$.mobile").value(standardCustomerDetails(quickRegisterSavedEntityDTO.getCustomerId()).getMobile()))
			    .andExpect(jsonPath("$.email").value(standardCustomerDetails(quickRegisterSavedEntityDTO.getCustomerId()).getEmail()))
			    .andExpect(jsonPath("$.isMobileVerified").value(standardCustomerDetails(quickRegisterSavedEntityDTO.getCustomerId()).getIsMobileVerified()))
			    .andExpect(jsonPath("$.isEmailVerified").value(standardCustomerDetails(quickRegisterSavedEntityDTO.getCustomerId()).getIsEmailVerified()))
			    .andExpect(jsonPath("$.language").value(standardCustomerDetails(quickRegisterSavedEntityDTO.getCustomerId()).getLanguage()))
			    .andExpect(jsonPath("$.businessDomain").value(standardCustomerDetails(quickRegisterSavedEntityDTO.getCustomerId()).getBusinessDomain()))
			    .andExpect(jsonPath("$.nameOfFirm").value(standardCustomerDetails(quickRegisterSavedEntityDTO.getCustomerId()).getNameOfFirm()))
			    .andExpect(jsonPath("$.firmAddressId").exists())
			    .andExpect(jsonPath("$.secondaryMobile").doesNotExist())
			    .andExpect(jsonPath("$.isSecondaryMobileVerified").value(false))
			    .andExpect(jsonPath("$.secondaryEmail").value(standardCustomerDetails(quickRegisterSavedEntityDTO.getCustomerId()).getSecondaryEmail()))
			    .andExpect(jsonPath("$.dateOfBirth").exists())
			    .andExpect(jsonPath("$.insertTime").exists())
				.andExpect(jsonPath("$.updateTime").exists())
				.andExpect(jsonPath("$.requestedBy").value(standardCustomerDetails(quickRegisterSavedEntityDTO.getCustomerId()).getUpdatedBy()));
				
	
	}
	
}
