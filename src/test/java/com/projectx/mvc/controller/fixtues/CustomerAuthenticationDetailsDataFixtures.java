package com.projectx.mvc.controller.fixtues;

import com.projectx.mvc.domain.UpdatePasswordDTO;
import com.projectx.rest.domain.CustomerAuthenticationDetailsDTO;
import com.projectx.rest.domain.LoginVerificationDTO;

import static com.projectx.mvc.controller.fixtues.CustomerQuickRegisterDataFixture.*;



public class CustomerAuthenticationDetailsDataFixtures {
	
	
	public static CustomerAuthenticationDetailsDTO standardCustomerEmailMobileAuthenticationDetails()
	{
		return new CustomerAuthenticationDetailsDTO(CUST_ID, CUST_EMAIL, CUST_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT);
	}
	
	public static CustomerAuthenticationDetailsDTO standardCustomerEmailAuthenticationDetails()
	{
		return new CustomerAuthenticationDetailsDTO(CUST_ID, CUST_EMAIL, null, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT);
	}

	public static CustomerAuthenticationDetailsDTO standardCustomerMobileAuthenticationDetails()
	{
		return new CustomerAuthenticationDetailsDTO(CUST_ID, null, CUST_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT);
	}

	public static CustomerAuthenticationDetailsDTO standardCustomerEmailMobileAuthenticationDetailsWithNewPassword()
	{
		return new CustomerAuthenticationDetailsDTO(CUST_ID, CUST_EMAIL, CUST_MOBILE, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED);
	}
	
	public static CustomerAuthenticationDetailsDTO standardCustomerEmailAuthenticationDetailsWithNewPassword()
	{
		return new CustomerAuthenticationDetailsDTO(CUST_ID, CUST_EMAIL, null, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED);
	}

	public static CustomerAuthenticationDetailsDTO standardCustomerMobileAuthenticationDetailsWithNewPassword()
	{
		return new CustomerAuthenticationDetailsDTO(CUST_ID, null, CUST_MOBILE, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED);
	}

	
	

	
	
	public static LoginVerificationDTO standardLoginVerificationWithEmail()
	{
		return new LoginVerificationDTO(CUST_EMAIL, null, CUST_PASSWORD_DEFAULT);
	}
	
	public static LoginVerificationDTO standardLoginVerificationWithMobile()
	{
		return new LoginVerificationDTO(null, CUST_MOBILE, CUST_PASSWORD_DEFAULT);
	}
	
	public static LoginVerificationDTO standardLoginVerificationWithEmailNewPassword()
	{
		return new LoginVerificationDTO(CUST_EMAIL, null, CUST_PASSWORD_CHANGED);
	}
	
	public static LoginVerificationDTO standardLoginVerificationWithMobileNewPassword()
	{
		return new LoginVerificationDTO(null, CUST_MOBILE, CUST_PASSWORD_CHANGED);
	}

	public static UpdatePasswordDTO standardUpdatePassword()
	{
		return new UpdatePasswordDTO(CUST_ID, CUST_PASSWORD_CHANGED);
	}
	
	
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

		if(loginVerificationDTO.getEmail()!=null)
			jsonBuilder.append("{\"email\":\""+loginVerificationDTO.getEmail()+"\",");
		else
			jsonBuilder.append("{\"email\":"+loginVerificationDTO.getEmail()+",");

		jsonBuilder.append("\"mobile\":"+loginVerificationDTO.getMobile()+",");
		
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
