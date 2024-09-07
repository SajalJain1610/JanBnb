package com.main.janBnb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> UserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest webRequest){
         ExceptionDetails exceptionDetails = new ExceptionDetails();
         exceptionDetails.setMessage(ex.getMessage());
         exceptionDetails.setDateTime(LocalDateTime.now());
         exceptionDetails.setWebUrl(webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<?> countryNotFoundException(CountryNotFoundException ex, WebRequest webRequest){
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage(ex.getMessage());
        exceptionDetails.setDateTime(LocalDateTime.now());
        exceptionDetails.setWebUrl(webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CountryAlreadyRegisteredException.class)
    public ResponseEntity<?> CountryAlreadyRegisteredException(CountryAlreadyRegisteredException ex, WebRequest webRequest){
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage(ex.getMessage());
        exceptionDetails.setDateTime(LocalDateTime.now());
        exceptionDetails.setWebUrl(webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(LocationAlreadyRegisteredException.class)
    public ResponseEntity<?> locationAlreadyRegisteredException(LocationAlreadyRegisteredException ex, WebRequest webRequest){
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage(ex.getMessage());
        exceptionDetails.setDateTime(LocalDateTime.now());
        exceptionDetails.setWebUrl(webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<?> locationNotFoundException(LocationNotFoundException ex, WebRequest webRequest){
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage(ex.getMessage());
        exceptionDetails.setDateTime(LocalDateTime.now());
        exceptionDetails.setWebUrl(webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(PropertyNotFoundException.class)
    public ResponseEntity<?> propertyNotFoundException(PropertyNotFoundException ex, WebRequest webRequest){
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage(ex.getMessage());
        exceptionDetails.setDateTime(LocalDateTime.now());
        exceptionDetails.setWebUrl(webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReviewAlreadyExistsException.class)
    public ResponseEntity<?> reviewAlreadyExistsException(ReviewAlreadyExistsException ex, WebRequest webRequest){
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage(ex.getMessage());
        exceptionDetails.setDateTime(LocalDateTime.now());
        exceptionDetails.setWebUrl(webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<?> reviewNotFoundException(ReviewNotFoundException ex, WebRequest webRequest){
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage(ex.getMessage());
        exceptionDetails.setDateTime(LocalDateTime.now());
        exceptionDetails.setWebUrl(webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<?> bookingNotFoundException(BookingNotFoundException ex, WebRequest webRequest){
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage(ex.getMessage());
        exceptionDetails.setDateTime(LocalDateTime.now());
        exceptionDetails.setWebUrl(webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(RoomsAreNotAvailable.class)
    public ResponseEntity<?> roomsAreNotAvailable(RoomsAreNotAvailable ex, WebRequest webRequest){
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage(ex.getMessage());
        exceptionDetails.setDateTime(LocalDateTime.now());
        exceptionDetails.setWebUrl(webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
}
