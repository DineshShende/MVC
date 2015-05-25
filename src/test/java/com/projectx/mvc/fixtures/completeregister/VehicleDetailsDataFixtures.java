package com.projectx.mvc.fixtures.completeregister;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.projectx.mvc.domain.completeregister.Commodity;
import com.projectx.mvc.domain.completeregister.DLClassOfVehicle;
import com.projectx.mvc.domain.completeregister.L1VehicleCompleteRegistration;
import com.projectx.mvc.domain.completeregister.VehicleBodyType;
import com.projectx.mvc.domain.completeregister.VehicleBrand;
import com.projectx.mvc.domain.completeregister.VehicleModel;
import com.projectx.mvc.domain.completeregister.VehiclePermitType;
import com.projectx.mvc.domain.completeregister.VehicleSimplified;
import com.projectx.mvc.domain.completeregister.VehicleType;
import com.projectx.rest.domain.completeregister.VehicleBrandDetails;
import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;
import com.projectx.rest.domain.completeregister.VehicleTypeDetails;


public class VehicleDetailsDataFixtures {
	
	private static Integer ACTOR_ENTITY_SELF_WEB=1;
	
	public static String VEHICLE_TYPE_NAME="VehcileTypeName";
	
	public static Long VEHICLE_TYPE_ID=235L;
	
	public static Long VEHICLE_BRAND_ID=267L;
	
	public static String VEHICLE_BRAND_NAME="Tata tempo";
	
	public static String VEHICLE_MODEL_NUMBER="407";
	
	public static Long VEHICLE_ID=269L;
	
	public static String VEHICLE_OWNER_FIRST_NAME="Abraham";
	
	public static String VEHICLE_OWNER_MIDDLE_NAME="Benjamin";
	
	public static String VEHICLE_OWNER_LAST_NAME="Deviliers";
	
	public static String VEHICLE_OWNER_FIRST_NAME_OTHER="John";
	
	public static String VEHICLE_OWNER_MIDDLE_NAME_OTHER="Hamish";
	
	public static String VEHICLE_OWNER_LAST_NAME_OTHER="Watson";
	
	public static String VEHICLE_BODY_TYPE_OPEN="OPEN";
	
	public static String VEHICLE_BODY_TYPE_CLOSED="CLOSED";
	
	public static Boolean VEHICLE_BOOLEAN_TRUE=true;
	
	public static String VEHICLE_REGISTRATION_NUMBER="MH12HG4563";
	
	public static String VEHICLE_CHASIS_NUMBER="MH12HG4563JDFR634";
	
	public static String VEHICLE_REGISTRATION_NUMBER_OTHER="MH14HG4588";
	
	public static String VEHICLE_CHASIS_NUMBER_OTHER="MH12HG456DFKJDFR634";
	
	public static Integer VEHICLE_LOAD_CAPACITY=100;
	
	public static Integer VEHICLE_NOOF_WHEELS=8;
	
	public static String VEHICLE_PERMIT_TYPE_NATIONAL="NATIONAL";
	
	public static String VEHICLE_PERMIT_TYPE_STATE="STATE";
	
	public static String VEHICLE_INSURANCE_NUMBER="ADRF3442537JD";
	
	public static String VEHICLE_INSURANCE_COMPANY="LIC";
	
	public static Integer VEHICLE_LENGTH=100;
	
	public static Integer VEHICLE_HEIGHT=10;
	
	public static Integer VEHICLE_WIDTH=40;
	
	public static Long VEHICLE_VENDOR_ID=213L;
	
	public static Date VEHCLE_DATE=new Date();
	
	public static Integer VEHICLE_UPDATED_BY=1;
	
	private static Gson gson=new Gson();
	
public static VehicleBrand VEHICLE_BRAND_NAME_OBJ=new VehicleBrand(VEHICLE_BRAND_ID,"Tata tempo", VEHCLE_DATE,VEHCLE_DATE,ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,VEHICLE_VENDOR_ID,VEHICLE_VENDOR_ID);
	
	public static VehicleBrand VEHICLE_BRAND_NAME_NEW_OBJ=new VehicleBrand(VEHICLE_BRAND_ID,"Tata Tempo", VEHCLE_DATE,VEHCLE_DATE,ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,VEHICLE_VENDOR_ID,VEHICLE_VENDOR_ID);
	
	public static VehicleBrand VEHICLE_BRAND_NAME_EICHER_OBJ=new VehicleBrand(VEHICLE_BRAND_ID,"EICHER", VEHCLE_DATE,VEHCLE_DATE,ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,VEHICLE_VENDOR_ID,VEHICLE_VENDOR_ID);
	
	public static VehicleBodyType VEHICLE_BODY_TYPE_OPEN_OBJ=new VehicleBodyType(VEHICLE_BRAND_ID,"OPEN",VEHCLE_DATE,VEHCLE_DATE,ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,VEHICLE_VENDOR_ID,VEHICLE_VENDOR_ID);
	
	public static VehicleBodyType VEHICLE_BODY_TYPE_CLOSED_OBJ=new VehicleBodyType(VEHICLE_BRAND_ID,"CLOSED",VEHCLE_DATE,VEHCLE_DATE,ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,VEHICLE_VENDOR_ID,VEHICLE_VENDOR_ID);;
	
	public static VehiclePermitType VEHICLE_PERMIT_TYPE_NATIONAL_OBJ=new VehiclePermitType(VEHICLE_BRAND_ID,"NATIONAL",VEHCLE_DATE,VEHCLE_DATE,ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,VEHICLE_VENDOR_ID,VEHICLE_VENDOR_ID);
	
	public static VehiclePermitType VEHICLE_PERMIT_TYPE_STATE_OBJ=new VehiclePermitType(VEHICLE_BRAND_ID,"STATE",VEHCLE_DATE,VEHCLE_DATE,ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,VEHICLE_VENDOR_ID,VEHICLE_VENDOR_ID);;
	
	public static DLClassOfVehicle standardDlClassOfVehicle()
	{
		return new DLClassOfVehicle(1L, "MCWG", VEHCLE_DATE,VEHCLE_DATE,ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,VEHICLE_VENDOR_ID,VEHICLE_VENDOR_ID);
	}
	
	public static Commodity standardCommodity1()
	{
		return new Commodity(1L,"Wheat",VEHCLE_DATE,VEHCLE_DATE,ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,VEHICLE_VENDOR_ID,VEHICLE_VENDOR_ID);
	}
	
	public static Commodity standardCommodity2()
	{
		return new Commodity(2L,"Grain",VEHCLE_DATE,VEHCLE_DATE,ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,VEHICLE_VENDOR_ID,VEHICLE_VENDOR_ID);
	}
	
	public static Commodity standardCommodity3()
	{
		return new Commodity(3L,"Cement",VEHCLE_DATE,VEHCLE_DATE,ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,VEHICLE_VENDOR_ID,VEHICLE_VENDOR_ID);
	}
	
	public static Commodity standardCommodity4()
	{
		return new Commodity(4L,"Fertiliser",VEHCLE_DATE,VEHCLE_DATE,ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,VEHICLE_VENDOR_ID,VEHICLE_VENDOR_ID);
	}
	
	public static VehicleType standVehicleType()
	{
		return new VehicleType(VEHICLE_BRAND_ID,VEHICLE_TYPE_NAME,VEHCLE_DATE,VEHCLE_DATE,ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,VEHICLE_VENDOR_ID,VEHICLE_VENDOR_ID);
	}
	
	
	
	
	public static VehicleModel standardModelBrandDetails()
	{
		
		return new VehicleModel(VEHICLE_BRAND_ID,VEHICLE_BRAND_NAME_NEW_OBJ, standVehicleType(), VEHICLE_MODEL_NUMBER,"photo".getBytes(),
				VEHCLE_DATE,VEHCLE_DATE,ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,VEHICLE_VENDOR_ID,VEHICLE_VENDOR_ID);
		
	}
	
	public static VehicleModel standardVehicleBrandDetails(VehicleBrand vehicleBodyType,VehicleType vehicleType)
	{
		
		return new VehicleModel(VEHICLE_BRAND_ID,vehicleBodyType, vehicleType, VEHICLE_MODEL_NUMBER,"photo".getBytes(),
				VEHCLE_DATE,VEHCLE_DATE,ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,VEHICLE_VENDOR_ID,VEHICLE_VENDOR_ID);
		
	}
	
	public static VehicleModel standardVehicleModelDetails307()
	{
		return new VehicleModel(VEHICLE_BRAND_ID,VEHICLE_BRAND_NAME_NEW_OBJ, standVehicleType(),"307","photo".getBytes(),
				VEHCLE_DATE,VEHCLE_DATE,ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,VEHICLE_VENDOR_ID,VEHICLE_VENDOR_ID);
		
		
		
	}
	
	public static VehicleModel standardVehicleModelDetailsAcer()
	{
		return new VehicleModel(VEHICLE_BRAND_ID,VEHICLE_BRAND_NAME_EICHER_OBJ, standVehicleType(),"507","photo".getBytes(),
				VEHCLE_DATE,VEHCLE_DATE,ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,VEHICLE_VENDOR_ID,VEHICLE_VENDOR_ID);
		
		
		
	}
	
	
	
	public static VehicleTypeDetails standVehicleTypeDetails()
	{
		return new VehicleTypeDetails(VEHICLE_TYPE_ID, VEHICLE_TYPE_NAME);
	}
	
	public static VehicleBrandDetails standardVehicleBrandDetails()
	{
		return new VehicleBrandDetails(VEHICLE_BRAND_ID, standVehicleTypeDetails(), VEHICLE_BRAND_NAME, VEHICLE_MODEL_NUMBER);
		
	}
	
	public static VehicleBrandDetails standardVehicleBrandDetails307()
	{
		return new VehicleBrandDetails(VEHICLE_BRAND_ID, standVehicleTypeDetails(), VEHICLE_BRAND_NAME, "307");
		
	}
	
	public static VehicleBrandDetails standardVehicleBrandDetailsAcer()
	{
		return new VehicleBrandDetails(VEHICLE_BRAND_ID, standVehicleTypeDetails(), "ACER", "507");
		
	}
	
	
	public static List<String> commodityList()
	{
		List<String> commodityList=new ArrayList<String>();
		
		commodityList.add("Wheat");
		commodityList.add("Grain");
		commodityList.add("Cement");
		
		return commodityList;
	}

	public static VehicleModel standardVehicleModelDetails()
	{
		
		return new VehicleModel(VEHICLE_BRAND_ID,VEHICLE_BRAND_NAME_NEW_OBJ, standVehicleType(), VEHICLE_MODEL_NUMBER,"photo".getBytes(),
				VEHCLE_DATE,VEHCLE_DATE,ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,VEHICLE_VENDOR_ID,VEHICLE_VENDOR_ID);
		
	}
	
	public static VehicleModel standardVehicleModelDetails(VehicleBrand vehicleBodyType,VehicleType vehicleType)
	{
		
		return new VehicleModel(VEHICLE_BRAND_ID,vehicleBodyType, vehicleType, VEHICLE_MODEL_NUMBER,"photo".getBytes(),
				VEHCLE_DATE,VEHCLE_DATE,ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,VEHICLE_VENDOR_ID,VEHICLE_VENDOR_ID);
		
	}
	
	
	
	public static VehicleDetailsDTO standardVehicleDetails()
	{
		
		return new VehicleDetailsDTO(VEHICLE_ID, VEHICLE_OWNER_FIRST_NAME, VEHICLE_OWNER_MIDDLE_NAME, VEHICLE_OWNER_LAST_NAME,
				 standardVehicleBrandDetails(), VEHICLE_BODY_TYPE_OPEN, VEHICLE_BOOLEAN_TRUE,
				VEHICLE_REGISTRATION_NUMBER, VEHICLE_CHASIS_NUMBER,VEHICLE_LOAD_CAPACITY,VEHICLE_LENGTH,VEHICLE_WIDTH,VEHICLE_HEIGHT, VEHICLE_NOOF_WHEELS,
				VEHICLE_PERMIT_TYPE_NATIONAL, VEHICLE_BOOLEAN_TRUE, VEHICLE_INSURANCE_NUMBER, VEHICLE_INSURANCE_COMPANY, VEHICLE_VENDOR_ID,true,false,commodityList(),
				false,VEHCLE_DATE, VEHCLE_DATE, VEHICLE_UPDATED_BY,VEHICLE_UPDATED_BY,VEHICLE_ID,VEHICLE_ID);
	}
	
	public static VehicleDetailsDTO standardVehicleDetails(Long vehicleId)
	{
		
		return new VehicleDetailsDTO(vehicleId, VEHICLE_OWNER_FIRST_NAME, VEHICLE_OWNER_MIDDLE_NAME, VEHICLE_OWNER_LAST_NAME,
				 standardVehicleBrandDetails(), VEHICLE_BODY_TYPE_OPEN, VEHICLE_BOOLEAN_TRUE,
				VEHICLE_REGISTRATION_NUMBER, VEHICLE_CHASIS_NUMBER,VEHICLE_LOAD_CAPACITY,VEHICLE_LENGTH,VEHICLE_WIDTH,VEHICLE_HEIGHT, VEHICLE_NOOF_WHEELS,
				VEHICLE_PERMIT_TYPE_NATIONAL, VEHICLE_BOOLEAN_TRUE, VEHICLE_INSURANCE_NUMBER, VEHICLE_INSURANCE_COMPANY, VEHICLE_VENDOR_ID,true,false,commodityList(),
				false,VEHCLE_DATE, VEHCLE_DATE, VEHICLE_UPDATED_BY,VEHICLE_UPDATED_BY,VEHICLE_ID,VEHICLE_ID);
	}
	
	public static VehicleDetailsDTO standardVehicleDetailsOpen()
	{
		
		return new VehicleDetailsDTO(VEHICLE_ID, VEHICLE_OWNER_FIRST_NAME, VEHICLE_OWNER_MIDDLE_NAME, VEHICLE_OWNER_LAST_NAME,
				 standardVehicleBrandDetails(), VEHICLE_BODY_TYPE_OPEN, false,
				"VEHICLE_REGISTRATION_NUMBERO", "VEHICLE_CHASIS_NUMBERO",40,100,40,10, VEHICLE_NOOF_WHEELS,
				VEHICLE_PERMIT_TYPE_NATIONAL, VEHICLE_BOOLEAN_TRUE, VEHICLE_INSURANCE_NUMBER, VEHICLE_INSURANCE_COMPANY, VEHICLE_VENDOR_ID,true,false,commodityList(),
				false,VEHCLE_DATE, VEHCLE_DATE,VEHICLE_UPDATED_BY,VEHICLE_UPDATED_BY,VEHICLE_ID,VEHICLE_ID);
	}
	
	public static VehicleDetailsDTO standardVehicleDetailsOpen307()
	{
		
		return new VehicleDetailsDTO(VEHICLE_ID, VEHICLE_OWNER_FIRST_NAME, VEHICLE_OWNER_MIDDLE_NAME, VEHICLE_OWNER_LAST_NAME,
				 standardVehicleBrandDetails307(), VEHICLE_BODY_TYPE_OPEN, false,
				"VEHICLE_REGISTRATION_NUMBERO", "VEHICLE_CHASIS_NUMBERO",40,100,40,10, VEHICLE_NOOF_WHEELS,
				VEHICLE_PERMIT_TYPE_NATIONAL, VEHICLE_BOOLEAN_TRUE, VEHICLE_INSURANCE_NUMBER, VEHICLE_INSURANCE_COMPANY, VEHICLE_VENDOR_ID,true,false,commodityList(),
				false,VEHCLE_DATE, VEHCLE_DATE,VEHICLE_UPDATED_BY,VEHICLE_UPDATED_BY,VEHICLE_ID,VEHICLE_ID);
	}
	
	public static VehicleDetailsDTO standardVehicleDetailsClosed()
	{
		
		return new VehicleDetailsDTO(VEHICLE_ID, VEHICLE_OWNER_FIRST_NAME, VEHICLE_OWNER_MIDDLE_NAME, VEHICLE_OWNER_LAST_NAME,
				 standardVehicleBrandDetails(), VEHICLE_BODY_TYPE_CLOSED, false,
				"VEHICLE_REGISTRATION_NUMBERC", "VEHICLE_CHASIS_NUMBERC",60,100,60,10, VEHICLE_NOOF_WHEELS,
				VEHICLE_PERMIT_TYPE_NATIONAL, VEHICLE_BOOLEAN_TRUE, VEHICLE_INSURANCE_NUMBER, VEHICLE_INSURANCE_COMPANY, VEHICLE_VENDOR_ID,true,false,commodityList(),
				false,VEHCLE_DATE, VEHCLE_DATE,VEHICLE_UPDATED_BY,VEHICLE_UPDATED_BY,VEHICLE_ID,VEHICLE_ID);
	}
	
	
	public static VehicleDetailsDTO standardVehicleDetailsFlexible()
	{
		
		return new VehicleDetailsDTO(VEHICLE_ID, VEHICLE_OWNER_FIRST_NAME, VEHICLE_OWNER_MIDDLE_NAME, VEHICLE_OWNER_LAST_NAME,
				 standardVehicleBrandDetailsAcer(), VEHICLE_BODY_TYPE_OPEN, VEHICLE_BOOLEAN_TRUE,
				"VEHICLE_REGISTRATION_NUMBERF", "VEHICLE_CHASIS_NUMBERF",100,200,50,20, VEHICLE_NOOF_WHEELS,
				VEHICLE_PERMIT_TYPE_NATIONAL, VEHICLE_BOOLEAN_TRUE, VEHICLE_INSURANCE_NUMBER, VEHICLE_INSURANCE_COMPANY, VEHICLE_VENDOR_ID,true,false,commodityList(),
				false,VEHCLE_DATE, VEHCLE_DATE,VEHICLE_UPDATED_BY,VEHICLE_UPDATED_BY,VEHICLE_ID,VEHICLE_ID);
	}
	
	public static VehicleDetailsDTO standardVehicleDetailsOther()
	{
		return new VehicleDetailsDTO(VEHICLE_ID, VEHICLE_OWNER_FIRST_NAME_OTHER, VEHICLE_OWNER_MIDDLE_NAME_OTHER, VEHICLE_OWNER_LAST_NAME_OTHER,
				 standardVehicleBrandDetails(), VEHICLE_BODY_TYPE_OPEN, VEHICLE_BOOLEAN_TRUE,
				VEHICLE_REGISTRATION_NUMBER_OTHER, VEHICLE_CHASIS_NUMBER_OTHER,VEHICLE_LOAD_CAPACITY,VEHICLE_LENGTH,VEHICLE_WIDTH,VEHICLE_HEIGHT, VEHICLE_NOOF_WHEELS,
				VEHICLE_PERMIT_TYPE_NATIONAL, VEHICLE_BOOLEAN_TRUE, VEHICLE_INSURANCE_NUMBER, VEHICLE_INSURANCE_COMPANY, VEHICLE_VENDOR_ID,true,false,commodityList(),
				false,VEHCLE_DATE, VEHCLE_DATE, VEHICLE_UPDATED_BY,VEHICLE_UPDATED_BY,VEHICLE_ID,VEHICLE_ID);
	}
	
	public static VehicleSimplified standardVehicleSimplified(Long vehicleId)
	{
		return new VehicleSimplified(vehicleId,VEHICLE_BODY_TYPE_OPEN, VEHICLE_BOOLEAN_TRUE,
				VEHICLE_LOAD_CAPACITY, VEHICLE_LENGTH, VEHICLE_WIDTH, VEHICLE_HEIGHT, VEHICLE_NOOF_WHEELS, VEHICLE_VENDOR_ID,
				commodityList(), ACTOR_ENTITY_SELF_WEB, VEHICLE_VENDOR_ID);
	}
	
	public static VehicleSimplified standardVehicleSimplifiedOther(Long vehicleId)
	{
		return new VehicleSimplified(vehicleId,VEHICLE_BODY_TYPE_CLOSED, VEHICLE_BOOLEAN_TRUE,
				VEHICLE_LOAD_CAPACITY, VEHICLE_LENGTH, VEHICLE_WIDTH, VEHICLE_HEIGHT, VEHICLE_NOOF_WHEELS, VEHICLE_VENDOR_ID,
				commodityList(), ACTOR_ENTITY_SELF_WEB, VEHICLE_VENDOR_ID);
	}
	
	public static L1VehicleCompleteRegistration standardL1VehicleCompleteRegistration(Long vehicleId)
	{
		return new L1VehicleCompleteRegistration(vehicleId, VEHICLE_OWNER_FIRST_NAME,
				VEHICLE_OWNER_MIDDLE_NAME, VEHICLE_OWNER_LAST_NAME, standardVehicleBrandDetails().getVehicleBrandName(),
				standardVehicleBrandDetails().getModelNumber(),standardVehicleBrandDetails().getVehicleTypeId().getVehicleTypeName(), 
				VEHICLE_REGISTRATION_NUMBER,VEHICLE_CHASIS_NUMBER,VEHICLE_BOOLEAN_TRUE, VEHICLE_INSURANCE_NUMBER, VEHICLE_INSURANCE_COMPANY, VEHICLE_PERMIT_TYPE_NATIONAL, true);
		
	}

	

	public static String standardVehicleSimplifiedJson(VehicleSimplified vehicleSimplified)
	{
		System.out.println(vehicleSimplified);
		
		return gson.toJson(vehicleSimplified);
	}
	
	public static String standardL1VehicleCompleteRegistrationJson(L1VehicleCompleteRegistration l1VehicleCompleteRegistration)
	{
		return gson.toJson(l1VehicleCompleteRegistration);
	}

	
	public static String standardVehicleJson(VehicleDetailsDTO vehicleDetails)
	{
		System.out.println(gson.toJson(vehicleDetails));
		return gson.toJson(vehicleDetails);
	}

	public static String standardVehicleBrandType(VehicleBrand  vehicleBrand)
	{
		return gson.toJson(vehicleBrand);
	}
	
	public static String standardVehicleType(VehicleType  vehicleBrand)
	{
		return gson.toJson(vehicleBrand);
	}
	
	public static String standardVehicleBodyType(VehicleBodyType  vehicleBrand)
	{
		return gson.toJson(vehicleBrand);
	}
	
	public static String standardVehiclePermitType(VehiclePermitType  vehicleBrand)
	{
		return gson.toJson(vehicleBrand);
	}
	
	public static String standardCommodity(Commodity  vehicleBrand)
	{
		return gson.toJson(vehicleBrand);
	}
	
	public static String standardVehicleModel(VehicleModel  vehicleBrand)
	{
		return gson.toJson(vehicleBrand);
	}

	
}
