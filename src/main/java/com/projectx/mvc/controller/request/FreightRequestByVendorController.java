package com.projectx.mvc.controller.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.domain.commn.ResponseDTO;
import com.projectx.mvc.exception.repository.completeregister.ResourceAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.services.request.FreightRequestByCustomerService;
import com.projectx.mvc.services.request.FreightRequestByVendorService;
import com.projectx.rest.domain.ang.FreightRequestByVendorAngDTO;
import com.projectx.rest.domain.ang.FreightRequestByVendorAngReqDTO;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.completeregister.EntityIdTypeDTO;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;

@RestController
@RequestMapping(value = "/request/freightrequestByVendor")
public class FreightRequestByVendorController {

	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@Value("${FREIGHTALLOCATIONSTATUS_NEW}")
	private String FREIGHTALLOCATIONSTATUS_NEW;
	
	@Value("${FREIGHT_REQUEST_BY_VENDOR_NOT_FOUND_BY_ID}")
	private String FREIGHT_REQUEST_BY_VENDOR_NOT_FOUND_BY_ID;
	
	
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<String>> save(@Valid @RequestBody FreightRequestByVendorAngReqDTO freightRequestByVendorDTO,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("failure", "Error"),HttpStatus.NOT_ACCEPTABLE);
		
		freightRequestByVendorDTO.setStatus(FREIGHTALLOCATIONSTATUS_NEW);
		
		FreightRequestByVendorDTO requestByVendorDTO=freightRequestByVendorDTO.toFreightRequestByVendorDTO();
		
		try{
			FreightRequestByVendorDTO savedEntity=freightRequestByVendorService.save(requestByVendorDTO);

			freightRequestByCustomerService.getCustmerReqForVendorReq(savedEntity);
			
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("sucess", ""),HttpStatus.OK);
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("failure", e.getMessage()),HttpStatus.NOT_ACCEPTABLE);
		}
		
		
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<FreightRequestByVendorAngDTO>> getById(@RequestBody EntityIdDTO entityIdDTO)
	{
		try{
		
		FreightRequestByVendor fetchedEntity=freightRequestByVendorService.getRequestById(entityIdDTO.getEntityId());
		
		FreightRequestByVendorAngDTO byVendorAngDTO=FreightRequestByVendorAngDTO.fromFreightRequestByVendor(fetchedEntity);
		
		return new ResponseEntity<ResponseDTO<FreightRequestByVendorAngDTO>>
			(new ResponseDTO<FreightRequestByVendorAngDTO>(byVendorAngDTO,""),HttpStatus.OK);
		
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<FreightRequestByVendorAngDTO>>
			(new ResponseDTO<FreightRequestByVendorAngDTO>(null,FREIGHT_REQUEST_BY_VENDOR_NOT_FOUND_BY_ID),HttpStatus.OK);
		}
		
		
		
		
	}
	
	@RequestMapping(value="/getByVendorId",method=RequestMethod.POST)
	public List<FreightRequestByVendorAngDTO> showRequestForCustomer(@RequestBody EntityIdDTO entityIdDTO ,Model model)
	{
		List<FreightRequestByVendor> requestList=freightRequestByVendorService.getAllRequestForVendor(entityIdDTO.getEntityId());
		
		List<FreightRequestByVendorAngDTO> angList=new ArrayList<FreightRequestByVendorAngDTO>();
		
		for(int i=0;i<requestList.size();i++)
		{
			FreightRequestByVendor fetchedEntity=requestList.get(i);
			
			FreightRequestByVendorAngDTO byVendorAngDTO=FreightRequestByVendorAngDTO.fromFreightRequestByVendor(fetchedEntity);
			
			angList.add(byVendorAngDTO);
			
		}
		
		
		return angList;
	}
	
	@RequestMapping(value="/deleteById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<String>> deleteRequestById(@RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			Boolean status=freightRequestByVendorService.deleteRequestById(entityIdDTO.getEntityId());
		
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("sucess", ""), HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("failure", e.getMessage()), HttpStatus.OK);
		}
		
//		if(status)
//			return new ResponseDTO("sucess", "");
//		else
//			return new ResponseDTO("failure", "error");
//		
		
	}
	
}

