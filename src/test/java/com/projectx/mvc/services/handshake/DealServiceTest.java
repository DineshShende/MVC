package com.projectx.mvc.services.handshake;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.mvc.config.BasicConfig;
import com.projectx.rest.domain.handshake.DealInfoAndVendorContactDetailsDTO;
import com.projectx.rest.domain.handshake.TriggerDealDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicConfig.class)
@ActiveProfiles(value="Dev")
public class DealServiceTest {

	@Autowired
	DealService dealService;
	
	@Test
	public void test() {
		
	}
	
	@Test
	public void triggerDeal()
	{
		
		DealInfoAndVendorContactDetailsDTO contactDetailsDTO= dealService.triggerDeal(new TriggerDealDTO(1L, 2L, 1, 3L));
		
		System.out.println(contactDetailsDTO);
	}

}
