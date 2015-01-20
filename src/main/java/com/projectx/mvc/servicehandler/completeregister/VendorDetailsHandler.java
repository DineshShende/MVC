package com.projectx.mvc.servicehandler.completeregister;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;
import com.projectx.rest.domain.completeregister.VerifyEmailDTO;
import com.projectx.rest.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;

@Component
@Profile(value="Dev")
@PropertySource(value="classpath:/application.properties")
public class VendorDetailsHandler implements VendorDetailsService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	
	private Integer ENTITY_TYPE_VENDOR=2;
	
	private Integer ENTITY_TYPE_PRIMARY=1;
	
	
	@Override
	public VendorDetailsDTO createCustomerDetailsFromQuickRegisterEntity(
			QuickRegisterDTO quickRegisterEntity) {
	
		VendorDetailsDTO status=restTemplate.postForObject(env.getProperty("rest.host")+"/vendor/createFromQuickRegister", quickRegisterEntity, VendorDetailsDTO.class);
		
		return status;
		
	}

	@Override
	public VendorDetailsDTO update(VendorDetailsDTO vendorDetails) {
		
		VendorDetailsDTO status=restTemplate.postForObject(env.getProperty("rest.host")+"/vendor/update", vendorDetails, VendorDetailsDTO.class);
		
		return status;
		
		
	}

	@Override
	public VendorDetailsDTO getCustomerDetailsById(Long vendorId) {

		VendorDetailsDTO customerDetailsDTO=restTemplate
				.getForObject(env.getProperty("rest.host")+"/vendor/getVendorDetailsById/"+vendorId, VendorDetailsDTO.class);
		
		return customerDetailsDTO;

		
	}

	@Override
	public Boolean verifyMobileDetails(VerifyMobileDTO verifyMobileDTO) {

		Boolean status=restTemplate
				.postForObject(env.getProperty("rest.host")+"/vendor/verifyMobileDetails", verifyMobileDTO, Boolean.class);
		
		return status;
		
	}

	@Override
	public Boolean verifyEmailDetails(VerifyEmailDTO verifyEmailDTO) {
	
		Boolean status=restTemplate
				.postForObject(env.getProperty("rest.host")+"/vendor/verifyEmailDetails", verifyEmailDTO, Boolean.class);
		
		return status;
		
	}

	@Override
	public Boolean sendMobileVerificationDetails(
			CustomerIdTypeMobileTypeDTO customerIdTypeMobileDTO) {
		
		Boolean status=restTemplate
				.postForObject(env.getProperty("rest.host")+"/vendor/sendMobileVerificationDetails", customerIdTypeMobileDTO, Boolean.class);
		
		return status;

	}

	@Override
	public Boolean sendEmailVerificationDetails(
			CustomerIdTypeEmailTypeDTO customerIdTypeEmailDTO) {

		Boolean status=restTemplate
				.postForObject(env.getProperty("rest.host")+"/vendor/sendEmailVerificationDetails", customerIdTypeEmailDTO, Boolean.class);
		
		return status;
		
	}

	@Override
	public Integer count() {

		Integer count=restTemplate
				.getForObject(env.getProperty("rest.host")+"/vendor/count", Integer.class);
		
		return count;
		
	}

	@Override
	public Boolean clearTestData() {
	
		Boolean result=restTemplate
				.getForObject(env.getProperty("rest.host")+"/vendor/clearTestData", Boolean.class);
		
		return result;
		
		
	}

	@Override
	public VendorDetailsDTO intializeMetaData(VendorDetailsDTO vendorDetails) {
		
		if(vendorDetails.getUpdatedBy()==null)
			vendorDetails.setUpdatedBy("CUST_ONLINE");
		
		vendorDetails.setUpdateTime(new Date());
			
		if(vendorDetails.getInsertTime()==null)
			vendorDetails.setInsertTime(new Date());
		
		
		
		if(vendorDetails.getFirmAddress()!=null)
		{
			if(vendorDetails.getFirmAddress().getUpdatedBy()==null)
				vendorDetails.getFirmAddress().setUpdatedBy("CUST_ONLINE");
			
			vendorDetails.getFirmAddress().setUpdateTime(new Date());
			
			if(vendorDetails.getFirmAddress().getInsertTime()==null)
				vendorDetails.getFirmAddress().setInsertTime(new Date());
			
			
		}
		
		return vendorDetails;

		
	}

	@Override
	public Model initialiseShowVendorDetails(Long entityId, Model model) {
		
		EmailVerificationDetailsDTO emailVerificationDetails=quickRegisterService
				.getEmailVerificationDetailsByCustomerIdTypeAndEmail(entityId,ENTITY_TYPE_VENDOR , ENTITY_TYPE_PRIMARY);
		
		MobileVerificationDetailsDTO mobileVerificationDetailsPrimary=quickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(entityId, ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY);
		
		
		model.addAttribute("emailVerificationDetails", emailVerificationDetails);
		model.addAttribute("mobileVerificationDetailsPrimary", mobileVerificationDetailsPrimary);
		
		
		return model;
		
		
	}

}
