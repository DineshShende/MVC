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
import com.projectx.mvc.exception.repository.completeregister.ResourceAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.services.completeregister.CommodityService;
import com.projectx.rest.domain.completeregister.EntityIdDTO;

@RestController
@RequestMapping(value = "/vehicle/commodity")
public class CommodityController {

	@Autowired
	CommodityService commodityService;
	
	
	@Value("${COMMODITY_ALREADY_REPORTED}")
	private String COMMODITY_ALREADY_REPORTED;
	
	@Value("${COMMODITY_NOT_FOUND_BY_ID}")
	private String COMMODITY_NOT_FOUND_BY_ID;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Commodity>> save(@Valid @RequestBody CommodityDTO commodityDTO,BindingResult bindingResult)
	{
		
		
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		Commodity commodity=null;
		
		if(commodityDTO.getCommodityId()==null)
		{
			commodity=new Commodity(commodityDTO.getCommodityId(),commodityDTO.getCommodityName(), new Date(), new Date(), commodityDTO.getRequestedBy(),
					commodityDTO.getRequestedBy(), commodityDTO.getRequestedById(), commodityDTO.getRequestedById());
		}
		else
		{
			commodity=commodityService.getById(commodityDTO.getCommodityId());
			
			commodity.setCommodityName(commodityDTO.getCommodityName());
			commodity.setUpdatedBy(commodityDTO.getRequestedBy());
			commodity.setUpdatedById(commodityDTO.getRequestedById());
			commodity.setUpdateTime(new Date());
			
		}
		
		try{
			Commodity savedEntity=commodityService.save(commodity);
			return new ResponseEntity<ResponseDTO<Commodity>>(new ResponseDTO<Commodity>(savedEntity,""), HttpStatus.OK);
			
		}catch(ResourceAlreadyPresentException e)
		{
			return new ResponseEntity<ResponseDTO<Commodity>>(new ResponseDTO<Commodity>(null,COMMODITY_ALREADY_REPORTED), HttpStatus.OK);
		}
			
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Commodity>> getById(@Valid @RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			Commodity commodity=commodityService.getById(entityIdDTO.getEntityId());
			return new ResponseEntity<ResponseDTO<Commodity>>(new ResponseDTO<Commodity>(commodity, ""),HttpStatus.OK); 
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<Commodity>>(new ResponseDTO<Commodity>(null, COMMODITY_NOT_FOUND_BY_ID),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/deleteById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> deleteById(@Valid @RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			Boolean result=commodityService.deleteById(entityIdDTO.getEntityId());
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(result,""), HttpStatus.OK);
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(null,e.getMessage()), HttpStatus.OK);
		}
		
		
	}
	
}
