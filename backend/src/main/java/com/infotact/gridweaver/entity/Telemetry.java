package com.infotact.gridweaver.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "telemetry")
@Data
public class Telemetry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @jakarta.persistence.Column(name = "device_id")
    private Long deviceId;
    private Double powerOutputKw;
    private Double gridLoadPercentage;
    private java.time.LocalDateTime timestamp;
    @jakarta.persistence.PrePersist
    public void prePersist() { if (this.timestamp == null) this.timestamp = java.time.LocalDateTime.now(); }
}
