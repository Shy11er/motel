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
//    @Column(name = "room_number", columnDefinition = "BIGINT")
    private Long roomNumber;
    public BookingRequest() {
        super();
    }
//    @Override
//    public LocalDateTime getArrival() {
//        return this.arrival;
//    }
}
