package com.projectx.mvc.services.handshake;

import org.springframework.stereotype.Service;

import com.projectx.rest.domain.handshake.DealInfoAndVendorContactDetailsDTO;
import com.projectx.rest.domain.handshake.TriggerDealDTO;


@Service
public interface DealService {
	
	 DealInfoAndVendorContactDetailsDTO triggerDeal(TriggerDealDTO triggerDealDTO);

}
