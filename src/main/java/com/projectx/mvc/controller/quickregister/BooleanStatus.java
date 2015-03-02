package com.projectx.mvc.controller.quickregister;

public class BooleanStatus {

	private Boolean status;

	
	
	public BooleanStatus() {
		super();
	}



	public BooleanStatus(Boolean status) {
		super();
		this.status = status;
	}



	public Boolean getStatus() {
		return status;
	}



	public void setStatus(Boolean status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "BooleanStatus [status=" + status + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		BooleanStatus other = (BooleanStatus) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	
}
