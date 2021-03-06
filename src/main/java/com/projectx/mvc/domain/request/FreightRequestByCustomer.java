package com.projectx.mvc.domain.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.mvc.util.serializer.JsonDateDeSerializer;
import com.projectx.mvc.util.serializer.JsonDateSerializer;
import com.projectx.rest.domain.request.FreightRequestByCustomerDTO;

public class FreightRequestByCustomer {
	
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
	
	private Integer updatedBy;
	
	private Integer insertedBy;
	
	private Long updatedById;
	
	private Long insertedById;
	
	public FreightRequestByCustomer() {

	}

	



	public FreightRequestByCustomer(Long requestId, Integer source,
			Integer destination, Date pickupDate, Integer noOfVehicles,
			String loadType, Integer capacity, String bodyType,
			Integer grossWeight, Integer length, Integer width, Integer height,
			String vehicleBrand, String model, String commodity,
			String pickupTime, Long customerId, String status,
			Long allocatedFor, Date insertTime, 
			Date updateTime,Integer updatedBy,Integer insertedBy,Long updatedById,Long insertedById) {
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
		this.updatedBy = updatedBy;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.insertedBy=insertedBy;
		this.updatedById=updatedById;
		this.insertedById=insertedById;
	}





	public  FreightRequestByCustomerDTO toFreightRequestByCustomerDTO()
	{
				
		FreightRequestByCustomerDTO newDTO=new FreightRequestByCustomerDTO(this.requestId,
				this.source, this.destination, this.pickupDate, this.noOfVehicles, 
				(this.loadType.equals("FullTruckLoad")), (this.loadType.equals("LessThanTruckLoad")), this.capacity, this.bodyType, 
				this.grossWeight, this.length, this.width, this.height, this.vehicleBrand, 
				this.model, this.commodity,this.pickupTime, this.customerId, this.status,this.allocatedFor,  
				this.insertTime, this.updateTime, this.updatedBy,this.insertedBy,this.updatedById,this.insertedById);
		
		return newDTO;
	}
	
	
	public static FreightRequestByCustomer fromFreightRequestByCustomerDTO(FreightRequestByCustomerDTO freightRequestByCustomerDTO)
	{
		
		FreightRequestByCustomer freightRequestByCustomer=
				new FreightRequestByCustomer(freightRequestByCustomerDTO.getRequestId(), 
						freightRequestByCustomerDTO.getSource(), freightRequestByCustomerDTO.getDestination(),
						freightRequestByCustomerDTO.getPickupDate(),freightRequestByCustomerDTO.getNoOfVehicles(), 
						(freightRequestByCustomerDTO.getIsFullTruckLoad())?"FullTruckLoad":"LessThanTruckLoad", freightRequestByCustomerDTO.getCapacity(),
						freightRequestByCustomerDTO.getBodyType(), freightRequestByCustomerDTO.getGrossWeight(), 
						freightRequestByCustomerDTO.getLength(), freightRequestByCustomerDTO.getWidth(),freightRequestByCustomerDTO.getHeight(),
						freightRequestByCustomerDTO.getVehicleBrand(), freightRequestByCustomerDTO.getModel(),freightRequestByCustomerDTO.getCommodity(),
						freightRequestByCustomerDTO.getPickupTime(), freightRequestByCustomerDTO.getCustomerId(),freightRequestByCustomerDTO.getAllocationStatus(),
						freightRequestByCustomerDTO.getAllocatedFor(),
						freightRequestByCustomerDTO.getInsertTime(), 
						freightRequestByCustomerDTO.getUpdateTime(),
						freightRequestByCustomerDTO.getUpdatedBy(),
						freightRequestByCustomerDTO.getInsertedBy(),
						freightRequestByCustomerDTO.getUpdatedById(),
						freightRequestByCustomerDTO.getInsertedById());
		
		return freightRequestByCustomer;
	}


	
	
	public Long getAllocatedFor() {
		return allocatedFor;
	}





	public void setAllocatedFor(Long allocatedFor) {
		this.allocatedFor = allocatedFor;
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

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getPickupDate() {
		return pickupDate;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getInsertTime() {
		return insertTime;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}


	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	


	public Integer getUpdatedBy() {
		return updatedBy;
	}





	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}





	public Integer getInsertedBy() {
		return insertedBy;
	}





	public void setInsertedBy(Integer insertedBy) {
		this.insertedBy = insertedBy;
	}





	public Long getUpdatedById() {
		return updatedById;
	}





	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}





	public Long getInsertedById() {
		return insertedById;
	}





	public void setInsertedById(Long insertedById) {
		this.insertedById = insertedById;
	}





	@Override
	public String toString() {
		return "FreightRequestByCustomer [requestId=" + requestId + ", source="
				+ source + ", destination=" + destination + ", pickupDate="
				+ pickupDate + ", noOfVehicles=" + noOfVehicles + ", loadType="
				+ loadType + ", capacity=" + capacity + ", bodyType="
				+ bodyType + ", grossWeight=" + grossWeight + ", length="
				+ length + ", width=" + width + ", height=" + height
				+ ", vehicleBrand=" + vehicleBrand + ", model=" + model
				+ ", commodity=" + commodity + ", pickupTime=" + pickupTime
				+ ", customerId=" + customerId + ", status=" + status
				+ ", allocatedFor=" + allocatedFor + ", insertTime="
				+ insertTime + ", updateTime=" + updateTime + ", updatedBy="
				+ updatedBy + ", insertedBy=" + insertedBy + ", updatedById="
				+ updatedById + ", insertedById=" + insertedById + "]";
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
		result = prime * result
				+ ((insertedBy == null) ? 0 : insertedBy.hashCode());
		result = prime * result
				+ ((insertedById == null) ? 0 : insertedById.hashCode());
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
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result
				+ ((updatedById == null) ? 0 : updatedById.hashCode());
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
		FreightRequestByCustomer other = (FreightRequestByCustomer) obj;
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
		}
		if (insertedBy == null) {
			if (other.insertedBy != null)
				return false;
		} else if (!insertedBy.equals(other.insertedBy))
			return false;
		if (insertedById == null) {
			if (other.insertedById != null)
				return false;
		} else if (!insertedById.equals(other.insertedById))
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
		}
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
		}
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (updatedById == null) {
			if (other.updatedById != null)
				return false;
		} else if (!updatedById.equals(other.updatedById))
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
