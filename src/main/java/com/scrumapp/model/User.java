package com.scrumapp.model;

import jakarta.persistence.*;
import java.util.HashSet;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})

public class User {

    @Id
    @GeneratedValue
    Integer id;
    @Basic
    @Column(nullable = false)
    String username;
    String email;
    String password;
    @Enumerated(EnumType.STRING)
    ERole role;

    }
}
