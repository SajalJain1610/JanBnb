package com.main.janBnb.entity;

import com.main.janBnb.entity.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "password", nullable = false, unique = true, length = 250)
    private String password;

    @Id
    @Column(length = 36, unique = true, nullable = false)
    private String userId;

    @Column(name = "username", nullable = false, unique = true, length = 250)
    private String username;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

}