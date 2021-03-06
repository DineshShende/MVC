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
import com.projectx.mvc.domain.completeregister.VehiclePermitType;
import com.projectx.mvc.domain.completeregister.VehiclePermitTypeDTO;
import com.projectx.mvc.domain.completeregister.VehicleType;
import com.projectx.mvc.domain.completeregister.VehicleTypeDTO;
import com.projectx.mvc.exception.repository.completeregister.ResourceAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.services.completeregister.VehiclePermitTypeService;
import com.projectx.mvc.services.completeregister.VehicleTypeService;
import com.projectx.rest.domain.completeregister.EntityIdDTO;

@RestController
@RequestMapping(value = "/vehicle/vehicletype")
public class VehicleTypeController {
	
	
	@Autowired
	VehicleTypeService vehicleTypeService;
	
	@Value("${VEHICLE_TYPE_ALREADY_REPORTED}")
	private String VEHICLE_TYPE_ALREADY_REPORTED;
	
	@Value("${VEHICLE_TYPE_NOT_FOUND_BY_ID}")
	private String VEHICLE_TYPE_NOT_FOUND_BY_ID;
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<VehicleType>> save(@Valid @RequestBody VehicleTypeDTO vehicleTypeDTO,BindingResult bindingResult)
	{
		
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		VehicleType vehicleType=null;
		
		if(vehicleTypeDTO.getVehicleTypeId()==null)
		{
			vehicleType=new VehicleType(vehicleTypeDTO.getVehicleTypeId(), vehicleTypeDTO.getVehicleTypeName(),
					new Date(),new Date(), vehicleTypeDTO.getRequestedBy(), vehicleTypeDTO.getRequestedBy(),
					vehicleTypeDTO.getRequestedById(), vehicleTypeDTO.getRequestedById());
			
		}
		else
		{
			
			vehicleType=vehicleTypeService.getById(vehicleTypeDTO.getVehicleTypeId());
			
			vehicleType.setVehicleTypeName(vehicleTypeDTO.getVehicleTypeName());
			vehicleType.setUpdatedBy(vehicleTypeDTO.getRequestedBy());
			vehicleType.setUpdatedById(vehicleTypeDTO.getRequestedById());
			vehicleType.setUpdateTime(new Date());
			
			
		}
		
		try{
			VehicleType savedEntity=vehicleTypeService.save(vehicleType);
			return new ResponseEntity<ResponseDTO<VehicleType>>(new ResponseDTO<VehicleType>(savedEntity,""), HttpStatus.OK);
			
		}catch(ResourceAlreadyPresentException e)
		{
			return new ResponseEntity<ResponseDTO<VehicleType>>(new ResponseDTO<VehicleType>(null,VEHICLE_TYPE_ALREADY_REPORTED), HttpStatus.OK);
		}
			
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<VehicleType>> getById(@Valid @RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			VehicleType vehicleType=vehicleTypeService.getById(entityIdDTO.getEntityId());
			return new ResponseEntity<ResponseDTO<VehicleType>>(new ResponseDTO<VehicleType>(vehicleType, ""),HttpStatus.OK); 
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<VehicleType>>(new ResponseDTO<VehicleType>(null, VEHICLE_TYPE_NOT_FOUND_BY_ID),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/deleteById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> deleteById(@Valid @RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			Boolean result=vehicleTypeService.deleteById(entityIdDTO.getEntityId());
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(result,""), HttpStatus.OK);
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(null,e.getMessage()), HttpStatus.OK);
		}
		
		
	}



}
