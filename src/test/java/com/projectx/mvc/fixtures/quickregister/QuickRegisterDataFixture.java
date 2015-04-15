package com.projectx.mvc.fixtures.quickregister;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.mvc.domain.quickregister.LoginDetailsDTO;
import com.projectx.mvc.domain.quickregister.QuickRegisterEntity;
import com.projectx.mvc.domain.quickregister.ResetPasswordRedirectDTO;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailOrMobileOptionUpdatedBy;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailDTO;
import com.projectx.rest.domain.quickregister.VerifyMobileDTO;


public class QuickRegisterDataFixture {

	private static Gson gson=new Gson();
	
	public static Long CUST_ID=212L;
	public static String CUST_FIRSTNAME="dinesh";
	public static String CUST_LASTNAME="shende";
	
	public static String CUST_EMAIL="dineshshe@gmail.com";
	public static String CUST_EMAIL_OTHER="shendedinesh@gmail.com";
	public static Long CUST_MOBILE=9960821869L;
	
	public static Integer CUST_PIN=413133;
	
	public static String CUST_STATUS_EMAILMOBILE="EmailMobileVerificationPending";
	public static String CUST_STATUS_EMAIL="EmailVerificationPending";
	public static String CUST_STATUS_MOBILE="MobileVerificationPending";

	
	
	public static Integer CUST_MOBILEPIN=101010;
	public static Integer CUST_MOBILEPIN_UPDATED=102010;
	public static String CUST_EMAILHASH="02b99c87926ed36ed1b41afccf9b05f9efd6e54e6e9d116b8ed3a7eaf257b85a";
	public static String CUST_EMAILHASH_UPDATED="277f7fb2ede95f935b08c63273471c9077ace61f1cbb1f376b2983032fda5321";
	public static Integer CUST_MOBILE_VERIFICATION_ATTEMPTS=0;
	public static Date CUST_EMAIL_HASH_SENT_TIME=new Date();
	public static Date CUST_MOBILE_PIN_SENT_TIME=new Date();
	public static Date CUST_LAST_STATUS_CHANGED_TIME=new Date();
	public static String CUST_PASSWORD_DEFAULT="123456";
	public static String CUST_PASSWORD_TYPE_DEFAULT="Default";
	public static String CUST_PASSWORD_CHANGED="654321";
	public static String CUST_PASSWORD_TYPE_CHANGED="Changed";
	
	public static String CUST_UPDATED_BY="CUST_ONLINE";
	public static Boolean CUST_VERIFICATION_STATUS=false;
	public static Date CUST_LAST_STATUS_CHANGE_TIME=new Date();
	
	
	public static Integer ENTITY_TYPE_CUSTOMER=1;
	public static Integer ENTITY_TYPE_VENDOR=2;
	public static Integer ENTITY_TYPE_DRIVER=3;
	
	public static Integer ENTITY_TYPE_PRIMARY=1;
	public static Integer ENTITY_TYPE_SECONDARY=2;
	
	public static String STATUS_EMAIL_VERFIED_MOBILE_PENDING="EmailVerifiedMobileVerficationPending";
	public static String STATUS_MOBILE_VERFIED_EMAIL_PENDING="MobileVerifiedEmailVerficationPending";
	public static String STATUS_EMAIL_MOBILE_VERIFIED="EmailMobileVerified";
	public static String STATUS_MOBILE_VERFIED="MobileVerified";
	public static String STATUS_EMAIL_VERFIED="EmailVerified";
	
	
	public static String REGISTER_NOT_REGISTERED="NOT_REGISTERED";
	public static String REGISTER_REGISTERED_SUCESSFULLY="REGISTERED_SUCESSFULLY";
	
	public static String REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED="EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED";
	public static String REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_VERIFIED="EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_VERIFIED";
	public static String REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_MOBILE_VERIFIED="EMAIL_MOBILE_ALREADY_REGISTERED_MOBILE_VERIFIED";
	public static String REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_VERIFIED="EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_VERIFIED";
	public static String REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED="MOBILE_ALREADY_REGISTERED_NOT_VERIFIED";
	public static String REGISTER_MOBILE_ALREADY_REGISTERED_VERIFIED="MOBILE_ALREADY_REGISTERED_VERIFIED";
	public static String REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED="EMAIL_ALREADY_REGISTERED_NOT_VERIFIED";
	public static String REGISTER_EMAIL_ALREADY_REGISTERED_VERIFIED="EMAIL_ALREADY_REGISTERED_VERIFIED";
	
	public static String MSG_REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED="Provided Email Already Registered.Click Here Resend verification Mail";
	public static String MSG_REGISTER_EMAIL_ALREADY_REGISTERED_VERIFIED="Provided Email Already Registered.Click On Forget password to receive new Password";
	public static String MSG_REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED="Provided Mobile Already Registered. Click Here to verify mobile.";
	public static String MSG_REGISTER_MOBILE_ALREADY_REGISTERED_VERIFIED="Provided Mobile Already Registered. Click here to send pin SMS.";
	public static String MSG_REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED="Provided Email And Mobile Already Registered.Click here to verify";
	public static String MSG_REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_VERIFIED="Provided Email And Mobile Already Registered.Click to send new Password to mobile/mobile";
	public static String MSG_REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_MOBILE_VERIFIED="Provided Email And Mobile Already Registered.Click here to send new password to mobile";
	public static String MSG_REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_VERIFIED="Provided Email And Mobile Already Registered.Click here to verify to email";
	
	
	
	/*
	public static CustomerQuickRegisterKey standardEmailMobileCustomerKey()
	{
		return new CustomerQuickRegisterKey(CUST_EMAIL, CUST_MOBILE);
	}
	
	public static CustomerQuickRegisterKey standardEmailCustomerKey()
	{
		return new CustomerQuickRegisterKey(CUST_EMAIL, 0L);
	}
	
	
	public static CustomerQuickRegisterKey standardMobileCustomerKey()
	{
		return new CustomerQuickRegisterKey("", CUST_MOBILE);
	}
	*/
	
	public static QuickRegisterEntity standardCustomerQuickRegisterEntity()
	{
		return new QuickRegisterEntity(CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, CUST_MOBILE, CUST_PIN,ENTITY_TYPE_CUSTOMER,CUST_UPDATED_BY);
	}
	
	public static QuickRegisterDTO standardEmailMobileCustomer()
	{

		return new QuickRegisterDTO(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, CUST_MOBILE, CUST_PIN,ENTITY_TYPE_CUSTOMER ,CUST_VERIFICATION_STATUS, CUST_VERIFICATION_STATUS, CUST_LAST_STATUS_CHANGE_TIME,CUST_LAST_STATUS_CHANGE_TIME, CUST_UPDATED_BY);
	}
	

	public static QuickRegisterDTO standardEmailMobileVendor()
	{

		return new QuickRegisterDTO(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, CUST_MOBILE, CUST_PIN,ENTITY_TYPE_VENDOR ,CUST_VERIFICATION_STATUS, CUST_VERIFICATION_STATUS, CUST_LAST_STATUS_CHANGE_TIME,CUST_LAST_STATUS_CHANGE_TIME, CUST_UPDATED_BY);
	}
	
	public static QuickRegisterEntity standardCustomerQuickRegisterEntityVendor()
	{
		return new QuickRegisterEntity(CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, CUST_MOBILE, CUST_PIN,ENTITY_TYPE_VENDOR,CUST_UPDATED_BY);
	}
	


	
	public static QuickRegisterDTO standardMobileCustomer()
	{
		return new QuickRegisterDTO(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, null, CUST_MOBILE, CUST_PIN, ENTITY_TYPE_CUSTOMER,null, CUST_VERIFICATION_STATUS, CUST_LAST_STATUS_CHANGE_TIME,
				CUST_LAST_STATUS_CHANGE_TIME, CUST_UPDATED_BY);
	}
	
	public static QuickRegisterDTO standardEmailCustomer()
	{
		return new QuickRegisterDTO(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, null, CUST_PIN,ENTITY_TYPE_CUSTOMER, CUST_VERIFICATION_STATUS,
				null, CUST_LAST_STATUS_CHANGE_TIME,CUST_LAST_STATUS_CHANGE_TIME,CUST_UPDATED_BY);
		
	}

	
	
	
	public static QuickRegisterEntity standardEmailCustomerDTO()
	{
		return new QuickRegisterEntity(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,null,CUST_PIN,ENTITY_TYPE_CUSTOMER,CUST_UPDATED_BY);
	}
	
	public static QuickRegisterEntity standardMobileCustomerDTO()
	{
		return new QuickRegisterEntity(CUST_FIRSTNAME,CUST_LASTNAME,null,CUST_MOBILE,CUST_PIN,ENTITY_TYPE_CUSTOMER,CUST_UPDATED_BY);
	}
	
	public static QuickRegisterEntity standardEmailMobileCustomerDTO()
	{
		return new QuickRegisterEntity(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE,CUST_PIN,ENTITY_TYPE_CUSTOMER,CUST_UPDATED_BY);
	}
	
	/*
	public static QuickRegisterEntity standardEmailMobileVendorDTO()
	{
		return new QuickRegisterEntity(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE,CUST_PIN,ENTITY_TYPE_VENDOR,CUST_UPDATED_BY);
	}
	*/
	
	public static QuickRegisterEntity standardEmailCustomerDTOWithOutStatus()
	{
		return new QuickRegisterEntity(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,null,CUST_PIN,ENTITY_TYPE_CUSTOMER,CUST_UPDATED_BY);
	}
	
	public static QuickRegisterEntity standardMobileCustomerDTOWithOutStatus()
	{
		return new QuickRegisterEntity(CUST_FIRSTNAME,CUST_LASTNAME,null,CUST_MOBILE,CUST_PIN,ENTITY_TYPE_CUSTOMER,CUST_UPDATED_BY);
	}
	
	public static QuickRegisterEntity standardEmailMobileCustomerDTOWithOutStatus()
	{
		return new QuickRegisterEntity(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE,CUST_PIN,ENTITY_TYPE_CUSTOMER,CUST_UPDATED_BY);
	}
	
	public static String standardJsonQuickRegisterEntity(QuickRegisterEntity quickRegisterEntity)
	{
		return gson.toJson(quickRegisterEntity);
	}
	

	public static String standardJsonEmailMobileCustomer()
	{
		return "{\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"email\":\"dineshshe@gmail.com\",\"mobile\":9960821869,\"pin\":413133}";
		//return "{\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"email\":\"dineshshe@gmail.com\",\"mobile\":\"9960821869\",\"pin\":\"413133\",\"status\":\"\"}";
		        	
	}
	
	public static String standardJsonEmailCustomer()
	{
		return "{\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"email\":\"dineshshe@gmail.com\",\"pin\":413133}";
	}
	
	public static String standardJsonEmailCustomerForEmailVerification()
	{
		return "{\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"email\":\"dineshshe\",\"pin\":413133}";
	}
	
	public static String standardJsonMobileCustomer()
	{
		return "{\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"mobile\":9960821869,\"pin\":413133}";
	}
	/*
	public static GetByEmailDTO standardGetByEmailDTO()
	{
		return new GetByEmailDTO(CUST_EMAIL);
	}
	
	public static GetByMobileDTO standardGetByMobile()
	{
		return new GetByMobileDTO(CUST_MOBILE);
	}
	*/
	public static VerifyMobileDTO standardVerifyMobileDTO()
	{
		return new VerifyMobileDTO(CUST_ID,ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY, CUST_MOBILEPIN,CUST_UPDATED_BY);
	}
	
	public static String standardJsonVerifyMobileDTO(VerifyMobileDTO verifyMobileDTO)
	{
		return gson.toJson(verifyMobileDTO);
	}
	
	public static VerifyEmailDTO standardVerifyEmailDTO()
	{
		return new VerifyEmailDTO(CUST_ID,ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY, CUST_EMAILHASH,CUST_UPDATED_BY);
	}
	
	public  static String standardJsonCustomerIdTypeMobileTypeUpdatedByDTO(CustomerIdTypeMobileTypeRequestedByDTO dto)
	{
		
		System.out.println(gson.toJson(dto));
		return gson.toJson(dto);
	}
	
	public  static String standardJsonCustomerIdTypeEmailTypeUpdatedByDTO(CustomerIdTypeEmailTypeUpdatedByDTO dto)
	{
		
		System.out.println(gson.toJson(dto));
		return gson.toJson(dto);
	}
	
	
	public  static String standardJsonLoginDetailsDTO(LoginDetailsDTO dto)
	{
		
		System.out.println(gson.toJson(dto));
		return gson.toJson(dto);
	}
	
	public static String standardJsonUpdatePasswordDTO(UpdatePasswordDTO dto)
	{
		return gson.toJson(dto);
	}
	
	public static String standardJsonCustomerIdTypeEmailOrMobileOptionUpdatedBy(CustomerIdTypeEmailOrMobileOptionUpdatedBy by)
	{
		return gson.toJson(by);
	}
	
	
	public static String standardJsonResetPasswordRedirectDTO(ResetPasswordRedirectDTO by)
	{
		return gson.toJson(by);
	}
	
	public static String standardJsonGetByEmailDTO()
	{
		return "{\"email\":\"dineshshe@gmail.com\"}";
	}
	
	public static String standardJsonGetByEmailDTOForEmailVerification()
	{
		return "{\"email\":\"dineshshe\"}";
	}
	
	public static String standardJsonGetByMobileDTO()
	{
		return "{\"mobile\":9960821869}";
	}
	
	public static String standardJsonVerifyMobileDTO()
	{
		return "{\"mobile\":9960821869,\"mobilePin\":101010}";
		       
	}
	
}
