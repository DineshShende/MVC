package com.projectx.rest.domain.completeregister;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.projectx.mvc.domain.completeregister.VehicleBrand;


public class VehicleBrandTypeList {

	List<VehicleBrand> list;

	
	
	
	public VehicleBrandTypeList() {
		super();
	}


	@JsonCreator
	public VehicleBrandTypeList(List<VehicleBrand> list) {
		super();
		this.list = list;
	}


	public List<VehicleBrand> getList() {
		return list;
	}


	public void setList(List<VehicleBrand> list) {
		this.list = list;
	}


	@Override
	public String toString() {
		return "VehicleBrandTypeList []";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VehicleBrandTypeList other = (VehicleBrandTypeList) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}

	
	
}
