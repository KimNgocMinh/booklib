package com.minh.booklib.exception;

public class InvalidAccountException extends RuntimeException {
    private int code;
    private String message;

    public InvalidAccountException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
