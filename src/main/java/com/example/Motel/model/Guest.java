package com.example.Motel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Table
@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String surName;
    private String lastName;
    @OneToMany(mappedBy = "guest", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Request> requests;
    public Guest() {}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSurName() {
        return surName;
    }

    public Long getId() {
        return id;
    }

    public List<Request> getRequests() {
        return requests;
    }
}
