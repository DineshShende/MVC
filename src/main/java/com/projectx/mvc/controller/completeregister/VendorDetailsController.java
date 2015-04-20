package com.projectx.mvc.controller.completeregister;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.domain.completeregister.ResponseDTO;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.util.validator.DriverDetailsValidator;
import com.projectx.mvc.util.validator.VendorDetailsValidator;
import com.projectx.rest.domain.ang.CustomerDetailsDTOAng;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.rest.domain.completeregister.DriverDetailsDTO;
import com.projectx.rest.domain.completeregister.EntityIdTypeDTO;
import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;
import com.projectx.rest.domain.completeregister.VerifyEmailDTO;
import com.projectx.rest.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;

@RestController
@RequestMapping(value = "/vendor")
public class VendorDetailsController {

	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Autowired
	VendorDetailsValidator vendorDetailsValidator;
	
	private Integer ENTITY_TYPE_VENDOR=2;
	
	private static final Logger logger = Logger.getLogger(VendorDetailsController.class);
	
	@InitBinder("vendorDetailsDTO")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(vendorDetailsValidator);
    }
	
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO> save(@Valid @RequestBody CustomerDetailsDTOAng vendorDetailsDTO,BindingResult result)
	{
		
		VendorDetailsDTO vendorDetails=new VendorDetailsDTO(vendorDetailsDTO.getCustomerId(), vendorDetailsDTO.getFirstName(), vendorDetailsDTO.getMiddleName(),
				vendorDetailsDTO.getLastName(),vendorDetailsDTO.getDateOfBirth(), vendorDetailsDTO.getNameOfFirm(), 
				vendorDetailsDTO.getFirmAddressId(), vendorDetailsDTO.getHomeAddressId(), vendorDetailsDTO.getMobile(), vendorDetailsDTO.getPhoneNumber(),
				vendorDetailsDTO.getIsMobileVerified(), vendorDetailsDTO.getEmail(), vendorDetailsDTO.getIsEmailVerified(), vendorDetailsDTO.getLanguage(),
				vendorDetailsDTO.getSecondaryMobile(),vendorDetailsDTO.getIsSecondaryMobileVerified(), new Date(),new Date(), 
				vendorDetailsDTO.getRequestedBy(),vendorDetailsDTO.getRequestedBy(),vendorDetailsDTO.getRequestedById(),
				vendorDetailsDTO.getRequestedById());
		
		if(result.hasErrors())
		{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		vendorDetails=vendorDetailsService.intializeMetaData(vendorDetails);
	
		VendorDetailsDTO newVendorDetailsDTO=vendorDetailsService
				.update(vendorDetails);
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO("sucess", ""), HttpStatus.OK);
	
	}
	

	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<CustomerDetailsDTOAng> getById(@RequestBody EntityIdTypeDTO entityIdDTO)
	{
	
		try{
			VendorDetailsDTO fetchedEntity=vendorDetailsService.getCustomerDetailsById(entityIdDTO.getEntityId());
			
			CustomerDetailsDTOAng ang=new CustomerDetailsDTOAng(fetchedEntity.getVendorId(), fetchedEntity.getFirstName(), fetchedEntity.getMiddleName(),
					fetchedEntity.getLastName(), fetchedEntity.getDateOfBirth(), fetchedEntity.getHomeAddress(), 
					fetchedEntity.getMobile(), fetchedEntity.getPhoneNumber(), fetchedEntity.getIsMobileVerified(), 
					fetchedEntity.getEmail(), fetchedEntity.getIsEmailVerified(), fetchedEntity.getLaguage(), 
					null, fetchedEntity.getFirmName(), fetchedEntity.getFirmAddress(), 
					fetchedEntity.getSecondaryMobile(), fetchedEntity.getIsSecondaryMobileVerified(),null,ENTITY_TYPE_VENDOR, 
					fetchedEntity.getInsertTime(),fetchedEntity.getUpdateTime(), fetchedEntity.getUpdatedBy(),fetchedEntity.getUpdatedById());
			
			return new ResponseEntity<CustomerDetailsDTOAng>(ang, HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
}