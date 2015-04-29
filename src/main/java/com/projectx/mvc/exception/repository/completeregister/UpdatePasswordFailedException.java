package com.projectx.mvc.exception.repository.completeregister;

public class UpdatePasswordFailedException extends RuntimeException {
	
	public UpdatePasswordFailedException(Throwable cause) {
        super(cause);
    }

    public UpdatePasswordFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdatePasswordFailedException(String message) {
        super(message);
    }

    public UpdatePasswordFailedException() {
    }


}
