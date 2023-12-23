package com.example.Motel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "request_id")
    @JsonBackReference
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

    public Request getRequest() {
        return request;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public Long getId() {
        return id;
    }

    public Long getRoomCount() {
        return roomCount;
    }

    public Long getPrice() {
        return price;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public String getFeatures() {
        return features;
    }
}
