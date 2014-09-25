package com.projectx.mvc.fixture;

import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;


public class CustomerQuickRegisterDataFixture {

	public static Long CUST_ID=212L;
	public static String CUST_FIRSTNAME="dinesh";
	public static String CUST_LASTNAME="shende";
	
	public static String CUST_EMAIL="dineshshe@gmail.com";
	public static Long CUST_MOBILE=9960821869L;
	
	public static Integer CUST_PIN=413133;
	
	public static String CUST_STATUS_EMAILMOBILE="EmailMobileVerificationPending";
	public static String CUST_STATUS_EMAIL="EmailVerificationPending";
	public static String CUST_STATUS_MOBILE="MobileVerificationPending";
	
	public static String STATUS_EMAIL_VERFIED_MOBILE_PENDING="EmailVerifiedMobileVerficationPending";
	public static String STATUS_MOBILE_VERFIED_EMAIL_PENDING="MobileVerifiedEmailVerficationPending";
	public static String STATUS_EMAIL_MOBILE_VERIFIED="EmailMobileVerified";
	public static String STATUS_MOBILE_VERFIED="MobileVerified";
	public static String STATUS_EMAIL_VERFIED="EmailVerified";
	
	public static String REGISTER_NOT_REGISTERED="NOT_REGISTERED";
	public static String REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED="EMAIL_MOBILE_ALREADY_REGISTERED";
	public static String REGISTER_MOBILE_ALREADY_REGISTERED="MOBILE_ALREADY_REGISTERED";
	public static String REGISTER_EMAIL_ALREADY_REGISTERED="EMAIL_ALREADY_REGISTERED";
	

	public static Integer CUST_MOBILEPIN=101010;
	public static Integer CUST_MOBILEPIN_UPDATED=102010;
	public static Long CUST_EMAILHASH=1010101010L;
	public static Long CUST_EMAILHASH_UPDATED=1020102010L;

	
	public static CustomerQuickRegisterDTO standardEmailMobileCustomer()
	{
		return new CustomerQuickRegisterDTO(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE, CUST_PIN,CUST_STATUS_EMAILMOBILE, CUST_MOBILEPIN, CUST_EMAILHASH);
	}
	
	public static CustomerQuickRegisterDTO standardMobileCustomer()
	{
		return new CustomerQuickRegisterDTO(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME,null,CUST_MOBILE, CUST_PIN,CUST_STATUS_MOBILE, CUST_MOBILEPIN, null);
		
	}
	
	public static CustomerQuickRegisterDTO standardEmailCustomer()
	{
		return new CustomerQuickRegisterDTO(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME,CUST_EMAIL,null, CUST_PIN,CUST_STATUS_EMAIL, null, CUST_EMAILHASH);
		
	}

	public static CustomerQuickRegisterDTO standardEmailMobileCustomerWOCustoemrId()
	{
		return new CustomerQuickRegisterDTO(null,CUST_FIRSTNAME, CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE, CUST_PIN,CUST_STATUS_EMAILMOBILE, CUST_MOBILEPIN, CUST_EMAILHASH);
	}
	
	public static CustomerQuickRegisterDTO standardMobileCustomerWOCustoemrId()
	{
		return new CustomerQuickRegisterDTO(null,CUST_FIRSTNAME, CUST_LASTNAME,null,CUST_MOBILE, CUST_PIN,CUST_STATUS_MOBILE, CUST_MOBILEPIN, null);
		
	}
	
	public static CustomerQuickRegisterDTO standardEmailCustomerWOCustoemrId()
	{
		return new CustomerQuickRegisterDTO(null,CUST_FIRSTNAME, CUST_LASTNAME,CUST_EMAIL,null, CUST_PIN,CUST_STATUS_EMAIL, null, CUST_EMAILHASH);
		
	}
	
	
	public static CustomerQuickRegisterEntity standardEmailCustomerDTO()
	{
		return new CustomerQuickRegisterEntity(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,null,CUST_PIN,CUST_STATUS_EMAIL);
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomerDTO()
	{
		return new CustomerQuickRegisterEntity(CUST_FIRSTNAME,CUST_LASTNAME,null,CUST_MOBILE,CUST_PIN,CUST_STATUS_MOBILE);
	}
	
	public static CustomerQuickRegisterEntity standardEmailMobileCustomerDTO()
	{
		return new CustomerQuickRegisterEntity(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE,CUST_PIN,CUST_STATUS_EMAILMOBILE);
	}
	
	
	public static CustomerQuickRegisterEntity standardEmailCustomerDTOWithOutStatus()
	{
		return new CustomerQuickRegisterEntity(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,null,CUST_PIN,null);
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomerDTOWithOutStatus()
	{
		return new CustomerQuickRegisterEntity(CUST_FIRSTNAME,CUST_LASTNAME,null,CUST_MOBILE,CUST_PIN,null);
	}
	
	public static CustomerQuickRegisterEntity standardEmailMobileCustomerDTOWithOutStatus()
	{
		return new CustomerQuickRegisterEntity(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE,CUST_PIN,null);
	}
	

	
}
