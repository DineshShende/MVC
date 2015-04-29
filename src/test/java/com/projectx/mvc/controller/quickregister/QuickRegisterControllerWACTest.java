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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.*;

import javax.servlet.http.HttpSession;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.projectx.mvc.config.Application;
import com.projectx.mvc.domain.quickregister.LoginDetailsDTO;
import com.projectx.mvc.domain.quickregister.QuickRegisterMVCDTO;
import com.projectx.mvc.domain.quickregister.ResetPasswordRedirectDTO;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.completeregister.DocumentDetailsService;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.ang.CustomerIdTypeEmailOrMobileOptionUpdatedByAng;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailOrMobileOptionUpdatedBy;
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
	public void AddNewCustomer() throws Exception
	{
		
		this.mockMvc.perform(
				post("/quickregister")
					.content(standardJsonQuickRegisterEntity(standardEmailMobileCustomerDTO()))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.status").value(REGISTER_REGISTERED_SUCESSFULLY))
				.andExpect(jsonPath("$.customer.firstName").value(standardEmailMobileCustomerDTO().getFirstName()))		
				.andExpect(jsonPath("$.customer.lastName").value(standardEmailMobileCustomerDTO().getLastName()))
				.andExpect(jsonPath("$.customer.email").value(standardEmailMobileCustomerDTO().getEmail()))
				.andExpect(jsonPath("$.customer.mobile").value(standardEmailMobileCustomerDTO().getMobile()))
				.andExpect(jsonPath("$.customer.pincode").value(standardEmailMobileCustomerDTO().getPincode()))
				.andExpect(jsonPath("$.customer.customerType").value(standardEmailMobileCustomerDTO().getCustomerType()))
				.andExpect(jsonPath("$.customer.isEmailVerified").value(false))
				.andExpect(jsonPath("$.customer.isMobileVerified").value(false))
				.andExpect(jsonPath("$.customer.updatedBy").value(standardEmailMobileCustomerDTO().getRequestBy()))
				.andExpect(jsonPath("$.customer.insertTime").exists())
				.andExpect(jsonPath("$.customer.updateTime").exists());
		
	}
	
	@Test
	public void verifyMobile() throws Exception
	{
		
		QuickRegisterSavedEntityDTO  entityDTO=quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=quickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(entityDTO.getCustomer().getCustomerId(), entityDTO.getCustomer().getCustomerType(),
						ENTITY_TYPE_PRIMARY);
		
		this.mockMvc.perform(
				post("/quickregister/verifyMobilePin")
					.content(standardJsonVerifyMobileDTO(new VerifyMobileDTO(entityDTO.getCustomer().getCustomerId(),
							entityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY, mobileVerificationDetailsDTO.getMobilePin(),
							CUST_UPDATED_BY,entityDTO.getCustomer().getCustomerId())))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("true"));
		
		
	}
	
	@Test
	public void verifyEmail() throws Exception
	{
		
		QuickRegisterSavedEntityDTO  entityDTO=quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		EmailVerificationDetailsDTO mobileVerificationDetailsDTO=quickRegisterService
				.getEmailVerificationDetailsByCustomerIdTypeAndEmail(entityDTO.getCustomer().getCustomerId(), entityDTO.getCustomer().getCustomerType(),
						ENTITY_TYPE_PRIMARY);
		
		this.mockMvc.perform(
				get("/quickregister/verifyEmailHash/"+entityDTO.getCustomer().getCustomerId()+"/"+entityDTO.getCustomer().getCustomerType()+
						"/"+ENTITY_TYPE_PRIMARY+"/"+CUST_UPDATED_BY+"/"+entityDTO.getCustomer().getCustomerId()+"/"+mobileVerificationDetailsDTO.getEmailHash())
					)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("quickregister/loginForm"));
		
		
	}
	
	@Test
	public void sendMobilePin() throws Exception
	{
		
		QuickRegisterSavedEntityDTO  entityDTO=quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		
		this.mockMvc.perform(
				post("/quickregister/sendMobilePin")
					.content(standardJsonCustomerIdTypeMobileTypeUpdatedByDTO(new CustomerIdTypeMobileTypeRequestedByDTO
							(entityDTO.getCustomer().getCustomerId(),entityDTO.getCustomer().getCustomerType(),
									ENTITY_TYPE_PRIMARY, CUST_UPDATED_BY,entityDTO.getCustomer().getCustomerId())))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("true"));
		
		
	}
	
	@Test
	public void reSendMobilePin() throws Exception
	{
		
		QuickRegisterSavedEntityDTO  entityDTO=quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		
		this.mockMvc.perform(
				post("/quickregister/resendMobilePin")
					.content(standardJsonCustomerIdTypeMobileTypeUpdatedByDTO(new CustomerIdTypeMobileTypeRequestedByDTO
							(entityDTO.getCustomer().getCustomerId(),entityDTO.getCustomer().getCustomerType(),
									ENTITY_TYPE_PRIMARY, CUST_UPDATED_BY,entityDTO.getCustomer().getCustomerId())))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("true"));
		
		
	}
	
	@Test
	public void sendEmailHash() throws Exception
	{
		
		QuickRegisterSavedEntityDTO  entityDTO=quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		
		this.mockMvc.perform(
				post("/quickregister/sendEmailHash")
					.content(standardJsonCustomerIdTypeEmailTypeUpdatedByDTO(new CustomerIdTypeEmailTypeUpdatedByDTO
							(entityDTO.getCustomer().getCustomerId(),entityDTO.getCustomer().getCustomerType(), 
									ENTITY_TYPE_PRIMARY, CUST_UPDATED_BY,entityDTO.getCustomer().getCustomerId())))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("true"));
		
		
	}
	
	@Test
	public void resendEmailHash() throws Exception
	{
		
		QuickRegisterSavedEntityDTO  entityDTO=quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		
		this.mockMvc.perform(
				post("/quickregister/resendEmailHash")
					.content(standardJsonCustomerIdTypeEmailTypeUpdatedByDTO(new CustomerIdTypeEmailTypeUpdatedByDTO
							(entityDTO.getCustomer().getCustomerId(),entityDTO.getCustomer().getCustomerType(), 
									ENTITY_TYPE_PRIMARY, CUST_UPDATED_BY,entityDTO.getCustomer().getCustomerId())))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("true"));
		
		
	}
	
	@Test
	public void verifyLoginDetails() throws Exception
	{
		
		QuickRegisterSavedEntityDTO  entityDTO=quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=quickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(entityDTO.getCustomer().getCustomerId(), entityDTO.getCustomer().getCustomerType(),
						ENTITY_TYPE_PRIMARY);

		
		quickRegisterService.verifyMobile(new VerifyMobileDTO(entityDTO.getCustomer().getCustomerId(), entityDTO.getCustomer().getCustomerType(),
				ENTITY_TYPE_PRIMARY, mobileVerificationDetailsDTO.getMobilePin(),CUST_UPDATED_BY,entityDTO.getCustomer().getCustomerId()));
		
		
		AuthenticationDetails authenticationDetails=quickRegisterService
				.getAuthenticationDetailsByCustomerIdType(entityDTO.getCustomer().getCustomerId(), entityDTO.getCustomer().getCustomerType());
		
		
		
		this.mockMvc.perform(
				post("/quickregister/verifyLoginDetails")
					.content(standardJsonLoginDetailsDTO(new LoginDetailsDTO(entityDTO.getCustomer().getEmail(), authenticationDetails.getPassword())))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.key.customerId").exists())
				.andExpect(jsonPath("$.key.customerType").exists())
				.andExpect(jsonPath("$.passwordType").value("Default"))
				.andExpect(jsonPath("$.isCompleteRegisterCompleted").value(false));
				
				
				//.andExpect(content().string("true"));
		
	}
	
	
	@Test
	public void verifyLoginDetailsWithEmailPassword() throws Exception
	{
		
		QuickRegisterSavedEntityDTO  entityDTO=quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=quickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(entityDTO.getCustomer().getCustomerId(), entityDTO.getCustomer().getCustomerType(),
						ENTITY_TYPE_PRIMARY);

		
		quickRegisterService.verifyMobile(new VerifyMobileDTO(entityDTO.getCustomer().getCustomerId(), entityDTO.getCustomer().getCustomerType(),
				ENTITY_TYPE_PRIMARY, mobileVerificationDetailsDTO.getMobilePin(),CUST_UPDATED_BY,entityDTO.getCustomer().getCustomerId()));
		
		
		AuthenticationDetails authenticationDetails=quickRegisterService
				.getAuthenticationDetailsByCustomerIdType(entityDTO.getCustomer().getCustomerId(), entityDTO.getCustomer().getCustomerType());

		///emailPasswordVerification/{customerId}/{customerType}/{emailPassword}
		
		this.mockMvc.perform(
				get("/quickregister/emailPasswordVerification/"+entityDTO.getCustomer().getCustomerId()+"/"+entityDTO.getCustomer().getCustomerType()
						+"/"+authenticationDetails.getEmailPassword())
					)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("sucess"));
		
		
	}
	
	
	@Test
	public void updatePassword() throws Exception
	{
		
		QuickRegisterSavedEntityDTO  entityDTO=quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=quickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(entityDTO.getCustomer().getCustomerId(), entityDTO.getCustomer().getCustomerType(),
						ENTITY_TYPE_PRIMARY);

		
		quickRegisterService.verifyMobile(new VerifyMobileDTO(entityDTO.getCustomer().getCustomerId(), entityDTO.getCustomer().getCustomerType(),
				ENTITY_TYPE_PRIMARY, mobileVerificationDetailsDTO.getMobilePin(),CUST_UPDATED_BY,entityDTO.getCustomer().getCustomerId()));
		
		AuthenticationDetails authenticationDetails=quickRegisterService
				.getAuthenticationDetailsByCustomerIdType(entityDTO.getCustomer().getCustomerId(), entityDTO.getCustomer().getCustomerType());
		
		
		this.mockMvc.perform(
				post("/quickregister/updatePassword")
					.content(standardJsonUpdatePasswordDTO(new UpdatePasswordDTO(
							new AuthenticationDetailsKey(entityDTO.getCustomer().getCustomerId(), entityDTO.getCustomer().getCustomerType()),
							authenticationDetails.getPassword(),"abc", true, CUST_UPDATED_BY,entityDTO.getCustomer().getCustomerId())))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("true"));
		
		
		
	}
	

	@Test
	public void resetPassword() throws Exception
	{
		
		QuickRegisterSavedEntityDTO  entityDTO=quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=quickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(entityDTO.getCustomer().getCustomerId(), entityDTO.getCustomer().getCustomerType(),
						ENTITY_TYPE_PRIMARY);

		
		quickRegisterService.verifyMobile(new VerifyMobileDTO(entityDTO.getCustomer().getCustomerId(), entityDTO.getCustomer().getCustomerType(),
				ENTITY_TYPE_PRIMARY, mobileVerificationDetailsDTO.getMobilePin(),CUST_UPDATED_BY,entityDTO.getCustomer().getCustomerId()));
		
		
		this.mockMvc.perform(
				post("/quickregister/resetPassword")
					.content(standardJsonCustomerIdTypeEmailOrMobileOptionUpdatedByAng(new CustomerIdTypeEmailOrMobileOptionUpdatedByAng(
							entityDTO.getCustomer().getCustomerId(), entityDTO.getCustomer().getCustomerType(), 1, CUST_UPDATED_BY,
							entityDTO.getCustomer().getCustomerId())))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("true"));
		
		
		
	}

	@Test
	public void resetPasswordRedirect() throws Exception
	{
		
		QuickRegisterSavedEntityDTO  entityDTO=quickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=quickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(entityDTO.getCustomer().getCustomerId(), entityDTO.getCustomer().getCustomerType(),
						ENTITY_TYPE_PRIMARY);

		
		quickRegisterService.verifyMobile(new VerifyMobileDTO(entityDTO.getCustomer().getCustomerId(), entityDTO.getCustomer().getCustomerType(),
				ENTITY_TYPE_PRIMARY, mobileVerificationDetailsDTO.getMobilePin(),CUST_UPDATED_BY,entityDTO.getCustomer().getCustomerId()));
		
		
		this.mockMvc.perform(
				post("/quickregister/resetPasswordRedirect")
					.content(standardJsonResetPasswordRedirectDTO(new ResetPasswordRedirectDTO(entityDTO.getCustomer().getEmail(),
							CUST_UPDATED_BY,entityDTO.getCustomer().getCustomerId())))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.email").value(standardEmailMobileCustomerDTO().getEmail()))
				.andExpect(jsonPath("$.mobile").value(standardEmailMobileCustomerDTO().getMobile()))
				.andExpect(jsonPath("$.isEmailVerified").value(false))
				.andExpect(jsonPath("$.isMobileVerified").value(true));

		
		
		
	}

	
	
}
