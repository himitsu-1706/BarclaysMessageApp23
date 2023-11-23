package com.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy =  "sender")
    private List<Message> sentMessages = new ArrayList<>();
    String name;

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue
    Long id;

    private String email;
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}


    public Person(){}

    public Person(String name, String email)
    {
        this.name = name;
        this.email = email;
    }

}
