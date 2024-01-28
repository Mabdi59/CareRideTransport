package com.careridetransport.server.exception;

public class CustomInvalidOperationException extends RuntimeException {

    public CustomInvalidOperationException(String message) {
        super(message);
    }

    // Optionally, add a constructor for message and cause.
    public CustomInvalidOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
