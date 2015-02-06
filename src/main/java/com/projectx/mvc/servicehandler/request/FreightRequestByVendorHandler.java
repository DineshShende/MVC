package com.projectx.mvc.servicehandler.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.mvc.domain.request.FreightRequestByCustomer;
import com.projectx.mvc.services.request.FreightRequestByVendorService;
import com.projectx.rest.domain.quickregister.QuickRegisterStringStatusDTO;
import com.projectx.rest.domain.request.FreightRequestByCustomerDTO;
import com.projectx.rest.domain.request.FreightRequestByCustomerList;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;
import com.projectx.rest.domain.request.FreightRequestByVendorList;

@Component
@Profile(value="Dev")
@PropertySource(value="classpath:/application.properties")
public class FreightRequestByVendorHandler implements
		FreightRequestByVendorService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Override
	public FreightRequestByVendorDTO save(
			FreightRequestByVendorDTO freightRequestByCustomer) {

		FreightRequestByVendorDTO status=restTemplate.postForObject(env.getProperty("rest.host")+"/request/freightRequestByVendor",
				freightRequestByCustomer, FreightRequestByVendorDTO.class);
		
		return status;
		
		
	}

	@Override
	public FreightRequestByVendorDTO getRequestById(Long requestId) {

		FreightRequestByVendorDTO status=restTemplate
				.getForObject(env.getProperty("rest.host")+"/request/freightRequestByVendor/getById/"+requestId, FreightRequestByVendorDTO.class);
		
		return status;

		
	}

	@Override
	public List<FreightRequestByVendorDTO> getAllRequestForVendor(Long vendorId) {

		FreightRequestByVendorList status=restTemplate
				.getForObject(env.getProperty("rest.host")+"/request/freightRequestByVendor/findByVendorId/"+vendorId, FreightRequestByVendorList.class);
		
		return status.getRequestList();


		
	}

	@Override
	public Boolean deleteRequestById(Long requestId) {
		
		Boolean status=restTemplate
				.getForObject(env.getProperty("rest.host")+"/request/freightRequestByVendor/deleteById/"+requestId, Boolean.class);
		
		return status;

	}

	@Override
	public Boolean clearTestData() {

		Boolean status=restTemplate
				.getForObject(env.getProperty("rest.host")+"/request/freightRequestByVendor/clearTestData", Boolean.class);
		
		return status;

	}

	@Override
	public Integer count() {

		Integer status=restTemplate
				.getForObject(env.getProperty("rest.host")+"/request/freightRequestByVendor/count", Integer.class);
		
		return status;
		
	}

	@Override
	public List<FreightRequestByVendorDTO> getMatchingVendorReqForCustReq(
			FreightRequestByCustomerDTO freightRequestByCustomer) {
	
		FreightRequestByVendorList status=restTemplate.postForObject(env.getProperty("rest.host")+"/request/freightRequestByVendor/getMatchingVendorReqForCustomerReq",
				freightRequestByCustomer, FreightRequestByVendorList.class);
		
		return status.getRequestList();
		
		
		
		
	}

}
