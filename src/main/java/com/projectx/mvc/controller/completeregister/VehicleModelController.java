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
import com.projectx.mvc.domain.completeregister.VehicleBodyType;
import com.projectx.mvc.domain.completeregister.VehicleBodyTypeDTO;
import com.projectx.mvc.domain.completeregister.VehicleModel;
import com.projectx.mvc.domain.completeregister.VehicleModelDTOInp;
import com.projectx.mvc.exception.repository.completeregister.ResourceAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.services.completeregister.VehicleBodyTypeService;
import com.projectx.mvc.services.completeregister.VehicleModelService;
import com.projectx.rest.domain.completeregister.EntityIdDTO;

@RestController
@RequestMapping(value = "/vehicle/vehiclemodel")
public class VehicleModelController {
	
	@Autowired
	VehicleModelService vehicleModelService;
	
	@Value("${VEHICLE_MODEL_ALREADY_REPORTED}")
	private String VEHICLE_MODEL_ALREADY_REPORTED;
	
	@Value("${VEHICLE_MODEL_NOT_FOUND_BY_ID}")
	private String VEHICLE_MODEL_NOT_FOUND_BY_ID;
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<VehicleModel>> save(@Valid @RequestBody VehicleModelDTOInp vehicleModelDTOInp,BindingResult bindingResult)
	{
		
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		VehicleModel vehicleModel=null;
		
		if(vehicleModelDTOInp.getVehicleModeId()==null)
		{
			vehicleModel=new VehicleModel(vehicleModelDTOInp.getVehicleModeId(),vehicleModelDTOInp.getVehicleBrand(),
					vehicleModelDTOInp.getVehicleType(), vehicleModelDTOInp.getVehiclemodelName(),
					vehicleModelDTOInp.getVehiclePhoto(), new Date(), new Date(), vehicleModelDTOInp.getRequestedBy(),
					vehicleModelDTOInp.getRequestedBy(), vehicleModelDTOInp.getRequestedById(), vehicleModelDTOInp.getRequestedById());
			
		}
		else
		{
			
			vehicleModel=vehicleModelService.getById(vehicleModelDTOInp.getVehicleModeId());
			
			vehicleModel.setVehicleBrand(vehicleModelDTOInp.getVehicleBrand());
			vehicleModel.setVehicleType(vehicleModelDTOInp.getVehicleType());
			vehicleModel.setVehiclemodelName(vehicleModelDTOInp.getVehiclemodelName());
			vehicleModel.setVehiclePhoto(vehicleModelDTOInp.getVehiclePhoto());
			
			vehicleModel.setUpdatedBy(vehicleModelDTOInp.getRequestedBy());
			vehicleModel.setUpdatedById(vehicleModelDTOInp.getRequestedById());
			vehicleModel.setUpdateTime(new Date());
			
			
		}
		
		try{
			VehicleModel savedEntity=vehicleModelService.save(vehicleModel);
			return new ResponseEntity<ResponseDTO<VehicleModel>>(new ResponseDTO<VehicleModel>(savedEntity,""), HttpStatus.OK);
			
		}catch(ResourceAlreadyPresentException e)
		{
			return new ResponseEntity<ResponseDTO<VehicleModel>>(new ResponseDTO<VehicleModel>(null,VEHICLE_MODEL_ALREADY_REPORTED), HttpStatus.OK);
		}
			
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<VehicleModel>> getById(@Valid @RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			VehicleModel vehicleModel=vehicleModelService.getById(entityIdDTO.getEntityId());
			return new ResponseEntity<ResponseDTO<VehicleModel>>(new ResponseDTO<VehicleModel>(vehicleModel, ""),HttpStatus.OK); 
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<VehicleModel>>(new ResponseDTO<VehicleModel>(null, VEHICLE_MODEL_NOT_FOUND_BY_ID),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/deleteById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> deleteById(@Valid @RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			Boolean result=vehicleModelService.deleteById(entityIdDTO.getEntityId());
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(result,""), HttpStatus.OK);
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(null,e.getMessage()), HttpStatus.OK);
		}
		
		
	}


}
