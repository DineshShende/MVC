package com.projectx.mvc.servicehandler.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.mvc.domain.request.FreightRequestByCustomer;
import com.projectx.mvc.services.request.FreightRequestByCustomerService;
import com.projectx.rest.domain.quickregister.QuickRegisterStringStatusDTO;
import com.projectx.rest.domain.request.FreightRequestByCustomerDTO;
import com.projectx.rest.domain.request.FreightRequestByCustomerList;

@Component
@Profile(value="Dev")
@PropertySource(value="classpath:/application.properties")
public class FreightRequestByCustomerHandler implements
		FreightRequestByCustomerService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Override
	public FreightRequestByCustomer save(
			FreightRequestByCustomer freightRequestByCustomer) {
		
			
		FreightRequestByCustomerDTO freightRequestByCustomerDTO=freightRequestByCustomer.toFreightRequestByCustomerDTO();
		
			
		FreightRequestByCustomerDTO status=restTemplate.postForObject(env.getProperty("rest.host")+"/request/freightRequestByCustomer",
				freightRequestByCustomerDTO, FreightRequestByCustomerDTO.class);
		
		return FreightRequestByCustomer.fromFreightRequestByCustomerDTO(status);
		
	}

	@Override
	public FreightRequestByCustomer getRequestById(Long requestId) {
		
		FreightRequestByCustomerDTO status=restTemplate.getForObject(env.getProperty("rest.host")+"/request/freightRequestByCustomer/getById/"+requestId, FreightRequestByCustomerDTO.class);
		
		return FreightRequestByCustomer.fromFreightRequestByCustomerDTO(status);
	}

	@Override
	public List<FreightRequestByCustomer> getAllRequestForCustomer(
			Long customerId) {
		FreightRequestByCustomerList status=restTemplate
				.getForObject(env.getProperty("rest.host")+"/request/freightRequestByCustomer/getByCustomerId/"+customerId, FreightRequestByCustomerList.class);
		
		List<FreightRequestByCustomer> customerList=new ArrayList<FreightRequestByCustomer>();
		
		for(int i=0;i<status.getRequestList().size();i++)
		{
			customerList.add(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(status.getRequestList().get(i)));
		}
		
		return customerList;

		
	}

	@Override
	public Boolean deleteRequestById(Long requestId) {

		Boolean status=restTemplate
				.getForObject(env.getProperty("rest.host")+"/request/freightRequestByCustomer/deleteById/"+requestId, Boolean.class);
		
		return status;
	}

	@Override
	public Boolean clearTestData() {

		Boolean status=restTemplate
				.getForObject(env.getProperty("rest.host")+"/request/freightRequestByCustomer/clearTestData", Boolean.class);
		
		return status;

	}

	@Override
	public Integer count() {

		Integer status=restTemplate
				.getForObject(env.getProperty("rest.host")+"/request/freightRequestByCustomer/count", Integer.class);
		
		return status;
		
	}

}
