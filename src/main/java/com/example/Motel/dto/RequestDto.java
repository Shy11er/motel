package com.example.Motel.dto;

import com.example.Motel.model.Request;

import java.time.LocalDateTime;

public class RequestDto {
    Request request;
    Long guest_id;
    LocalDateTime date;
    Request.RequestType requestType;

    public Long getGuest_id() {
        return guest_id;
    }
    public Request getRequest() {
        return request;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public Request.RequestType getRequestType() {
        return requestType;
    }
}
