package com.infotact.gridweaver.service;

import com.infotact.gridweaver.dto.TelemetryDto;
import java.util.List;

public interface TelemetryService {
    TelemetryDto create(TelemetryDto dto);
    TelemetryDto update(Long id, TelemetryDto dto);
    TelemetryDto getById(Long id);
    List<TelemetryDto> getAll();
    void delete(Long id);
}
