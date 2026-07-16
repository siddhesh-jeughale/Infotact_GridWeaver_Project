package com.infotact.gridweaver.service;

import com.infotact.gridweaver.dto.DeviceDto;
import java.util.List;

public interface DeviceService {
    DeviceDto create(DeviceDto dto);
    DeviceDto update(Long id, DeviceDto dto);
    DeviceDto getById(Long id);
    List<DeviceDto> getAll();
    void delete(Long id);
}
