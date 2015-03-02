package com.projectx.mvc.exception.repository.quickregister;

public class EmailVerificationDetailNotFoundException extends RuntimeException {

	public EmailVerificationDetailNotFoundException(Throwable cause) {
        super(cause);
    }

    public EmailVerificationDetailNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailVerificationDetailNotFoundException(String message) {
        super(message);
    }

    public EmailVerificationDetailNotFoundException() {
    }


	
}
