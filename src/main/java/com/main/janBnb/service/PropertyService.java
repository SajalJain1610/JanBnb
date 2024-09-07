package com.main.janBnb.service;

import com.main.janBnb.payload.PropertyDto;
import com.main.janBnb.payload.PropertyReturnDto;

import java.util.List;

public interface PropertyService {
    PropertyReturnDto addProperty(PropertyDto dto);

    PropertyReturnDto updateProperty(PropertyDto dto, String propertyId);

    List<PropertyReturnDto> searchHotel(String locationName);

    String deleteProperty(String propertyId);


}
