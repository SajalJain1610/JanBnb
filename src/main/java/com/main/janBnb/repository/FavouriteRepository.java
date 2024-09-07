package com.main.janBnb.repository;

import com.main.janBnb.entity.Favourite;
import com.main.janBnb.entity.Property;
import com.main.janBnb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite, String> {
    Optional<Favourite> findByUserAndProperty(User user, Property property);
}