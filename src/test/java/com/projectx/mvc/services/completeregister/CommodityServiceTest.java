package com.projectx.mvc.services.completeregister;

import static com.projectx.mvc.fixtures.completeregister.VehicleDetailsDataFixtures.standardCommodity1;
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
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicConfig.class)
@ActiveProfiles(value="Dev")
public class CommodityServiceTest {

	@Autowired
	CommodityService commodityService;
	
	@Test
	public void test() {
		
	}

	@Test
	public void saveAndGet()
	{
		try{
			commodityService.getById(standardCommodity1().getCommodityId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		Commodity commodity=commodityService.save(standardCommodity1());
		
		assertEquals(commodity, commodityService.getById(commodity.getCommodityId()));
		
		commodityService.deleteById(commodity.getCommodityId());
		
		try{
			commodityService.getById(commodity.getCommodityId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
	}
	
	@Test
	public void getAll()
	{
		Commodity commodity=commodityService.save(standardCommodity1());

		List<Commodity> list=commodityService.getAll();
		
		assertEquals(commodity, list.get(0));
		
		commodityService.deleteById(commodity.getCommodityId());

		
	}
	
	@Test
	public void update()
	{
		try{
			commodityService.getById(standardCommodity1().getCommodityId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		Commodity commodity=commodityService.save(standardCommodity1());
		
		commodity.setCommodityName("Updated");
		
		commodityService.save(commodity);
		
		assertEquals(commodity, commodityService.getById(commodity.getCommodityId()));
		
		commodityService.deleteById(commodity.getCommodityId());
		
		try{
			commodityService.getById(commodity.getCommodityId());
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
			commodityService.getById(standardCommodity1().getCommodityId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		Commodity commodity=commodityService.save(standardCommodity1());
		
		assertEquals(commodity, commodityService.getById(commodity.getCommodityId()));
		
		commodityService.deleteById(commodity.getCommodityId());
		
		try{
			commodityService.getById(commodity.getCommodityId());
			assertEquals(0, 1);
			
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
	}

}
