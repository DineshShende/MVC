package com.projectx.mvc.fixtures.request;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.mvc.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByCustomerDTO;



public class FreightRequestByCustomerDataFixture {

public static Long CREQ_REQ_ID=414L;
	
	public static Integer CREQ_SOURCE=411045;
	
	public static Integer CREQ_DEST=413102;
	
	public static Date CREQ_PICK_UP_TIME=new Date();
	
	public static Integer CREQ_NOOFVEHICLE=1;
	
	public static Boolean CREQ_BOOL_FALSE=false;
	
	public static Boolean CREQ_BOOL_TRUE=true;
	
	public static Integer CREQ_CAPACITY=40;

	public static String CREQ_BODYTYPE_OPEN="Open";
	
	public static String CREQ_BODYTYPE_CLOSED="Closed";
	
	public static Integer CREQ_GROSS_WEIGHT=40;
	
	public static Integer CREQ_LENGTH=100;
	
	public static Integer CREQ_WIDTH=40;
	
	public static Integer CREQ_HEIGHT=10;
	
	public static String CREQ_VEHICLE_BRAND="Tata Tempo";
	
	public static String CREQ_VEHICLE_MODEL="407";
	
	public static String CREQ_COMMITITY="Fertiliser";
	
	public static String CREQ_PICKUP_TIME="1:00PM";
	
	public static Date CREQ_DATE=new Date();
	
	public static Long CREQ_CUST_ID=212L;
	
	public static String CREQ_STATUS="NEW";
	
	public static String CREQ_UPDATED_BY="CUST_ONLINE";
	
	private static Gson gson=new Gson();
	
	public static FreightRequestByCustomerDTO standardFreightRequestByCustomerFullTruckLoad()
	{
				
		return new FreightRequestByCustomerDTO(1L,CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_TRUE, CREQ_BOOL_FALSE,
				100,CREQ_BODYTYPE_OPEN,null , null, null,null, CREQ_VEHICLE_BRAND, CREQ_VEHICLE_MODEL, CREQ_COMMITITY,
				CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY);
	}
	
	public static FreightRequestByCustomerDTO standardFreightRequestByCustomerFullTruckLoadError()
	{
		return new FreightRequestByCustomerDTO(1L,null, null, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_TRUE, CREQ_BOOL_FALSE,
				100,CREQ_BODYTYPE_OPEN,null , null, null,null, CREQ_VEHICLE_BRAND, CREQ_VEHICLE_MODEL, CREQ_COMMITITY,
				CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY);
	}
	
	public static FreightRequestByCustomerDTO standardFreightRequestByCustomerFullTruckLoad110()
	{
		return new FreightRequestByCustomerDTO(1L,CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_TRUE, CREQ_BOOL_FALSE,
				110,CREQ_BODYTYPE_OPEN,null , null, null,null, CREQ_VEHICLE_BRAND, CREQ_VEHICLE_MODEL, CREQ_COMMITITY,
				CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY);
	}
	
	public static FreightRequestByCustomerDTO standardFreightRequestByCustomerFullTruckLoadOpenTataReq()
	{
		return new FreightRequestByCustomerDTO(1L,CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_TRUE, CREQ_BOOL_FALSE,
				CREQ_CAPACITY,CREQ_BODYTYPE_OPEN,null , null, null,null, CREQ_VEHICLE_BRAND, "307", CREQ_COMMITITY,
				CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY);
	}
	
	public static FreightRequestByCustomerDTO standardFreightRequestByCustomerFullTruckLoadClosedAcerReq()
	{
		return new FreightRequestByCustomerDTO(1L,CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_TRUE, CREQ_BOOL_FALSE,
				CREQ_CAPACITY,CREQ_BODYTYPE_CLOSED,null , null, null,null, "Acer", "507", CREQ_COMMITITY,
				CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY);
	}
	
	
	public static FreightRequestByCustomerDTO standardFreightRequestByCustomerFullTruckLoadUpdated()
	{
		return new FreightRequestByCustomerDTO(1L,CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_TRUE, CREQ_BOOL_FALSE,
				CREQ_CAPACITY,CREQ_BODYTYPE_CLOSED, null, null, null,null, CREQ_VEHICLE_BRAND, CREQ_VEHICLE_MODEL, CREQ_COMMITITY,
				CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY);
	}
	
	
	public static FreightRequestByCustomerDTO standardFreightRequestByCustomerLessThanTruckLoad()
	{
		return new FreightRequestByCustomerDTO(1L,CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_CLOSED, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	10, CREQ_VEHICLE_BRAND, CREQ_VEHICLE_MODEL, CREQ_COMMITITY,CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,
				CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY);
	}
	
	public static FreightRequestByCustomerDTO standardFreightRequestByCustomerLessThanTruckLoad15()
	{
		return new FreightRequestByCustomerDTO(1L,CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_CLOSED, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	15, CREQ_VEHICLE_BRAND, CREQ_VEHICLE_MODEL, CREQ_COMMITITY, 
				CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY);
	}
	
	public static FreightRequestByCustomerDTO standardFreightRequestByCustomerLessThanTruckLoadOpenNoBrandAndNoModel()
	{
		return new FreightRequestByCustomerDTO(1L,CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_OPEN, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	CREQ_HEIGHT, "", "", CREQ_COMMITITY,CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,
				CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY);
	}
	
	public static FreightRequestByCustomerDTO standardFreightRequestByCustomerLessThanTruckLoadOpenNoBrand()
	{
		return new FreightRequestByCustomerDTO(1L,CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_OPEN, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	CREQ_HEIGHT, "", "307", CREQ_COMMITITY, CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,
				CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY);
	}
	
	public static FreightRequestByCustomerDTO standardFreightRequestByCustomerLessThanTruckLoadOpenNoModel()
	{
		return new FreightRequestByCustomerDTO(1L,CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_OPEN, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	CREQ_HEIGHT, CREQ_VEHICLE_BRAND, "", CREQ_COMMITITY, CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,
				CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY);
	}
	
	public static FreightRequestByCustomerDTO standardFreightRequestByCustomerLessThanTruckLoadOpenTata()
	{
		return new FreightRequestByCustomerDTO(1L,CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_OPEN, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	CREQ_HEIGHT, CREQ_VEHICLE_BRAND, CREQ_VEHICLE_MODEL, CREQ_COMMITITY, CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,
				CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY);
	}
	
	public static FreightRequestByCustomerDTO standardFreightRequestByCustomerLessThanTruckLoadOpenAcer()
	{
		return new FreightRequestByCustomerDTO(1L,CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_OPEN, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	CREQ_HEIGHT, "Acer", "507", CREQ_COMMITITY, CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,
				CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY);
	}
	
	public static String standardJsonFreightRequestByCustomer(FreightRequestByCustomer freightRequestByCustomer)
	{
		System.out.println(gson.toJson(freightRequestByCustomer));
		
		return gson.toJson(freightRequestByCustomer);
	}
	
}
