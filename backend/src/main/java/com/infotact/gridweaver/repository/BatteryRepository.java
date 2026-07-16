package com.infotact.gridweaver.repository;

import com.infotact.gridweaver.entity.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long> {
}
