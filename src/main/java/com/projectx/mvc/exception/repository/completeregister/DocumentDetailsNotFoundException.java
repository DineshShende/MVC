package com.projectx.mvc.exception.repository.completeregister;

public class DocumentDetailsNotFoundException extends RuntimeException{

	public DocumentDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public DocumentDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentDetailsNotFoundException(String message) {
        super(message);
    }

    public DocumentDetailsNotFoundException() {
    }

	
}
