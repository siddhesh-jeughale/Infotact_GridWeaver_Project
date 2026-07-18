package com.infotact.gridweaver.service;

import com.infotact.gridweaver.dto.TelemetryDto;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class IoTSimulatorService {

    private final TelemetryService telemetryService;
    private final ExecutorService virtualThreadExecutor;
    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    public IoTSimulatorService(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
        this.virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();
    }

    public String startSimulation(int deviceCount) {
        if (isRunning.get()) {
            return "Simulation is already running.";
        }
        isRunning.set(true);

        for (int i = 1; i <= deviceCount; i++) {
            final long deviceId = i;
            virtualThreadExecutor.submit(() -> {
                while (isRunning.get()) {
                    try {
                        TelemetryDto telemetry = new TelemetryDto();
                        telemetry.setDeviceId(deviceId);
                        // Simulate power output between 0 and 10 kW
                        telemetry.setPowerOutputKw(Math.round(Math.random() * 1000.0) / 100.0);
                        // Simulate grid load between 40% and 100%
                        telemetry.setGridLoadPercentage(40.0 + Math.round(Math.random() * 600.0) / 10.0);
                        
                        telemetryService.create(telemetry);
                        
                        // Wait before sending next telemetry (e.g. 2-5 seconds)
                        Thread.sleep(2000 + (long)(Math.random() * 3000));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    } catch (Exception e) {
                        // Ignore sporadic insert errors
                    }
                }
            });
        }
        return "Simulation started with " + deviceCount + " concurrent virtual threads.";
    }

    public String stopSimulation() {
        if (!isRunning.get()) {
            return "Simulation is not running.";
        }
        isRunning.set(false);
        return "Simulation stopped.";
    }
}
