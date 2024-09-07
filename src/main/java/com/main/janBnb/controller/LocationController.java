package com.main.janBnb.controller;
import com.main.janBnb.entity.Location;
import com.main.janBnb.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/main/location")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/addLocation")
    public ResponseEntity<?> addLocation(@RequestBody Location location){
         Location savedlocation = locationService.addLocation(location);
         return new ResponseEntity<>(savedlocation, HttpStatus.CREATED);
    }

    @PutMapping("/updLocation/{locationId}")
    public ResponseEntity<?> updateLocation(@PathVariable String locationId,@RequestParam String name){
        Location savedlocation = locationService.updateLocation(locationId,name);
        return new ResponseEntity<>(savedlocation, HttpStatus.OK);
    }


    @DeleteMapping("/remove/{locationId}")
    public ResponseEntity<?> removeLocation(@PathVariable String locationId){
         String message = locationService.removeLocation(locationId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchLocation(@RequestParam String name){
        Location location = locationService.searchLocation(name);
         return new ResponseEntity<>(location,HttpStatus.OK);
    }
}
