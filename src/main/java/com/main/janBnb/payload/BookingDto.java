package com.main.janBnb.payload;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;


@Data
public class BookingDto {

    private String id;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Mobile is mandatory")
    private String mobile;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, message = "Name should be between 2 and 50 characters")
    private String name;

    @Min(value = 1, message = "Number of nights should be at least 1")
    private int noOfNights;


    private String propertyName;

    private Double gst;

    private int noOfRooms;

    private Double totalPrice;

    private LocalDateTime localDateTime;



}
