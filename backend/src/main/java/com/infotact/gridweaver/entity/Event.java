package com.infotact.gridweaver.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "event")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @jakarta.persistence.Column(name = "device_id")
    private Long deviceId;
    private String previousState;
    private String newState;
    private String reason;
    private java.time.LocalDateTime timestamp;
    @jakarta.persistence.PrePersist
    public void prePersist() { if (this.timestamp == null) this.timestamp = java.time.LocalDateTime.now(); }
}
