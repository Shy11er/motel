package com.example.Motel.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

//@Getter
//@Setter
@Component
public class Otchet {
    private long RequestsNotSubmitted = 0;
    private long RequestsSubmitted = 0;

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
}
