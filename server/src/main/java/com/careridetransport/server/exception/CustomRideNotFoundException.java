package com.careridetransport.server.exception;

public class CustomRideNotFoundException extends RuntimeException {

    public CustomRideNotFoundException(String message) {
        super(message);
    }

    // Optionally, you can add a constructor that accepts a message and a cause.
    public CustomRideNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
