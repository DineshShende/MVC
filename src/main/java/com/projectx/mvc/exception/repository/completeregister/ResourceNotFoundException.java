package com.projectx.mvc.exception.repository.completeregister;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException() {
    }


}
