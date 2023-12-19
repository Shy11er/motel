package com.example.Motel.controller;

import com.example.Motel.model.Guest;
import com.example.Motel.service.GuestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path="guest")
public class GuestController {
    private final GuestService guestService;

    @PostMapping("/create")
    public ResponseEntity<Guest> create(@RequestBody Guest guestDto) {
        Guest guest = guestService.create(guestDto);
        return new ResponseEntity<>(guest, HttpStatus.CREATED);
    }

    @PutMapping("/update/{guest_id}")
    public ResponseEntity<String> update(@PathVariable("guest_id") Long guest_id, @RequestBody Guest updateGuest) {
        if (guestService.guestExist(guest_id)) {
            guestService.update(guest_id, updateGuest);
            return new ResponseEntity<>("Гость обновлен", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Гость не найден", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{guest_id}")
    public ResponseEntity<Guest> delete(@PathVariable("guest_id") Long guest_id) {
        boolean isDeleted = guestService.delete(guest_id);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Guest>> getAll() {
        List<Guest> guests = guestService.getAll();
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }
}