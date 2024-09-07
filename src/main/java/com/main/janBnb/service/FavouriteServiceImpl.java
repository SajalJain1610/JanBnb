package com.main.janBnb.service;

import com.main.janBnb.entity.Favourite;
import com.main.janBnb.entity.Property;
import com.main.janBnb.entity.User;
import com.main.janBnb.exception.PropertyNotFoundException;
import com.main.janBnb.repository.FavouriteRepository;
import com.main.janBnb.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FavouriteServiceImpl implements FavouriteService{
    @Autowired
    private FavouriteRepository favouriteRepository;
    @Autowired
    private PropertyRepository propertyRepository;

    public FavouriteServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Favourite addFavourite(User user, Boolean status, String propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(
                () -> new PropertyNotFoundException("Property not found")
        );
        Optional<Favourite> opFavourite = favouriteRepository.findByUserAndProperty(user, property);
        if(opFavourite.isPresent()){
            Favourite favourite = opFavourite.get();
            favourite.setStatus(status);
            return favouriteRepository.save(favourite);
        }
        Favourite favourite = new Favourite();
        String favouriteId = UUID.randomUUID().toString();
        favourite.setId(favouriteId);
        favourite.setStatus(status);
        favourite.setUser(user);
        favourite.setProperty(property);
        return favouriteRepository.save(favourite);

    }

    @Override
    public Favourite removeFavourite(User user, Boolean status, String propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(
                () -> new PropertyNotFoundException("Property not found")
        );
        Optional<Favourite> opFavourite = favouriteRepository.findByUserAndProperty(user, property);
        Favourite favourite = opFavourite.get();
        favourite.setStatus(status);
        return favouriteRepository.save(favourite);
    }
}