package com.infotact.gridweaver.repository;

import com.infotact.gridweaver.entity.Telemetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelemetryRepository extends JpaRepository<Telemetry, Long> {
    java.util.List<Telemetry> findTop50ByOrderByTimestampDesc();
}
