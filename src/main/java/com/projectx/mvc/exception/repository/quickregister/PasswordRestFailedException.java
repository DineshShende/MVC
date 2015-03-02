package com.projectx.mvc.exception.repository.quickregister;

public class PasswordRestFailedException extends RuntimeException {

	
	public PasswordRestFailedException(Throwable cause) {
        super(cause);
    }

    public PasswordRestFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordRestFailedException(String message) {
        super(message);
    }

    public PasswordRestFailedException() {
    }

}
