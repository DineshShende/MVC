package com.projectx.rest.domain.ang;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;

public class FreightRequestByVendorAngReqDTO {

	private Long requestId;
	
	@NotNull
	private String vehicleRegistrationNumber;
	
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

	public FreightRequestByVendorAngReqDTO() {
		super();
	}

	public FreightRequestByVendorAngReqDTO(Long requestId,
			String vehicleRegistrationNumber, Integer source,
			Integer destination, Long driverId, Date availableDate,
			String availableTime, Integer pickupRangeInKm, Long vendorId,
			String status, Long reservedBy, Date insertTime, Date updateTime,
			Integer updatedBy,Long updatedById) {
		super();
		this.requestId = requestId;
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
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
	
	
	public FreightRequestByVendorDTO toFreightRequestByVendorDTO()
	{
		FreightRequestByVendorDTO requestByVendorDTO=new FreightRequestByVendorDTO(this.getVehicleRegistrationNumber(),
				this.getSource(),this.getDestination(),this.getDriverId(),
				this.getAvailableDate(), this.getAvailableTime(),this.getPickupRangeInKm(),
				this.getVendorId(), this.getStatus(), this.getReservedBy(), 
				new Date(), new Date(), this.getRequestedBy(),this.getRequestedBy(),
				this.getRequestedById(),this.getRequestedById());
		
		return requestByVendorDTO;
	}
	
	
	public static FreightRequestByVendorAngDTO fromFreightRequestByVendor(FreightRequestByVendor fetchedEntity)
	{
		
		FreightRequestByVendorAngDTO angReqDTO=new FreightRequestByVendorAngDTO(fetchedEntity.getRequestId(),
				fetchedEntity.getVehicleDetailsId(),fetchedEntity.getSource(), fetchedEntity.getDestination(),
				fetchedEntity.getDriverId(), fetchedEntity.getAvailableDate(),fetchedEntity.getAvailableTime(), fetchedEntity.getPickupRangeInKm(),
				fetchedEntity.getVendorId(), fetchedEntity.getStatus(),fetchedEntity.getReservedBy(), fetchedEntity.getInsertTime(),
				fetchedEntity.getUpdateTime(), fetchedEntity.getUpdatedBy(),fetchedEntity.getUpdatedById());
		
		return angReqDTO;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}


	

	public String getVehicleRegistrationNumber() {
		return vehicleRegistrationNumber;
	}

	public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
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
		return "FreightRequestByVendorAngReqDTO [requestId=" + requestId
				+ ", vehicleRegistrationNumber=" + vehicleRegistrationNumber
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
				+ ((requestedBy == null) ? 0 : requestedBy.hashCode());
		result = prime * result
				+ ((requestedById == null) ? 0 : requestedById.hashCode());
		result = prime * result
				+ ((reservedBy == null) ? 0 : reservedBy.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime
				* result
				+ ((vehicleRegistrationNumber == null) ? 0
						: vehicleRegistrationNumber.hashCode());
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
		FreightRequestByVendorAngReqDTO other = (FreightRequestByVendorAngReqDTO) obj;
		if (availableDate == null) {
			if (other.availableDate != null)
				return false;
		}		if (availableTime == null) {
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
		if (requestedBy == null) {
			if (other.requestedBy != null)
				return false;
		}
		if (requestedById == null) {
			if (other.requestedById != null)
				return false;
		}
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
		if (vehicleRegistrationNumber == null) {
			if (other.vehicleRegistrationNumber != null)
				return false;
		} else if (!vehicleRegistrationNumber
				.equals(other.vehicleRegistrationNumber))
			return false;
		if (vendorId == null) {
			if (other.vendorId != null)
				return false;
		} else if (!vendorId.equals(other.vendorId))
			return false;
		return true;
	}

	
}
