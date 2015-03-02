package com.projectx.mvc.exception.repository.completeregister;

public class ResourceAlreadyPresentException extends RuntimeException{

	public ResourceAlreadyPresentException(Throwable cause) {
        super(cause);
    }

    public ResourceAlreadyPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceAlreadyPresentException(String message) {
        super(message);
    }

    public ResourceAlreadyPresentException() {
    }
	
}
