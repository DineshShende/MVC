package com.projectx.mvc.exception.repository.completeregister;

public class VendorDetailsNotFoundException extends RuntimeException {

	public VendorDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public VendorDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VendorDetailsNotFoundException(String message) {
        super(message);
    }

    public VendorDetailsNotFoundException() {
    }


	
}
