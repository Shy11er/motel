package com.example.Motel.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

//@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "booking_request")
public class BookingRequest extends Request{
    @Id
    @GeneratedValue
    private Long id;
//    @Column(name = "arrival", columnDefinition = "TIMESTAMP")
    private LocalDateTime arrival;
//    @Column(name = "room_number", columnDefinition = "BIGINT")
    private Long roomNumber;
    public BookingRequest() {
        super();
    }
//    @Override
//    public LocalDateTime getArrival() {
//        return this.arrival;
//    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }
}
