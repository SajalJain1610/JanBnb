package com.main.janBnb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {

    @Column(name = "name", nullable = false, unique = true, length = 200)
    @Size(min = 2,message = "Country name must be at least 2 characters")
    private String name;

    @Id
    @Column(name="id" ,length = 36, unique = true, nullable = false)
    private String id;

}