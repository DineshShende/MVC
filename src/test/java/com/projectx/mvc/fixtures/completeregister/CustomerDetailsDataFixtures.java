
package com.projectx.mvc.fixtures.completeregister;


import java.util.Date;

import com.google.gson.Gson;
import com.projectx.rest.domain.ang.CustomerDetailsDTOAng;
import com.projectx.rest.domain.completeregister.CustomerDetailsAngDTO;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.EntityIdTypeDTO;

import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.mvc.fixtures.completeregister.AddressDataFixture.*;

public class CustomerDetailsDataFixtures {
	
	
	public static Date CUST_DOB =new Date();
	
	public static String CUST_LANG="Marathi";
	public static String CUST_BUSINESS_DOMAIN="TRANSPORT";
	
	public static String CUST_NAME_OF_FIRM="ABC TRANSPORT";
	public static Long CUST_SEC_MOBILE=8598058044L;
	public static Long CUST_PHONE_NUMBER=null;
	public static Long CUST_SEC_MOBILE_NEW=8888888888L;
	public static Long CUST_MOBILE_NEW=9999999999L;
	public static String CUST_SEC_EMAIL="dineshshende@hotmail.com";
	
	public static String CUST_EMAIL_NEW="dineshshende@gmail.com";
	
	public static Date CUST_DATE=new Date();
	
	public static Boolean CUST_VERIFICATION_FLAG=false;
	
	public static Gson gson=new Gson();
	
	
	
	
	public static CustomerDetailsDTO standardCustomerDetailsCopiedFromQuickRegisterEntity()
	{
		return new CustomerDetailsDTO(standardEmailMobileCustomer().getCustomerId(), standardEmailMobileCustomer().getFirstName(),null,
				standardEmailMobileCustomer().getLastName(), null, null, standardEmailMobileCustomer().getMobile(),CUST_PHONE_NUMBER, 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), null, null, null, null, null, false,
				null,false, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,standardEmailMobileCustomer().getCustomerId(),standardEmailMobileCustomer().getCustomerId());
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsCopiedFromQuickRegisterEntity(Long customerId)
	{
		return new CustomerDetailsDTO(customerId, standardEmailMobileCustomer().getFirstName(),null,
				standardEmailMobileCustomer().getLastName(), null, null, standardEmailMobileCustomer().getMobile(),CUST_PHONE_NUMBER, 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), null, null, null, null, null, false,
				null,false, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,customerId,customerId);
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsCopiedFromQuickRegisterEntityWithDefaultAdd(Long customerId)
	{
		return new CustomerDetailsDTO(customerId, standardEmailMobileCustomer().getFirstName(),null,
				standardEmailMobileCustomer().getLastName(), null, standardAddressDefault(ENTITY_TYPE_CUSTOMER), standardEmailMobileCustomer().getMobile(),CUST_PHONE_NUMBER, 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), null, null, null, null, null, false,
				null,false, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,customerId,customerId);
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsCopiedFromQuickRegisterEntityWithDefaultAddMobVerified(Long customerId)
	{
		return new CustomerDetailsDTO(customerId, standardEmailMobileCustomer().getFirstName(),null,
				standardEmailMobileCustomer().getLastName(), null, standardAddressDefault(ENTITY_TYPE_CUSTOMER), standardEmailMobileCustomer().getMobile(),CUST_PHONE_NUMBER, 
				true,standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), null, null, null, null, null, false,
				null,false, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,customerId,customerId);
	}
	
	public static CustomerDetailsDTO standardCustomerDetails(Long customerId)
	{
		return new CustomerDetailsDTO(customerId, standardEmailMobileCustomer().getFirstName(),null,
				standardEmailMobileCustomer().getLastName(), CUST_DOB,standardAddress() , standardEmailMobileCustomer().getMobile(),CUST_PHONE_NUMBER, 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, 
				standardAddress(), CUST_SEC_MOBILE, false,
				CUST_EMAIL_NEW,false, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,customerId,customerId);
	}
	
	public static CustomerDetailsDTOAng standardCustomerDetailsAng(Long customerId)
	{
		return new CustomerDetailsDTOAng(customerId, standardEmailMobileCustomer().getFirstName(),null,
				standardEmailMobileCustomer().getLastName(), CUST_DOB,standardAddress() , standardEmailMobileCustomer().getMobile(),CUST_PHONE_NUMBER, 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, 
				standardAddress(), CUST_SEC_MOBILE, false,
				CUST_EMAIL_NEW,ENTITY_TYPE_CUSTOMER,false,null, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,customerId);
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsFirstPart()
	{
		return new CustomerDetailsDTO(standardEmailMobileCustomer().getCustomerId(), standardEmailMobileCustomer().getFirstName(),null,
				standardEmailMobileCustomer().getLastName(), CUST_DATE, standardAddress(), standardEmailMobileCustomer().getMobile(),CUST_PHONE_NUMBER, 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), CUST_LANG, null, null, null, null, null,
				null,false, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,standardEmailMobileCustomer().getCustomerId(),standardEmailMobileCustomer().getCustomerId());
		
	}
	
	public static CustomerDetailsDTO standardCustomerDetails(CustomerDetailsDTO customerDetails)
	{
		return new CustomerDetailsDTO(customerDetails.getCustomerId(), customerDetails.getFirstName(),null,
				customerDetails.getLastName(), CUST_DATE, standardAddress(), customerDetails.getMobile(),CUST_PHONE_NUMBER, 
				customerDetails.getIsEmailVerified(),customerDetails.getEmail(),
				customerDetails.getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, standardAddress(),
				CUST_SEC_MOBILE, false,	CUST_SEC_EMAIL, false,CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,
				customerDetails.getCustomerId(),customerDetails.getCustomerId());
		
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsWithMerge(CustomerDetailsDTO customerDetails,Long mobile,Long secondaryMobile,String email)
	{
		return new CustomerDetailsDTO(customerDetails.getCustomerId(), customerDetails.getFirstName(),null,
				customerDetails.getLastName(), CUST_DATE, standardAddress(), mobile,CUST_PHONE_NUMBER, 
				customerDetails.getIsEmailVerified(),email,
				customerDetails.getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, standardAddress(),
				secondaryMobile, false,	CUST_SEC_EMAIL,false, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,
				customerDetails.getCustomerId(),customerDetails.getCustomerId());
		
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsNew(CustomerDetailsDTO customerDetails)
	{
		return new CustomerDetailsDTO(customerDetails.getCustomerId(), customerDetails.getFirstName(),null,
				customerDetails.getLastName(), CUST_DATE, standardAddress(), customerDetails.getMobile(),CUST_PHONE_NUMBER, 
				customerDetails.getIsEmailVerified(),customerDetails.getEmail(),
				customerDetails.getIsEmailVerified(), "English", "HardWare", CUST_NAME_OF_FIRM, standardAddress(),
				CUST_SEC_MOBILE, false,	"abc@gmail",false, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,customerDetails.getCustomerId(),
				customerDetails.getCustomerId());
		
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsWithOutMetaData()
	{
		return new CustomerDetailsDTO(CUST_ID, CUST_FIRSTNAME,null, CUST_LASTNAME, CUST_DOB, standardAddressWithOutMetaData(), CUST_MOBILE,CUST_PHONE_NUMBER, CUST_VERIFICATION_FLAG,
				CUST_EMAIL, CUST_VERIFICATION_FLAG, CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, standardAddressWithOutMetaData(),CUST_SEC_MOBILE , 
				false,CUST_SEC_EMAIL,false, new Date(), new Date(), CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsWithNewSecondaryMobile(CustomerDetailsDTO customerDetails)
	{
		return new CustomerDetailsDTO(standardEmailMobileCustomer().getCustomerId(), standardEmailMobileCustomer().getFirstName(),null,
				standardEmailMobileCustomer().getLastName(), CUST_DATE, customerDetails.getHomeAddressId(), standardEmailMobileCustomer().getMobile(),CUST_PHONE_NUMBER, 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, customerDetails.getFirmAddressId(),
				CUST_SEC_MOBILE_NEW, null,	CUST_SEC_EMAIL,false, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,
				standardEmailMobileCustomer().getCustomerId(),standardEmailMobileCustomer().getCustomerId());
		
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsWithNewMobile(CustomerDetailsDTO customerDetails)
	{
		return new CustomerDetailsDTO(customerDetails.getCustomerId(), customerDetails.getFirstName(),null,
				customerDetails.getLastName(), CUST_DATE, customerDetails.getHomeAddressId(), CUST_MOBILE_NEW,CUST_PHONE_NUMBER, 
				customerDetails.getIsEmailVerified(),customerDetails.getEmail(),
				customerDetails.getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, customerDetails.getFirmAddressId(),
				CUST_SEC_MOBILE_NEW, null,	CUST_SEC_EMAIL,false, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,
				customerDetails.getCustomerId(),customerDetails.getCustomerId());
		
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsWithNewEmail(CustomerDetailsDTO customerDetails)
	{
		return new CustomerDetailsDTO(customerDetails.getCustomerId(), customerDetails.getFirstName(),null,
				customerDetails.getLastName(), CUST_DATE, customerDetails.getHomeAddressId(), CUST_MOBILE_NEW,CUST_PHONE_NUMBER, 
				customerDetails.getIsEmailVerified(),CUST_EMAIL_NEW,
				customerDetails.getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, customerDetails.getFirmAddressId(),
				CUST_SEC_MOBILE_NEW, null,	CUST_SEC_EMAIL,false, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,
				customerDetails.getCustomerId(),customerDetails.getCustomerId());
		
	}
	
	public static CustomerDetailsDTO standardCustomerDetailsWithNewHomeAddress(CustomerDetailsDTO customerDetails)
	{
		return new CustomerDetailsDTO(customerDetails.getCustomerId(), customerDetails.getFirstName(),null,
				customerDetails.getLastName(), CUST_DATE, standardAddressUpdated(), CUST_MOBILE_NEW,CUST_PHONE_NUMBER, 
				customerDetails.getIsEmailVerified(),CUST_EMAIL_NEW,
				customerDetails.getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, customerDetails.getFirmAddressId(),
				CUST_SEC_MOBILE_NEW, null,	CUST_SEC_EMAIL,false, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,
				customerDetails.getCustomerId(),customerDetails.getCustomerId());
		
	}
	
	
	
	public static String standardJsonCustomerDetails(CustomerDetailsDTO customerDetails)
	{
		System.out.println(gson.toJson(customerDetails));
		
		return gson.toJson(customerDetails);
	}
	
	public static String standardJsonCustomerDetailsAng(CustomerDetailsDTOAng customerDetails)
	{
		System.out.println(gson.toJson(customerDetails));
		
		return gson.toJson(customerDetails);
	}
	
	public static EntityIdTypeDTO standardEntityIdDTO(Long entityId,Integer entityType)
	{
		return new EntityIdTypeDTO(entityId,entityType);
	}
	
	public static String standardJsonEntityIdDTO(EntityIdTypeDTO entityIdDTO)
	{
		return gson.toJson(entityIdDTO);
	}
	
	
}
