package com.main.janBnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {




    @Id
    @Column(name = "id", nullable = false,length = 36)
    private String id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "mobile", nullable = false, length = 15)
    private String mobile;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gst")
    private Double gst;

    @Column(name = "total_price")
    private Double  totalPrice;

    @Column(name = "no_of_nights", nullable = false)
    private int noOfNights;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    private int noOfRooms;

}