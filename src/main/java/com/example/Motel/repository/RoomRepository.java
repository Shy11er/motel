package com.example.Motel.repository;

import com.example.Motel.model.Guest;
import com.example.Motel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
