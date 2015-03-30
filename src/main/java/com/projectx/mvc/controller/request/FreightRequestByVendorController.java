package com.projectx.mvc.controller.request;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.projectx.mvc.services.request.FreightRequestByCustomerService;
import com.projectx.mvc.services.request.FreightRequestByVendorService;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;

@RestController
@RequestMapping(value = "/request/freightrequestByVendor")
public class FreightRequestByVendorController {

	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<FreightRequestByVendorDTO> save(@Valid @RequestBody FreightRequestByVendorDTO freightRequestByVendorDTO,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		freightRequestByVendorDTO.setStatus("New");
		
		FreightRequestByVendorDTO savedEntity=freightRequestByVendorService.save(freightRequestByVendorDTO);
		
		if(savedEntity.getRequestId()!=null)
		{
			//List<FreightRequestByCustomer> reuqestList=
			//model.addAttribute("requestList", reuqestList);
			
			freightRequestByCustomerService.getCustmerReqForVendorReq(savedEntity);
			
			return new ResponseEntity<FreightRequestByVendorDTO>(savedEntity, HttpStatus.OK);
			
		}
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public FreightRequestByVendorDTO getById(@RequestBody EntityIdDTO entityIdDTO)
	{
		FreightRequestByVendorDTO fetchedEntity=freightRequestByVendorService.getRequestById(entityIdDTO.getEntityId());
		
		return fetchedEntity;
	}
	
	@RequestMapping(value="/getByVendorId",method=RequestMethod.POST)
	public List<FreightRequestByVendorDTO> showRequestForCustomer(@ModelAttribute EntityIdDTO entityIdDTO ,Model model)
	{
		List<FreightRequestByVendorDTO> requestList=freightRequestByVendorService.getAllRequestForVendor(entityIdDTO.getEntityId());
		
		return requestList;
	}
	
	@RequestMapping(value="/deleteRequestById",method=RequestMethod.POST)
	public Boolean deleteRequestById(@ModelAttribute EntityIdDTO entityIdDTO)
	{
		Boolean status=freightRequestByVendorService.deleteRequestById(entityIdDTO.getEntityId());
		
		return status;
	}
	
}
/*
	@RequestMapping(value="/requestForm",method=RequestMethod.POST)
	public String requestForm(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		FreightRequestByVendorDTO newRequest=new FreightRequestByVendorDTO();
		newRequest.setVendorId(entityIdDTO.getEntityId());
		
		model.addAttribute("freightRequestByVendor", newRequest);
		
		return "request/freightRequestByVendorForm";
		
	}


	@RequestMapping(value="/updateFrom",method=RequestMethod.POST)
	public String update(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		FreightRequestByVendorDTO request=freightRequestByVendorService.getRequestById(entityIdDTO.getEntityId());
		
		model.addAttribute("freightRequestByVendor", request);
		
		return "request/freightRequestByVendorForm";
		
	}

 */
