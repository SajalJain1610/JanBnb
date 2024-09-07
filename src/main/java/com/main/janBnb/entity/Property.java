package com.main.janBnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property")
public class Property {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;


    @Column(name = "property_name", nullable = false, length = 200)
    private String propertyName;

    @Column(name = "price")
    private Double price;

    @Column(name = "no_of_bedrooms", nullable = false)
    private Integer noOfBedrooms;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "no_of_guests", nullable = false)
    private Integer noOfGuests;

    @Column(name = "available_rooms")
    private Integer availableRooms;

}