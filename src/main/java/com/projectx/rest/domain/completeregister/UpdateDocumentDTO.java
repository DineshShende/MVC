package com.projectx.rest.domain.completeregister;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.projectx.rest.domain.completeregister.DocumentKey;

public class UpdateDocumentDTO {

	private DocumentKey key;
	
	private List<Document> document;

	private Integer requestedBy;
	
	private Long requestedById;

	public UpdateDocumentDTO() {

	}

	
	public UpdateDocumentDTO(DocumentKey key, List<Document> document,
			Integer requestedBy, Long requestedById) {
		super();
		this.key = key;
		this.document = document;
		this.requestedBy = requestedBy;
		this.requestedById = requestedById;
	}


	public DocumentKey getKey() {
		return key;
	}

	public void setKey(DocumentKey key) {
		this.key = key;
	}

	
	public List<Document> getDocument() {
		return document;
	}


	public void setDocument(List<Document> document) {
		this.document = document;
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
		return "UpdateDocumentDTO [key=" + key + ", document=" + document
				+ ", requestedBy=" + requestedBy + ", requestedById="
				+ requestedById + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((document == null) ? 0 : document.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result
				+ ((requestedBy == null) ? 0 : requestedBy.hashCode());
		result = prime * result
				+ ((requestedById == null) ? 0 : requestedById.hashCode());
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
		UpdateDocumentDTO other = (UpdateDocumentDTO) obj;
		if (document == null) {
			if (other.document != null)
				return false;
		} else if (!document.equals(other.document))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (requestedBy == null) {
			if (other.requestedBy != null)
				return false;
		}
		if (requestedById == null) {
			if (other.requestedById != null)
				return false;
		}
		return true;
	}


			
	
}
