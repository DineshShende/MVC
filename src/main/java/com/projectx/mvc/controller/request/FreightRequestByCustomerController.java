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

import com.projectx.mvc.domain.request.FreightRequestByCustomer;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.services.request.FreightRequestByCustomerService;
import com.projectx.mvc.services.request.FreightRequestByVendorService;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;


@Controller
@RequestMapping(value = "/request/freightrequestByCustomer")
public class FreightRequestByCustomerController {

	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<List<FreightRequestByVendorDTO>> save(@Valid @RequestBody FreightRequestByCustomer freightRequestByCustomerDTO,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		freightRequestByCustomerDTO.setStatus("New");
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService.save(freightRequestByCustomerDTO);
		
		if(savedEntity.getRequestId()!=null)
		{	
		
			List<FreightRequestByVendorDTO> requestList=freightRequestByVendorService
					.getMatchingVendorReqForCustReq(savedEntity.toFreightRequestByCustomerDTO());
		
			//model.addAttribute("requestList", requestList);
		
			return new ResponseEntity<List<FreightRequestByVendorDTO>>(requestList, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<FreightRequestByCustomer> getById(@RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			FreightRequestByCustomer fetchedEntity=freightRequestByCustomerService.getRequestById(entityIdDTO.getEntityId());
			return new ResponseEntity<FreightRequestByCustomer>(fetchedEntity, HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		
		
	}
	
	@RequestMapping(value="/getByCustomerId",method=RequestMethod.POST)
	public List<FreightRequestByCustomer> showRequestForCustomer(@ModelAttribute EntityIdDTO entityIdDTO ,Model model)
	{
		List<FreightRequestByCustomer> requestList=freightRequestByCustomerService.getAllRequestForCustomer(entityIdDTO.getEntityId());
		
		//model.addAttribute("requestList", requestList);
		
		return requestList;
	}
	
	@RequestMapping(value="/deleteRequestById",method=RequestMethod.POST)
	public Boolean deleteRequestById(@ModelAttribute EntityIdDTO entityIdDTO)
	{
		Boolean status=freightRequestByCustomerService.deleteRequestById(entityIdDTO.getEntityId());
		
		return status;
	}
}
/*	

@RequestMapping(value="/updateFrom",method=RequestMethod.POST)
	public String update(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		FreightRequestByCustomer request=freightRequestByCustomerService.getRequestById(entityIdDTO.getEntityId());
		
		model.addAttribute("freightRequestByCustomer", request);
		
		return "request/freightRequestByCustomerForm";
		
	}

@RequestMapping(value="/requestForm",method=RequestMethod.POST)
public String requestForm(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
{
	FreightRequestByCustomer newRequest=new FreightRequestByCustomer();
	newRequest.setCustomerId(entityIdDTO.getEntityId());
	
	model.addAttribute("freightRequestByCustomer", newRequest);
	
	return "request/freightRequestByCustomerForm";
	
}
*/