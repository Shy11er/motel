package com.example.Motel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

public class Motel {
    private List<Room> rooms;
    private List<Request> requests;

    public Motel(List<Room> rooms, List<Request> requests) {
        this.rooms= rooms;
        this.requests = requests;
    }

    public List<Room> getRooms() {
        return rooms;
    }
    public List<Request> getRequests() {
        return requests;
    }
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
