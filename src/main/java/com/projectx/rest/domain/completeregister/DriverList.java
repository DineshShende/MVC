package com.projectx.rest.domain.completeregister;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;


public class DriverList {
	
	ArrayList<DriverDetailsDTO> driverList;

	public DriverList() {

	}

	@JsonCreator
	public DriverList(ArrayList<DriverDetailsDTO> driverList) {

		this.driverList = driverList;
	}

	public ArrayList<DriverDetailsDTO> getDriverList() {
		return driverList;
	}

	public void setDriverList(ArrayList<DriverDetailsDTO> driverList) {
		this.driverList = driverList;
	}

	@Override
	public String toString() {
		return "DriverList [driverList=" + driverList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((driverList == null) ? 0 : driverList.hashCode());
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
		DriverList other = (DriverList) obj;
		if (driverList == null) {
			if (other.driverList != null)
				return false;
		} else if (!driverList.equals(other.driverList))
			return false;
		return true;
	}
	
	

}
