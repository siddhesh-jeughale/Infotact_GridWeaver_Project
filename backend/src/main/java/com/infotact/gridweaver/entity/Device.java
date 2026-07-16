package com.infotact.gridweaver.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "device")
@Data
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private Double latitude;
    private Double longitude;
    private String status;
    private java.time.LocalDateTime createdAt;
    @jakarta.persistence.PrePersist
    public void prePersist() { this.createdAt = java.time.LocalDateTime.now(); }
}
