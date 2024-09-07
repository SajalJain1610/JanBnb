package com.main.janBnb.repository;

import com.main.janBnb.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, String> {
    Optional<Location> findByName(String name);
}