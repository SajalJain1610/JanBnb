package com.main.janBnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "favourite")
public class Favourite {

    @Column(name = "status")
    private Boolean status;

    @Id
    @Column(name = "id", nullable = false,length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

}