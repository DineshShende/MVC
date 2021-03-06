package com.projectx.mvc.fixtures.completeregister;

import java.util.Date;

import com.projectx.rest.domain.completeregister.Address;



public class AddressDataFixture {

	public static Long ADDRESS_ID=42L;

	public static Integer ADDRESS_CUST_TYPE=1;
	
	public static String ADDRESS_LINE="AT-GHADGE WASTI PO-SAWAL";
	
	public static String ADDRESS_CITY="Baramati";
	
	public static String ADDRESS_DIST="Pune";
	
	public static String ADDRESS_STATE="Maharashtra";
	
	public static Integer ADDRESS_PINCODE=413133;
	
	public static Date ADDRESS_DATE=new Date();
	
	public static Integer ADDRESS_UPDATED_BY=1;
	
	
	
	public static Address standardAddress()
	{
		return new Address(ADDRESS_CUST_TYPE, ADDRESS_LINE, ADDRESS_CITY, ADDRESS_DIST, ADDRESS_STATE, ADDRESS_PINCODE, ADDRESS_DATE, ADDRESS_DATE, 
				ADDRESS_UPDATED_BY,ADDRESS_UPDATED_BY,ADDRESS_ID,ADDRESS_ID);
	}
	
	public static Address standardAddress(Integer entityType)
	{
		return new Address(entityType, ADDRESS_LINE, ADDRESS_CITY, ADDRESS_DIST, ADDRESS_STATE, ADDRESS_PINCODE, ADDRESS_DATE, ADDRESS_DATE, 
				ADDRESS_UPDATED_BY,ADDRESS_UPDATED_BY,ADDRESS_ID,ADDRESS_ID);
	}
	
	public static Address standardAddressDefault(Integer customerType)
	{
		return new Address(customerType, "addressLine", "city", "district", "state", ADDRESS_PINCODE, ADDRESS_DATE, ADDRESS_DATE, 
				ADDRESS_UPDATED_BY,ADDRESS_UPDATED_BY,ADDRESS_ID,ADDRESS_ID);
	}
	
	public static Address standardAddressWithOutMetaData()
	{
		return new Address(ADDRESS_CUST_TYPE, ADDRESS_LINE, ADDRESS_CITY, ADDRESS_DIST, ADDRESS_STATE, ADDRESS_PINCODE, ADDRESS_DATE, ADDRESS_DATE,
				ADDRESS_UPDATED_BY,ADDRESS_UPDATED_BY,ADDRESS_ID,ADDRESS_ID);
	}
	
	public static Address standardAddressWithId()
	{
		return new Address(ADDRESS_ID,ADDRESS_CUST_TYPE, ADDRESS_LINE, ADDRESS_CITY, ADDRESS_DIST, ADDRESS_STATE, ADDRESS_PINCODE,
				ADDRESS_DATE, ADDRESS_DATE,ADDRESS_UPDATED_BY,ADDRESS_UPDATED_BY,ADDRESS_ID,ADDRESS_ID);
	}
	
	public static Address standardAddressUpdated()
	{
		return new Address(ADDRESS_CUST_TYPE, "Updated", "Updated", "Updated", "Updated", 111111, ADDRESS_DATE, ADDRESS_DATE,
				ADDRESS_UPDATED_BY,ADDRESS_UPDATED_BY,ADDRESS_ID,ADDRESS_ID);
	}
}
