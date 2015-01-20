
package com.projectx.mvc.fixtures.completeregister;


import java.util.Date;

import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.mvc.fixtures.completeregister.AddressDataFixture.*;

public class CustomerDetailsDataFixtures {
	
	
	public static Date CUST_DOB =new Date();
	
	public static String CUST_LANG="Marathi";
	public static String CUST_BUSINESS_DOMAIN="TRANSPORT";
	
	public static String CUST_NAME_OF_FIRM="ABC TRANSPORT";
	public static Long CUST_SEC_MOBILE=8598058044L;
	public static Long CUST_SEC_MOBILE_NEW=8888888888L;
	public static Long CUST_MOBILE_NEW=9999999999L;
	public static String CUST_SEC_EMAIL="dineshshende@hotmail.com";
	
	public static String CUST_EMAIL_NEW="dineshshende@gmail.com";
	
	public static Date CUST_DATE=new Date();
	
	public static Boolean CUST_VERIFICATION_FLAG=false;
	
	//static Gson gson=new Gson();
	
	
	/*
	public static CustomerDetails standardCustomerDetails()
	{
		return new CustomerDetails(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, CUST_DOB, standardAddress(), CUST_MOBILE, CUST_ISMOBILE_VERIFIED,
				CUST_EMAIL, CUST_ISEMAIL_VERIFIED, CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, standardAddress(),CUST_SEC_MOBILE , 
				CUST_ISMOBILE_VERIFIED, 
				CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
*/
	
	public static CustomerDetailsDTO standardCustomerDetailsCopiedFromQuickRegisterEntity()
	{
		return new CustomerDetailsDTO(standardEmailMobileCustomer().getCustomerId(), standardEmailMobileCustomer().getFirstName(),
				standardEmailMobileCustomer().getLastName(), null, null, standardEmailMobileCustomer().getMobile(), 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), null, null, null, null, null, false,
				null, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsCopiedFromQuickRegisterEntity(Long customerId)
	{
		return new CustomerDetailsDTO(customerId, standardEmailMobileCustomer().getFirstName(),
				standardEmailMobileCustomer().getLastName(), null, null, standardEmailMobileCustomer().getMobile(), 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), null, null, null, null, null, false,
				null, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsFirstPart()
	{
		return new CustomerDetailsDTO(standardEmailMobileCustomer().getCustomerId(), standardEmailMobileCustomer().getFirstName(),
				standardEmailMobileCustomer().getLastName(), CUST_DATE, standardAddress(), standardEmailMobileCustomer().getMobile(), 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), CUST_LANG, null, null, null, null, null,
				null, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	public static CustomerDetailsDTO standardCustomerDetails(CustomerDetailsDTO customerDetails)
	{
		return new CustomerDetailsDTO(customerDetails.getCustomerId(), customerDetails.getFirstName(),
				customerDetails.getLastName(), CUST_DATE, standardAddress(), customerDetails.getMobile(), 
				customerDetails.getIsEmailVerified(),customerDetails.getEmail(),
				customerDetails.getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, standardAddress(),
				CUST_SEC_MOBILE, false,	CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsWithMerge(CustomerDetailsDTO customerDetails,Long mobile,Long secondaryMobile,String email)
	{
		return new CustomerDetailsDTO(customerDetails.getCustomerId(), customerDetails.getFirstName(),
				customerDetails.getLastName(), CUST_DATE, standardAddress(), mobile, 
				customerDetails.getIsEmailVerified(),email,
				customerDetails.getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, standardAddress(),
				secondaryMobile, false,	CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsNew(CustomerDetailsDTO customerDetails)
	{
		return new CustomerDetailsDTO(customerDetails.getCustomerId(), customerDetails.getFirstName(),
				customerDetails.getLastName(), CUST_DATE, standardAddress(), customerDetails.getMobile(), 
				customerDetails.getIsEmailVerified(),customerDetails.getEmail(),
				customerDetails.getIsEmailVerified(), "English", "HardWare", CUST_NAME_OF_FIRM, standardAddress(),
				CUST_SEC_MOBILE, false,	"abc@gmail", CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsWithOutMetaData()
	{
		return new CustomerDetailsDTO(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, CUST_DOB, standardAddressWithOutMetaData(), CUST_MOBILE, CUST_VERIFICATION_FLAG,
				CUST_EMAIL, CUST_VERIFICATION_FLAG, CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, standardAddressWithOutMetaData(),CUST_SEC_MOBILE , 
				false,CUST_SEC_EMAIL, null, null, null);
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsWithNewSecondaryMobile(CustomerDetailsDTO customerDetails)
	{
		return new CustomerDetailsDTO(standardEmailMobileCustomer().getCustomerId(), standardEmailMobileCustomer().getFirstName(),
				standardEmailMobileCustomer().getLastName(), CUST_DATE, customerDetails.getHomeAddressId(), standardEmailMobileCustomer().getMobile(), 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, customerDetails.getFirmAddressId(),
				CUST_SEC_MOBILE_NEW, null,	CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsWithNewMobile(CustomerDetailsDTO customerDetails)
	{
		return new CustomerDetailsDTO(customerDetails.getCustomerId(), customerDetails.getFirstName(),
				customerDetails.getLastName(), CUST_DATE, customerDetails.getHomeAddressId(), CUST_MOBILE_NEW, 
				customerDetails.getIsEmailVerified(),customerDetails.getEmail(),
				customerDetails.getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, customerDetails.getFirmAddressId(),
				CUST_SEC_MOBILE_NEW, null,	CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsWithNewEmail(CustomerDetailsDTO customerDetails)
	{
		return new CustomerDetailsDTO(customerDetails.getCustomerId(), customerDetails.getFirstName(),
				customerDetails.getLastName(), CUST_DATE, customerDetails.getHomeAddressId(), CUST_MOBILE_NEW, 
				customerDetails.getIsEmailVerified(),CUST_EMAIL_NEW,
				customerDetails.getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, customerDetails.getFirmAddressId(),
				CUST_SEC_MOBILE_NEW, null,	CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsWithNewHomeAddress(CustomerDetailsDTO customerDetails)
	{
		return new CustomerDetailsDTO(customerDetails.getCustomerId(), customerDetails.getFirstName(),
				customerDetails.getLastName(), CUST_DATE, standardAddressUpdated(), CUST_MOBILE_NEW, 
				customerDetails.getIsEmailVerified(),CUST_EMAIL_NEW,
				customerDetails.getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, customerDetails.getFirmAddressId(),
				CUST_SEC_MOBILE_NEW, null,	CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	
	/*
	public static String standardJsonCustomerDetails(CustomerDetails customerDetails)
	{
		
		
		
		
		System.out.println(gson.toJson(customerDetails));
		
		return gson.toJson(customerDetails);
	}
	
	public static String standardJsonUpdateAddress()
	{
		System.out.println(gson.toJson(standardUpdateAddressDTO()));
		
		return gson.toJson(standardUpdateAddressDTO());
	}
	
	public static String standardJsonUpdateMobileVerificationStatus()
	{
		System.out.println(gson.toJson(standardMobileVerificationStatusDTO()));
		
		return gson.toJson(standardMobileVerificationStatusDTO());
	}
	
	
	public static String standardJsonVerifyMobileDetails(VerifyMobileDTO verifyMobileDTO)
	{
		System.out.println(gson.toJson(verifyMobileDTO));
		
		return gson.toJson(verifyMobileDTO);
	}
	
	public static String standardJsonVerifyEmailDetails(VerifyEmailDTO verifyEmailDTO)
	{
		System.out.println(gson.toJson(verifyEmailDTO));
		
		return gson.toJson(verifyEmailDTO);
	}
	
	public static String standardJsonCustomerIdTypeMobileDTO(CustomerIdTypeMobileDTO idTypeMobileDTO)
	{
		System.out.println(gson.toJson(idTypeMobileDTO));
		
		return gson.toJson(idTypeMobileDTO);
	}
	
	public static String standardJsonCustomerIdTypeEmailDTO(CustomerIdTypeEmailDTO idTypeEmailDTO)
	{
		System.out.println(gson.toJson(idTypeEmailDTO));
		
		return gson.toJson(idTypeEmailDTO);
	}
	*/
}
