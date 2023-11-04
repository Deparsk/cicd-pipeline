package com.paschal.blogTask.exception;

public class WrongDetailsException extends RuntimeException {
    public WrongDetailsException(String message) {
        super(message);
    }
}

