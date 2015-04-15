package com.projectx.rest.domain.completeregister;

public class EntityIdTypeDTO {

	private Long entityId;

	private Integer entityType;
	
	
	public EntityIdTypeDTO() {

	}


	public EntityIdTypeDTO(Long entityId, Integer entityType) {
		super();
		this.entityId = entityId;
		this.entityType = entityType;
	}


	public Long getEntityId() {
		return entityId;
	}


	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}


	public Integer getEntityType() {
		return entityType;
	}


	public void setEntityType(Integer entityType) {
		this.entityType = entityType;
	}


	@Override
	public String toString() {
		return "EntityIdDTO [entityId=" + entityId + ", entityType="
				+ entityType + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((entityId == null) ? 0 : entityId.hashCode());
		result = prime * result
				+ ((entityType == null) ? 0 : entityType.hashCode());
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
		EntityIdTypeDTO other = (EntityIdTypeDTO) obj;
		if (entityId == null) {
			if (other.entityId != null)
				return false;
		} else if (!entityId.equals(other.entityId))
			return false;
		if (entityType == null) {
			if (other.entityType != null)
				return false;
		} else if (!entityType.equals(other.entityType))
			return false;
		return true;
	}

		
	
}
