package com.example.Motel.controller;

import com.example.Motel.service.SimulationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path="/simulation")
@CrossOrigin(origins = "http://localhost:5173")
public class SimulationController {
    private final SimulationService simulationService;

//    @GetMapping("/start")
    @GetMapping("/start/{step}/{day}")
//    public String startSim() {
    public String startSim(@PathVariable("day") int day, @PathVariable("step") int step) {
        simulationService.init(day, step);
//        simulationService.init(1, 10);
        return "Симуляция началась, МЫ ВЗЛЕТАЕ-Е-Е-Е-ЕМ!!!";
    }
}
