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
	
	public static String VENDOR_EMAIL="tedmosby@gmail.com";
	
	public static Boolean VENDOR_STATUS_TRUE=true;
	
	public static Boolean VENDOR_STATUS_FALSE=false;
	
	public static String VENDOR_LANGUAGE="English";
	
	public static String VENDOR_UPDATEDBY="CUST_ONLINE";
	
	private static Gson gson=new Gson();
	
	public static QuickRegisterEntity standardEmailMobileVendorDTO()
	{
		return new QuickRegisterEntity(VENDER_FIRSTNAME,VENDER_LASTNAME,VENDOR_EMAIL,VENDOR_MOBILE,ADDRESS_PINCODE,ENTITY_TYPE_VENDOR);
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
		return new VendorDetailsDTO(VENDOR_ID, VENDER_FIRSTNAME, VENDER_LASTNAME, VENDOR_DATE, VENDOR_ADDRESS, VENDOR_MOBILE, VENDOR_STATUS_FALSE,
				VENDOR_EMAIL,VENDOR_STATUS_FALSE, VENDOR_LANGUAGE, VENDOR_DATE, VENDOR_DATE, VENDOR_UPDATEDBY);
	}
	
	public static VendorDetailsDTO standardVendor(Long vendorId)
	{
		return new VendorDetailsDTO(vendorId, VENDER_FIRSTNAME, VENDER_LASTNAME, VENDOR_DATE, VENDOR_ADDRESS, VENDOR_MOBILE, VENDOR_STATUS_FALSE,
				VENDOR_EMAIL,VENDOR_STATUS_FALSE, VENDOR_LANGUAGE, VENDOR_DATE, VENDOR_DATE, VENDOR_UPDATEDBY);
	}
	
	public static VendorDetailsDTO standardVendorCreatedFromQuickRegister()
	{
		return new VendorDetailsDTO(standardEmailMobileCustomerVendor().getCustomerId(), standardEmailMobileCustomerVendor().getFirstName(),
				standardEmailMobileCustomerVendor().getLastName(), null, null, standardEmailMobileCustomerVendor().getMobile(), 
				standardEmailMobileCustomerVendor().getIsMobileVerified(),standardEmailMobileCustomerVendor().getEmail(),standardEmailMobileCustomerVendor().getIsEmailVerified(),
				null, standardEmailMobileCustomerVendor().getInsertTime(), new Date(), standardEmailMobileCustomerVendor().getUpdatedBy());
	}
	
	
	
	public static VendorDetailsDTO standardVendorCreatedFromQuickRegister(Long vendorId)
	{
		return new VendorDetailsDTO(vendorId, standardEmailMobileCustomerVendor().getFirstName(),
				standardEmailMobileCustomerVendor().getLastName(), null, null, standardEmailMobileCustomerVendor().getMobile(), 
				standardEmailMobileCustomerVendor().getIsMobileVerified(),standardEmailMobileCustomerVendor().getEmail(),standardEmailMobileCustomerVendor().getIsEmailVerified(),
				null, standardEmailMobileCustomerVendor().getInsertTime(), new Date(), standardEmailMobileCustomerVendor().getUpdatedBy());
	}
	

	
	public static VendorDetailsDTO standardVendor(VendorDetailsDTO vendorDetails)
	{
		return new VendorDetailsDTO(vendorDetails.getVendorId(), vendorDetails.getFirstName(),vendorDetails.getLastName(),
				new Date(), standardAddress(), vendorDetails.getMobile(),vendorDetails.getIsMobileVerified(),vendorDetails.getEmail(),
				vendorDetails.getIsEmailVerified(),VENDOR_LANGUAGE, vendorDetails.getInsertTime(), new Date(), vendorDetails.getUpdatedBy());
	}
	
	
	public static VendorDetailsDTO standardVendorUpdatedFirstLastName()
	{
		return new VendorDetailsDTO(VENDOR_ID, "Updated", "Updated", VENDOR_DATE, VENDOR_ADDRESS, VENDOR_MOBILE, VENDOR_STATUS_FALSE,
				VENDOR_EMAIL,VENDOR_STATUS_FALSE, VENDOR_LANGUAGE, VENDOR_DATE, VENDOR_DATE, VENDOR_UPDATEDBY);
	}
	
	public static AuthenticationDetailsKey standardAuthenticationDetailsKeyVendor()
	{
		return new AuthenticationDetailsKey(VENDOR_ID, ENTITY_TYPE_VENDOR);
	}

	public static AuthenticationDetailsDTO standardCustomerEmailMobileAuthenticationDetailsVendor()
	{
		
		return new AuthenticationDetailsDTO(standardAuthenticationDetailsKeyVendor(), VENDOR_EMAIL, VENDOR_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT, CUST_EMAILHASH, 0, 0,new Date(),new Date(),CUST_UPDATED_BY);
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
