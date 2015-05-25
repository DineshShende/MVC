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
import com.projectx.mvc.domain.completeregister.Commodity;
import com.projectx.mvc.domain.completeregister.CommodityDTO;
import com.projectx.mvc.domain.completeregister.VehicleBodyType;
import com.projectx.mvc.domain.completeregister.VehicleBodyTypeDTO;
import com.projectx.mvc.exception.repository.completeregister.ResourceAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.services.completeregister.VehicleBodyTypeService;
import com.projectx.rest.domain.completeregister.EntityIdDTO;

@RestController
@RequestMapping(value = "/vehicle/vehiclebody")
public class VehicleBodyTypeController {

	@Autowired
	VehicleBodyTypeService vehicleBodyTypeService;
	
	@Value("${VEHICLE_BODY_TYPE_ALREADY_REPORTED}")
	private String VEHICLE_BODY_TYPE_ALREADY_REPORTED;
	
	@Value("${VEHICLE_BODY_TYPE_NOT_FOUND_BY_ID}")
	private String VEHICLE_BODY_TYPE_NOT_FOUND_BY_ID;
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<VehicleBodyType>> save(@Valid @RequestBody VehicleBodyTypeDTO vehicleBodyTypeDTO,BindingResult bindingResult)
	{
		
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		VehicleBodyType vehicleBodyType=null;
		
		if(vehicleBodyTypeDTO.getVehicleBodyTypeId()==null)
		{
			vehicleBodyType=new VehicleBodyType(vehicleBodyTypeDTO.getVehicleBodyTypeId(), vehicleBodyTypeDTO.getVehicleBodyTypeName(),
					new Date(),new Date(), vehicleBodyTypeDTO.getRequestedBy(), vehicleBodyTypeDTO.getRequestedBy(),
					vehicleBodyTypeDTO.getRequestedById(), vehicleBodyTypeDTO.getRequestedById());
			
		}
		else
		{
			
			vehicleBodyType=vehicleBodyTypeService.getById(vehicleBodyTypeDTO.getVehicleBodyTypeId());
			
			vehicleBodyType.setVehicleBodyTypeName(vehicleBodyTypeDTO.getVehicleBodyTypeName());
			vehicleBodyType.setUpdatedBy(vehicleBodyTypeDTO.getRequestedBy());
			vehicleBodyType.setUpdatedById(vehicleBodyTypeDTO.getRequestedById());
			vehicleBodyType.setUpdateTime(new Date());
			
			
		}
		
		try{
			VehicleBodyType savedEntity=vehicleBodyTypeService.save(vehicleBodyType);
			return new ResponseEntity<ResponseDTO<VehicleBodyType>>(new ResponseDTO<VehicleBodyType>(savedEntity,""), HttpStatus.OK);
			
		}catch(ResourceAlreadyPresentException e)
		{
			return new ResponseEntity<ResponseDTO<VehicleBodyType>>(new ResponseDTO<VehicleBodyType>(null,VEHICLE_BODY_TYPE_ALREADY_REPORTED), HttpStatus.OK);
		}
			
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<VehicleBodyType>> getById(@Valid @RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			VehicleBodyType vehicleBodyType=vehicleBodyTypeService.getById(entityIdDTO.getEntityId());
			return new ResponseEntity<ResponseDTO<VehicleBodyType>>(new ResponseDTO<VehicleBodyType>(vehicleBodyType, ""),HttpStatus.OK); 
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<VehicleBodyType>>(new ResponseDTO<VehicleBodyType>(null, VEHICLE_BODY_TYPE_NOT_FOUND_BY_ID),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/deleteById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> deleteById(@Valid @RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			Boolean result=vehicleBodyTypeService.deleteById(entityIdDTO.getEntityId());
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(result,""), HttpStatus.OK);
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(null,e.getMessage()), HttpStatus.OK);
		}
		
		
	}

	
}
