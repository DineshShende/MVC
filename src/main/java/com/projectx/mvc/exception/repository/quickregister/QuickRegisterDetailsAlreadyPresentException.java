package com.projectx.mvc.exception.repository.quickregister;

public class QuickRegisterDetailsAlreadyPresentException extends
		RuntimeException {

	public QuickRegisterDetailsAlreadyPresentException(Throwable cause) {
        super(cause);
    }

    public QuickRegisterDetailsAlreadyPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuickRegisterDetailsAlreadyPresentException(String message) {
        super(message);
    }

    public QuickRegisterDetailsAlreadyPresentException() {
    }

	
}
