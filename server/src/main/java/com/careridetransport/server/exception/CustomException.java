package com.careridetransport.server.exception;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final Throwable cause;

    public CustomException(String message) {
        super(message);
        this.message = message;
        this.cause = null;
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }
}
