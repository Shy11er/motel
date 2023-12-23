package com.example.Motel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "request")
@Entity
//public abstract class Request implements RequestInterface {
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Room.RoomType roomType;
    private RequestType requestType;
    private Long daysToLive;
    private Boolean payImmediatle;
    private String feature;
    private Long roomNumber;
    private Long roomCount;
    private Long amountPrice;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private String status;

//    private List<BookingRequest> bookingRequests;
//    private List<CheckInRequest> checkInRequests;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "guest_id")
    @JsonIgnoreProperties("request")
    private Guest guest;
    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("request")
    private List<Room> rooms;

    public Request() {}
    public enum RequestType {
        Booking,
        CheckIn
    }

//    @Override
//    public Room.RoomType getRoomType() {
//        return this.roomType;
//    }
//    @Override
//    public Boolean getPayImmediatle() {
//        return this.payImmediatle;
//    }
//    @Override
//    public String getFeature() {
//        return this.feature;
//    }
//    @Override
//    public Long getDaysToLive() {
//        return this.daysToLive;
//    }
//    @Override
//    public Guest getGuest() {
//        return this.guest;
//    }
//    @Override
//    public List<Room> getRooms() {
//        return this.rooms;
//    }


    public void setRoomType(Room.RoomType roomType) {
        this.roomType = roomType;
    }
    public void setPayImmediatle(Boolean payImmediatle) {
        this.payImmediatle = payImmediatle;
    }
    public void setFeature(String feature) {
        this.feature = feature;
    }
    public void setDaysToLive(Long daysToLive) {
        this.daysToLive = daysToLive;
    }
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
    public void setAmountPrice(Long amountPrice) {
        this.amountPrice = amountPrice;
    }
    public Long getAmountPrice() {
        return amountPrice;
    }
    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }
    public Long getDaysToLive() {
        return daysToLive;
    }
    public Boolean getPayImmediatle() {
        return payImmediatle;
    }
    public Room.RoomType getRoomType() {
        return roomType;
    }
    public String getFeature() {
        return feature;
    }
    public Guest getGuest() {
        return guest;
    }
    public List<Room> getRooms() {
        return rooms;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public Long getRoomNumber() {
        return roomNumber;
    }
    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }
    public RequestType getRequestType() {
        return requestType;
    }
    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }
    public LocalDateTime getDeparture() {
        return departure;
    }
    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }
    public Long getRoomCount() {
        return roomCount;
    }
    public void setRoomCount(Long roomCount) {
        this.roomCount = roomCount;
    }
    public LocalDateTime getArrival() {
        return arrival;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
