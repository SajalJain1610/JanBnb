package com.main.janBnb.controller;
import com.main.janBnb.entity.*;
import com.main.janBnb.payload.BookingDto;
import com.main.janBnb.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/main/booking")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create/property/{propertyId}")
     public ResponseEntity<?> createBooking(
            @Valid @RequestBody BookingDto dto,
             @PathVariable String propertyId,
             @AuthenticationPrincipal User user,
             BindingResult result
    ) throws IOException {
        if(result.hasErrors()){
        return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
         BookingDto booking = bookingService.createBooking(dto, propertyId, user);
        return new ResponseEntity<>(booking,HttpStatus.CREATED);
    }

    @PostMapping("/delete/{bookingId}")
    public ResponseEntity<?> deleteBooking(
            @PathVariable String bookingId

    ) {
         String msg = bookingService.deleteBooking(bookingId);
        return new ResponseEntity<>(msg,HttpStatus.CREATED);
    }
}
