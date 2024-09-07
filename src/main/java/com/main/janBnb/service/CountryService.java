package com.main.janBnb.service;
import com.main.janBnb.exception.*;
import com.main.janBnb.entity.Country;
import com.main.janBnb.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CountryService {

    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country addCountry(Country country) {
         Optional<Country> opCountry = countryRepository.findByName(country.getName());
         if(opCountry.isPresent()){
             throw new CountryAlreadyRegisteredException("Country is already registered.");
         }
         String countryId = UUID.randomUUID().toString();
         country.setId(countryId);
        return countryRepository.save(country);
    }

    public Country updateCountry(String countryId,String name) {
         Country country = countryRepository.findById(countryId).orElseThrow(
                () -> new CountryNotFoundException("User with ID " + countryId + " not found.")
        );
         country.setName(name);
         return countryRepository.save(country);
    }

    public String removeCountry(String countryId) {
        countryRepository.deleteById(countryId);
        return "Record Deleted Successfully";
    }

    public Country searchCountry(String name) {
         Optional<Country> opCountry = countryRepository.findByName(name);
         if(opCountry.isPresent()){
             return opCountry.get();
         }else {
             throw new CountryNotFoundException("Country is not Available");
         }
    }
}
