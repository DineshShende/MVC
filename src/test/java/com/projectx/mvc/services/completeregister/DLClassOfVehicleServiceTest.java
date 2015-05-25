package com.projectx.mvc.services.completeregister;

import static com.projectx.mvc.fixtures.completeregister.VehicleDetailsDataFixtures.standardDlClassOfVehicle;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.mvc.config.BasicConfig;
import com.projectx.mvc.domain.completeregister.Commodity;
import com.projectx.mvc.domain.completeregister.DLClassOfVehicle;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicConfig.class)
@ActiveProfiles(value="Dev")
public class DLClassOfVehicleServiceTest {

	@Autowired
	DLClassOfVehicleService dLClassOfVehicleService;
	
	@Test
	public void test() {
		
	}

	@Test
	public void saveAndGet()
	{
		try{
			dLClassOfVehicleService.getById(standardDlClassOfVehicle().getCovId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		DLClassOfVehicle dlClassOfVehicle=dLClassOfVehicleService.save(standardDlClassOfVehicle());
		
		assertEquals(dlClassOfVehicle, dLClassOfVehicleService.getById(dlClassOfVehicle.getCovId()));
		
		dLClassOfVehicleService.deleteById(dlClassOfVehicle.getCovId());
		
		try{
			dLClassOfVehicleService.getById(dlClassOfVehicle.getCovId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
	}
	
	@Test
	public void getAll()
	{
		DLClassOfVehicle dlClassOfVehicle=dLClassOfVehicleService.save(standardDlClassOfVehicle());

		List<DLClassOfVehicle> list=dLClassOfVehicleService.getAll();
		
		assertEquals(dlClassOfVehicle, list.get(0));
		
		dLClassOfVehicleService.deleteById(dlClassOfVehicle.getCovId());

		
	}
	
	@Test
	public void update()
	{
		try{
			dLClassOfVehicleService.getById(standardDlClassOfVehicle().getCovId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		DLClassOfVehicle dlClassOfVehicle=dLClassOfVehicleService.save(standardDlClassOfVehicle());
		
		dlClassOfVehicle.setCovName("MCWOG");
		
		dLClassOfVehicleService.save(dlClassOfVehicle);
		
		assertEquals(dlClassOfVehicle, dLClassOfVehicleService.getById(dlClassOfVehicle.getCovId()));
		
		dLClassOfVehicleService.deleteById(dlClassOfVehicle.getCovId());
		
		try{
			dLClassOfVehicleService.getById(dlClassOfVehicle.getCovId());
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
			dLClassOfVehicleService.getById(standardDlClassOfVehicle().getCovId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		DLClassOfVehicle dlClassOfVehicle=dLClassOfVehicleService.save(standardDlClassOfVehicle());
		
		assertEquals(dlClassOfVehicle, dLClassOfVehicleService.getById(dlClassOfVehicle.getCovId()));
		
		dLClassOfVehicleService.deleteById(dlClassOfVehicle.getCovId());
		
		try{
			dLClassOfVehicleService.getById(dlClassOfVehicle.getCovId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
	}


}
