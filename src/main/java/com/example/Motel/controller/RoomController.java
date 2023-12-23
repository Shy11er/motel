package com.example.Motel.controller;

import com.example.Motel.dto.RequestDto;
import com.example.Motel.model.Guest;
import com.example.Motel.model.Request;
import com.example.Motel.model.Room;
import com.example.Motel.service.GuestService;
import com.example.Motel.service.RequestService;
import com.example.Motel.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path="room")
@CrossOrigin(origins = "http://localhost:5173")
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Room>> getAll() {
        List<Room> rooms = roomService.getAll();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }


}