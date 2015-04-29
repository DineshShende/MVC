package com.projectx.rest.domain.ang;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;

public class FreightRequestByVendorAngDTO {

	private Long requestId;
	
	@NotNull
	private VehicleDetailsDTO vehicleDetailsId;
	
	@NotNull
	private Integer source;
	
	@NotNull
	private Integer destination;
	
	private Long driverId;
	
	@NotNull
	private Date availableDate;
	
	private String availableTime;
	
	private Integer pickupRangeInKm;
	
	@NotNull
	private Long vendorId;


	private String status;

	private Long reservedBy;
	
	private Date insertTime;
	
	private Date updateTime;
	
	@NotNull
	private Integer requestedBy;
	
	@NotNull
	private Long requestedById;

	public FreightRequestByVendorAngDTO() {
		super();
	}

	public FreightRequestByVendorAngDTO(Long requestId,
			VehicleDetailsDTO vehicleRegistrationNumber, Integer source,
			Integer destination, Long driverId, Date availableDate,
			String availableTime, Integer pickupRangeInKm, Long vendorId,
			String status, Long reservedBy, Date insertTime, Date updateTime,
			Integer updatedBy,Long updatedById) {
		super();
		this.requestId = requestId;
		this.vehicleDetailsId = vehicleRegistrationNumber;
		this.source = source;
		this.destination = destination;
		this.driverId = driverId;
		this.availableDate = availableDate;
		this.availableTime = availableTime;
		this.pickupRangeInKm = pickupRangeInKm;
		this.vendorId = vendorId;
		this.status = status;
		this.reservedBy = reservedBy;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.requestedBy = updatedBy;
		this.requestedById=updatedById;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}



	public VehicleDetailsDTO getVehicleDetailsId() {
		return vehicleDetailsId;
	}

	public void setVehicleDetailsId(VehicleDetailsDTO vehicleDetailsId) {
		this.vehicleDetailsId = vehicleDetailsId;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getDestination() {
		return destination;
	}

	public void setDestination(Integer destination) {
		this.destination = destination;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
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

	public Integer getPickupRangeInKm() {
		return pickupRangeInKm;
	}

	public void setPickupRangeInKm(Integer pickupRangeInKm) {
		this.pickupRangeInKm = pickupRangeInKm;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getReservedBy() {
		return reservedBy;
	}

	public void setReservedBy(Long reservedBy) {
		this.reservedBy = reservedBy;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	


	public Integer getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(Integer requestedBy) {
		this.requestedBy = requestedBy;
	}

	public Long getRequestedById() {
		return requestedById;
	}

	public void setRequestedById(Long requestedById) {
		this.requestedById = requestedById;
	}

	@Override
	public String toString() {
		return "FreightRequestByVendorAngDTO [requestId=" + requestId
				+ ", vehicleDetailsId=" + vehicleDetailsId
				+ ", source=" + source + ", destination=" + destination
				+ ", driverId=" + driverId + ", availableDate=" + availableDate
				+ ", availableTime=" + availableTime + ", pickupRangeInKm="
				+ pickupRangeInKm + ", vendorId=" + vendorId + ", status="
				+ status + ", reservedBy=" + reservedBy + ", insertTime="
				+ insertTime + ", updateTime=" + updateTime + ", requestedBy="
				+ requestedBy + ", requestedById=" + requestedById + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((availableDate == null) ? 0 : availableDate.hashCode());
		result = prime * result
				+ ((availableTime == null) ? 0 : availableTime.hashCode());
		result = prime * result
				+ ((destination == null) ? 0 : destination.hashCode());
		result = prime * result
				+ ((driverId == null) ? 0 : driverId.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result
				+ ((pickupRangeInKm == null) ? 0 : pickupRangeInKm.hashCode());
		result = prime * result
				+ ((requestId == null) ? 0 : requestId.hashCode());
		result = prime * result
				+ ((reservedBy == null) ? 0 : reservedBy.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((requestedBy == null) ? 0 : requestedBy.hashCode());
		result = prime * result
				+ ((requestedById == null) ? 0 : requestedById.hashCode());
		result = prime
				* result
				+ ((vehicleDetailsId == null) ? 0
						: vehicleDetailsId.hashCode());
		result = prime * result
				+ ((vendorId == null) ? 0 : vendorId.hashCode());
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
		FreightRequestByVendorAngDTO other = (FreightRequestByVendorAngDTO) obj;
		if (availableDate == null) {
			if (other.availableDate != null)
				return false;
		}
		if (availableTime == null) {
			if (other.availableTime != null)
				return false;
		} else if (!availableTime.equals(other.availableTime))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (driverId == null) {
			if (other.driverId != null)
				return false;
		} else if (!driverId.equals(other.driverId))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		}
		if (pickupRangeInKm == null) {
			if (other.pickupRangeInKm != null)
				return false;
		} else if (!pickupRangeInKm.equals(other.pickupRangeInKm))
			return false;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
			return false;
		if (reservedBy == null) {
			if (other.reservedBy != null)
				return false;
		} else if (!reservedBy.equals(other.reservedBy))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		}
		if (requestedBy == null) {
			if (other.requestedBy != null)
				return false;
		} else if (!requestedBy.equals(other.requestedBy))
			return false;
		if (requestedById == null) {
			if (other.requestedById != null)
				return false;
		} else if (!requestedById.equals(other.requestedById))
			return false;
		if (vehicleDetailsId == null) {
			if (other.vehicleDetailsId != null)
				return false;
		} else if (!vehicleDetailsId
				.equals(other.vehicleDetailsId))
			return false;
		if (vendorId == null) {
			if (other.vendorId != null)
				return false;
		} else if (!vendorId.equals(other.vendorId))
			return false;
		return true;
	}

	
	
	
}
