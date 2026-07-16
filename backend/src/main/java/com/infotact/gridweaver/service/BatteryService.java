package com.infotact.gridweaver.service;

import com.infotact.gridweaver.dto.BatteryDto;
import java.util.List;

public interface BatteryService {
    BatteryDto create(BatteryDto dto);
    BatteryDto update(Long id, BatteryDto dto);
    BatteryDto getById(Long id);
    List<BatteryDto> getAll();
    void delete(Long id);
}
