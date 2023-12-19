package com.example.Motel.service;

import com.example.Motel.model.Guest;
import com.example.Motel.repository.GuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GuestService {
    private final GuestRepository guestRepository;

    public Guest create(Guest guestDto) {
        Guest guest = new Guest();
        guest.setFirstName(guestDto.getFirstName());
        guest.setSurName(guestDto.getSurName());
        guest.setLastName(guestDto.getLastName());
        guestRepository.save(guest);
        return guest;
    }

    public void update(Long guest_id, Guest updateGuest) {
        Guest existingGuest = guestRepository.findById(guest_id).orElse(null);
        if (existingGuest != null) {
            if (updateGuest.getFirstName() != null) {
                existingGuest.setFirstName(updateGuest.getFirstName());
            }

            if (updateGuest.getSurName() != null) {
                existingGuest.setSurName(updateGuest.getSurName());
            }

            if (updateGuest.getLastName() != null) {
                existingGuest.setLastName(updateGuest.getLastName());
            }

            guestRepository.save(existingGuest);
        }
    }
    public boolean delete(Long guest_id) {
        boolean guestExist = this.guestExist(guest_id);

        if (guestExist) {
            guestRepository.deleteById(guest_id);
            return true;
        }
        return false;
    }
    public List<Guest> getAll() {
        return guestRepository.findAll();
    }

    public boolean guestExist(Long guest_id) {
        return guestRepository.existsById(guest_id);
    }

    public Guest get(Long guest_id) {
        return guestRepository.findById(guest_id).orElseThrow(() -> new RuntimeException("Гость не найден"));
    }
}
