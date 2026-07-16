package com.infotact.gridweaver.dto;

import lombok.Data;

@Data
public class EventDto {
    private Long id;
    @jakarta.validation.constraints.NotNull
    private Long deviceId;
    private String previousState;
    private String newState;
    private String reason;
    private java.time.LocalDateTime timestamp;
}
