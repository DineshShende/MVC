package com.projectx.mvc.util.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;

@Component
public class VendorDetailsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return VendorDetailsDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		VendorDetailsDTO entity=(VendorDetailsDTO)target;
		
		if(entity.getFirstName().equals(""))
			errors.rejectValue("firstName", "firstName can't be null", "firstName can't be null");
		
		if(entity.getLastName().equals(""))
			errors.rejectValue("lastName", "lastName can't be null", "lastName can't be null");
		
		if(entity.getMobile()!=null && (entity.getMobile()<1000000000L || entity.getMobile()>9999999999L))
			errors.rejectValue("mobile", "mobile out of range", "mobile should be between 1000000000L and 9999999999L");
		
		if(entity.getEmail().equals("") && entity.getMobile()==null)
			errors.rejectValue("email", "Both Can't be Null", "Both email and mobile can't be Null");
		
		if(entity.getLaguage().equals(""))
			errors.rejectValue("laguage", "laguage can't be null", "laguage can't be null");
		
		
		if(entity.getFirmAddress().getAddressLine().equals(""))
			errors.rejectValue("firmAddress.addressLine", "firmAddress.addressLine can't be null", "firmAddress.addressLine can't be null");
	
		if(entity.getFirmAddress().getCity().equals(""))
			errors.rejectValue("firmAddress.city", "firmAddress.city can't be null", "firmAddress.city can't be null");
	
		if(entity.getFirmAddress().getDistrict().equals(""))
			errors.rejectValue("firmAddress.district", "firmAddress.district can't be null", "firmAddress.district can't be null");
		
		if(entity.getFirmAddress().getState().equals(""))
			errors.rejectValue("firmAddress.state", "firmAddress.state can't be null", "firmAddress.state can't be null");
		
		
		if(entity.getFirmAddress().getPincode()==null)
			errors.rejectValue("firmAddress.pincode", "pin can't be null", "pin can't be null");
		else if(entity.getFirmAddress().getPincode()<100000 || entity.getFirmAddress().getPincode()>999999)
			errors.rejectValue("firmAddress.pincode", "Pin value out of range", "Pin code values should between 100000 and 999999");

		

	}

}
