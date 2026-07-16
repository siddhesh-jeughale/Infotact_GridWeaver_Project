package com.infotact.gridweaver.dto;

import lombok.Data;

@Data
public class BatteryDto {
    private Long id;
    @jakarta.validation.constraints.NotNull
    private Long deviceId;
    private Double capacityKwh;
    private Double currentChargeKwh;
    private String state;
    private java.time.LocalDateTime lastUpdated;
}
