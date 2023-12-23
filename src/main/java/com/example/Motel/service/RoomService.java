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

    public List<Room> getRooms(RequestDto dto) {
        List<Room> rooms = new ArrayList<>();
        Long roomNumber = dto.getRoomNumber() == null || dto.getRoomNumber() == 0 ? 1 : dto.getRoomNumber();
        System.out.println("roomNumber %d".formatted(roomNumber));
        Long roomCount = dto.getRoomCount();
        Room.RoomType roomType = dto.getRoomType();
        String feature = dto.getFeature();
        List<Room> findRoom = roomRepository.findOneBy(roomCount, roomType, Arrays.asList(Room.RoomStatus.Busy, Room.RoomStatus.Booked), feature);
        System.out.println(findRoom.size());
        if (findRoom.size() >= roomNumber) {
            for (int i = 0; i < roomNumber; i++) {
                Room room = findRoom.get(i);
                Room.RoomStatus roomStatus = dto.getRequestType() == Request.RequestType.Booking ? Room.RoomStatus.Booked : Room.RoomStatus.Busy;
                room.setStatus(roomStatus);
//                room.setRequest(request);
//                roomRepository.save(room);
//                System.out.println("room %a".formatted(room));
                rooms.add(room);
                roomNumber--;
            }
        } else if (findRoom.size() > 0) {
            int i = 0;
            while (roomNumber > 0) {
                roomNumber--;
                if (findRoom.size() <= i) break;
                Room room = findRoom.get(i);
                i++;
//                Room.RoomStatus roomStatus = dto.getRequestType() == Request.RequestType.Booking ? Room.RoomStatus.Booked : Room.RoomStatus.Busy;
//                room.setStatus(roomStatus);
//                room.setRequest(request);
//                roomRepository.save(room);
                rooms.add(room);
            }
        }
        else {
            int i = 0;
            List<Room> available_rooms = roomRepository.findOneBy(null, roomType,Arrays.asList(Room.RoomStatus.Busy, Room.RoomStatus.Booked), null);
            System.out.println(available_rooms.size());
            if (roomNumber <= available_rooms.size()) {
                for (i = 0; i < roomNumber; i++) {
                    roomNumber--;
                    if (i >= available_rooms.size()) break;
                    Room room = available_rooms.get(i);
//                    Room.RoomStatus roomStatus = dto.getRequestType() == Request.RequestType.Booking ? Room.RoomStatus.Booked : Room.RoomStatus.Busy;
//                    room.setStatus(roomStatus);
//                    room.setRequest(request);
//                    roomRepository.save(room);
                    rooms.add(room);
                }
            } else {
                i = 0;
                while(roomNumber > 0) {
                    roomNumber--;
                    if (i >= available_rooms.size()) break;
                    Room room = available_rooms.get(i);
                    i++;
//                    Room.RoomStatus roomStatus = dto.getRequestType() == Request.RequestType.Booking ? Room.RoomStatus.Booked : Room.RoomStatus.Busy;
//                    room.setStatus(roomStatus);
//                    room.setRequest(request);
//                    roomRepository.save(room);
                    rooms.add(room);
                }
            }
        }
        return rooms;
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }
}
