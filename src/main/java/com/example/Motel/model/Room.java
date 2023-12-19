package com.example.Motel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table
public class Room {
    @Id
    @GeneratedValue
    private Long id;
    private String features;
    private RoomType roomType;
    private Long price;
    private Long roomCount;
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_id")
    @JsonIgnoreProperties("room")
    private Request request;
    public Room() {}

    public enum RoomType {
        Luxury,
        Ordinary
    }
}
