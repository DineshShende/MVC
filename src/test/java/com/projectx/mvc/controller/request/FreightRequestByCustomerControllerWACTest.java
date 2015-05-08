package com.projectx.mvc.controller.request;

import static com.projectx.mvc.fixtures.request.FreightRequestByCustomerDataFixture.*;
import static com.projectx.mvc.fixtures.completeregister.DriverDetailsDataFixtures.*;
import static com.projectx.mvc.fixtures.request.FreightRequestByVendorDataFixture.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.projectx.mvc.config.Application;
import com.projectx.mvc.domain.request.FreightRequestByCustomer;
import com.projectx.mvc.services.request.FreightRequestByCustomerService;
import com.projectx.rest.domain.completeregister.EntityIdDTO;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class FreightRequestByCustomerControllerWACTest {

	@Autowired
	private WebApplicationContext wac;

	MockMvc mockMvc;

	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		freightRequestByCustomerService.clearTestData();		
	}
	
	
	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void save() throws Exception
	{
		System.out.println("{\"source\":411045,\"destination\":413102,\"pickupDate\":"+new Date().getTime()+",\"noOfVehicles\":1,\"loadType\":\"FullTruckLoad\",\"capacity\":100,\"length\":100,\"width\":60,\"height\":10,\"bodyType\":\"Open\",\"vehicleBrand\":\"Tata Temp\",\"model\":\"407\",\"commodity\":\"Fertiliser\",\"pickupTime\":\"1:00PM\",\"customerId\":212,\"status\":\"NEW\",\"updatedBy\":\"CUST_ONLINE\"}");
		
		this.mockMvc.perform(
	            post("/request/freightrequestByCustomer")
	                    .content("{\"source\":411045,\"destination\":413102,\"pickupDate\":"+new Date().getTime()+",\"noOfVehicles\":1,\"loadType\":\"FullTruckLoad\",\"capacity\":100,\"length\":100,\"width\":60,\"height\":10,\"bodyType\":\"Open\",\"vehicleBrand\":\"Tata Temp\",\"model\":\"407\",\"commodity\":\"Fertiliser\",\"pickupTime\":\"1:00PM\",\"customerId\":212,\"status\":\"NEW\",\"requestedBy\":1,\"requestedById\":1}")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result.entityId").exists())
	            .andExpect(jsonPath("$.errorMessage").value(""));
	    
	}
	
	@Test
	public void getMatchingVendorRequestsByCustomerRequestId() throws Exception
	{
		FreightRequestByCustomer request= freightRequestByCustomerService.save(standardFreightRequestByCustomer());
		
		this.mockMvc.perform(
	            post("/request/freightrequestByCustomer/getMatchingVendorRequestsByCustomerRequestId")
	                    .content(standardEntityIdDTO(new EntityIdDTO(request.getRequestId())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());
	    
	}
	
	@Test
	public void getById() throws Exception
	{
		FreightRequestByCustomer request= freightRequestByCustomerService.save(standardFreightRequestByCustomer());
		
		this.mockMvc.perform(
	            post("/request/freightrequestByCustomer/getById")
	                    .content(standardEntityIdDTO(new EntityIdDTO(request.getRequestId())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());
	    
		
	}
	
	@Test
	public void getByCustomerId() throws Exception
	{
		FreightRequestByCustomer request= freightRequestByCustomerService.save(standardFreightRequestByCustomer());
		
		this.mockMvc.perform(
	            post("/request/freightrequestByCustomer/getByCustomerId")
	                    .content(standardEntityIdDTO(new EntityIdDTO(request.getCustomerId())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());
	    
		
	}
	
	@Test
	public void deleteRequestById() throws Exception
	{
		FreightRequestByCustomer request= freightRequestByCustomerService.save(standardFreightRequestByCustomer());
		
		this.mockMvc.perform(
	            post("/request/freightrequestByCustomer/deleteRequestById")
	                    .content(standardEntityIdDTO(new EntityIdDTO(request.getRequestId())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());
	    
		
	}

}
