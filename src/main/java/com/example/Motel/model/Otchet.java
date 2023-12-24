package com.example.Motel.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

//@Getter
//@Setter
@Component
public class Otchet {
    private long allRooms = 0;
    private long RequestsNotSubmitted = 0;
    private long RequestsSubmitted = 0;
    private long amountPrice = 0;
    private long luxury_count = 0;
    private long ordinary_count = 0;
    private double lux_occupancy_percentage = 0;
    private double ordinary_occupancy_percentage = 0;
    private long one_room = 0;
    private long two_room = 0;
    private long three_room = 0;

    public void addRequestsNotSubmitted() {
        this.RequestsNotSubmitted++;
    }
    public void addRequestsSubmitted() {
        this.RequestsSubmitted++;
    }
    public long getRequestsNotSubmitted() {
        return RequestsNotSubmitted;
    }
    public long getRequestsSubmitted() {
        return RequestsSubmitted;
    }
    public void setRequestsSubmitted(long requestsSubmitted) {
        RequestsSubmitted = requestsSubmitted;
    }
    public void setRequestsNotSubmitted(long requestsNotSubmitted) {
        RequestsNotSubmitted = requestsNotSubmitted;
    }

    public long getAmountPrice() {
        return amountPrice;
    }

    public void setAmountPrice(long amountPrice) {
        this.amountPrice = amountPrice;
    }
    public void addAmountPrice(long amountPrice) {
        this.amountPrice += amountPrice;
    }
    public void setLux_occupancy_percentage(long lux_occupancy_percentage) {
        this.lux_occupancy_percentage = lux_occupancy_percentage;
    }
    public void setOne_room(long one_room) {
        this.one_room = one_room;
    }
    public void setLuxury_count(long luxury_count) {
        this.luxury_count = luxury_count;
    }
    public void setOrdinary_count(long ordinary_count) {
        this.ordinary_count = ordinary_count;
    }
    public void setOrdinary_occupancy_percentage(long ordinary_occupancy_percentage) {
        this.ordinary_occupancy_percentage = ordinary_occupancy_percentage;
    }
    public void setThree_room(long three_room) {
        this.three_room = three_room;
    }
    public void setTwo_room(long two_room) {
        this.two_room = two_room;
    }
    public double getLux_occupancy_percentage() {
        return lux_occupancy_percentage;
    }
    public long getLuxury_count() {
        return luxury_count;
    }
    public long getOne_room() {
        return one_room;
    }
    public long getOrdinary_count() {
        return ordinary_count;
    }
    public double getOrdinary_occupancy_percentage() {
        return ordinary_occupancy_percentage;
    }
    public long getThree_room() {
        return three_room;
    }
    public long getTwo_room() {
        return two_room;
    }

    public void setOrdinary_occupancy_percentage(double ordinary_occupancy_percentage) {
        this.ordinary_occupancy_percentage = ordinary_occupancy_percentage;
    }

    public void setLux_occupancy_percentage(double lux_occupancy_percentage) {
        this.lux_occupancy_percentage = lux_occupancy_percentage;
    }

    public void setAllRooms(long allRooms) {
        this.allRooms = allRooms;
    }

    public long getAllRooms() {
        return allRooms;
    }
}
