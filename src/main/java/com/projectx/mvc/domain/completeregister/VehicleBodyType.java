package com.projectx.mvc.domain.completeregister;

import java.util.Date;


import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.mvc.util.serializer.JsonDateDeSerializer;
import com.projectx.mvc.util.serializer.JsonDateSerializer;


public class VehicleBodyType {

	@NotNull
	private Long vehicleBodyTypeId;
	
	@NotNull
	private String vehicleBodyTypeName;
	
	@NotNull
	private Date insertTime;
	
	@NotNull
	private Date updateTime;
	
	@NotNull
	private Integer updatedBy;
	
	@NotNull
	private Integer insertedBy;
	
	@NotNull
	private Long updatedById;
	
	@NotNull
	private Long insertedById;

	public VehicleBodyType() {
		super();
	}

	public VehicleBodyType(Long vehicleBodyTypeId, String vehicleBodyTypeName,
			Date insertTime, Date updateTime, Integer updatedBy,
			Integer insertedBy, Long updatedById, Long insertedById) {
		super();
		this.vehicleBodyTypeId = vehicleBodyTypeId;
		this.vehicleBodyTypeName = vehicleBodyTypeName;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.insertedBy = insertedBy;
		this.updatedById = updatedById;
		this.insertedById = insertedById;
	}

	public VehicleBodyType(String vehicleBodyTypeName, Date insertTime,
			Date updateTime, Integer updatedBy, Integer insertedBy,
			Long updatedById, Long insertedById) {
		super();
		this.vehicleBodyTypeName = vehicleBodyTypeName;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.insertedBy = insertedBy;
		this.updatedById = updatedById;
		this.insertedById = insertedById;
	}

	public Long getVehicleBodyTypeId() {
		return vehicleBodyTypeId;
	}

	public void setVehicleBodyTypeId(Long vehicleBodyTypeId) {
		this.vehicleBodyTypeId = vehicleBodyTypeId;
	}

	public String getVehicleBodyTypeName() {
		return vehicleBodyTypeName;
	}

	public void setVehicleBodyTypeName(String vehicleBodyTypeName) {
		this.vehicleBodyTypeName = vehicleBodyTypeName;
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
		return "VehicleBodyType [vehicleBodyTypeId=" + vehicleBodyTypeId
				+ ", vehicleBodyTypeName=" + vehicleBodyTypeName
				+ ", insertTime=" + insertTime + ", updateTime=" + updateTime
				+ ", updatedBy=" + updatedBy + ", insertedBy=" + insertedBy
				+ ", updatedById=" + updatedById + ", insertedById="
				+ insertedById + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result
				+ ((insertedBy == null) ? 0 : insertedBy.hashCode());
		result = prime * result
				+ ((insertedById == null) ? 0 : insertedById.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result
				+ ((updatedById == null) ? 0 : updatedById.hashCode());
		result = prime
				* result
				+ ((vehicleBodyTypeId == null) ? 0 : vehicleBodyTypeId
						.hashCode());
		result = prime
				* result
				+ ((vehicleBodyTypeName == null) ? 0 : vehicleBodyTypeName
						.hashCode());
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
		VehicleBodyType other = (VehicleBodyType) obj;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (!insertTime.equals(other.insertTime))
			return false;
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
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
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
		if (vehicleBodyTypeId == null) {
			if (other.vehicleBodyTypeId != null)
				return false;
		} else if (!vehicleBodyTypeId.equals(other.vehicleBodyTypeId))
			return false;
		if (vehicleBodyTypeName == null) {
			if (other.vehicleBodyTypeName != null)
				return false;
		} else if (!vehicleBodyTypeName.equals(other.vehicleBodyTypeName))
			return false;
		return true;
	}

		
	
	
}
