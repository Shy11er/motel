package com.example.Motel.service;

import com.example.Motel.config.SimulationInterface;
import com.example.Motel.dto.RequestDto;
import com.example.Motel.model.Guest;
import com.example.Motel.model.Request;
import com.example.Motel.model.Room;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
//@AllArgsConstructor
//@NoArgsConstructor
public class SimulationService implements SimulationInterface {
    private final GuestService guestService;
    private final RequestService requestService;

    @Autowired
    public SimulationService(GuestService guestService, RequestService requestService) {
        this.guestService = guestService;
        this.requestService = requestService;
    }

    private static List<String> features = Arrays.asList("Вид на море", "Удобные креслы", "Просторный шкаф", "Есть приставка", "Есть мини-бар");

    private List<String> RandFirstName = Arrays.asList("Александр", "Иван", "Дмитрий", "Егор", "Николай", "Максим", "Артем", "Владимир", "Сергей", "Андрей", "Алексей", "Павел", "Михаил", "Владислав", "Кирилл", "Денис", "Роман", "Ярослав", "Тимофей", "Игорь");
    private List<String> RandSurName = Arrays.asList("Иванов", "Петров", "Смирнов", "Соколов", "Кузнецов", "Попов", "Лебедев", "Козлов", "Новиков", "Морозов", "Петров", "Волков", "Соловьев", "Васильев", "Зайцев", "Павлов", "Семенов", "Голубев", "Виноградов", "Богданов");
    private List<String> RandLastName = Arrays.asList("Иванович", "Петрович", "Сидорович", "Александрович", "Николаевич", "Павлович", "Сергеевич", "Васильевич", "Григорьевич", "Дмитриевич", "Анатольевич", "Константинович", "Станиславович", "Владимирович", "Тимофеевич", "Егорович", "Владиславович", "Михайлович", "Никитович", "Артемович");

    private static final long interval = 1;
    private boolean run = false;
    private static int dayInHour;
    private int step;
    private static LocalDateTime currentDate;


    @Scheduled(fixedRate = interval)
    public void start() {
        if (run) {
            dayInHour -= step;

            for(int i = 0; i < 2; i++) {
                Request.RequestType requestTypes[] = Request.RequestType.values();
                Room.RoomType roomType[] = Room.RoomType.values();
                int randFN = getRandomNumberInRange(0, RandFirstName.size() - 1);
                int randSN = getRandomNumberInRange(0, RandSurName.size() - 1);
                int randLN = getRandomNumberInRange(0,RandLastName.size() - 1);
                long roomNumber = getRandomNumberInRange(1, 10);
                long roomCount = getRandomNumberInRange(1, 3);
                int randReqType = getRandomNumberInRange(0, requestTypes.length - 1);
                int randRoomType = getRandomNumberInRange(0, roomType.length-1);
                int featureInd = getRandomNumberInRange(0, features.size()-1);
                long daysToLive = getRandomNumberInRange(1, 4);

                Guest newGuest = new Guest();
                newGuest.setFirstName(RandFirstName.get(randFN));
                newGuest.setSurName(RandSurName.get(randSN));
                newGuest.setLastName(RandLastName.get(randLN));
                Guest guest = guestService.create(newGuest);

                RequestDto requestDto = new RequestDto();
                requestDto.setGuest_id(guest.getId());
                requestDto.setPayImmediatle(getRandomNumberInRange(0, 1) == 0 ? false : true);
                requestDto.setRoomNumber(roomNumber);
                requestDto.setRequestType(requestTypes[randReqType]);
                requestDto.setRoomType(roomType[randRoomType]);
                requestDto.setDaysToLive(daysToLive);
                requestDto.setRoomCount(roomCount);

                int randFe = new Random().nextInt(10);
                if (randFe >= 6) {
                    requestDto.setFeature(features.get(featureInd));
                } else {
                    requestDto.setFeature(null);
                }

                if (requestDto.getRequestType().equals(Request.RequestType.Booking)) {
                    requestDto.setArrival(currentDate.plusHours(getRandomNumberInRange(0, 4)).plusHours(getRandomNumberInRange(1, 5)));
                } else {
                    requestDto.setArrival(currentDate);
                }
                requestService.create(guest, requestDto);
            }

            currentDate = currentDate.plusHours(step);

            if (dayInHour <= 0) {
                run = false;
            }
        }
    }

    public int getRandomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public void init(int day, int step) {
        run = true;
        this.dayInHour = day*24;
        this.step = step;
        this.currentDate = LocalDateTime.now();
    }

    public int getStep() {
        return step;
    }
}
