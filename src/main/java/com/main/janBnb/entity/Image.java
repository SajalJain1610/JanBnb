package com.main.janBnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "image")
public class Image {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "file_name")
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

}