package com.projectx.mvc.services.completeregister;

import static com.projectx.mvc.fixtures.completeregister.VehicleDetailsDataFixtures.VEHICLE_PERMIT_TYPE_NATIONAL_OBJ;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.mvc.config.BasicConfig;
import com.projectx.mvc.domain.completeregister.VehiclePermitType;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicConfig.class)
@ActiveProfiles(value="Dev")
public class VehiclePermitServiceTest {

	@Autowired
	VehiclePermitTypeService vehiclePermitTypeService;
	
	@Test
	public void test() {
		
	}

	@Test
	public void saveAndGet()
	{
		try{
			vehiclePermitTypeService.getById(VEHICLE_PERMIT_TYPE_NATIONAL_OBJ.getVehiclePermitTypeId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
				
		VehiclePermitType vehiclePermitType=vehiclePermitTypeService.save(VEHICLE_PERMIT_TYPE_NATIONAL_OBJ);
		
		assertEquals(vehiclePermitType, vehiclePermitTypeService.getById(vehiclePermitType.getVehiclePermitTypeId()));
		
		vehiclePermitTypeService.deleteById(vehiclePermitType.getVehiclePermitTypeId());
		
		try{
			vehiclePermitTypeService.getById(vehiclePermitType.getVehiclePermitTypeId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		
	}
	
	@Test
	public void getAll()
	{
		VehiclePermitType vehiclePermitType=vehiclePermitTypeService.save(VEHICLE_PERMIT_TYPE_NATIONAL_OBJ);

		List<VehiclePermitType> list=vehiclePermitTypeService.getAll();
		
		assertEquals(vehiclePermitType, list.get(0));
		
		vehiclePermitTypeService.deleteById(vehiclePermitType.getVehiclePermitTypeId());
		
	}
	
	@Test
	public void update()
	{
		try{
			vehiclePermitTypeService.getById(VEHICLE_PERMIT_TYPE_NATIONAL_OBJ.getVehiclePermitTypeId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		VehiclePermitType vehiclePermitType=vehiclePermitTypeService.save(VEHICLE_PERMIT_TYPE_NATIONAL_OBJ);
		
		vehiclePermitType.setVehiclePermitTypeName("Updated");
		
		vehiclePermitTypeService.save(vehiclePermitType);
		
		assertEquals(vehiclePermitType, vehiclePermitTypeService.getById(vehiclePermitType.getVehiclePermitTypeId()));
		
		vehiclePermitTypeService.deleteById(vehiclePermitType.getVehiclePermitTypeId());
		
		try{
			vehiclePermitTypeService.getById(vehiclePermitType.getVehiclePermitTypeId());
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
			vehiclePermitTypeService.getById(VEHICLE_PERMIT_TYPE_NATIONAL_OBJ.getVehiclePermitTypeId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		VehiclePermitType vehiclePermitType=vehiclePermitTypeService.save(VEHICLE_PERMIT_TYPE_NATIONAL_OBJ);
		
		assertEquals(vehiclePermitType, vehiclePermitTypeService.getById(vehiclePermitType.getVehiclePermitTypeId()));
		
		vehiclePermitTypeService.deleteById(vehiclePermitType.getVehiclePermitTypeId());
		
		try{
			vehiclePermitTypeService.getById(vehiclePermitType.getVehiclePermitTypeId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
	}

	
}
