package com.projectx.mvc.controller.request;

import java.util.List;

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
import com.projectx.rest.domain.completeregister.EntityIdDTO;


@Controller
@RequestMapping(value = "/request/freightrequestByCustomer")
public class FreightRequestByCustomerController {

	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	
	@RequestMapping(value="/requestForm",method=RequestMethod.POST)
	public String requestForm(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		FreightRequestByCustomer newRequest=new FreightRequestByCustomer();
		newRequest.setCustomerId(entityIdDTO.getEntityId());
		
		model.addAttribute("freightRequestByCustomer", newRequest);
		
		return "freightRequestByCustomerForm";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute FreightRequestByCustomer freightRequestByCustomerDTO)
	{
		freightRequestByCustomerDTO.setStatus("New");
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService.save(freightRequestByCustomerDTO);
		
		if(savedEntity.getRequestId()!=null)
			return "SUCESS";
		else
			return "FAILURE";
	}
	
	@RequestMapping(value="/updateFrom",method=RequestMethod.POST)
	public String update(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		FreightRequestByCustomer request=freightRequestByCustomerService.getRequestById(entityIdDTO.getEntityId());
		
		model.addAttribute("freightRequestByCustomer", request);
		
		return "freightRequestByCustomerForm";
		
	}
	
	@RequestMapping(value="/getByCustomerId",method=RequestMethod.POST)
	public String showRequestForCustomer(@ModelAttribute EntityIdDTO entityIdDTO ,Model model)
	{
		List<FreightRequestByCustomer> requestList=freightRequestByCustomerService.getAllRequestForCustomer(entityIdDTO.getEntityId());
		
		model.addAttribute("requestList", requestList);
		
		return "showFreightRequestFromCustomer";
	}
	
	@RequestMapping(value="/deleteRequestById",method=RequestMethod.POST)
	@ResponseBody
	public Boolean deleteRequestById(@ModelAttribute EntityIdDTO entityIdDTO)
	{
		Boolean status=freightRequestByCustomerService.deleteRequestById(entityIdDTO.getEntityId());
		
		return status;
	}
}
