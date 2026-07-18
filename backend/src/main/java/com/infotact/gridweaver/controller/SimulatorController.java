package com.infotact.gridweaver.controller;

import com.infotact.gridweaver.service.IoTSimulatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/simulator")
public class SimulatorController {

    private final IoTSimulatorService simulatorService;

    public SimulatorController(IoTSimulatorService simulatorService) {
        this.simulatorService = simulatorService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startSimulation(@RequestParam(defaultValue = "1000") int count) {
        return ResponseEntity.ok(simulatorService.startSimulation(count));
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stopSimulation() {
        return ResponseEntity.ok(simulatorService.stopSimulation());
    }
}
