package com.projectx.rest.domain.completeregister;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.projectx.mvc.domain.completeregister.VehiclePermitType;


public class VehiclePermitTypeList {
	
	private List<VehiclePermitType> list;

	
	
	public VehiclePermitTypeList() {
		super();
	}



	@JsonCreator
	public VehiclePermitTypeList(List<VehiclePermitType> list) {
		super();
		this.list = list;
	}



	public List<VehiclePermitType> getList() {
		return list;
	}



	public void setList(List<VehiclePermitType> list) {
		this.list = list;
	}



	@Override
	public String toString() {
		return "VehiclePermitTypeList []";
	}
	
	

}
