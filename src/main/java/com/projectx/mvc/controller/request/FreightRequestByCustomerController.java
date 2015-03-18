package com.projectx.mvc.controller.request;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectx.mvc.domain.request.FreightRequestByCustomer;
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
	
	
	@RequestMapping(value="/requestForm",method=RequestMethod.POST)
	public String requestForm(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		FreightRequestByCustomer newRequest=new FreightRequestByCustomer();
		newRequest.setCustomerId(entityIdDTO.getEntityId());
		
		model.addAttribute("freightRequestByCustomer", newRequest);
		
		return "request/freightRequestByCustomerForm";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(@Valid @ModelAttribute FreightRequestByCustomer freightRequestByCustomerDTO,BindingResult bindingResult,Model model)
	{
		if(bindingResult.hasErrors())
			return "request/freightRequestByCustomerForm";
		
		freightRequestByCustomerDTO.setStatus("New");
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService.save(freightRequestByCustomerDTO);
		
		if(savedEntity.getRequestId()!=null)
		{	
		
			List<FreightRequestByVendorDTO> requestList=freightRequestByVendorService
					.getMatchingVendorReqForCustReq(savedEntity.toFreightRequestByCustomerDTO());
		
			model.addAttribute("requestList", requestList);
		
			return "response/showMatchingVendorRequests";
		}
		else
			return "FAILURE";
	}
	
	@RequestMapping(value="/updateFrom",method=RequestMethod.POST)
	public String update(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		FreightRequestByCustomer request=freightRequestByCustomerService.getRequestById(entityIdDTO.getEntityId());
		
		model.addAttribute("freightRequestByCustomer", request);
		
		return "request/freightRequestByCustomerForm";
		
	}
	
	@RequestMapping(value="/getByCustomerId",method=RequestMethod.POST)
	public String showRequestForCustomer(@ModelAttribute EntityIdDTO entityIdDTO ,Model model)
	{
		List<FreightRequestByCustomer> requestList=freightRequestByCustomerService.getAllRequestForCustomer(entityIdDTO.getEntityId());
		
		model.addAttribute("requestList", requestList);
		
		return "request/showFreightRequestFromCustomer";
	}
	
	@RequestMapping(value="/deleteRequestById",method=RequestMethod.POST)
	@ResponseBody
	public Boolean deleteRequestById(@ModelAttribute EntityIdDTO entityIdDTO)
	{
		Boolean status=freightRequestByCustomerService.deleteRequestById(entityIdDTO.getEntityId());
		
		return status;
	}
}
