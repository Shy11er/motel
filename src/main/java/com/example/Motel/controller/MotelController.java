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
        int step = simulationService.getStep();
        List<Request> requestsToRemove = new ArrayList<>();

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
        List<Room> rooms = roomRepository.findAll();
        long luxury_count = 0, ordinary_count = 0;
        double lux_occupancy_percentage = 0,ordinary_occupancy_percentage = 0;
        long one_room = 0, two_room = 0, three_room = 0;

        for (Room room : rooms) {
            if (room.getRoomType() == Room.RoomType.Ordinary) {
                if (room.getRequest() != null) {
                    ordinary_occupancy_percentage++;
                    System.out.println("обычный");
                }
                ordinary_count++;
            } else {
                if (room.getRequest() != null) {
                    lux_occupancy_percentage++;
                    System.out.println("люкс");
                }
                luxury_count++;
            }

            if (room.getRoomCount() == 1) {
                one_room++;
            } else if (room.getRoomCount() == 2) {
                two_room++;
            } else if (room.getRoomCount() == 3) {
                three_room++;
            }
        }

        if (ordinary_count != 0) {
            ordinary_occupancy_percentage = (ordinary_occupancy_percentage / ordinary_count) * 100;
        }
        if (luxury_count != 0) {
            lux_occupancy_percentage = (lux_occupancy_percentage / luxury_count) * 100;
        }

        otchet.setAllRooms(rooms.size());
        otchet.setLuxury_count(luxury_count);
        otchet.setLux_occupancy_percentage(lux_occupancy_percentage);
        otchet.setOrdinary_count(ordinary_count);
        otchet.setOrdinary_occupancy_percentage(ordinary_occupancy_percentage);
        otchet.setOne_room(one_room);
        otchet.setThree_room(three_room);
        otchet.setTwo_room(two_room);

        System.out.println(otchet);
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
            otchet.addAmountPrice(amountPrice);
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