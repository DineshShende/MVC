package com.projectx.mvc.exception.repository.completeregister;

public class DriverDetailsAlreadyPresentException extends RuntimeException {
	
	public DriverDetailsAlreadyPresentException(Throwable cause) {
        super(cause);
    }

    public DriverDetailsAlreadyPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public DriverDetailsAlreadyPresentException(String message) {
        super(message);
    }

    public DriverDetailsAlreadyPresentException() {
    }


}
