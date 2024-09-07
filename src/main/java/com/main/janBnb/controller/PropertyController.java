package com.main.janBnb.controller;

import com.main.janBnb.payload.PropertyDto;
import com.main.janBnb.payload.PropertyReturnDto;
import com.main.janBnb.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/main/property")
public class PropertyController {

    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    @PostMapping("/addProperty")
    public ResponseEntity<?> addProperty(@Valid @RequestBody PropertyDto dto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        PropertyReturnDto PropertyReturnDto = propertyService.addProperty(dto);
        return new ResponseEntity<>(PropertyReturnDto,HttpStatus.CREATED);
    }

    @PostMapping("/updProperty/{propertyId}")
    public ResponseEntity<?> updateProperty( @RequestBody PropertyDto dto, @PathVariable String propertyId){

        PropertyReturnDto PropertyReturnDto = propertyService.updateProperty(dto,propertyId);
        return new ResponseEntity<>(PropertyReturnDto,HttpStatus.CREATED);
    }

    @GetMapping("/searchHotel")
    public ResponseEntity<?> searchProperty(@RequestParam String locationName){
         List<PropertyReturnDto> listOfHotels = propertyService.searchHotel(locationName);
        return new ResponseEntity<>(listOfHotels,HttpStatus.OK);
    }


    @DeleteMapping("/delete/{propertyId}")
    public ResponseEntity<?> deleteProperty(@PathVariable String propertyId){
        String message = propertyService.deleteProperty(propertyId);
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }
}
