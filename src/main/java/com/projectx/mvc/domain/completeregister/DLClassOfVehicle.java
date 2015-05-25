package com.projectx.mvc.domain.completeregister;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.mvc.util.serializer.JsonDateDeSerializer;
import com.projectx.mvc.util.serializer.JsonDateSerializer;

public class DLClassOfVehicle {

	private Long covId;
	

	private String covName;
	
	
	@NotNull(message="InsertTime can't be NULL")
	private Date insertTime;
	
	@NotNull(message="UpdateTime can't be NULL")
	private Date updateTime;
	
	@NotNull(message="UpdatedBy can't be NULL")
	private Integer updatedBy;
	
	@NotNull(message="InsertedBy can't be NULL")
	private Integer insertedBy;
	
	@NotNull(message="UpdatedById can't be NULL")
	private Long updatedById;
	
	@NotNull(message="InsertedById can't be NULL")
	private Long insertedById;

	public DLClassOfVehicle() {
		super();
	}

	
	
	public DLClassOfVehicle(Long commodityId, String commodityName,
			 Date insertTime,
			Date updateTime, Integer updatedBy, Integer insertedBy,
			Long updatedById, Long insertedById) {
		super();
		this.covId = commodityId;
		this.covName = commodityName;
		//this.vehicleDetailsDummyId = vehicleDetailsDummyId;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.insertedBy = insertedBy;
		this.updatedById = updatedById;
		this.insertedById = insertedById;
	}


	public DLClassOfVehicle(String commodityName,
			 Date insertTime,
			Date updateTime, Integer updatedBy, Integer insertedBy,
			Long updatedById, Long insertedById) {
		super();
		this.covName = commodityName;
		//this.vehicleDetailsDummyId = vehicleDetailsDummyId;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.insertedBy = insertedBy;
		this.updatedById = updatedById;
		this.insertedById = insertedById;
	}

	
	
	public Long getCovId() {
		return covId;
	}



	public void setCovId(Long covId) {
		this.covId = covId;
	}



	public String getCovName() {
		return covName;
	}



	public void setCovName(String covName) {
		this.covName = covName;
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
		return "Commodity [commodityId=" + covId + ", commodityName="
				+ covName + ", insertTime=" + insertTime
				+ ", updateTime=" + updateTime + ", updatedBy=" + updatedBy
				+ ", insertedBy=" + insertedBy + ", updatedById=" + updatedById
				+ ", insertedById=" + insertedById + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((covId == null) ? 0 : covId.hashCode());
		result = prime * result + ((covName == null) ? 0 : covName.hashCode());
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
		DLClassOfVehicle other = (DLClassOfVehicle) obj;
		if (covId == null) {
			if (other.covId != null)
				return false;
		} else if (!covId.equals(other.covId))
			return false;
		if (covName == null) {
			if (other.covName != null)
				return false;
		} else if (!covName.equals(other.covName))
			return false;
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
		return true;
	}

	
	
	
}
