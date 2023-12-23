package com.example.Motel.dto;

import com.example.Motel.model.Request;
import com.example.Motel.model.Room;

import java.time.LocalDateTime;

public class RequestDto {
    Long guest_id;
    LocalDateTime arrival;
    Boolean payImmediatle;
    Long daysToLive;
    Request.RequestType requestType;
    Room.RoomType roomType;
    Long roomCount;
    Long roomNumber;
    String feature;

    public Long getGuest_id() {
        return guest_id;
    }
    public LocalDateTime getArrival() {
        return arrival;
    }
    public Request.RequestType getRequestType() {
        return requestType;
    }
    public Long getRoomCount() {
        return roomCount;
    }
    public Long getRoomNumber() {
        return roomNumber;
    }
    public Room.RoomType getRoomType() {
        return roomType;
    }
    public String getFeature() {
        return feature;
    }
    public Boolean getPayImmediatle() {
        return payImmediatle;
    }
    public Long getDaysToLive() {
        return daysToLive;
    }
}
//