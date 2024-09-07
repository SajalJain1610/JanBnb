package com.main.janBnb.controller;

import com.main.janBnb.entity.Country;
import com.main.janBnb.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/main/country")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/addCountry")
    public ResponseEntity<?> addCountry(@RequestBody Country country){
         Country savedCountry = countryService.addCountry(country);
         return new ResponseEntity<>(savedCountry, HttpStatus.CREATED);
    }

    @PutMapping("/updCountry/{countryId}")
    public ResponseEntity<?> updateCountry(@PathVariable String countryId,@RequestParam String name){
        Country savedCountry = countryService.updateCountry(countryId,name);
        return new ResponseEntity<>(savedCountry, HttpStatus.OK);
    }


    @DeleteMapping("/remove/{countryId}")
    public ResponseEntity<?> removeCountry(@PathVariable String countryId){
         String message = countryService.removeCountry(countryId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCountry(@RequestParam String name){
         Country country = countryService.searchCountry(name);
         return new ResponseEntity<>(country,HttpStatus.OK);
    }
}
