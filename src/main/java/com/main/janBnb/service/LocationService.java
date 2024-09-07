package com.main.janBnb.service;
import com.main.janBnb.entity.Location;
import com.main.janBnb.exception.LocationAlreadyRegisteredException;
import com.main.janBnb.exception.LocationNotFoundException;
import com.main.janBnb.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LocationService {

    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location addLocation(Location location) {
         Optional<Location> opLocation = locationRepository.findByName(location.getName());
         if(opLocation.isPresent()){
             throw new LocationAlreadyRegisteredException("Location is already registered.");
         }
        String locationId = UUID.randomUUID().toString();
        location.setId(locationId);
        return locationRepository.save(location);
    }

    public Location updateLocation(String locationId,String name) {
         Location location = locationRepository.findById(locationId).orElseThrow(
                () -> new LocationNotFoundException("Location with ID " + locationId + " not found.")
        );
         location.setName(name);
         return locationRepository.save(location);
    }

    public String removeLocation(String locationId) {
        locationRepository.deleteById(locationId);
        return "Record Deleted Successfully";
    }

    public Location searchLocation(String name) {
         Optional<Location> opLocation = locationRepository.findByName(name);
         if(opLocation.isPresent()){
             return opLocation.get();
         }else {
             throw new LocationNotFoundException("Location is not Available");
         }
    }
}
