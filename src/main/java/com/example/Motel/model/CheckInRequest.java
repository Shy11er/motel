package com.example.Motel.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

//@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "checkIn_request")
public class CheckInRequest extends Request{
    @Id
    @GeneratedValue
    private Long id;
//    @Column(name = "arrival", columnDefinition = "TIMESTAMP")

    public CheckInRequest() {
        super();
    }

//    @Override
//    public LocalDateTime getArrival() {
//        return this.arrival;
//    }

}
