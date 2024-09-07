package com.main.janBnb.exception;

public class LocationAlreadyRegisteredException extends RuntimeException{
    public LocationAlreadyRegisteredException(String message) {
        super(message);
    }
}
