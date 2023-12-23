package com.example.Motel.service;

import com.example.Motel.dto.RequestDto;
import com.example.Motel.model.*;
import com.example.Motel.repository.RequestRepository;
import com.example.Motel.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final RoomRepository roomRepository;
    private final RoomService roomService;

    public Request create(Guest guest, RequestDto requestDto) {
        Request request;
        Request.RequestType requestType = requestDto.getRequestType() == Request.RequestType.CheckIn ? Request.RequestType.CheckIn: Request.RequestType.Booking;

        if (requestType == Request.RequestType.Booking) {
            request = new BookingRequest();
        } else {
            request = new CheckInRequest();
        }

        request.setArrival(requestDto.getArrival());
        request.setDeparture(requestDto.getArrival().plusDays(requestDto.getDaysToLive()));
        request.setDaysToLive(requestDto.getDaysToLive());
        request.setFeature(requestDto.getFeature());
        request.setPayImmediatle(requestDto.getPayImmediatle());
        request.setGuest(guest);
        request.setRoomType(requestDto.getRoomType());
        request.setRequestType(requestType);
        request.setRoomNumber(requestDto.getRoomNumber());
        request.setRoomCount(requestDto.getRoomCount());
        request.setStatus("Заехал");
        requestRepository.save(request);
        return request;
    }

    public boolean delete(Long request_id) {
        boolean findRequest = this.existRequest(request_id);

        if (findRequest) {
            requestRepository.deleteById(request_id);
            return true;
        }
        return false;
    }

    public void update(Long request_id, RequestDto updateRequest) {
        Request request = requestRepository.findById(request_id).orElse(null);

        if (request != null) {
            request.setDaysToLive(request.getDaysToLive());
            request.setRoomCount(request.getRoomCount());
            request.setRoomType(request.getRoomType());
            request.setFeature(request.getFeature());
            request.setPayImmediatle(request.getPayImmediatle());
            requestRepository.save(request);
        }
    }

    public List<Request> getAll() {
        return requestRepository.findAll();
    }

    public boolean existRequest(Long request_id) {
        return requestRepository.existsById(request_id);
    }
}
