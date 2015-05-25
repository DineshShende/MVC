package com.projectx.mvc.domain.completeregister;

import java.util.Arrays;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.mvc.util.serializer.JsonDateDeSerializer;
import com.projectx.mvc.util.serializer.JsonDateSerializer;


public class VehicleModel {

	private Long vehicleModeId;
	
	@NotNull
	private VehicleBrand vehicleBrand;
	
	@NotNull
	private VehicleType vehicleType;
	
	@NotNull
	private String vehiclemodelName;
	
	@NotNull
	private byte[] vehiclePhoto;
	
	
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

	public VehicleModel() {
		super();
	}

	public VehicleModel(Long vehicleModeId, VehicleBrand vehicleBrandId,
			VehicleType vehicleTypeId, String vehiclemodelName,
			byte[] vehiclePhoto, Date insertTime, Date updateTime,
			Integer updatedBy, Integer insertedBy, Long updatedById,
			Long insertedById) {
		super();
		this.vehicleModeId = vehicleModeId;
		this.vehicleBrand = vehicleBrandId;
		this.vehicleType = vehicleTypeId;
		this.vehiclemodelName = vehiclemodelName;
		this.vehiclePhoto = vehiclePhoto;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.insertedBy = insertedBy;
		this.updatedById = updatedById;
		this.insertedById = insertedById;
	}
	
	public VehicleModel(VehicleBrand vehicleBrandId,
			VehicleType vehicleTypeId, String vehiclemodelName,
			byte[] vehiclePhoto, Date insertTime, Date updateTime,
			Integer updatedBy, Integer insertedBy, Long updatedById,
			Long insertedById) {
		super();
		
		this.vehicleBrand = vehicleBrandId;
		this.vehicleType = vehicleTypeId;
		this.vehiclemodelName = vehiclemodelName;
		this.vehiclePhoto = vehiclePhoto;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.insertedBy = insertedBy;
		this.updatedById = updatedById;
		this.insertedById = insertedById;
	}


	public Long getVehicleModeId() {
		return vehicleModeId;
	}

	public void setVehicleModeId(Long vehicleModeId) {
		this.vehicleModeId = vehicleModeId;
	}

	public VehicleBrand getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(VehicleBrand vehicleBrandId) {
		this.vehicleBrand = vehicleBrandId;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleTypeId) {
		this.vehicleType = vehicleTypeId;
	}

	public String getVehiclemodelName() {
		return vehiclemodelName;
	}

	public void setVehiclemodelName(String vehiclemodelName) {
		this.vehiclemodelName = vehiclemodelName;
	}

	public byte[] getVehiclePhoto() {
		return vehiclePhoto;
	}

	public void setVehiclePhoto(byte[] vehiclePhoto) {
		this.vehiclePhoto = vehiclePhoto;
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
		return "VehicleModel [vehicleModeId=" + vehicleModeId
				+ ", vehicleBrand=" + vehicleBrand + ", vehicleType="
				+ vehicleType + ", vehiclemodelName=" + vehiclemodelName
				+ ", vehiclePhoto=" + Arrays.toString(vehiclePhoto)
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
		result = prime * result
				+ ((vehicleBrand == null) ? 0 : vehicleBrand.hashCode());
		result = prime * result
				+ ((vehicleModeId == null) ? 0 : vehicleModeId.hashCode());
		result = prime * result + Arrays.hashCode(vehiclePhoto);
		result = prime * result
				+ ((vehicleType == null) ? 0 : vehicleType.hashCode());
		result = prime
				* result
				+ ((vehiclemodelName == null) ? 0 : vehiclemodelName.hashCode());
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
		VehicleModel other = (VehicleModel) obj;
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
		}
		if (vehicleBrand == null) {
			if (other.vehicleBrand != null)
				return false;
		} else if (!vehicleBrand.equals(other.vehicleBrand))
			return false;
		if (vehicleModeId == null) {
			if (other.vehicleModeId != null)
				return false;
		} else if (!vehicleModeId.equals(other.vehicleModeId))
			return false;
		if (!Arrays.equals(vehiclePhoto, other.vehiclePhoto))
			return false;
		if (vehicleType == null) {
			if (other.vehicleType != null)
				return false;
		} else if (!vehicleType.equals(other.vehicleType))
			return false;
		if (vehiclemodelName == null) {
			if (other.vehiclemodelName != null)
				return false;
		} else if (!vehiclemodelName.equals(other.vehiclemodelName))
			return false;
		return true;
	}

	
	
	
}
