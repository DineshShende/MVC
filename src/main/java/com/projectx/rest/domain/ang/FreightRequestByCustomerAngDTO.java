package com.projectx.rest.domain.ang;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.projectx.mvc.domain.request.FreightRequestByCustomer;

public class FreightRequestByCustomerAngDTO {
	
	private Long requestId;

	@NotNull
	private Integer source;
	
	@NotNull
	private Integer destination;
	
	@NotNull
	private Date pickupDate;
	
	@NotNull
	private Integer noOfVehicles;
	
	@NotNull
	private String loadType;

	
	private Integer capacity;
	
	
	private String bodyType;
	
	private Integer grossWeight;
	
	private Integer length;
	
	private Integer width;
	
	private Integer height;
	
	private String vehicleBrand;

	private String model;
	
	private String commodity;
	
	private  String pickupTime;

	@NotNull
	private Long customerId;
	
	
	private String status;
	
	private Long allocatedFor;
	
	private Date insertTime;
	
	private Date updateTime;
	
	@NotNull
	private Integer requestedBy;
	
	@NotNull
	private Long requestedById;


	public FreightRequestByCustomerAngDTO() {
		super();
	}


	public FreightRequestByCustomerAngDTO(Long requestId, Integer source,
			Integer destination, Date pickupDate, Integer noOfVehicles,
			String loadType, Integer capacity, String bodyType,
			Integer grossWeight, Integer length, Integer width, Integer height,
			String vehicleBrand, String model, String commodity,
			String pickupTime, Long customerId, String status,
			Long allocatedFor, Date insertTime,
			Date updateTime, Integer updatedBy,Long updatedById) {
		super();
		this.requestId = requestId;
		this.source = source;
		this.destination = destination;
		this.pickupDate = pickupDate;
		this.noOfVehicles = noOfVehicles;
		this.loadType = loadType;
		this.capacity = capacity;
		this.bodyType = bodyType;
		this.grossWeight = grossWeight;
		this.length = length;
		this.width = width;
		this.height = height;
		this.vehicleBrand = vehicleBrand;
		this.model = model;
		this.commodity = commodity;
		this.pickupTime = pickupTime;
		this.customerId = customerId;
		this.status = status;
		this.allocatedFor = allocatedFor;
		this.insertTime = insertTime;
		this.requestedBy = updatedBy;
		this.requestedById=updatedById;
		this.updateTime = updateTime;
	}

	
	public FreightRequestByCustomer toFreightRequestByCustomer()
	{
		FreightRequestByCustomer freightRequestByCustomer=new FreightRequestByCustomer(this.getRequestId(),
				this.getSource(),this.getDestination(), this.getPickupDate(),
				this.getNoOfVehicles(), this.getLoadType(), this.getCapacity(),
				this.getBodyType(),this.getGrossWeight(), this.getLength(),
				this.getWidth(), this.getHeight(), this.getVehicleBrand(), 
				this.getModel(), this.getCommodity(), this.getPickupTime(),
				this.getCustomerId(), this.getStatus(),null, new Date(),
				this.getUpdateTime(), this.getRequestedBy(),this.getRequestedBy(),
				this.getRequestedById(),this.getRequestedById());
		
		return freightRequestByCustomer;
	}

	public static FreightRequestByCustomerAngDTO fromFreightRequestByCustomer(FreightRequestByCustomer fetchedEntity)
	{
		FreightRequestByCustomerAngDTO angDTO=new FreightRequestByCustomerAngDTO(fetchedEntity.getRequestId(), fetchedEntity.getSource(),
				fetchedEntity.getDestination(),fetchedEntity.getPickupDate(), fetchedEntity.getNoOfVehicles(), fetchedEntity.getLoadType(),
				fetchedEntity.getCapacity(), fetchedEntity.getBodyType(),fetchedEntity.getGrossWeight(), fetchedEntity.getLength(),
				fetchedEntity.getWidth(),fetchedEntity.getHeight(), fetchedEntity.getVehicleBrand(), fetchedEntity.getModel(),
				fetchedEntity.getCommodity(), fetchedEntity.getPickupTime(), fetchedEntity.getCustomerId(), fetchedEntity.getStatus(),
				fetchedEntity.getAllocatedFor(),fetchedEntity.getInsertTime(), fetchedEntity.getPickupDate(),fetchedEntity.getUpdatedBy(),
				fetchedEntity.getUpdatedById());
		
		return angDTO;
		
	}
	
	public Long getRequestId() {
		return requestId;
	}


	public void setRequestId(Long requestId) {
		this.requestId = requestId;
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


	public Date getPickupDate() {
		return pickupDate;
	}


	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}


	public Integer getNoOfVehicles() {
		return noOfVehicles;
	}


	public void setNoOfVehicles(Integer noOfVehicles) {
		this.noOfVehicles = noOfVehicles;
	}


	public String getLoadType() {
		return loadType;
	}


	public void setLoadType(String loadType) {
		this.loadType = loadType;
	}


	public Integer getCapacity() {
		return capacity;
	}


	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}


	public String getBodyType() {
		return bodyType;
	}


	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}


	public Integer getGrossWeight() {
		return grossWeight;
	}


	public void setGrossWeight(Integer grossWeight) {
		this.grossWeight = grossWeight;
	}


	public Integer getLength() {
		return length;
	}


	public void setLength(Integer length) {
		this.length = length;
	}


	public Integer getWidth() {
		return width;
	}


	public void setWidth(Integer width) {
		this.width = width;
	}


	public Integer getHeight() {
		return height;
	}


	public void setHeight(Integer height) {
		this.height = height;
	}


	public String getVehicleBrand() {
		return vehicleBrand;
	}


	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getCommodity() {
		return commodity;
	}


	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}


	public String getPickupTime() {
		return pickupTime;
	}


	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Long getAllocatedFor() {
		return allocatedFor;
	}


	public void setAllocatedFor(Long allocatedFor) {
		this.allocatedFor = allocatedFor;
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
		return "FreightRequestByCustomerAngDTO [requestId=" + requestId
				+ ", source=" + source + ", destination=" + destination
				+ ", pickupDate=" + pickupDate + ", noOfVehicles="
				+ noOfVehicles + ", loadType=" + loadType + ", capacity="
				+ capacity + ", bodyType=" + bodyType + ", grossWeight="
				+ grossWeight + ", length=" + length + ", width=" + width
				+ ", height=" + height + ", vehicleBrand=" + vehicleBrand
				+ ", model=" + model + ", commodity=" + commodity
				+ ", pickupTime=" + pickupTime + ", customerId=" + customerId
				+ ", status=" + status + ", allocatedFor=" + allocatedFor
				+ ", insertTime=" + insertTime + ", updateTime=" + updateTime
				+ ", requestedBy=" + requestedBy + ", requestedById=" + requestedById
				+ "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((allocatedFor == null) ? 0 : allocatedFor.hashCode());
		result = prime * result
				+ ((bodyType == null) ? 0 : bodyType.hashCode());
		result = prime * result
				+ ((capacity == null) ? 0 : capacity.hashCode());
		result = prime * result
				+ ((commodity == null) ? 0 : commodity.hashCode());
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((destination == null) ? 0 : destination.hashCode());
		result = prime * result
				+ ((grossWeight == null) ? 0 : grossWeight.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result
				+ ((loadType == null) ? 0 : loadType.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result
				+ ((noOfVehicles == null) ? 0 : noOfVehicles.hashCode());
		result = prime * result
				+ ((pickupDate == null) ? 0 : pickupDate.hashCode());
		result = prime * result
				+ ((pickupTime == null) ? 0 : pickupTime.hashCode());
		result = prime * result
				+ ((requestId == null) ? 0 : requestId.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((requestedBy == null) ? 0 : requestedBy.hashCode());
		result = prime * result
				+ ((requestedById == null) ? 0 : requestedById.hashCode());
		result = prime * result
				+ ((vehicleBrand == null) ? 0 : vehicleBrand.hashCode());
		result = prime * result + ((width == null) ? 0 : width.hashCode());
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
		FreightRequestByCustomerAngDTO other = (FreightRequestByCustomerAngDTO) obj;
		if (allocatedFor == null) {
			if (other.allocatedFor != null)
				return false;
		} else if (!allocatedFor.equals(other.allocatedFor))
			return false;
		if (bodyType == null) {
			if (other.bodyType != null)
				return false;
		} else if (!bodyType.equals(other.bodyType))
			return false;
		if (capacity == null) {
			if (other.capacity != null)
				return false;
		} else if (!capacity.equals(other.capacity))
			return false;
		if (commodity == null) {
			if (other.commodity != null)
				return false;
		} else if (!commodity.equals(other.commodity))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (grossWeight == null) {
			if (other.grossWeight != null)
				return false;
		} else if (!grossWeight.equals(other.grossWeight))
			return false;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (!insertTime.equals(other.insertTime))
			return false;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (loadType == null) {
			if (other.loadType != null)
				return false;
		} else if (!loadType.equals(other.loadType))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (noOfVehicles == null) {
			if (other.noOfVehicles != null)
				return false;
		} else if (!noOfVehicles.equals(other.noOfVehicles))
			return false;
		if (pickupDate == null) {
			if (other.pickupDate != null)
				return false;
		} else if (!pickupDate.equals(other.pickupDate))
			return false;
		if (pickupTime == null) {
			if (other.pickupTime != null)
				return false;
		} else if (!pickupTime.equals(other.pickupTime))
			return false;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
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
		} else if (!updateTime.equals(other.updateTime))
			return false;
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
		if (vehicleBrand == null) {
			if (other.vehicleBrand != null)
				return false;
		} else if (!vehicleBrand.equals(other.vehicleBrand))
			return false;
		if (width == null) {
			if (other.width != null)
				return false;
		} else if (!width.equals(other.width))
			return false;
		return true;
	}


		
	

}
