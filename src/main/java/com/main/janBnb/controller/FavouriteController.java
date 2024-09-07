package com.main.janBnb.controller;

import com.main.janBnb.entity.Favourite;
import com.main.janBnb.entity.User;
import com.main.janBnb.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/main/favourite")
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;

    @PostMapping("/addFavourite/{propertyId}")
    public ResponseEntity<?> addFavourite(
            @AuthenticationPrincipal User user,
            @RequestParam Boolean status,
            @PathVariable String propertyId
    ){
        Favourite favourite = favouriteService.addFavourite(user, status, propertyId);
        return new ResponseEntity<>(favourite, HttpStatus.CREATED);
    }

    @PostMapping("/removeFavourite/{propertyId}")
    public ResponseEntity<?> removeFavourite(
            @AuthenticationPrincipal User user,
            @RequestParam Boolean status,
            @PathVariable String propertyId
    ){
        Favourite favourite = favouriteService.removeFavourite(user, status, propertyId);
        return new ResponseEntity<>(favourite, HttpStatus.CREATED);
    }

}