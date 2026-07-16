package com.infotact.gridweaver.dto;

import lombok.Data;

@Data
public class TelemetryDto {
    private Long id;
    @jakarta.validation.constraints.NotNull
    private Long deviceId;
    private Double powerOutputKw;
    private Double gridLoadPercentage;
    private java.time.LocalDateTime timestamp;
}
