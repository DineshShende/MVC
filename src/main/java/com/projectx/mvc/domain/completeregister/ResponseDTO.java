package com.projectx.mvc.domain.completeregister;

public class ResponseDTO {
	
	private String status;
	
	private String errorMessage;

	public ResponseDTO(String status, String errorMessage) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
	}

	public ResponseDTO() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
