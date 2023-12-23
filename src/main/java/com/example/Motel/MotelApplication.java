package com.example.Motel;

import com.example.Motel.model.Room;
import com.example.Motel.repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class MotelApplication {
	public static void main(String[] args) {
		SpringApplication.run(MotelApplication.class, args);
	}


	private static List<String> features = Arrays.asList("Вид на море", "Удобные креслы", "Просторный шкаф", "Есть приставка", "Есть мини-бар");
//	private static List<String> features = Arrays.asList("View at the sea", "Comfortable chairs ", "Spacious closet", "Game console ", "Minibar");

	@Bean
	CommandLineRunner commandLineRunnerRooms(RoomRepository roomRepository){
		System.out.println(1);
		return args -> {
			int RandRooms = getRandomNumberInRange(50, 70);
			List<Room> rooms = new ArrayList<>();

			while (RandRooms > 0) {
				int randRoomCount = getRandomNumberInRange(1, 3);
				int randRoomType = getRandomNumberInRange(1, 10);
				int randPrice = getRandomNumberInRange(1000, 3000);
				int randFeature = getRandomNumberInRange(1, features.size()) - 1;
				String feature = features.get(randFeature);
				Room.RoomType roomType;

				if (randRoomType >= 8) {
					roomType = Room.RoomType.Luxury;
				} else {
					roomType = Room.RoomType.Ordinary;
				}

				if (roomType == Room.RoomType.Luxury) {
					randPrice += 50;
				}

				Room room = new Room();
				room.setRoomType(roomType);
				room.setRoomCount((long)randRoomCount);
				room.setPrice((long)randPrice);
				room.setFeatures(feature);
				room.setStatus(Room.RoomStatus.Free);
				room.setRequest(null);
				rooms.add(room);
				RandRooms--;
			}

			roomRepository.saveAll(rooms);
		};
	}

	public int getRandomNumberInRange(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}
}
