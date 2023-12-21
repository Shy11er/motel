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
    private RoomStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_id")
    @JsonIgnoreProperties("room")
    private Request request;
    public Room() {}
    public enum RoomType {
        Luxury,
        Ordinary
    }
    public enum RoomStatus {
        Free,
        Busy,
        Booked
    }
    public void setRequest(Request request) {
        this.request = request;
    }
    public void setStatus(RoomStatus status) {
        this.status = status;
    }
    public void setFeatures(String features) {
        this.features = features;
    }
    public void setPrice(Long price) {
        this.price = price;
    }
    public void setRoomCount(Long roomCount) {
        this.roomCount = roomCount;
    }
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
