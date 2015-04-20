package com.projectx.mvc.fixtures.quickregister;

import java.util.Date;

import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.LoginVerificationDTO;

import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.*;



public class AuthenticationDetailsDataFixtures {
	
	
	public static Integer ZERO_COUNT=0;
	
	public static Date CUST_DATE=new Date();
	
	
	
	public static AuthenticationDetailsKey standardAuthenticationKey()
	{
		return new AuthenticationDetailsKey(CUST_ID, ENTITY_TYPE_CUSTOMER);
	}
	
	public static AuthenticationDetailsDTO standardCustomerEmailMobileAuthenticationDetails()
	{
		return new  AuthenticationDetailsDTO(standardAuthenticationKey(), "completeName",  CUST_PASSWORD_DEFAULT, false);
	}
	
	public static AuthenticationDetailsDTO standardCustomerEmailAuthenticationDetails()
	{
		return new  AuthenticationDetailsDTO(standardAuthenticationKey(), "completeName",  CUST_PASSWORD_DEFAULT, false);
	}

	public static AuthenticationDetailsDTO standardCustomerMobileAuthenticationDetails()
	{
		return new  AuthenticationDetailsDTO(standardAuthenticationKey(), "completeName",  CUST_PASSWORD_DEFAULT, false);
	}

	public static AuthenticationDetailsDTO standardCustomerEmailMobileAuthenticationDetailsWithNewPassword()
	{
		return new  AuthenticationDetailsDTO(standardAuthenticationKey(), "completeName", CUST_PASSWORD_CHANGED, false);
	}
	
	public static AuthenticationDetailsDTO standardCustomerEmailAuthenticationDetailsWithNewPassword()
	{
		
		
		return new  AuthenticationDetailsDTO(standardAuthenticationKey(), "completeName", CUST_PASSWORD_CHANGED, false);
		
		
	}

	public static AuthenticationDetailsDTO standardCustomerMobileAuthenticationDetailsWithNewPassword()
	{
		return new  AuthenticationDetailsDTO(standardAuthenticationKey(), "completeName", CUST_PASSWORD_DEFAULT, false);
	}
	
	

	
	
	public static LoginVerificationDTO standardLoginVerificationWithEmail()
	{
		return new LoginVerificationDTO(CUST_EMAIL,CUST_PASSWORD_DEFAULT);
	}
	
	public static LoginVerificationDTO standardLoginVerificationWithMobile()
	{
		return new LoginVerificationDTO(Long.toString(CUST_MOBILE), CUST_PASSWORD_DEFAULT);
	}
	
	public static LoginVerificationDTO standardLoginVerificationWithEmailNewPassword()
	{
		return new LoginVerificationDTO(CUST_EMAIL, CUST_PASSWORD_CHANGED);
	}
	
	public static LoginVerificationDTO standardLoginVerificationWithMobileNewPassword()
	{
		return new LoginVerificationDTO(Long.toString(CUST_MOBILE), CUST_PASSWORD_CHANGED);
	}

	public static UpdatePasswordDTO standardUpdatePassword()
	{
		return new UpdatePasswordDTO(new AuthenticationDetailsKey(CUST_ID,ENTITY_TYPE_CUSTOMER), CUST_PASSWORD_CHANGED,true,CUST_UPDATED_BY,CUST_ID);
	}
	
	/*
	public static String standardJsonCustomerAuthenticationDetails(CustomerAuthenticationDetailsDTO standardCustomer)
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":"+standardCustomer.getCustomerId()+",");
		
		if(standardCustomer.getEmail()!=null)
			jsonBuilder.append("\"email\":\""+standardCustomer.getEmail()+"\",");
		else
			jsonBuilder.append("\"email\":"+standardCustomer.getEmail()+",");
		
		jsonBuilder.append("\"mobile\":"+standardCustomer.getMobile()+",");
		
			
		jsonBuilder.append("\"password\":\""+standardCustomer.getPassword()+"\",");
		jsonBuilder.append("\"passwordType\":\""+standardCustomer.getPasswordType()+"\"}");
		
		//System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
		
	}
	
	public static String standardJsonLoginVerification(LoginVerificationDTO loginVerificationDTO)
	{
		StringBuilder jsonBuilder=new StringBuilder();

		if(loginVerificationDTO.getLoginEntity()!=null)
			jsonBuilder.append("{\"email\":\""+loginVerificationDTO.getLoginEntity() +"\",");
		else
			jsonBuilder.append("{\"email\":"+loginVerificationDTO.getLoginEntity()+",");

		//jsonBuilder.append("\"mobile\":"+loginVerificationDTO.getMobile()+",");
		
		jsonBuilder.append("\"password\":\""+loginVerificationDTO.getPassword()+"\"}");
		
		//System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
	}
	
/*	
	public static String standardJsonUpdatePasswordAndPasswordType()
	{
		StringBuilder jsonBuilder=new StringBuilder();

		jsonBuilder.append("{\"customerId\":"+standardUpdatePasswordAndPasswordTypeDTO().getCustomerId()+",");
		
		jsonBuilder.append("\"password\":\""+standardUpdatePasswordAndPasswordTypeDTO().getPassword()+"\",");
		jsonBuilder.append("\"passwordType\":\""+standardUpdatePasswordAndPasswordTypeDTO().getPasswordType()+"\"}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
		
	}
	*/
}
