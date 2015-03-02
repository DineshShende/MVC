package com.projectx.mvc.exception.repository.quickregister;

public class AuthenticationDetailsNotFoundException extends RuntimeException {

	public AuthenticationDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public AuthenticationDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationDetailsNotFoundException(String message) {
        super(message);
    }

    public AuthenticationDetailsNotFoundException() {
    }

	
}
