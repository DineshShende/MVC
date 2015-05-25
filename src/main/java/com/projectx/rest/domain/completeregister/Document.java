package com.projectx.rest.domain.completeregister;

import java.util.Arrays;

public class Document {
	
private Long id;
	
	private byte[] document;

	public Document() {
		super();
	}

	public Document(Long id, byte[] document) {
		super();
		this.id = id;
		this.document = document;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	@Override
	public String toString() {
		return "Document [id=" + id + ", document=" + Arrays.toString(document)
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(document);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Document other = (Document) obj;
		if (!Arrays.equals(document, other.document))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		return true;
	}
	


}
