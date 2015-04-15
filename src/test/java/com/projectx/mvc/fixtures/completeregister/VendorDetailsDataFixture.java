package com.projectx.mvc.fixtures.completeregister;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.mvc.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.domain.completeregister.UpdateEmailVerificationStatusDTO;
import com.projectx.rest.domain.completeregister.UpdateMobileVerificationStatusDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;

import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.mvc.fixtures.completeregister.AddressDataFixture.*;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.mvc.fixtures.quickregister.AuthenticationDetailsDataFixtures.*;


public class VendorDetailsDataFixture {

	public static Long VENDOR_ID=213L;
	
	public static String VENDER_FIRSTNAME="Ted";
	
	public static String VENDER_LASTNAME="Mosby";
	
	public static Date VENDOR_DATE=new Date();
	
	public static Address VENDOR_ADDRESS=standardAddress();
	
	public static Long VENDOR_MOBILE=8625867370L;
	
	public static Long VENDOR_SEC_MOBILE=8600007370L;
	
	public static Long VENDOR_PHONE_NUMBER=2000007370L;
	
	public static String VENDOR_FIRM_NAME="ABC";
	
	public static String VENDOR_EMAIL="tedmosby@gmail.com";
	
	public static Boolean VENDOR_STATUS_TRUE=true;
	
	public static Boolean VENDOR_STATUS_FALSE=false;
	
	public static String VENDOR_LANGUAGE="English";
	
	public static String VENDOR_UPDATEDBY="CUST_ONLINE";
	
	private static Gson gson=new Gson();
	
	public static QuickRegisterEntity standardEmailMobileVendorDTO()
	{
		return new QuickRegisterEntity(VENDER_FIRSTNAME,VENDER_LASTNAME,VENDOR_EMAIL,VENDOR_MOBILE,ADDRESS_PINCODE,ENTITY_TYPE_VENDOR,CUST_UPDATED_BY);
	}
	
	public static QuickRegisterDTO standardEmailMobileVendorQuick()
	{
		return new QuickRegisterDTO(VENDOR_ID, VENDER_FIRSTNAME, VENDER_LASTNAME, VENDOR_EMAIL, VENDOR_MOBILE, ADDRESS_PINCODE, ENTITY_TYPE_VENDOR, VENDOR_STATUS_FALSE, VENDOR_STATUS_FALSE, VENDOR_DATE, VENDOR_DATE, VENDOR_UPDATEDBY); 
		
	}

	public static QuickRegisterDTO standardEmailMobileCustomerVendor()
	{

		return new QuickRegisterDTO(VENDOR_ID, VENDER_FIRSTNAME, VENDER_LASTNAME, VENDOR_EMAIL, VENDOR_MOBILE, ADDRESS_PINCODE, ENTITY_TYPE_VENDOR, VENDOR_STATUS_FALSE, VENDOR_STATUS_FALSE, VENDOR_DATE, VENDOR_DATE, VENDOR_UPDATEDBY);
	}
	
	public static VendorDetailsDTO standardVendor()
	{
		return new VendorDetailsDTO(VENDOR_ID, VENDER_FIRSTNAME,null, VENDER_LASTNAME, VENDOR_DATE,null, VENDOR_ADDRESS,null, VENDOR_MOBILE,null, VENDOR_STATUS_FALSE,
				VENDOR_EMAIL,VENDOR_STATUS_FALSE, VENDOR_LANGUAGE,null,false, VENDOR_DATE, VENDOR_DATE, VENDOR_UPDATEDBY);
	}
	
	public static VendorDetailsDTO standardVendor(Long vendorId)
	{
		return new VendorDetailsDTO(vendorId, VENDER_FIRSTNAME,null, VENDER_LASTNAME, VENDOR_DATE,null, VENDOR_ADDRESS,null, VENDOR_MOBILE,null, VENDOR_STATUS_FALSE,
				VENDOR_EMAIL,VENDOR_STATUS_FALSE, VENDOR_LANGUAGE,null,false, VENDOR_DATE, VENDOR_DATE, VENDOR_UPDATEDBY);
	}
	
	public static VendorDetailsDTO standardVendorComplete(Long vendorId)
	{
		return new VendorDetailsDTO(vendorId, VENDER_FIRSTNAME,"A.", VENDER_LASTNAME, VENDOR_DATE,VENDOR_FIRM_NAME, VENDOR_ADDRESS,
				VENDOR_ADDRESS, VENDOR_MOBILE,VENDOR_PHONE_NUMBER, VENDOR_STATUS_FALSE,
				VENDOR_EMAIL,VENDOR_STATUS_FALSE, VENDOR_LANGUAGE,VENDOR_SEC_MOBILE,false, VENDOR_DATE, VENDOR_DATE, VENDOR_UPDATEDBY);
	}
	
	public static VendorDetailsDTO standardVendorCreatedFromQuickRegister()
	{
		return new VendorDetailsDTO(standardEmailMobileCustomerVendor().getCustomerId(), standardEmailMobileCustomerVendor().getFirstName(),null,
				standardEmailMobileCustomerVendor().getLastName(), null,null, null,null, standardEmailMobileCustomerVendor().getMobile(),null, 
				standardEmailMobileCustomerVendor().getIsMobileVerified(),standardEmailMobileCustomerVendor().getEmail(),standardEmailMobileCustomerVendor().getIsEmailVerified(),
				null,null,false, new Date(), new Date(), CUST_UPDATED_BY);
	}
	
	
	
	public static VendorDetailsDTO standardVendorCreatedFromQuickRegister(Long vendorId)
	{
		return new VendorDetailsDTO(vendorId, standardEmailMobileCustomerVendor().getFirstName(),null,
				standardEmailMobileCustomerVendor().getLastName(), null,null, null,null, standardEmailMobileCustomerVendor().getMobile(),null, 
				standardEmailMobileCustomerVendor().getIsMobileVerified(),standardEmailMobileCustomerVendor().getEmail(),standardEmailMobileCustomerVendor().getIsEmailVerified(),
				null,null,null, new Date(), new Date(), CUST_UPDATED_BY);
	}
	

	
	public static VendorDetailsDTO standardVendor(VendorDetailsDTO vendorDetails)
	{
		return new VendorDetailsDTO(vendorDetails.getVendorId(), vendorDetails.getFirstName(),null,vendorDetails.getLastName(),
				new Date(),null, standardAddress(),null, vendorDetails.getMobile(),null,vendorDetails.getIsMobileVerified(),vendorDetails.getEmail(),
				vendorDetails.getIsEmailVerified(),VENDOR_LANGUAGE,null,false, vendorDetails.getInsertTime(), new Date(), vendorDetails.getUpdatedBy());
	}
	
	
	public static VendorDetailsDTO standardVendorUpdatedFirstLastName()
	{
		return new VendorDetailsDTO(VENDOR_ID, "Updated",null, "Updated", VENDOR_DATE,null, VENDOR_ADDRESS,null, VENDOR_MOBILE,null, VENDOR_STATUS_FALSE,
				VENDOR_EMAIL,VENDOR_STATUS_FALSE, VENDOR_LANGUAGE,null,false, VENDOR_DATE, VENDOR_DATE, VENDOR_UPDATEDBY);
	}
	
	public static AuthenticationDetailsKey standardAuthenticationDetailsKeyVendor()
	{
		return new AuthenticationDetailsKey(VENDOR_ID, ENTITY_TYPE_VENDOR);
	}

	public static AuthenticationDetailsDTO standardCustomerEmailMobileAuthenticationDetailsVendor()
	{
		
		return new  AuthenticationDetailsDTO(standardAuthenticationKey(), "completeName",  CUST_PASSWORD_DEFAULT, false);
	}
	
	public static UpdateMobileVerificationStatusDTO standardMobileUpdateVerificationStatusDTO()
	{
		return new UpdateMobileVerificationStatusDTO(VENDOR_ID,VENDOR_MOBILE, VENDOR_STATUS_TRUE);
	}
	
	public static UpdateEmailVerificationStatusDTO standardUpdateEmailVerificationStatusDTO()
	{
		return new UpdateEmailVerificationStatusDTO(VENDOR_ID,VENDOR_EMAIL, VENDOR_STATUS_TRUE);
	}
	
	public static String standardJsonVendor(VendorDetailsDTO vendorDetails)
	{
		System.out.println(gson.toJson(vendorDetails));
		
		return gson.toJson(vendorDetails);
	}
	
	public static String standardJsonUpdateVerificationStatus(UpdateMobileVerificationStatusDTO vendorDetails)
	{
		System.out.println(gson.toJson(vendorDetails));
		
		return gson.toJson(vendorDetails);
	}
	
	
}
