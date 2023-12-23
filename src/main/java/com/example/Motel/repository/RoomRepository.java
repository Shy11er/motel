package com.example.Motel.repository;

import com.example.Motel.model.Guest;
import com.example.Motel.model.Room;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r WHERE (:roomCount IS NULL OR r.roomCount = :roomCount) AND r.status NOT IN :status  AND r.roomType = :roomType" +
            " AND (:feature IS NULL OR r.features = :feature)")
    List<Room> findOneBy(@Param("roomCount") Long roomCount,
                         @Param("roomType") Room.RoomType roomType,
                         @Param("status") List<Room.RoomStatus> status,
                         @Param("feature") String feature);
}
