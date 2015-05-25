package com.projectx.mvc.fixtures.completeregister;


import static com.projectx.mvc.fixtures.completeregister.AddressDataFixture.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.projectx.mvc.domain.completeregister.DriverSimplified;
import com.projectx.mvc.domain.completeregister.L1DriverCompleteRegistration;
import com.projectx.rest.domain.completeregister.DriverDetailsDTO;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.completeregister.EntityIdTypeDTO;
import com.projectx.rest.domain.completeregister.UpdateMobileVerificationStatusDTO;


public class DriverDetailsDataFixtures {
	
	
	public static String DRIVER_FIRST_NAME="ARUN";
	
	public static String DRIVER_MIDDLE_NAME="M.";
	
	public static String DRIVER_LAST_NAME="GUPTA";

	public static String DRIVER_FIRST_NAME_OTHER="John";
	
	public static String DRIVER_MIDDLE_NAME_OTHER="M.";
	
	public static String DRIVER_LAST_NAME_OTHER="Snow";

	
	
	public static Date DRIVER_DATE=new Date();
	
	public static String DRIVER_BLOOD_GROUP="A+";
	
	public static String DRIVER_BLOOD_GROUP_OTHER="B+";
	
	public static Long DRIVER_MOBILE=9876542312L;
	
	public static Long DRIVER_MOBILE_OTHER=9876000000L;
	
	public static Long DRIVER_MOBILE_UPDATED=9876502312L;
	
	public static Long DRIVER_HOME_NUMBER=9896592312L;
	
	public static String DRIVER_LICENCE_NUMBER="MH42A6543";
	
	public static String DRIVER_LICENCE_NUMBER_OTHER="MH12A6543";
	
	public static Boolean DRIVER_BOOLEAN_TRUE=true;
	
	public static Boolean DRIVER_BOOLEAN_FALSE=false;
	
	public static String DRIVER_LANGUAGE="TELGU";
	
	public static Long DRIVER_VENDOR_ID=213L;
	
	public static Integer DRIVER_UPDATED_BY=1;
	
	private static Gson gson=new Gson();
	

	public static List<String> covList()
	{
		List<String> covList=new ArrayList<String>();
		
		covList.add("MCWG");
		covList.add("LMV");
		
		return covList;
	}
	
	
	public static DriverDetailsDTO standardDriverDetails()
	{
		return new DriverDetailsDTO(1L, DRIVER_FIRST_NAME, DRIVER_MIDDLE_NAME, DRIVER_LAST_NAME, DRIVER_DATE, DRIVER_BLOOD_GROUP, standardAddress(),
				DRIVER_MOBILE,DRIVER_BOOLEAN_FALSE, DRIVER_HOME_NUMBER, DRIVER_LICENCE_NUMBER, DRIVER_DATE, DRIVER_DATE, DRIVER_BOOLEAN_FALSE,
				DRIVER_BOOLEAN_FALSE, DRIVER_LANGUAGE,DRIVER_DATE,DRIVER_DATE,covList(),DRIVER_BOOLEAN_TRUE,DRIVER_BOOLEAN_FALSE,
				DRIVER_VENDOR_ID,false, DRIVER_DATE, DRIVER_DATE, 
				DRIVER_UPDATED_BY,DRIVER_UPDATED_BY,1L,1L);
		
		
	}
	
	public static DriverDetailsDTO standardDriverDetails(Long driverId,Integer entityType)
	{
		return new DriverDetailsDTO(driverId, DRIVER_FIRST_NAME, DRIVER_MIDDLE_NAME, DRIVER_LAST_NAME, DRIVER_DATE, DRIVER_BLOOD_GROUP, standardAddress(entityType),
				DRIVER_MOBILE,DRIVER_BOOLEAN_FALSE, DRIVER_HOME_NUMBER, DRIVER_LICENCE_NUMBER, DRIVER_DATE, DRIVER_DATE, DRIVER_BOOLEAN_FALSE,
				DRIVER_BOOLEAN_FALSE, DRIVER_LANGUAGE,DRIVER_DATE,DRIVER_DATE,covList(),DRIVER_BOOLEAN_TRUE,DRIVER_BOOLEAN_FALSE,
				DRIVER_VENDOR_ID,false, DRIVER_DATE, DRIVER_DATE,DRIVER_UPDATED_BY,DRIVER_UPDATED_BY,1L,1L);
		
		
	}
	
	public static DriverDetailsDTO standardDriverDetailsWithError()
	{
		return new DriverDetailsDTO(1L, DRIVER_FIRST_NAME, DRIVER_MIDDLE_NAME, DRIVER_LAST_NAME, DRIVER_DATE, DRIVER_BLOOD_GROUP, standardAddress(),
				null,DRIVER_BOOLEAN_FALSE, DRIVER_HOME_NUMBER, DRIVER_LICENCE_NUMBER, DRIVER_DATE, DRIVER_DATE, DRIVER_BOOLEAN_FALSE,
				DRIVER_BOOLEAN_FALSE, DRIVER_LANGUAGE,DRIVER_DATE,DRIVER_DATE,covList(),DRIVER_BOOLEAN_TRUE,DRIVER_BOOLEAN_FALSE, DRIVER_VENDOR_ID,false, DRIVER_DATE, DRIVER_DATE,
				DRIVER_UPDATED_BY,DRIVER_UPDATED_BY,1L,1L);
		
		
	}
	
	public static DriverDetailsDTO standardDriverDetailsOther()
	{
		return new DriverDetailsDTO(1L, DRIVER_FIRST_NAME_OTHER, DRIVER_MIDDLE_NAME_OTHER, DRIVER_LAST_NAME_OTHER, DRIVER_DATE, DRIVER_BLOOD_GROUP, standardAddress(),
				DRIVER_MOBILE_OTHER,DRIVER_BOOLEAN_FALSE, DRIVER_HOME_NUMBER, DRIVER_LICENCE_NUMBER_OTHER, DRIVER_DATE, DRIVER_DATE, DRIVER_BOOLEAN_FALSE,
				DRIVER_BOOLEAN_FALSE, DRIVER_LANGUAGE,DRIVER_DATE,DRIVER_DATE,covList(),DRIVER_BOOLEAN_TRUE,DRIVER_BOOLEAN_FALSE, DRIVER_VENDOR_ID,false, DRIVER_DATE, DRIVER_DATE, 
				DRIVER_UPDATED_BY,DRIVER_UPDATED_BY,1L,1L);
		
		
	}
	
	public static DriverDetailsDTO standardDriverDetailsNewMobileAndFirstName(Long driverId)
	{
		return new DriverDetailsDTO(driverId, "FIRST_UPDATE", DRIVER_MIDDLE_NAME, DRIVER_LAST_NAME, DRIVER_DATE, DRIVER_BLOOD_GROUP, standardAddress(),
				DRIVER_MOBILE_UPDATED,DRIVER_BOOLEAN_FALSE, DRIVER_HOME_NUMBER, DRIVER_LICENCE_NUMBER, DRIVER_DATE, DRIVER_DATE, DRIVER_BOOLEAN_FALSE,
				DRIVER_BOOLEAN_FALSE, DRIVER_LANGUAGE,DRIVER_DATE,DRIVER_DATE,covList(),DRIVER_BOOLEAN_TRUE,DRIVER_BOOLEAN_FALSE, DRIVER_VENDOR_ID,false, DRIVER_DATE, DRIVER_DATE, 
				DRIVER_UPDATED_BY,DRIVER_UPDATED_BY,1L,1L);
		
		
	}
	
	
	
	public static L1DriverCompleteRegistration standardL1DriverCompleteRegistration(Long driverId)
	{
		return new L1DriverCompleteRegistration(driverId, DRIVER_FIRST_NAME, DRIVER_MIDDLE_NAME, DRIVER_LAST_NAME, DRIVER_DATE,
				standardAddress().getAddressLine(),standardAddress().getCity(),standardAddress().getDistrict(),
				standardAddress().getState(),standardAddress().getPincode(), DRIVER_LICENCE_NUMBER, DRIVER_DATE, DRIVER_DATE,
				covList(), DRIVER_BOOLEAN_FALSE );
	}
	
	public static DriverSimplified standardDriverSimplified()
	{
		return new DriverSimplified(DRIVER_BLOOD_GROUP, DRIVER_MOBILE, DRIVER_HOME_NUMBER, DRIVER_DATE, DRIVER_DATE, DRIVER_BOOLEAN_FALSE,
				DRIVER_BOOLEAN_FALSE, DRIVER_LANGUAGE, DRIVER_VENDOR_ID, DRIVER_UPDATED_BY, DRIVER_VENDOR_ID);
	}
	
	
	
	
	public static DriverSimplified standardDriverSimplifiedOther(Long driverId)
	{
		return new DriverSimplified(driverId,DRIVER_BLOOD_GROUP_OTHER, DRIVER_MOBILE, DRIVER_HOME_NUMBER, DRIVER_DATE, DRIVER_DATE, DRIVER_BOOLEAN_FALSE,
				DRIVER_BOOLEAN_FALSE, DRIVER_LANGUAGE, DRIVER_VENDOR_ID, DRIVER_UPDATED_BY, DRIVER_VENDOR_ID);
	}
	
	public static String standardDriverSimplifiedJson(DriverSimplified dto)
	{
		System.out.println(gson.toJson(dto));
		
		return gson.toJson(dto);
		
	}
	
	public static String standardL1DriverCompleteRegistrationJson(L1DriverCompleteRegistration dto)
	{
		System.out.println(gson.toJson(dto));
		
		return gson.toJson(dto);
	}
	
	public static String standardDriverJson(DriverDetailsDTO driverDetails)
	{
		System.out.println(gson.toJson(driverDetails));
		
		return gson.toJson(driverDetails);
	}
	
	
	
	public static String standardUpdateMobileVerificationStatusDTOJson(UpdateMobileVerificationStatusDTO dto)
	{
		System.out.println(gson.toJson(dto));
		
		return gson.toJson(dto);
	}
	
	public static String standardEntityIdDTO(EntityIdDTO entityIdDTO)
	{
		return gson.toJson(entityIdDTO);
	}

	public static String standardEntityIdTypeDTO(EntityIdTypeDTO entityIdDTO)
	{
		return gson.toJson(entityIdDTO);
	}
	
}
