package com.projectx.mvc.util.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.projectx.rest.domain.completeregister.DriverDetailsDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;

@Component
public class DriverDetailsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return DriverDetailsDTO.class.equals(clazz);
		
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		DriverDetailsDTO entity=(DriverDetailsDTO)target;
		
		if(entity.getHomeAddress().getAddressLine().equals(""))
			errors.rejectValue("homeAddress.addressLine", "homeAddress.addressLine can't be null", "homeAddress.addressLine can't be null");
	
		if(entity.getHomeAddress().getCity().equals(""))
			errors.rejectValue("homeAddress.city", "homeAddress.city can't be null", "homeAddress.city can't be null");
	
		if(entity.getHomeAddress().getDistrict().equals(""))
			errors.rejectValue("homeAddress.district", "homeAddress.district can't be null", "homeAddress.district can't be null");
		
		if(entity.getHomeAddress().getState().equals(""))
			errors.rejectValue("homeAddress.state", "homeAddress.state can't be null", "homeAddress.state can't be null");
		
		
		if(entity.getHomeAddress().getPincode()==null)
			errors.rejectValue("homeAddress.pincode", "pin can't be null", "pin can't be null");
		else if(entity.getHomeAddress().getPincode()<100000 || entity.getHomeAddress().getPincode()>999999)
			errors.rejectValue("homeAddress.pincode", "Pin value out of range", "Pin code values should between 100000 and 999999");

		

	}

}
