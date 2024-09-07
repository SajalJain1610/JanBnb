package com.main.janBnb.service;

import com.main.janBnb.entity.Favourite;
import com.main.janBnb.entity.User;

public interface FavouriteService {
    Favourite addFavourite(User user, Boolean status, String propertyId);

    Favourite removeFavourite(User user, Boolean status, String propertyId);
}