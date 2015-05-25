package com.projectx.mvc.services.completeregister;

import static com.projectx.mvc.fixtures.completeregister.VehicleDetailsDataFixtures.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.mvc.config.BasicConfig;
import com.projectx.mvc.domain.completeregister.VehiclePermitType;
import com.projectx.mvc.domain.completeregister.VehicleType;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicConfig.class)
@ActiveProfiles(value="Dev")
public class VehicleTypeServiceTest {

	@Autowired
	VehicleTypeService vehicleTypeService;
	
	@Before
	public void setUp()
	{
		vehicleTypeService.deleteAll();
	}
	
	@Test
	public void test() {
		
	}
	
	

	@Test
	public void saveAndGet()
	{
		
		try{
			vehicleTypeService.getById(standVehicleTypeDetails().getVehicleTypeId());
			
			assertEquals(0, 1);
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		
		VehicleType vehicleType=vehicleTypeService.save(standVehicleType());
		
		assertEquals(vehicleType, vehicleTypeService.getById(vehicleType.getVehicleTypeId()));
		
		vehicleTypeService.deleteById(vehicleType.getVehicleTypeId());
		
				
		try{
			vehicleTypeService.getById(vehicleType.getVehicleTypeId());
			
			assertEquals(0, 1);
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
	}

	@Test
	public void getAll()
	{
		VehicleType vehicleType=vehicleTypeService.save(standVehicleType());
		
		List<VehicleType> list=vehicleTypeService.getAll();
		
		assertEquals(vehicleType, list.get(0));
		
		vehicleTypeService.deleteById(vehicleType.getVehicleTypeId());

		
	}
	
	@Test
	public void update()
	{
		
		try{
			vehicleTypeService.getById(standVehicleTypeDetails().getVehicleTypeId());
			
			assertEquals(0, 1);
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		VehicleType vehicleType=vehicleTypeService.save(standVehicleType());
		
		vehicleType.setVehicleTypeName("Updated");	
		
		vehicleTypeService.save(vehicleType);
		
		assertEquals(vehicleType, vehicleTypeService.getById(vehicleType.getVehicleTypeId()));
		
		vehicleTypeService.deleteById(vehicleType.getVehicleTypeId());
		
		try{
			vehicleTypeService.getById(vehicleType.getVehicleTypeId());
			
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
			vehicleTypeService.getById(standVehicleTypeDetails().getVehicleTypeId());
			
			assertEquals(0, 1);
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		VehicleType vehicleType=vehicleTypeService.save(standVehicleType());
		
		vehicleTypeService.deleteById(vehicleType.getVehicleTypeId());
		
		try{
			vehicleTypeService.getById(vehicleType.getVehicleTypeId());
			
			assertEquals(0, 1);
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
	}

}
