package com.example.Motel.service;

import com.example.Motel.dto.RequestDto;
import com.example.Motel.model.Guest;
import com.example.Motel.model.Request;
import com.example.Motel.model.Room;
import com.example.Motel.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
//    private List<Room> rooms = new ArrayList<>();

//    public List<Room> getRooms(Request dto) {
//        List<Room> rooms = new ArrayList<>();
//        long roomNumber = dto.getRoomNumber() == null || dto.getRoomNumber() == 0 ? 1 : dto.getRoomNumber();
//        Long roomCount = dto.getRoomCount();
//        Room.RoomType roomType = dto.getRoomType();
//        String feature = dto.getFeature();
//        List<Room> findRoom = roomRepository.findAllBy(roomCount, roomType, Arrays.asList(Room.RoomStatus.Busy, Room.RoomStatus.Booked), null);
//        System.out.println(findRoom.size());
//        if (findRoom.size() >= roomNumber) {
//            for (int i = 0; i < roomNumber; i++) {
//                Room room = findRoom.get(i);
//                Room.RoomStatus roomStatus = dto.getRequestType() == Request.RequestType.Booking ? Room.RoomStatus.Booked : Room.RoomStatus.Busy;
//                room.setStatus(roomStatus);
//                rooms.add(room);
//                roomNumber--;
//            }
//        } else if (!findRoom.isEmpty()) {
//            int i = 0;
//            while (roomNumber > 0) {
//                roomNumber--;
//                if (findRoom.size() <= i) break;
//                Room room = findRoom.get(i);
//                i++;
//                rooms.add(room);
//            }
//        }
//        if (rooms.isEmpty()) {
//            int i = 0;
//            List<Room> available_rooms = roomRepository.findAllBy(null, roomType,Arrays.asList(Room.RoomStatus.Busy, Room.RoomStatus.Booked), null);
//            System.out.println(available_rooms.size());
//            if (roomNumber <= available_rooms.size()) {
//                for (i = 0; i < roomNumber; i++) {
//                    roomNumber--;
//                    if (i >= available_rooms.size()) break;
//                    Room room = available_rooms.get(i);
//                    rooms.add(room);
//                }
//            } else {
//                i = 0;
//                while(roomNumber > 0) {
//                    roomNumber--;
//                    if (i >= available_rooms.size()) break;
//                    Room room = available_rooms.get(i);
//                    i++;
//                    rooms.add(room);
//                }
//            }
//        }
//        return rooms;
//    }

    public List<Room> getRooms(Request request) {
        List<Room> rooms = findRoomsForRequest(request);

        if (rooms.size() >= request.getRoomNumber()) {
            return rooms.subList(0, Math.toIntExact(request.getRoomNumber()));
        } else {
            List<Room> availableRooms = findAvailableRooms(request.getRoomType(), request.getRoomCount());
            int remainingRooms = Math.toIntExact(request.getRoomNumber()) - rooms.size();

            if (remainingRooms <= availableRooms.size()) {
                rooms.addAll(availableRooms.subList(0, remainingRooms));
            } else {
                rooms.addAll(availableRooms);
            }

            return rooms;
        }
    }

    private List<Room> findRoomsForRequest(Request request) {
        return roomRepository.findAllBy(
                request.getRoomCount(),
                request.getRoomType(),
                Arrays.asList(Room.RoomStatus.Busy, Room.RoomStatus.Booked),
                null
        );
    }

    private List<Room> findAvailableRooms(Room.RoomType roomType, Long roomCount) {
        return roomRepository.findAllBy(
                null,
                roomType,
                Arrays.asList(Room.RoomStatus.Busy, Room.RoomStatus.Booked),
                null
        );
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }
}
