package com.main.janBnb.service;

import com.main.janBnb.entity.Country;
import com.main.janBnb.entity.Location;
import com.main.janBnb.entity.Property;
import com.main.janBnb.exception.CountryNotFoundException;
import com.main.janBnb.exception.LocationNotFoundException;
import com.main.janBnb.exception.PropertyNotFoundException;
import com.main.janBnb.payload.PropertyDto;
import com.main.janBnb.payload.PropertyReturnDto;
import com.main.janBnb.repository.CountryRepository;
import com.main.janBnb.repository.LocationRepository;
import com.main.janBnb.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService{

    private PropertyRepository propertyRepository;
    private LocationRepository locationRepository;
    private CountryRepository countryRepository;
    public PropertyServiceImpl(PropertyRepository propertyRepository, LocationRepository locationRepository, CountryRepository countryRepository) {
        this.propertyRepository = propertyRepository;
        this.locationRepository = locationRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public PropertyReturnDto addProperty(PropertyDto dto) {
         Property property = propertyDtoToEntity(dto);
         Property savedProperty = propertyRepository.save(property);
        PropertyReturnDto propertyReturnDto = propertyEntityToDto(savedProperty);
        return propertyReturnDto;
    }

    @Override
    public PropertyReturnDto updateProperty(PropertyDto dto,String propertyId) {
         Optional<Property> opProperty = propertyRepository.findById(propertyId);
         if(opProperty.isPresent()){
              Property property = opProperty.get();
              property.setDescription(dto.getDescription());
              property.setPrice(dto.getPrice());
              Property savedProperty = propertyRepository.save(property);
              return propertyEntityToDto(savedProperty);

         }else{
             throw new PropertyNotFoundException("Property Not Found");
         }
    }

    @Override
    public List<PropertyReturnDto> searchHotel(String locationName) {
         List<Property> hotels = propertyRepository.findHotels(locationName);
        final List<PropertyReturnDto> listOfHotels = hotels.stream().map(hotel -> propertyEntityToDto(hotel)).collect(Collectors.toList());
        return listOfHotels;
    }

    @Override
    public String deleteProperty(String propertyId) {
         Property property = propertyRepository.findById(propertyId).orElseThrow(
                () -> new PropertyNotFoundException("Property is not available")
        );
         propertyRepository.deleteById(propertyId);
        return "Property Deleted Successfully";
    }


    private PropertyReturnDto propertyEntityToDto(Property savedProperty) {
        PropertyReturnDto property = new PropertyReturnDto();
        property.setPropertyId(savedProperty.getId());
        property.setPropertyName(savedProperty.getPropertyName());
        property.setDescription(savedProperty.getDescription());
        property.setNoOfBedrooms(savedProperty.getNoOfBedrooms());
        property.setNoOfGuests(savedProperty.getNoOfGuests());
        property.setPrice(savedProperty.getPrice());
        property.setCountryName(savedProperty.getCountry().getName());
        property.setLocationName(savedProperty.getLocation().getName());
        return property;
    }

    private Property propertyDtoToEntity(PropertyDto dto) {
         Property property = new Property();
         String propertyId = UUID.randomUUID().toString();
         property.setId(propertyId);
         property.setAvailableRooms(dto.getNoOfBedrooms());
         property.setPropertyName(dto.getPropertyName());
         property.setDescription(dto.getDescription());
         property.setNoOfBedrooms(dto.getNoOfBedrooms());
         property.setNoOfGuests(dto.getNoOfGuests());
         property.setPrice(dto.getPrice());
         property.setAvailableRooms(dto.getNoOfBedrooms());
         Location location = locationRepository.findById(dto.getLocationId()).orElseThrow(
                () -> new LocationNotFoundException("Location is not found for " + dto.getLocationId() + ".")
        );
         property.setLocation(location);
        Country country = countryRepository.findById(dto.getCountryId()).orElseThrow(
                () -> new CountryNotFoundException("Country is not found for " + dto.getCountryId() + ".")
        );
        property.setCountry(country);
        return property;
    }
}
