package com.projectx.mvc.domain.completeregister;

import java.util.Date;


import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.mvc.util.serializer.JsonDateDeSerializer;
import com.projectx.mvc.util.serializer.JsonDateSerializer;

public class VehicleBrand {
	
	
	private Long vehicleBrandId;

	@NotNull
	private String vehicleBrandName;

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
	
	
	public VehicleBrand() {
		super();
	}


	public VehicleBrand(Long vehicleBrandId, String vehicleBrandName,
			Date insertTime, Date updateTime, Integer updatedBy,
			Integer insertedBy, Long updatedById, Long insertedById) {
		super();
		this.vehicleBrandId = vehicleBrandId;
		this.vehicleBrandName = vehicleBrandName;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.insertedBy = insertedBy;
		this.updatedById = updatedById;
		this.insertedById = insertedById;
	}

	public VehicleBrand(String vehicleBrandName,
			Date insertTime, Date updateTime, Integer updatedBy,
			Integer insertedBy, Long updatedById, Long insertedById) {
		super();
		this.vehicleBrandName = vehicleBrandName;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.insertedBy = insertedBy;
		this.updatedById = updatedById;
		this.insertedById = insertedById;
	}


	public Long getVehicleBrandId() {
		return vehicleBrandId;
	}


	public void setVehicleBrandId(Long vehicleBrandId) {
		this.vehicleBrandId = vehicleBrandId;
	}


	public String getVehicleBrandName() {
		return vehicleBrandName;
	}


	public void setVehicleBrandName(String vehicleBrandName) {
		this.vehicleBrandName = vehicleBrandName;
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
		return "VehicleBrand [vehicleBrandId=" + vehicleBrandId
				+ ", vehicleBrandName=" + vehicleBrandName + ", insertTime="
				+ insertTime + ", updateTime=" + updateTime + ", updatedBy="
				+ updatedBy + ", insertedBy=" + insertedBy + ", updatedById="
				+ updatedById + ", insertedById=" + insertedById + "]";
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
		result = prime * result
				+ ((vehicleBrandId == null) ? 0 : vehicleBrandId.hashCode());
		result = prime
				* result
				+ ((vehicleBrandName == null) ? 0 : vehicleBrandName.hashCode());
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
		VehicleBrand other = (VehicleBrand) obj;
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
		} 
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
		if (vehicleBrandId == null) {
			if (other.vehicleBrandId != null)
				return false;
		} else if (!vehicleBrandId.equals(other.vehicleBrandId))
			return false;
		if (vehicleBrandName == null) {
			if (other.vehicleBrandName != null)
				return false;
		} else if (!vehicleBrandName.equals(other.vehicleBrandName))
			return false;
		return true;
	}

	
	
	
	

}
