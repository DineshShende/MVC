package com.projectx.mvc.controller.completeregister;

import static com.projectx.mvc.fixtures.completeregister.DriverDetailsDataFixtures.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import java.util.Date;





import com.projectx.mvc.config.Application;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.DriverDetailsDTO;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.completeregister.EntityIdTypeDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class DriverDetailsControllerWACTest {

	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Autowired
	QuickRegisterService quickRegisterService;

	MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		vendorDetailsService.driverClearTestData();
		quickRegisterService.clearTestData();
		
	}
	
	@Test
	public void environmentTest() {
		
	}

	@Test
	public void save() throws Exception
	{
		this.mockMvc.perform(
				post("/driver/save")
				.content("{\"firstName\":\"Abc\",\"middleName\":\"def\",\"lastName\":\"def\",\"dateOfBirth\":"+new Date().getTime()+",\"bloodGroup\":\"A+\", \"homeAddress\":{\"customerType\":3,\"addressLine\":\"AT-GHADGE\",\"city\":\"Baramati\",\"district\":\"Pune\",\"state\":\"Maharashtra\",\"pincode\":413133,\"updatedBy\":1,\"updatedById\":1},\"mobile\":9980907076,\"homeContactNumber\":9090909090,\"licenceNumber\":\"MHGDDSS\",\"drivingSince\":"+new Date().getTime()+",\"employedSince\":"+new Date().getTime()+",\"isFreightRequestPermissionGiven\":false,\"isDealFinalizationPermissionGiven\":false,\"language\":\"Marathi\",\"licenceDOI\":"+new Date().getTime()+",\"licenceValidTill\":"+new Date().getTime()+",\"covList\":[\"MCWG\",\"LMV\"],\"vendorId\":231,\"requestedBy\":1,\"requestedById\":1})")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.result").value("sucess"))
    .andExpect(jsonPath("$.errorMessage").value(""));

    
	}
	
	@Test
	public void addDriverSimplified() throws Exception
	{
		this.mockMvc.perform(
	            post("/driver/save/simplified")
	                   // .content(standardDriverSimplifiedJson(standardDriverSimplified()))
	            		.content("{\"bloodGroup\":\"A+\",\"mobile\":9876542312,\"homeContactNumber\":9896592312,\"drivingSince\":"+new Date().getTime()+",\"employedSince\":"+new Date().getTime()+",\"isFreightRequestPermissionGiven\":false,\"isDealFinalizationPermissionGiven\":false,\"language\":\"TELGU\",\"vendorId\":213,\"requestedBy\":1,\"requestedById\":213}")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result").value("sucess"))
	            .andExpect(jsonPath("$.errorMessage").value(""));
	}
	
	@Test
	public void l1DataEntry() throws Exception
	{
		DriverDetailsDTO driverDetails=vendorDetailsService.addDriverSimplified(standardDriverSimplified());
		
		this.mockMvc.perform(
	            post("/driver/save/l1dataentry")
	                    //.content(standardL1DriverCompleteRegistrationJson(standardL1DriverCompleteRegistration(driverDetails.getDriverId())))
	            		.content("{\"driverId\":"+driverDetails.getDriverId()+",\"firstName\":\"ARUN\",\"middleName\":\"M.\",\"lastName\":\"GUPTA\",\"dateOfBirth\":"+new Date().getTime()+",\"addressLine\":\"AT-GHADGE WASTI PO-SAWAL\",\"city\":\"Baramati\",\"district\":\"Pune\",\"state\":\"Maharashtra\",\"pincode\":413133,\"licenceNumber\":\"MH42A6543\",\"licenceDOI\":"+new Date().getTime()+",\"licenceValidTill\":"+new Date().getTime()+",\"covList\":[\"MCWG\",\"LMV\"],\"isDetailRegistrationCompleted\":false}")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result").value("sucess"))
	            .andExpect(jsonPath("$.errorMessage").value(""));
	}
	
	@Test
	public void getById() throws Exception
	{
		DriverDetailsDTO driverDetailsDTO=vendorDetailsService.addDriver(standardDriverDetails());
		
		this.mockMvc.perform(
				post("/driver/getById")
				.content(standardEntityIdDTO(new EntityIdDTO(driverDetailsDTO.getDriverId())))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.result.firstName").value(standardDriverDetails().getFirstName()))
    .andExpect(jsonPath("$.result.middleName").value(standardDriverDetails().getMiddleName()))
    .andExpect(jsonPath("$.result.lastName").value(standardDriverDetails().getLastName()))
    .andExpect(jsonPath("$.result.bloodGroup").value(standardDriverDetails().getBloodGroup()))
    .andExpect(jsonPath("$.result.mobile").value(standardDriverDetails().getMobile()))
    .andExpect(jsonPath("$.result.homeAddress").exists())
    .andExpect(jsonPath("$.result.isMobileVerified").value(standardDriverDetails().getIsMobileVerified()))
    .andExpect(jsonPath("$.result.homeContactNumber").value(standardDriverDetails().getHomeContactNumber()))
    .andExpect(jsonPath("$.result.licenceNumber").value(standardDriverDetails().getLicenceNumber()))
    .andExpect(jsonPath("$.result.isFreightRequestPermissionGiven").value(standardDriverDetails().getIsFreightRequestPermissionGiven()))
    .andExpect(jsonPath("$.result.isDealFinalizationPermissionGiven").value(standardDriverDetails().getIsDealFinalizationPermissionGiven()))
    .andExpect(jsonPath("$.result.language").value(standardDriverDetails().getLanguage()))
    .andExpect(jsonPath("$.result.vendorId").exists())
    .andExpect(jsonPath("$.result.requestedBy").value(standardDriverDetails().getUpdatedBy()))
    .andExpect(jsonPath("$.result.dateOfBirth").exists())
    .andExpect(jsonPath("$.result.drivingSince").exists())
	.andExpect(jsonPath("$.result.employedSince").exists());
		
	}
	
	@Test
	public void getByVendorId() throws Exception
	{
		DriverDetailsDTO driverDetailsDTO=vendorDetailsService.addDriver(standardDriverDetails());
		
		this.mockMvc.perform(
				post("/driver/getByVendorId")
				.content(standardEntityIdDTO(new EntityIdDTO(driverDetailsDTO.getVendorId())))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.[0].driverId").exists())
    .andExpect(jsonPath("$.[0].firstName").value(standardDriverDetails().getFirstName()))
    .andExpect(jsonPath("$.[0].middleName").value(standardDriverDetails().getMiddleName()))
    .andExpect(jsonPath("$.[0].lastName").value(standardDriverDetails().getLastName()))
    .andExpect(jsonPath("$.[0].bloodGroup").value(standardDriverDetails().getBloodGroup()))
    .andExpect(jsonPath("$.[0].mobile").value(standardDriverDetails().getMobile()))
    .andExpect(jsonPath("$.[0].homeAddress").exists())
    .andExpect(jsonPath("$.[0].isMobileVerified").value(standardDriverDetails().getIsMobileVerified()))
    .andExpect(jsonPath("$.[0].homeContactNumber").value(standardDriverDetails().getHomeContactNumber()))
    .andExpect(jsonPath("$.[0].licenceNumber").value(standardDriverDetails().getLicenceNumber()))
    .andExpect(jsonPath("$.[0].isFreightRequestPermissionGiven").value(standardDriverDetails().getIsFreightRequestPermissionGiven()))
    .andExpect(jsonPath("$.[0].isDealFinalizationPermissionGiven").value(standardDriverDetails().getIsDealFinalizationPermissionGiven()))
    .andExpect(jsonPath("$.[0].language").value(standardDriverDetails().getLanguage()))
    .andExpect(jsonPath("$.[0].vendorId").exists())
    .andExpect(jsonPath("$.[0].updatedBy").value(standardDriverDetails().getUpdatedBy()))
    .andExpect(jsonPath("$.[0].dateOfBirth").exists())
    .andExpect(jsonPath("$.[0].drivingSince").exists())
	.andExpect(jsonPath("$.[0].employedSince").exists());
		
	}
	
	@Test
	public void deleteById() throws Exception
	{
		DriverDetailsDTO driverDetailsDTO=vendorDetailsService.addDriver(standardDriverDetails());
		
		this.mockMvc.perform(
				post("/driver/deleteById")
				.content(standardEntityIdDTO(new EntityIdDTO(driverDetailsDTO.getDriverId())))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.result").value("sucess"))
	.andExpect(jsonPath("$.errorMessage").value(""));
		
	}
	
}
