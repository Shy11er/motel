package com.example.Motel.controller;

import com.example.Motel.model.Otchet;
import com.example.Motel.model.Request;
import com.example.Motel.model.Room;
import com.example.Motel.repository.RequestRepository;
import com.example.Motel.repository.RoomRepository;
import com.example.Motel.service.RequestService;
import com.example.Motel.service.RoomService;
import com.example.Motel.service.SimulationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping(path="motel")
public class MotelController {
    private final RoomService roomService;
    private final RequestService requestService;
    private final SimulationService simulationService;
    private final Otchet otchet;

    private final RoomRepository roomRepository;
    private final RequestRepository requestRepository;

    private static LocalDateTime currDate = LocalDateTime.now();

    @GetMapping("/start")
    public void start() {
        List<Request> requests = requestService.getAll();
        requests.sort(Comparator.comparing(Request::getArrival));
//        long r
        int step = simulationService.getStep();
        List<Request> requestsToRemove = new ArrayList<>();
//        long RequestsNotSubmitted = 0;
//        long RequestsSubmitted=  0;

        while (!requests.isEmpty()) {
            Iterator<Request> iterator = requests.iterator();

            while (iterator.hasNext()) {
                Request request = iterator.next();

                int arrivalComparison = currDate.compareTo(request.getArrival());
                if (arrivalComparison < 0) {
                    break;
                }

                handleRequest(request);

                if (currDate.compareTo(request.getDeparture()) >= 0 || request.getRooms().isEmpty() || request.getRooms().equals(null)) {
                    iterator.remove();
                }
            }

            currDate = currDate.plusHours(step);
            System.out.println(currDate);
        }
    }

    @GetMapping("/otchet")
    public ResponseEntity<Otchet> otchet() {
        return new ResponseEntity<>(otchet, HttpStatus.OK);
    }

    private void handleRequest(Request request) {
        List<Room> rooms = roomService.getRooms(request);

        if (rooms.isEmpty()) {
            otchet.addRequestsNotSubmitted();
            request.setStatus("Не получил комнату");
        } else {
            otchet.addRequestsSubmitted();
            long amountPrice = calculateAmountPrice(request, rooms);
            request.setAmountPrice(amountPrice);
            request.setRooms(rooms);
        }

        if (request.getRooms() != null) {
            System.out.println("Current date: " + currDate);
            System.out.println("Departure date: " + request.getDeparture());

            if (currDate.compareTo(request.getDeparture()) >= 0) {
                freeRooms(request.getRooms());
                request.setStatus("Выехал");
                requestRepository.save(request);
                System.out.println("Setting status 'Выехал' for request: " + request.getId());
            }
        }

        requestRepository.save(request);
    }

    private long calculateAmountPrice(Request request, List<Room> rooms) {
        long amountPrice = 0;

        for (Room room : rooms) {
            Room.RoomStatus roomStatus = (request.getRequestType() == Request.RequestType.Booking)
                    ? Room.RoomStatus.Booked : Room.RoomStatus.Busy;

            room.setStatus(roomStatus);
            room.setRequest(request);

            Long roomCount = room.getPrice();
            amountPrice += (roomCount.equals(request.getRoomCount())) ? room.getPrice() : (long) (room.getPrice() * 0.7);

            roomRepository.save(room);
        }

        return amountPrice * request.getDaysToLive();
    }

    private void freeRooms(List<Room> rooms) {
        for (Room room : rooms) {
            room.setRequest(null);
            room.setStatus(Room.RoomStatus.Free);
            roomRepository.save(room);
            System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
        }
    }
}