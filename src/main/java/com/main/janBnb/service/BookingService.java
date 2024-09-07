package com.main.janBnb.service;

import com.main.janBnb.entity.User;
import com.main.janBnb.payload.BookingDto;

import java.io.IOException;

public interface BookingService {
    BookingDto createBooking(BookingDto dto, String propertyId, User user) throws IOException;

    String deleteBooking(String bookingId);
}
