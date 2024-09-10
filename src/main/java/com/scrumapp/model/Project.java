package com.scrumapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Project")

public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

}
