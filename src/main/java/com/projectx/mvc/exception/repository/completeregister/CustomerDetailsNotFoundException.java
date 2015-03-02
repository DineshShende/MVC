package com.projectx.mvc.exception.repository.completeregister;

public class CustomerDetailsNotFoundException extends RuntimeException {

	public CustomerDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public CustomerDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerDetailsNotFoundException(String message) {
        super(message);
    }

    public CustomerDetailsNotFoundException() {
    }

	
}
