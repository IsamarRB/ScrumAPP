package com.scrumapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "Project")

public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Integer getId(){
        return id;
    }
    public void setId (Integer id){
        this.id = id;
    }

    public  String getName(){
        return name;
    }
    public void setName (String name){
        this.name = name;
    }

}
