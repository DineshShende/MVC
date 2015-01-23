package com.projectx.mvc.util.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.projectx.mvc.domain.quickregister.QuickRegisterEntity;


@Component
public class QuickRegisterEntityValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
			return QuickRegisterEntity.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		QuickRegisterEntity entity=(QuickRegisterEntity)target;
		
		if(entity.getFirstName().equals(""))
			errors.rejectValue("firstName", "firstName can't be null", "firstName can't be null");
		
		if(entity.getLastName().equals(""))
			errors.rejectValue("lastName", "lastName can't be null", "lastName can't be null");
		
		
		if(entity.getMobile()!=null && (entity.getMobile()<1000000000L || entity.getMobile()>9999999999L))
			errors.rejectValue("mobile", "mobile out of range", "mobile should be between 1000000000L and 9999999999L");
		
		
		if(entity.getEmail().equals("") && entity.getMobile()==null)
			errors.rejectValue("email", "Both Can't be Null", "Both email and mobile can't be Null");
		
		if(entity.getPin()==null)
			errors.rejectValue("pin", "pin can't be null", "pin can't be null");
		else if(entity.getPin()<100000 || entity.getPin()>999999)
			errors.rejectValue("pin", "Pin value out of range", "Pin code values should between 100000 and 999999");
	}


}
