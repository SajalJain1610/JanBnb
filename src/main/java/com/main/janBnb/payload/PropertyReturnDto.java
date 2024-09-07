package com.main.janBnb.payload;

import lombok.Data;

@Data
public class PropertyReturnDto {


    private String propertyId;

    private String propertyName;

    private Double price;

    private Integer noOfBedrooms;

    private String locationName;

    private String description;

    private String  CountryName;

    private Integer noOfGuests;


}
