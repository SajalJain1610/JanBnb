package com.main.janBnb.repository;

import com.main.janBnb.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, String> {

    @Query("select p from Property p join Country c on p.country=c.id join Location l on p.location=l.id where c.name=:locationName or l.name=:locationName")
    List<Property> findHotels(@Param("locationName") String locationName);
}