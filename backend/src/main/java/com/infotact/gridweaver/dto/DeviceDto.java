package com.infotact.gridweaver.dto;

import lombok.Data;

@Data
public class DeviceDto {
    private Long id;
    @jakarta.validation.constraints.NotBlank(message = "Name required")
    private String name;
    private String type;
    private Double latitude;
    private Double longitude;
    private String status;
    private java.time.LocalDateTime createdAt;
}
