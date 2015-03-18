package com.projectx.mvc.exception.repository.quickregister;

import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;

public class EmailVerificationDetailNotFoundException extends ResourceNotFoundException {

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
