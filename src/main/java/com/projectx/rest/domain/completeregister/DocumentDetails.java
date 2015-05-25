package com.projectx.rest.domain.completeregister;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.mvc.util.serializer.JsonDateDeSerializer;
import com.projectx.mvc.util.serializer.JsonDateSerializer;

public class DocumentDetails {


	private DocumentKey key;
	
	private List<Document> documents=new ArrayList<Document>();
	
	private Integer verificationStatus;
	
	private String verificationRemark;
	
	private String doccumentUID;
	
	private Integer l1RejectionCount;
	
	private Integer l2RejectionCount;
	
	private Date insertTime;
	
	private Date updateTime;
	
	private Integer updatedBy;
	
	private Integer insertedBy;
	
	private Long updatedById;
	
	private Long insertedById;

	public DocumentDetails() {

	}



	public DocumentDetails(DocumentKey key,
			List<com.projectx.rest.domain.completeregister.Document> documents,
			Integer verificationStatus, String verificationRemark,
			String doccumentUID, Integer l1RejectionCount,
			Integer l2RejectionCount, Date insertTime, Date updateTime,
			Integer updatedBy, Integer insertedBy, Long updatedById,
			Long insertedById) {
		super();
		this.key = key;
		this.documents = documents;
		this.verificationStatus = verificationStatus;
		this.verificationRemark = verificationRemark;
		this.doccumentUID = doccumentUID;
		this.l1RejectionCount = l1RejectionCount;
		this.l2RejectionCount = l2RejectionCount;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.insertedBy = insertedBy;
		this.updatedById = updatedById;
		this.insertedById = insertedById;
	}



	public DocumentKey getKey() {
		return key;
	}

	public void setKey(DocumentKey key) {
		this.key = key;
	}


	public Integer getVerificationStatus() {
		return verificationStatus;
	}

	public void setVerificationStatus(Integer verificationStatus) {
		this.verificationStatus = verificationStatus;
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

	
	public String getVerificationRemark() {
		return verificationRemark;
	}

	public void setVerificationRemark(String verificationRemark) {
		this.verificationRemark = verificationRemark;
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

	
	
	public List<Document> getDocuments() {
		return documents;
	}



	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}



	public String getDoccumentUID() {
		return doccumentUID;
	}



	public void setDoccumentUID(String doccumentUID) {
		this.doccumentUID = doccumentUID;
	}



	public Integer getL1RejectionCount() {
		return l1RejectionCount;
	}



	public void setL1RejectionCount(Integer l1RejectionCount) {
		this.l1RejectionCount = l1RejectionCount;
	}



	public Integer getL2RejectionCount() {
		return l2RejectionCount;
	}



	public void setL2RejectionCount(Integer l2RejectionCount) {
		this.l2RejectionCount = l2RejectionCount;
	}



	@Override
	public String toString() {
		return "DocumentDetails [key=" + key + ", documents=" + documents
				+ ", verificationStatus=" + verificationStatus
				+ ", verificationRemark=" + verificationRemark
				+ ", doccumentUID=" + doccumentUID + ", l1RejectionCount="
				+ l1RejectionCount + ", l2RejectionCount=" + l2RejectionCount
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
				+ ((doccumentUID == null) ? 0 : doccumentUID.hashCode());
		result = prime * result
				+ ((documents == null) ? 0 : documents.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result
				+ ((insertedBy == null) ? 0 : insertedBy.hashCode());
		result = prime * result
				+ ((insertedById == null) ? 0 : insertedById.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime
				* result
				+ ((l1RejectionCount == null) ? 0 : l1RejectionCount.hashCode());
		result = prime
				* result
				+ ((l2RejectionCount == null) ? 0 : l2RejectionCount.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result
				+ ((updatedById == null) ? 0 : updatedById.hashCode());
		result = prime
				* result
				+ ((verificationRemark == null) ? 0 : verificationRemark
						.hashCode());
		result = prime
				* result
				+ ((verificationStatus == null) ? 0 : verificationStatus
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
		DocumentDetails other = (DocumentDetails) obj;
		if (doccumentUID == null) {
			if (other.doccumentUID != null)
				return false;
		} else if (!doccumentUID.equals(other.doccumentUID))
			return false;
		if (documents == null) {
			if (other.documents != null)
				return false;
		} else if (!documents.equals(other.documents))
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
		}
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (l1RejectionCount == null) {
			if (other.l1RejectionCount != null)
				return false;
		} else if (!l1RejectionCount.equals(other.l1RejectionCount))
			return false;
		if (l2RejectionCount == null) {
			if (other.l2RejectionCount != null)
				return false;
		} else if (!l2RejectionCount.equals(other.l2RejectionCount))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		}
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		}
		if (updatedById == null) {
			if (other.updatedById != null)
				return false;
		}
		if (verificationRemark == null) {
			if (other.verificationRemark != null)
				return false;
		} else if (!verificationRemark.equals(other.verificationRemark))
			return false;
		if (verificationStatus == null) {
			if (other.verificationStatus != null)
				return false;
		} else if (!verificationStatus.equals(other.verificationStatus))
			return false;
		return true;
	}



		
	
	
	
	
	/*
	private static byte[] writtingImage(String fileLocation) {
	      System.out.println("file lication is"+fileLocation);
	     IOManager manager=new IOManager();
	        try {
	           return manager.getBytesFromFile(fileLocation);
	            
	        } catch (IOException e) {
	        }
	        return null;
	    }
	*/
}
