package com.projectx.mvc.controller.request;

import static com.projectx.mvc.fixtures.completeregister.DriverDetailsDataFixtures.standardEntityIdDTO;
import static com.projectx.mvc.fixtures.completeregister.VehicleDetailsDataFixtures.standardVehicleDetails;
import static com.projectx.mvc.fixtures.request.FreightRequestByVendorDataFixture.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.services.request.FreightRequestByVendorService;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class FreightRequestByVendorControllerWACTest {

	@Autowired
	private WebApplicationContext wac;

	MockMvc mockMvc;

	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		freightRequestByVendorService.clearTestData();
		vendorDetailsService.clearTestData();
		vendorDetailsService.vehicleClearTestData();

				
	}
	
	
	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void save() throws Exception
	{
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		System.out.println("{\"vehicleRegistrationNumber\":\""+vehicleDetailsDTO.getRegistrationNumber()+"\",\"source\":411045,\"destination\":413102,\"driverId\":765,\"availableDate\":"+new Date().getTime()+",\"availableTime\":\"9:PM\",\"pickupRangeInKm\":10,\"vendorId\":231,\"updatedBy\":\"CUST_ONLINE\"}");
		
		
		
		this.mockMvc.perform(
	            post("/request/freightrequestByVendor")
	                    .content("{\"vehicleRegistrationNumber\":\""+vehicleDetailsDTO.getRegistrationNumber()+"\",\"source\":411045,\"destination\":413102,\"driverId\":765,\"availableDate\":"+new Date().getTime()+",\"availableTime\":\"9:PM\",\"pickupRangeInKm\":10,\"vendorId\":231,\"updatedBy\":\"CUST_ONLINE\"}")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());

		
		
	}
	
	@Test
	public void getById() throws Exception
	{
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		FreightRequestByVendorDTO  freightRequestByVendorDTO=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		
		this.mockMvc.perform(
	            post("/request/freightrequestByVendor/getById")
	                    .content(standardEntityIdDTO(new EntityIdDTO(freightRequestByVendorDTO.getRequestId())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());
	
	}
	
	@Test
	public void getByVendorId() throws Exception
	{
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		FreightRequestByVendorDTO  freightRequestByVendorDTO=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		
		this.mockMvc.perform(
	            post("/request/freightrequestByVendor/getByVendorId")
	                    .content(standardEntityIdDTO(new EntityIdDTO(freightRequestByVendorDTO.getVendorId())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());
	
	}
	
	@Test
	public void deleteById() throws Exception
	{
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		FreightRequestByVendorDTO  freightRequestByVendorDTO=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		
		this.mockMvc.perform(
	            post("/request/freightrequestByVendor/deleteRequestById")
	                    .content(standardEntityIdDTO(new EntityIdDTO(freightRequestByVendorDTO.getRequestId())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());
	
	}

}
