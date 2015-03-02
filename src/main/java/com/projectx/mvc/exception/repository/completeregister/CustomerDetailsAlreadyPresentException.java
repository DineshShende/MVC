package com.projectx.mvc.exception.repository.completeregister;

public class CustomerDetailsAlreadyPresentException extends RuntimeException {
	
	 public CustomerDetailsAlreadyPresentException(Throwable cause) {
	        super(cause);
	    }

	    public CustomerDetailsAlreadyPresentException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public CustomerDetailsAlreadyPresentException(String message) {
	        super(message);
	    }

	    public CustomerDetailsAlreadyPresentException() {
	    }

}
