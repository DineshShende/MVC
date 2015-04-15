package com.projectx.mvc.controller.completeregister;

import static com.projectx.mvc.fixtures.completeregister.VehicleDetailsDataFixtures.*;
import static com.projectx.mvc.fixtures.completeregister.DriverDetailsDataFixtures.standardEntityIdDTO;
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
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.rest.domain.completeregister.DriverDetailsDTO;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class VehicleDetailsControllerWACTest {

	@Autowired
	private WebApplicationContext wac;

	MockMvc mockMvc;
	
	@Autowired
	VendorDetailsService vendorDetailsService; 
	
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

		vendorDetailsService.vehicleClearTestData();
	}
	
	@Test
	public void environmentTest() {
		
	}

	@Test
	public void save() throws Exception
	{

		this.mockMvc.perform(
				post("/vehicle/save")
				.content("{\"ownerFirstName\":\"Abraham\",\"ownerMiddleName\":\"Benjamin\",\"ownerLastName\":\"Deviliers\",\"vehicleBrandId\":{\"vehicleTypeId\":{\"vehicleTypeName\":\"VehcileTypeName\"},\"vehicleBrandName\":\"Tata tempo\",\"modelNumber\":\"407\"},\"vehicleBodyType\":\"OPEN\",\"isBodyTypeFlexible\":true,\"registrationNumber\":\"MH12HG4563\",\"chassisNumber\":\"MH12HG4563JDFR634\",\"loadCapacityInTons\":100,\"length\":100,\"width\":40,\"height\":10,\"numberOfWheels\":8,\"permitType\":\"NATIONAL\",\"insuranceStatus\":true,\"insuranceNumber\":\"ADRF3442537JD\",\"insuranceCompany\":\"LIC\",\"vendorId\":213,\"commodityList\":[\"Wheat\",\"Grain\",\"Cement\"],\"updatedBy\":\"CUST_ONLINE\"}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.status").value("sucess"))
    .andExpect(jsonPath("$.errorMessage").value(""));

    
	}
	
	@Test
	public void getById() throws Exception
	{
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		this.mockMvc.perform(
				post("/vehicle/getById")
				.content(standardEntityIdDTO(new EntityIdDTO(vehicleDetailsDTO.getVehicleId())))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.ownerFirstName").value(standardVehicleDetails().getOwnerFirstName()))
    .andExpect(jsonPath("$.ownerMiddleName").value(standardVehicleDetails().getOwnerMiddleName()))
    .andExpect(jsonPath("$.ownerLastName").value(standardVehicleDetails().getOwnerLastName()))
    .andExpect(jsonPath("$.vehicleBrandId.vehicleTypeId.vehicleTypeName").value(standardVehicleDetails().getVehicleBrandId().getVehicleTypeId().getVehicleTypeName()))
    .andExpect(jsonPath("$.vehicleBrandId.vehicleBrandName").value(standardVehicleDetails().getVehicleBrandId().getVehicleBrandName()))
    .andExpect(jsonPath("$.vehicleBrandId.modelNumber").value(standardVehicleDetails().getVehicleBrandId().getModelNumber()))
    .andExpect(jsonPath("$.vehicleBodyType").value(standardVehicleDetails().getVehicleBodyType()))
    .andExpect(jsonPath("$.isBodyTypeFlexible").value(standardVehicleDetails().getIsBodyTypeFlexible()))
    .andExpect(jsonPath("$.registrationNumber").value(standardVehicleDetails().getRegistrationNumber()))
    .andExpect(jsonPath("$.chassisNumber").value(standardVehicleDetails().getChassisNumber()))
    .andExpect(jsonPath("$.loadCapacityInTons").value(standardVehicleDetails().getLoadCapacityInTons()))
    .andExpect(jsonPath("$.length").value(standardVehicleDetails().getLength()))
    .andExpect(jsonPath("$.width").value(standardVehicleDetails().getWidth()))
    .andExpect(jsonPath("$.height").value(standardVehicleDetails().getHeight()))
    .andExpect(jsonPath("$.numberOfWheels").value(standardVehicleDetails().getNumberOfWheels()))
    .andExpect(jsonPath("$.permitType").value(standardVehicleDetails().getPermitType()))
	.andExpect(jsonPath("$.insuranceStatus").value(standardVehicleDetails().getInsuranceStatus()))
	.andExpect(jsonPath("$.insuranceNumber").value(standardVehicleDetails().getInsuranceNumber()))
	.andExpect(jsonPath("$.insuranceCompany").value(standardVehicleDetails().getInsuranceCompany()))
	//.andExpect(jsonPath("$.commodityList").value(standardVehicleDetails().getCommodityList()))
	.andExpect(jsonPath("$.updatedBy").value(standardVehicleDetails().getUpdatedBy()));
		
	}
	
	@Test
	public void getByVendorId() throws Exception
	{
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		this.mockMvc.perform(
				post("/vehicle/getByVendorId")
				.content(standardEntityIdDTO(new EntityIdDTO(vehicleDetailsDTO.getVendorId())))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.[0].ownerFirstName").value(standardVehicleDetails().getOwnerFirstName()))
    .andExpect(jsonPath("$.[0].ownerMiddleName").value(standardVehicleDetails().getOwnerMiddleName()))
    .andExpect(jsonPath("$.[0].ownerLastName").value(standardVehicleDetails().getOwnerLastName()))
    .andExpect(jsonPath("$.[0].vehicleBrandId.vehicleTypeId.vehicleTypeName").value(standardVehicleDetails().getVehicleBrandId().getVehicleTypeId().getVehicleTypeName()))
    .andExpect(jsonPath("$.[0].vehicleBrandId.vehicleBrandName").value(standardVehicleDetails().getVehicleBrandId().getVehicleBrandName()))
    .andExpect(jsonPath("$.[0].vehicleBrandId.modelNumber").value(standardVehicleDetails().getVehicleBrandId().getModelNumber()))
    .andExpect(jsonPath("$.[0].vehicleBodyType").value(standardVehicleDetails().getVehicleBodyType()))
    .andExpect(jsonPath("$.[0].isBodyTypeFlexible").value(standardVehicleDetails().getIsBodyTypeFlexible()))
    .andExpect(jsonPath("$.[0].registrationNumber").value(standardVehicleDetails().getRegistrationNumber()))
    .andExpect(jsonPath("$.[0].chassisNumber").value(standardVehicleDetails().getChassisNumber()))
    .andExpect(jsonPath("$.[0].loadCapacityInTons").value(standardVehicleDetails().getLoadCapacityInTons()))
    .andExpect(jsonPath("$.[0].length").value(standardVehicleDetails().getLength()))
    .andExpect(jsonPath("$.[0].width").value(standardVehicleDetails().getWidth()))
    .andExpect(jsonPath("$.[0].height").value(standardVehicleDetails().getHeight()))
    .andExpect(jsonPath("$.[0].numberOfWheels").value(standardVehicleDetails().getNumberOfWheels()))
    .andExpect(jsonPath("$.[0].permitType").value(standardVehicleDetails().getPermitType()))
	.andExpect(jsonPath("$.[0].insuranceStatus").value(standardVehicleDetails().getInsuranceStatus()))
	.andExpect(jsonPath("$.[0].insuranceNumber").value(standardVehicleDetails().getInsuranceNumber()))
	.andExpect(jsonPath("$.[0].insuranceCompany").value(standardVehicleDetails().getInsuranceCompany()))
	//.andExpect(jsonPath("$.commodityList").value(standardVehicleDetails().getCommodityList()))
	.andExpect(jsonPath("$.[0].updatedBy").value(standardVehicleDetails().getUpdatedBy()));	
	}
	
	@Test
	public void deleteById() throws Exception
	{
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		this.mockMvc.perform(
				post("/vehicle/deleteById")
				.content(standardEntityIdDTO(new EntityIdDTO(vehicleDetailsDTO.getVehicleId())))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.status").value("sucess"))
	.andExpect(jsonPath("$.errorMessage").value(""));
		
	}

	
}
