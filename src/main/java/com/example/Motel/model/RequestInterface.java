package com.example.Motel.model;


import java.time.LocalDateTime;
import java.util.List;

public interface RequestInterface {
    Room.RoomType getRoomType();
    Boolean getPayImmediatle();
    String getFeature();
    Long getDaysToLive();
    Guest getGuest();
    List<Room> getRooms();
    LocalDateTime getArrival();
}