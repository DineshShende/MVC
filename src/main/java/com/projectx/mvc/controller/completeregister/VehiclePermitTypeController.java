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
import com.projectx.mvc.domain.completeregister.VehicleBrand;
import com.projectx.mvc.domain.completeregister.VehicleBrandDTO;
import com.projectx.mvc.domain.completeregister.VehiclePermitType;
import com.projectx.mvc.domain.completeregister.VehiclePermitTypeDTO;
import com.projectx.mvc.exception.repository.completeregister.ResourceAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.services.completeregister.VehicleBrandTypeService;
import com.projectx.mvc.services.completeregister.VehiclePermitTypeService;
import com.projectx.rest.domain.completeregister.EntityIdDTO;

@RestController
@RequestMapping(value = "/vehicle/vehiclepermittype")
public class VehiclePermitTypeController {

	@Autowired
	VehiclePermitTypeService vehiclePermitTypeService;
	
	@Value("${VEHICLE_PERMIT_TYPE_ALREADY_REPORTED}")
	private String VEHICLE_PERMIT_TYPE_ALREADY_REPORTED;
	
	@Value("${VEHICLE_PERMIT_TYPE_NOT_FOUND_BY_ID}")
	private String VEHICLE_PERMIT_TYPE_NOT_FOUND_BY_ID;
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<VehiclePermitType>> save(@Valid @RequestBody VehiclePermitTypeDTO vehiclePermitTypeDTO,BindingResult bindingResult)
	{
		
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		VehiclePermitType vehiclePermitType=null;
		
		if(vehiclePermitTypeDTO.getVehiclePermitTypeId()==null)
		{
			vehiclePermitType=new VehiclePermitType(vehiclePermitTypeDTO.getVehiclePermitTypeId(), vehiclePermitTypeDTO.getVehiclePermitTypeName(),
					new Date(),new Date(), vehiclePermitTypeDTO.getRequestedBy(), vehiclePermitTypeDTO.getRequestedBy(),
					vehiclePermitTypeDTO.getRequestedById(), vehiclePermitTypeDTO.getRequestedById());
			
		}
		else
		{
			
			vehiclePermitType=vehiclePermitTypeService.getById(vehiclePermitTypeDTO.getVehiclePermitTypeId());
			
			vehiclePermitType.setVehiclePermitTypeName(vehiclePermitTypeDTO.getVehiclePermitTypeName());
			vehiclePermitType.setUpdatedBy(vehiclePermitTypeDTO.getRequestedBy());
			vehiclePermitType.setUpdatedById(vehiclePermitTypeDTO.getRequestedById());
			vehiclePermitType.setUpdateTime(new Date());
			
			
		}
		
		try{
			VehiclePermitType savedEntity=vehiclePermitTypeService.save(vehiclePermitType);
			return new ResponseEntity<ResponseDTO<VehiclePermitType>>(new ResponseDTO<VehiclePermitType>(savedEntity,""), HttpStatus.OK);
			
		}catch(ResourceAlreadyPresentException e)
		{
			return new ResponseEntity<ResponseDTO<VehiclePermitType>>(new ResponseDTO<VehiclePermitType>(null,VEHICLE_PERMIT_TYPE_ALREADY_REPORTED), HttpStatus.OK);
		}
			
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<VehiclePermitType>> getById(@Valid @RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			VehiclePermitType vehicleBodyType=vehiclePermitTypeService.getById(entityIdDTO.getEntityId());
			return new ResponseEntity<ResponseDTO<VehiclePermitType>>(new ResponseDTO<VehiclePermitType>(vehicleBodyType, ""),HttpStatus.OK); 
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<VehiclePermitType>>(new ResponseDTO<VehiclePermitType>(null, VEHICLE_PERMIT_TYPE_NOT_FOUND_BY_ID),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/deleteById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> deleteById(@Valid @RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			Boolean result=vehiclePermitTypeService.deleteById(entityIdDTO.getEntityId());
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(result,""), HttpStatus.OK);
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(null,e.getMessage()), HttpStatus.OK);
		}
		
		
	}


	
}
