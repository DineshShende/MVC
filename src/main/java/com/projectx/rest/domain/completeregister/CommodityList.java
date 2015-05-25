package com.projectx.rest.domain.completeregister;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.projectx.mvc.domain.completeregister.Commodity;


public class CommodityList {

	private List<Commodity> list;

	public CommodityList() {
		super();
	}

	@JsonCreator
	public CommodityList(List<Commodity> list) {
		super();
		this.list = list;
	}

	public List<Commodity> getList() {
		return list;
	}

	public void setList(List<Commodity> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "CommodityList [list=" + list + "]";
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
		CommodityList other = (CommodityList) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}
	
	
	
	
}
