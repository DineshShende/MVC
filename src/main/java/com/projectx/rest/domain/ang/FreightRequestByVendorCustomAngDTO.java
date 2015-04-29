package com.projectx.rest.domain.ang;

import java.util.Date;

public class FreightRequestByVendorCustomAngDTO {

	private Integer source;

	private Date availableDate;

	private String availableTime;

	private VehicleDetailsCustomAngDTO vehicleDetailsId;

	public FreightRequestByVendorCustomAngDTO() {
		super();
	}

	public FreightRequestByVendorCustomAngDTO(Integer source,
			Date availableDate, String availableTime,
			VehicleDetailsCustomAngDTO vehicleDetailsId) {
		super();
		this.source = source;
		this.availableDate = availableDate;
		this.availableTime = availableTime;
		this.vehicleDetailsId = vehicleDetailsId;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Date getAvailableDate() {
		return availableDate;
	}

	public void setAvailableDate(Date availableDate) {
		this.availableDate = availableDate;
	}

	public String getAvailableTime() {
		return availableTime;
	}

	public void setAvailableTime(String availableTime) {
		this.availableTime = availableTime;
	}

	public VehicleDetailsCustomAngDTO getVehicleDetailsId() {
		return vehicleDetailsId;
	}

	public void setVehicleDetailsId(VehicleDetailsCustomAngDTO vehicleDetailsId) {
		this.vehicleDetailsId = vehicleDetailsId;
	}

	@Override
	public String toString() {
		return "FreightRequestByVendorCustomAngDTO [source=" + source
				+ ", availableDate=" + availableDate + ", availableTime="
				+ availableTime + ", vehicleDetailsId=" + vehicleDetailsId
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((availableDate == null) ? 0 : availableDate.hashCode());
		result = prime * result
				+ ((availableTime == null) ? 0 : availableTime.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime
				* result
				+ ((vehicleDetailsId == null) ? 0 : vehicleDetailsId.hashCode());
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
		FreightRequestByVendorCustomAngDTO other = (FreightRequestByVendorCustomAngDTO) obj;
		if (availableDate == null) {
			if (other.availableDate != null)
				return false;
		} else if (!availableDate.equals(other.availableDate))
			return false;
		if (availableTime == null) {
			if (other.availableTime != null)
				return false;
		} else if (!availableTime.equals(other.availableTime))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (vehicleDetailsId == null) {
			if (other.vehicleDetailsId != null)
				return false;
		} else if (!vehicleDetailsId.equals(other.vehicleDetailsId))
			return false;
		return true;
	}
	
	
	
}
