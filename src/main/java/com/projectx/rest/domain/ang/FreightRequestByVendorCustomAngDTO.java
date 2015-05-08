package com.projectx.rest.domain.ang;

import java.util.Date;

import com.projectx.rest.domain.request.FreightRequestByVendor;

public class FreightRequestByVendorCustomAngDTO {

	private Long freightRequestByVendorId;
	
	private Integer source;

	private Date availableDate;

	private String availableTime;

	private VehicleDetailsCustomAngDTO vehicleDetailsId;

	public FreightRequestByVendorCustomAngDTO() {
		super();
	}

	
	
	public FreightRequestByVendorCustomAngDTO(Long freightRequestByVendorId,
			Integer source, Date availableDate, String availableTime,
			VehicleDetailsCustomAngDTO vehicleDetailsId) {
		super();
		this.freightRequestByVendorId = freightRequestByVendorId;
		this.source = source;
		this.availableDate = availableDate;
		this.availableTime = availableTime;
		this.vehicleDetailsId = vehicleDetailsId;
	}



	public static FreightRequestByVendorCustomAngDTO fromFreightRequestByVendor(FreightRequestByVendor freightRequestByVendorDTO)
	{
		FreightRequestByVendorCustomAngDTO angDTO=new FreightRequestByVendorCustomAngDTO(freightRequestByVendorDTO.getRequestId(),
				freightRequestByVendorDTO.getSource(),freightRequestByVendorDTO.getAvailableDate(), freightRequestByVendorDTO.getAvailableTime(),
				new VehicleDetailsCustomAngDTO(freightRequestByVendorDTO.getVehicleDetailsId().getVehicleBrandId(),
						freightRequestByVendorDTO.getVehicleDetailsId().getVehicleBodyType(),
						freightRequestByVendorDTO.getVehicleDetailsId().getLoadCapacityInTons(),
						freightRequestByVendorDTO.getVehicleDetailsId().getLength(),
						freightRequestByVendorDTO.getVehicleDetailsId().getWidth(),
						freightRequestByVendorDTO.getVehicleDetailsId().getHeight(),
						freightRequestByVendorDTO.getVehicleDetailsId().getNumberOfWheels(),
						freightRequestByVendorDTO.getVehicleDetailsId().getPermitType(),
						freightRequestByVendorDTO.getVehicleDetailsId().getInsuranceStatus()));
		
		return angDTO;
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
	
	

	public Long getFreightRequestByVendorId() {
		return freightRequestByVendorId;
	}



	public void setFreightRequestByVendorId(Long freightRequestByVendorId) {
		this.freightRequestByVendorId = freightRequestByVendorId;
	}



	@Override
	public String toString() {
		return "FreightRequestByVendorCustomAngDTO [freightRequestByVendorId="
				+ freightRequestByVendorId + ", source=" + source
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
		result = prime
				* result
				+ ((freightRequestByVendorId == null) ? 0
						: freightRequestByVendorId.hashCode());
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
		if (freightRequestByVendorId == null) {
			if (other.freightRequestByVendorId != null)
				return false;
		} else if (!freightRequestByVendorId
				.equals(other.freightRequestByVendorId))
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
