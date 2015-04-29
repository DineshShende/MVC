package com.projectx.mvc.fixtures.handshake;

import com.google.gson.Gson;
import com.projectx.rest.domain.handshake.TriggerDealDTO;

public class DealDataFixture {
	
	
	static Gson gson=new Gson();
	
	public static TriggerDealDTO standardTriggerDealDTO(Long customerReqId,Long vendorReqId)
	{
		return new TriggerDealDTO(customerReqId, vendorReqId, 1, 213L);
	}
	
	public static String standardJsonTriggerDealDTO(TriggerDealDTO triggerDealDTO)
	{
		return gson.toJson(triggerDealDTO);
	}

}
