package com.example.Motel.controller;

import com.example.Motel.dto.RequestDto;
import com.example.Motel.model.Guest;
import com.example.Motel.model.Request;
import com.example.Motel.service.GuestService;
import com.example.Motel.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path="request")
@CrossOrigin(origins = "http://localhost:5173")
public class RequestController {
    private final RequestService requestService;
    private final GuestService guestService;

    @PostMapping("/create")
    public ResponseEntity<Request> create(@RequestBody RequestDto requestDto) {
        boolean findGuest = guestService.guestExist(requestDto.getGuest_id());
        if (findGuest) {
            Guest guest = guestService.get(requestDto.getGuest_id());
            Request request = requestService.create(guest, requestDto);

            return new ResponseEntity<>(request, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{request_id}")
    public ResponseEntity<String> update(@PathVariable("request_id") Long request_id, @RequestBody RequestDto updateRequest) {
        if (requestService.existRequest(request_id)) {
            requestService.update(request_id, updateRequest);
            return new ResponseEntity<>("Гость обновлен", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Гость не найден", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{request_id}")
    public ResponseEntity<Request> delete(@PathVariable("request_id") Long request_id) {
        boolean isDeleted = requestService.delete(request_id);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Request>> getAll() {
        List<Request> requests = requestService.getAll();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }


}