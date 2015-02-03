package com.projectx.mvc.controller.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectx.mvc.domain.request.FreightRequestByCustomer;
import com.projectx.mvc.services.request.FreightRequestByVendorService;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;

@Controller
@RequestMapping(value = "/request/freightrequestByVendor")
public class FreightRequestByVendorController {

	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@RequestMapping(value="/requestForm",method=RequestMethod.POST)
	public String requestForm(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		FreightRequestByVendorDTO newRequest=new FreightRequestByVendorDTO();
		newRequest.setVendorId(entityIdDTO.getEntityId());
		
		model.addAttribute("freightRequestByVendor", newRequest);
		
		return "freightRequestByVendorForm";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute FreightRequestByVendorDTO freightRequestByVendorDTO)
	{
		freightRequestByVendorDTO.setStatus("New");
		
		FreightRequestByVendorDTO savedEntity=freightRequestByVendorService.save(freightRequestByVendorDTO);
		
		if(savedEntity.getRequestId()!=null)
			return "SUCESS";
		else
			return "FAILURE";
	}
	
	@RequestMapping(value="/updateFrom",method=RequestMethod.POST)
	public String update(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		FreightRequestByVendorDTO request=freightRequestByVendorService.getRequestById(entityIdDTO.getEntityId());
		
		model.addAttribute("freightRequestByVendor", request);
		
		return "freightRequestByVendorForm";
		
	}
	
	@RequestMapping(value="/getByVendorId",method=RequestMethod.POST)
	public String showRequestForCustomer(@ModelAttribute EntityIdDTO entityIdDTO ,Model model)
	{
		List<FreightRequestByVendorDTO> requestList=freightRequestByVendorService.getAllRequestForVendor(entityIdDTO.getEntityId());
		
		model.addAttribute("requestList", requestList);
		
		return "showFreightRequestFromVendor";
	}
	
	@RequestMapping(value="/deleteRequestById",method=RequestMethod.POST)
	@ResponseBody
	public Boolean deleteRequestById(@ModelAttribute EntityIdDTO entityIdDTO)
	{
		Boolean status=freightRequestByVendorService.deleteRequestById(entityIdDTO.getEntityId());
		
		return status;
	}
	
}
