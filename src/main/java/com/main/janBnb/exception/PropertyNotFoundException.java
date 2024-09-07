package com.main.janBnb.exception;

public class PropertyNotFoundException extends  RuntimeException{

    public PropertyNotFoundException(String message) {
        super(message);
    }
}
