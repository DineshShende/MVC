package com.projectx.mvc.exception.repository.completeregister;

public class DriverDetailsNotFoundException extends ResourceNotFoundException {

	
	public DriverDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public DriverDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DriverDetailsNotFoundException(String message) {
        super(message);
    }

    public DriverDetailsNotFoundException() {
    }
}
