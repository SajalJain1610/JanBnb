package com.main.janBnb.exception;

public class CountryAlreadyRegisteredException extends RuntimeException{
    public CountryAlreadyRegisteredException(String message) {
        super(message);
    }
}
