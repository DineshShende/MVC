package com.projectx.mvc.services.completeregister;

import static com.projectx.mvc.fixtures.completeregister.VehicleDetailsDataFixtures.VEHICLE_BRAND_NAME_OBJ;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.mvc.config.BasicConfig;
import com.projectx.mvc.domain.completeregister.VehicleBrand;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicConfig.class)
@ActiveProfiles(value="Dev")
public class VehicleBrandTypeServiceTest {

	@Autowired
	VehicleBrandTypeService vehicleBrandService;
	
	@Test
	public void test() {
		
	}
	
	@Test
	public void saveAndGetById()
	{
			
		try{
			vehicleBrandService.getById(VEHICLE_BRAND_NAME_OBJ.getVehicleBrandId());
			
			assertEquals(0, 1);
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		
		VehicleBrand vehicleBrand=vehicleBrandService.save(VEHICLE_BRAND_NAME_OBJ);
		
		assertEquals(vehicleBrand,vehicleBrandService.getById(vehicleBrand.getVehicleBrandId()));
		
		vehicleBrandService.deleteById(vehicleBrand.getVehicleBrandId());
		
		
		try{
			vehicleBrandService.getById(vehicleBrand.getVehicleBrandId());
			
			assertEquals(0, 1);
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
	}

	@Test
	public void getAll()
	{
		VehicleBrand vehicleBrand=vehicleBrandService.save(VEHICLE_BRAND_NAME_OBJ);
		
		List<VehicleBrand> list=vehicleBrandService.getAll();
		
		assertEquals(vehicleBrand,vehicleBrandService.getById(vehicleBrand.getVehicleBrandId()));
		
		vehicleBrandService.deleteById(vehicleBrand.getVehicleBrandId());
		
	}
	
	@Test
	public void update()
	{
		try{
			vehicleBrandService.getById(VEHICLE_BRAND_NAME_OBJ.getVehicleBrandId());
			
			assertEquals(0, 1);
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		VehicleBrand vehicleBrand=vehicleBrandService.save(VEHICLE_BRAND_NAME_OBJ);
		
		vehicleBrand.setVehicleBrandName("Updated");
		
		vehicleBrandService.save(vehicleBrand);
		
		assertEquals(vehicleBrand, vehicleBrandService.getById(vehicleBrand.getVehicleBrandId()));
		
		vehicleBrandService.deleteById(vehicleBrand.getVehicleBrandId());
		
		try{
			vehicleBrandService.getById(vehicleBrand.getVehicleBrandId());
			
			assertEquals(0, 1);
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
	}
	
	@Test
	public void deleteById()
	{
		try{
			vehicleBrandService.getById(VEHICLE_BRAND_NAME_OBJ.getVehicleBrandId());
			
			assertEquals(0, 1);
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		VehicleBrand vehicleBrand=vehicleBrandService.save(VEHICLE_BRAND_NAME_OBJ);
		
		vehicleBrandService.deleteById(vehicleBrand.getVehicleBrandId());
		
		try{
			vehicleBrandService.getById(vehicleBrand.getVehicleBrandId());
			
			assertEquals(0, 1);
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
	}


}
