package com.minh.booklib.exception;

public class InvalidUsernameException extends RuntimeException {
    private String message;

    public InvalidUsernameException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
