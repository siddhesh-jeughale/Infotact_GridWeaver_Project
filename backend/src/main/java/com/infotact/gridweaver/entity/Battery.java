package com.infotact.gridweaver.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "battery")
@Data
public class Battery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @jakarta.persistence.Column(name = "device_id")
    private Long deviceId;
    private Double capacityKwh;
    private Double currentChargeKwh;
    private String state;
    private java.time.LocalDateTime lastUpdated;
    @jakarta.persistence.PrePersist
    @jakarta.persistence.PreUpdate
    public void preUpdate() { this.lastUpdated = java.time.LocalDateTime.now(); }
}
