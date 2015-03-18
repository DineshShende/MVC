package com.projectx.mvc.exception.repository.quickregister;

import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;

public class MobileVerificationDetailsNotFoundException extends
		ResourceNotFoundException {
	
	public MobileVerificationDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public MobileVerificationDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MobileVerificationDetailsNotFoundException(String message) {
        super(message);
    }

    public MobileVerificationDetailsNotFoundException() {
    }


}
