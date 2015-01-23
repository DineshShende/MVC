package com.projectx.mvc.util.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;

@Component
public class CustomerDetailsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return CustomerDetailsDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		CustomerDetailsDTO entity=(CustomerDetailsDTO)target;
		
		if(entity.getFirstName().equals(""))
			errors.rejectValue("firstName", "firstName can't be null", "firstName can't be null");
		
		if(entity.getLastName().equals(""))
			errors.rejectValue("lastName", "lastName can't be null", "lastName can't be null");
		
		if(entity.getMobile()!=null && (entity.getMobile()<1000000000L || entity.getMobile()>9999999999L))
			errors.rejectValue("mobile", "mobile out of range", "mobile should be between 1000000000L and 9999999999L");
		
		if(entity.getEmail().equals("") && entity.getMobile()==null)
			errors.rejectValue("email", "Both Can't be Null", "Both email and mobile can't be Null");
		
		if(entity.getSecondaryMobile()!=null &&(entity.getSecondaryMobile()<1000000000L || entity.getSecondaryMobile()>9999999999L))
			errors.rejectValue("secondaryMobile", "mobile out of range", "mobile should be between 1000000000L and 9999999999L");
		
		
		if(entity.getLanguage().equals(""))
			errors.rejectValue("language", "language can't be null", "language can't be null");
		
		if(entity.getBusinessDomain().equals(""))
			errors.rejectValue("businessDomain", "businessDomain can't be null", "businessDomain can't be null");
		
		if(entity.getNameOfFirm().equals(""))
			errors.rejectValue("nameOfFirm", "nameOfFirm can't be null", "nameOfFirm can't be null");
		
	
		
		if(entity.getHomeAddressId().getAddressLine().equals(""))
			errors.rejectValue("homeAddressId.addressLine", "homeAddressId.addressLine can't be null", "homeAddressId.addressLine can't be null");
	
		if(entity.getHomeAddressId().getCity().equals(""))
			errors.rejectValue("homeAddressId.city", "homeAddressId.city can't be null", "homeAddressId.city can't be null");
	
		if(entity.getHomeAddressId().getDistrict().equals(""))
			errors.rejectValue("homeAddressId.district", "homeAddressId.district can't be null", "homeAddressId.district can't be null");
		
		if(entity.getHomeAddressId().getState().equals(""))
			errors.rejectValue("homeAddressId.state", "homeAddressId.state can't be null", "homeAddressId.state can't be null");


		if(entity.getHomeAddressId().getPincode()==null)
			errors.rejectValue("homeAddressId.pincode", "pin can't be null", "pin can't be null");
		else if(entity.getHomeAddressId().getPincode()!=null &&(entity.getHomeAddressId().getPincode()<100000 || entity.getHomeAddressId().getPincode()>999999))
			errors.rejectValue("homeAddressId.pincode", "Pin value out of range", "Pin code values should between 100000 and 999999");
		
		
		if(entity.getFirmAddressId().getAddressLine().equals(""))
			errors.rejectValue("firmAddressId.addressLine", "firmAddressId.addressLine can't be null", "firmAddressId.addressLine can't be null");
	
		if(entity.getFirmAddressId().getCity().equals(""))
			errors.rejectValue("firmAddressId.city", "firmAddressId.city can't be null", "firmAddressId.city can't be null");
	
		if(entity.getFirmAddressId().getDistrict().equals(""))
			errors.rejectValue("firmAddressId.district", "firmAddressId.district can't be null", "firmAddressId.district can't be null");
		
		if(entity.getFirmAddressId().getState().equals(""))
			errors.rejectValue("firmAddressId.state", "firmAddressId.state can't be null", "firmAddressId.state can't be null");
		
		
		if(entity.getFirmAddressId().getPincode()==null)
			errors.rejectValue("firmAddressId.pincode", "pin can't be null", "pin can't be null");
		else if(entity.getFirmAddressId().getPincode()!=null &&(entity.getFirmAddressId().getPincode()<100000 || entity.getFirmAddressId().getPincode()>999999))
			errors.rejectValue("firmAddressId.pincode", "Pin value out of range", "Pin code values should between 100000 and 999999");


	}
	
	

}
