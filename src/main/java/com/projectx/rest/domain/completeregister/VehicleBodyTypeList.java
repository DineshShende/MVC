package com.projectx.rest.domain.completeregister;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.projectx.mvc.domain.completeregister.VehicleBodyType;


public class VehicleBodyTypeList {
	
	private List<VehicleBodyType> list;

	
	
	public VehicleBodyTypeList() {
		super();
	}


	@JsonCreator
	public VehicleBodyTypeList(List<VehicleBodyType> list) {
		super();
		this.list = list;
	}


	public List<VehicleBodyType> getList() {
		return list;
	}


	public void setList(List<VehicleBodyType> list) {
		this.list = list;
	}


	@Override
	public String toString() {
		return "VehicleBodyTypeList [list=" + list + "]";
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
		VehicleBodyTypeList other = (VehicleBodyTypeList) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}
	
	

}
