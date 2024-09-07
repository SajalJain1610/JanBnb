package com.main.janBnb.exception;

public class ReviewAlreadyExistsException extends  RuntimeException{
    public ReviewAlreadyExistsException(String message) {
        super(message);
    }
}
