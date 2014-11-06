package com.projectx.mvc.fixture;

import static com.projectx.mvc.fixture.CustomerQuickRegisterDataConstants.REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED;
import static com.projectx.mvc.fixture.CustomerQuickRegisterDataConstants.REGISTER_EMAIL_ALREADY_REGISTERED_VERIFIED;
import static com.projectx.mvc.fixture.CustomerQuickRegisterDataConstants.REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED;
import static com.projectx.mvc.fixture.CustomerQuickRegisterDataConstants.REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_VERIFIED;
import static com.projectx.mvc.fixture.CustomerQuickRegisterDataConstants.REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_VERIFIED;
import static com.projectx.mvc.fixture.CustomerQuickRegisterDataConstants.REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_MOBILE_VERIFIED;
import static com.projectx.mvc.fixture.CustomerQuickRegisterDataConstants.REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED;
import static com.projectx.mvc.fixture.CustomerQuickRegisterDataConstants.REGISTER_MOBILE_ALREADY_REGISTERED_VERIFIED;

import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;


public class CustomerQuickRegisterDataConstants {

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
	public static Integer CUST_MOBILEPIN_VERIFICATION_ATTEMPTS=0;
	public static Date CUST_EMAIL_HASH_SENT_TIME=new Date();
	public static Date CUST_MOBILE_PIN_SENT_TIME=new Date();
	public static Date CUST_LAST_STATUS_CHANGED_TIME=new Date();
	public static String CUST_PASSWORD="123456";
	public static String CUST_PASSWORD_TYPE_DEFAULT="Default";
	
	
	
	public static String STATUS_EMAIL_VERFIED_MOBILE_PENDING="EmailVerifiedMobileVerficationPending";
	public static String STATUS_MOBILE_VERFIED_EMAIL_PENDING="MobileVerifiedEmailVerficationPending";
	public static String STATUS_EMAIL_MOBILE_VERIFIED="EmailMobileVerified";
	public static String STATUS_MOBILE_VERFIED="MobileVerified";
	public static String STATUS_EMAIL_VERFIED="EmailVerified";
	
	public static String REGISTER_NOT_REGISTERED="NOT_REGISTERED";
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
	
	
	//private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	public static CustomerQuickRegisterDTO standardEmailMobileCustomer()
	{
		return new CustomerQuickRegisterDTO(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL,CUST_MOBILE, CUST_PIN,CUST_STATUS_EMAILMOBILE, CUST_MOBILEPIN, CUST_EMAILHASH,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS,CUST_MOBILE_PIN_SENT_TIME,CUST_EMAIL_HASH_SENT_TIME,CUST_LAST_STATUS_CHANGED_TIME,CUST_PASSWORD,CUST_PASSWORD_TYPE_DEFAULT);
		
	}
	
	public static CustomerQuickRegisterDTO standardMobileCustomer()
	{
		return new CustomerQuickRegisterDTO(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, null,CUST_MOBILE, CUST_PIN,CUST_STATUS_MOBILE, CUST_MOBILEPIN, null,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS,CUST_MOBILE_PIN_SENT_TIME,null,CUST_LAST_STATUS_CHANGED_TIME,CUST_PASSWORD,CUST_PASSWORD_TYPE_DEFAULT);
		
	}
	
	public static CustomerQuickRegisterDTO standardEmailCustomer()
	{
		return new CustomerQuickRegisterDTO(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL,null, CUST_PIN,CUST_STATUS_EMAIL, null, CUST_EMAILHASH,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS,null,CUST_EMAIL_HASH_SENT_TIME,CUST_LAST_STATUS_CHANGED_TIME,CUST_PASSWORD,CUST_PASSWORD_TYPE_DEFAULT);
		
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
