package com.projectx.mvc.controller.completeregister;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.domain.commn.ResponseDTO;
import com.projectx.mvc.domain.completeregister.VehicleBodyType;
import com.projectx.mvc.domain.completeregister.VehicleBodyTypeDTO;
import com.projectx.mvc.domain.completeregister.VehicleBrand;
import com.projectx.mvc.domain.completeregister.VehicleBrandDTO;
import com.projectx.mvc.exception.repository.completeregister.ResourceAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.services.completeregister.VehicleBodyTypeService;
import com.projectx.mvc.services.completeregister.VehicleBrandTypeService;
import com.projectx.rest.domain.completeregister.EntityIdDTO;

@RestController
@RequestMapping(value = "/vehicle/vehiclebrand")
public class VehicleBrandTypeController {

	@Autowired
	VehicleBrandTypeService vehicleBrandTypeService;
	
	@Value("${VEHICLE_BRAND_ALREADY_REPORTED}")
	private String VEHICLE_BRAND_ALREADY_REPORTED;
	
	@Value("${VEHICLE_BRAND_NOT_FOUND_BY_ID}")
	private String VEHICLE_BRAND_NOT_FOUND_BY_ID;
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<VehicleBrand>> save(@Valid @RequestBody VehicleBrandDTO vehicleBrandDTO,BindingResult bindingResult)
	{
		
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		VehicleBrand vehicleBrand=null;
		
		if(vehicleBrandDTO.getVehicleBrandId()==null)
		{
			vehicleBrand=new VehicleBrand(vehicleBrandDTO.getVehicleBrandId(), vehicleBrandDTO.getVehicleBrandName(),
					new Date(),new Date(), vehicleBrandDTO.getRequestedBy(), vehicleBrandDTO.getRequestedBy(),
					vehicleBrandDTO.getRequestedById(), vehicleBrandDTO.getRequestedById());
			
		}
		else
		{
			
			vehicleBrand=vehicleBrandTypeService.getById(vehicleBrandDTO.getVehicleBrandId());
			
			vehicleBrand.setVehicleBrandName(vehicleBrandDTO.getVehicleBrandName());
			vehicleBrand.setUpdatedBy(vehicleBrandDTO.getRequestedBy());
			vehicleBrand.setUpdatedById(vehicleBrandDTO.getRequestedById());
			vehicleBrand.setUpdateTime(new Date());
			
			
		}
		
		try{
			VehicleBrand savedEntity=vehicleBrandTypeService.save(vehicleBrand);
			return new ResponseEntity<ResponseDTO<VehicleBrand>>(new ResponseDTO<VehicleBrand>(savedEntity,""), HttpStatus.OK);
			
		}catch(ResourceAlreadyPresentException e)
		{
			return new ResponseEntity<ResponseDTO<VehicleBrand>>(new ResponseDTO<VehicleBrand>(null,VEHICLE_BRAND_ALREADY_REPORTED), HttpStatus.OK);
		}
			
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<VehicleBrand>> getById(@Valid @RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			VehicleBrand vehicleBodyType=vehicleBrandTypeService.getById(entityIdDTO.getEntityId());
			return new ResponseEntity<ResponseDTO<VehicleBrand>>(new ResponseDTO<VehicleBrand>(vehicleBodyType, ""),HttpStatus.OK); 
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<VehicleBrand>>(new ResponseDTO<VehicleBrand>(null, VEHICLE_BRAND_NOT_FOUND_BY_ID),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/deleteById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> deleteById(@Valid @RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			Boolean result=vehicleBrandTypeService.deleteById(entityIdDTO.getEntityId());
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(result,""), HttpStatus.OK);
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(null,e.getMessage()), HttpStatus.OK);
		}
		
		
	}


	
}
