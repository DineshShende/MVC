package com.projectx.mvc.services.completeregister;

import static com.projectx.mvc.fixtures.completeregister.VehicleDetailsDataFixtures.VEHICLE_BRAND_NAME_OBJ;
import static com.projectx.mvc.fixtures.completeregister.VehicleDetailsDataFixtures.standVehicleType;
import static com.projectx.mvc.fixtures.completeregister.VehicleDetailsDataFixtures.standardVehicleBrandDetails;
import static com.projectx.mvc.fixtures.completeregister.VehicleDetailsDataFixtures.standardVehicleModelDetails;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.mvc.config.BasicConfig;
import com.projectx.mvc.domain.completeregister.VehicleBrand;
import com.projectx.mvc.domain.completeregister.VehicleModel;
import com.projectx.mvc.domain.completeregister.VehicleType;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;




@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicConfig.class)
@ActiveProfiles(value="Dev")
public class VehicleModelServiceTest {

	@Autowired
	VehicleModelService vehicleModelService;
	
	@Autowired
	VehicleBrandTypeService vehicleBrandService;
	
	@Autowired
	VehicleTypeService vehicleTypeService;
	
	VehicleBrand vehicleBrand=null;
	
	VehicleType vehicleType=null;

	
	@Test
	public void test() {
		
	}
	
	@Before
	public void beforeTest()
	{
		vehicleBrand=vehicleBrandService.save(VEHICLE_BRAND_NAME_OBJ);
		
		vehicleType=vehicleTypeService.save(standVehicleType());
		
	}
	
	@After
	public void afterTest()
	{
		
		vehicleBrandService.deleteById(vehicleBrand.getVehicleBrandId());
		
		vehicleTypeService.deleteById(vehicleType.getVehicleTypeId());
		
	}
	
	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void saveAndGet()
	{
		try{
			
			vehicleModelService.getById(standardVehicleModelDetails().getVehicleModeId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		
		
		VehicleModel vehicleModel=vehicleModelService.save(standardVehicleBrandDetails(vehicleBrand,vehicleType));
		
		assertEquals(vehicleModel, vehicleModelService.getById(vehicleModel.getVehicleModeId()));
		
		vehicleModelService.deleteById(vehicleModel.getVehicleModeId());
		
		try{
			
			vehicleModelService.getById(vehicleModel.getVehicleModeId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
	}
	
	@Test
	public void getAll()
	{
		VehicleModel vehicleModel=vehicleModelService.save(standardVehicleBrandDetails(vehicleBrand,vehicleType));
		
		List<VehicleModel> list=vehicleModelService.getAll();
		
		assertEquals(vehicleModel, list.get(0));
		
		vehicleModelService.deleteById(vehicleModel.getVehicleModeId());
		
	}
	
	@Test
	public void update()
	{
		try{
			
			vehicleModelService.getById(standardVehicleModelDetails().getVehicleModeId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		VehicleModel vehicleModel=vehicleModelService.save(standardVehicleBrandDetails(vehicleBrand,vehicleType));
		
		vehicleModel.setVehiclemodelName("Updated");
		
		vehicleModelService.save(vehicleModel);
		
		assertEquals(vehicleModel, vehicleModelService.getById(vehicleModel.getVehicleModeId()));
		
		vehicleModelService.deleteById(vehicleModel.getVehicleModeId());
		
		try{
			
			vehicleModelService.getById(vehicleModel.getVehicleModeId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
	}
	
	@Test
	public void delete()
	{
		
		try{
			
			vehicleModelService.getById(standardVehicleModelDetails().getVehicleModeId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		
		VehicleModel vehicleModel=vehicleModelService.save(standardVehicleBrandDetails(vehicleBrand,vehicleType));
		
		assertEquals(vehicleModel, vehicleModelService.getById(vehicleModel.getVehicleModeId()));
		
		vehicleModelService.deleteById(vehicleModel.getVehicleModeId());
		
		try{
			
			vehicleModelService.getById(vehicleModel.getVehicleModeId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
	}


}
