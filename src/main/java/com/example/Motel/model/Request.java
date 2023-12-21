package com.example.Motel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Table
@Entity
public class Request {
    @Id
    @GeneratedValue
    private Long id;
    private RequestType requestType;
    private Room.RoomType roomType;
    private Long daysToLive;
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "guest_id")
    @JsonIgnoreProperties("request")
    private Guest guest;
    @OneToMany(mappedBy = "request", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Room> rooms;

    public Request() {}
    public enum RequestType {
        Booking,
        CheckInw
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDaysToLive(Long daysToLive) {
        this.daysToLive = daysToLive;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
