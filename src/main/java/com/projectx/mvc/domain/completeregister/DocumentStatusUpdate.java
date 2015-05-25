package com.projectx.mvc.domain.completeregister;

public class DocumentStatusUpdate {
	
	  private Long id;
	  
	  private Integer type;
	  
	  private String doccumentName;
	  
	  private Integer status;
	  
	  private String verificationRemark; 
	    
	  private Integer updatedBy;
	     
	  private Long updatedById;

	public DocumentStatusUpdate() {
		super();
	}

	public DocumentStatusUpdate(Long id, Integer type, String doccumentName,
			Integer status, String verificationRemark, Integer updatedBy,
			Long updatedById) {
		super();
		this.id = id;
		this.type = type;
		this.doccumentName = doccumentName;
		this.status = status;
		this.verificationRemark = verificationRemark;
		this.updatedBy = updatedBy;
		this.updatedById = updatedById;
	}

	@Override
	public String toString() {
		return "DocumentStatusUpdate [id=" + id + ", type=" + type
				+ ", doccumentName=" + doccumentName + ", status=" + status
				+ ", verificationRemark=" + verificationRemark + ", updatedBy="
				+ updatedBy + ", updatedById=" + updatedById + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((doccumentName == null) ? 0 : doccumentName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result
				+ ((updatedById == null) ? 0 : updatedById.hashCode());
		result = prime
				* result
				+ ((verificationRemark == null) ? 0 : verificationRemark
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
		DocumentStatusUpdate other = (DocumentStatusUpdate) obj;
		if (doccumentName == null) {
			if (other.doccumentName != null)
				return false;
		} else if (!doccumentName.equals(other.doccumentName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
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
		if (verificationRemark == null) {
			if (other.verificationRemark != null)
				return false;
		} else if (!verificationRemark.equals(other.verificationRemark))
			return false;
		return true;
	}
	
	  
	  

}
