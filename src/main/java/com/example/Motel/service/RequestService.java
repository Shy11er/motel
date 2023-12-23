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
            ((BookingRequest) request).setArrival(requestDto.getArrival());
        } else {
            request = new CheckInRequest();
            ((CheckInRequest) request).setArrival(LocalDateTime.now());
        }
//        request.setArrival(requestDto.getRequestType() == Request.RequestType.CheckIn ? LocalDateTime.now() : requestDto.getArrival());
        request.setDaysToLive(requestDto.getDaysToLive());
        request.setFeature(requestDto.getFeature());
        request.setPayImmediatle(requestDto.getPayImmediatle());
        List<Room> rooms = roomService.getRooms(requestDto);
        System.out.println("rooms size %d".formatted(rooms.size()));
        request.setGuest(guest);
//        request.setRooms(rooms);
        request.setRoomType(requestDto.getRoomType());
        request.setRequestType(requestType);
        request.setRoomNumber(requestDto.getRoomNumber());
        long amountPrice = 0;
        for (Room room : rooms) {
            Room.RoomStatus roomStatus = requestDto.getRequestType() == Request.RequestType.Booking ? Room.RoomStatus.Booked : Room.RoomStatus.Busy;
            room.setStatus(roomStatus);
            room.setRequest(request);
            Long roomCount = room.getPrice();
            if (roomCount != requestDto.getRoomCount()) {
                amountPrice += room.getPrice() * 0.7;
            } else {
                amountPrice += room.getPrice();
            }
            System.out.println(room);
            roomRepository.save(room);
        }
        amountPrice *= requestDto.getDaysToLive();
        request.setAmountPrice(amountPrice);
        requestRepository.save(request);
//        roomRepository.saveAll(rooms);
//        requestRepository.save(request);
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
        boolean findRequest = this.existRequest(request_id);

        if (findRequest) {

        }
    }

    public List<Request> getAll() {
        return requestRepository.findAll();
    }

    public boolean existRequest(Long request_id) {
        return requestRepository.existsById(request_id);
    }
}
