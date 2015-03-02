package com.projectx.mvc.servicehandler.completeregister;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import com.projectx.mvc.exception.repository.completeregister.CustomerDetailsNotFoundException;
import com.projectx.mvc.exception.repository.quickregister.DeleteQuickCreateDetailsEntityFailedException;
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.completeregister.VerifyEmailDTO;
import com.projectx.rest.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;



@Component
@Profile(value="Dev")
@PropertySource(value="classpath:/application.properties")
public class CustomerDetailsHandler implements CustomerDetailsService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	private Integer ENTITY_TYPE_CUSTOMER=1;
	
	private Integer ENTITY_TYPE_PRIMARY=1;
	private Integer ENTITY_TYPE_SECONDARY=2;
	
	@Override
	public CustomerDetailsDTO createCustomerDetailsFromQuickRegisterEntity(
			QuickRegisterDTO quickRegisterEntity) {
		
		HttpEntity<QuickRegisterDTO> entity=new HttpEntity<QuickRegisterDTO>(quickRegisterEntity);
		
		ResponseEntity<CustomerDetailsDTO> result=
				restTemplate.exchange(env.getProperty("rest.host")+"/customer/createFromQuickRegister", HttpMethod.POST, 
						entity, CustomerDetailsDTO.class);
				
		if(result.getStatusCode()==HttpStatus.OK)
				return result.getBody();
		else
			throw new DeleteQuickCreateDetailsEntityFailedException();
		
		
	}

	@Override
	public CustomerDetailsDTO merge(CustomerDetailsDTO customerDetails) {
		
		HttpEntity<CustomerDetailsDTO> entity=new HttpEntity<CustomerDetailsDTO>(customerDetails);
		
		ResponseEntity<CustomerDetailsDTO> result=restTemplate.exchange(env.getProperty("rest.host")+"/customer",
				HttpMethod.POST, entity, CustomerDetailsDTO.class);
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		else
			throw new CustomerDetailsNotFoundException();
		
		
	}



	@Override
	public CustomerDetailsDTO InitializeMetaData(
			CustomerDetailsDTO customerDetails) {
		
		if(customerDetails.getUpdatedBy()==null)
			customerDetails.setUpdatedBy("CUST_ONLINE");
		
		customerDetails.setUpdateTime(new Date());
			
		if(customerDetails.getInsertTime()==null)
			customerDetails.setInsertTime(new Date());
		
		if(customerDetails.getHomeAddressId()!=null)
		{
			if(customerDetails.getHomeAddressId().getUpdatedBy()==null)
				customerDetails.getHomeAddressId().setUpdatedBy("CUST_ONLINE");
			
			customerDetails.getHomeAddressId().setUpdateTime(new Date());
			
			if(customerDetails.getHomeAddressId().getInsertTime()==null)
				customerDetails.getHomeAddressId().setInsertTime(new Date());
			
		}
		
		if(customerDetails.getFirmAddressId()!=null)
		{
			if(customerDetails.getFirmAddressId().getUpdatedBy()==null)
				customerDetails.getFirmAddressId().setUpdatedBy("CUST_ONLINE");
			
			customerDetails.getFirmAddressId().setUpdateTime(new Date());
			
			if(customerDetails.getFirmAddressId().getInsertTime()==null)
				customerDetails.getFirmAddressId().setInsertTime(new Date());
			
			
		}
		
		return customerDetails;
	}

	@Override
	public Model initialiseShowCustomerDetails(Long customerId,Model model)
	{
		EmailVerificationDetailsDTO emailVerificationDetails=quickRegisterService
				.getEmailVerificationDetailsByCustomerIdTypeAndEmail(customerId,ENTITY_TYPE_CUSTOMER , ENTITY_TYPE_PRIMARY);
		
		MobileVerificationDetailsDTO mobileVerificationDetailsPrimary=quickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(customerId, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);
		
		MobileVerificationDetailsDTO mobileVerificationDetailsSeconadry=quickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(customerId, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY);
		
		model.addAttribute("emailVerificationDetails", emailVerificationDetails);
		model.addAttribute("mobileVerificationDetailsPrimary", mobileVerificationDetailsPrimary);
		model.addAttribute("mobileVerificationDetailsSeconadry", mobileVerificationDetailsSeconadry);
		
		return model;
	}
	
	@Override
	public Boolean verifyMobileDetails(VerifyMobileDTO verifyMobileDTO) {
		
		Boolean status=restTemplate
				.postForObject(env.getProperty("rest.host")+"/customer/verifyMobileDetails", verifyMobileDTO, Boolean.class);
		
		return status;
		
	}

	@Override
	public Boolean verifyEmailDetails(VerifyEmailDTO verifyEmailDTO) {
		
		Boolean status=restTemplate
				.postForObject(env.getProperty("rest.host")+"/customer/verifyEmailDetails", verifyEmailDTO, Boolean.class);
		
		return status;
		
	}

	@Override
	public Boolean sendMobileVerificationDetails(
			CustomerIdTypeMobileTypeDTO customerIdTypeMobileDTO) {
		
		Boolean status=restTemplate
				.postForObject(env.getProperty("rest.host")+"/customer/sendMobileVerificationDetails", customerIdTypeMobileDTO, Boolean.class);
		
		return status;
	}

	@Override
	public Boolean sendEmailVerificationDetails(
			CustomerIdTypeEmailTypeDTO customerIdTypeEmailDTO) {

		Boolean status=restTemplate
				.postForObject(env.getProperty("rest.host")+"/customer/sendEmailVerificationDetails", customerIdTypeEmailDTO, Boolean.class);
		
		return status;
		
	}

	@Override
	public Boolean clearTestData() {
		
		Boolean result=restTemplate
				.getForObject(env.getProperty("rest.host")+"/customer/clearTestData", Boolean.class);
		
		return result;
		
		
	}

	@Override
	public Integer count() {

		Integer count=restTemplate
				.getForObject(env.getProperty("rest.host")+"/customer/count", Integer.class);
		
		return count;
		
	}

	@Override
	public CustomerDetailsDTO getCustomerDetailsById(Long customerId) {

		ResponseEntity<CustomerDetailsDTO> responseEntity=restTemplate.exchange(env.getProperty("rest.host")+"/customer/getCustomerDetailsById/"+customerId,
					HttpMethod.GET, null, CustomerDetailsDTO.class);
		
		if(responseEntity.getStatusCode()==HttpStatus.FOUND)
			return responseEntity.getBody();
		else
			throw new CustomerDetailsNotFoundException();

	}
}
