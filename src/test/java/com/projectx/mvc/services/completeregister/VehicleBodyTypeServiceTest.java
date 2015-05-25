package com.projectx.mvc.services.completeregister;

import static com.projectx.mvc.fixtures.completeregister.VehicleDetailsDataFixtures.VEHICLE_BODY_TYPE_OPEN_OBJ;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.mvc.config.BasicConfig;
import com.projectx.mvc.domain.completeregister.VehicleBodyType;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicConfig.class)
@ActiveProfiles(value="Dev")
public class VehicleBodyTypeServiceTest {

	@Autowired
	VehicleBodyTypeService vehicleBodyService;
	
	@Test
	public void test() {

	}
	
	@Test
	public void saveAndUpdate()
	{
		try{
			vehicleBodyService.getById(VEHICLE_BODY_TYPE_OPEN_OBJ.getVehicleBodyTypeId());
			
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		
		
		VehicleBodyType vehicleBodyType=vehicleBodyService.save(VEHICLE_BODY_TYPE_OPEN_OBJ);
		
		assertEquals(vehicleBodyType, vehicleBodyService.getById(vehicleBodyType.getVehicleBodyTypeId()));
		
		vehicleBodyService.deleteById(vehicleBodyType.getVehicleBodyTypeId());
		
		try{
			vehicleBodyService.getById(vehicleBodyType.getVehicleBodyTypeId());
			
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		
	}
	
	@Test
	public void getAll()
	{
		VehicleBodyType vehicleBodyType=vehicleBodyService.save(VEHICLE_BODY_TYPE_OPEN_OBJ);
		
		List<VehicleBodyType> list=vehicleBodyService.getAll();
		
		assertEquals(vehicleBodyType, list.get(0));
		
		vehicleBodyService.deleteById(vehicleBodyType.getVehicleBodyTypeId());
		
	}
	
	@Test
	public void update()
	{
		try{
			vehicleBodyService.getById(VEHICLE_BODY_TYPE_OPEN_OBJ.getVehicleBodyTypeId());
			
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		
		VehicleBodyType vehicleBodyType=vehicleBodyService.save(VEHICLE_BODY_TYPE_OPEN_OBJ);
		
		vehicleBodyType.setVehicleBodyTypeName("Updated");
		
		vehicleBodyService.save(vehicleBodyType);
		
		assertEquals(vehicleBodyType, vehicleBodyService.getById(vehicleBodyType.getVehicleBodyTypeId()));
		
		vehicleBodyService.deleteById(vehicleBodyType.getVehicleBodyTypeId());
		
		try{
			vehicleBodyService.getById(vehicleBodyType.getVehicleBodyTypeId());
			
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
			vehicleBodyService.getById(VEHICLE_BODY_TYPE_OPEN_OBJ.getVehicleBodyTypeId());
			
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		
		VehicleBodyType vehicleBodyType=vehicleBodyService.save(VEHICLE_BODY_TYPE_OPEN_OBJ);
		
		assertEquals(vehicleBodyType, vehicleBodyService.getById(vehicleBodyType.getVehicleBodyTypeId()));
		
		vehicleBodyService.deleteById(vehicleBodyType.getVehicleBodyTypeId());
		
		try{
			vehicleBodyService.getById(vehicleBodyType.getVehicleBodyTypeId());
			
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
	}


}
