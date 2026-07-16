package com.infotact.gridweaver.mapper;

import com.infotact.gridweaver.dto.TelemetryDto;
import com.infotact.gridweaver.entity.Telemetry;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;

@Component
public class TelemetryMapper {
    public TelemetryDto toDto(Telemetry entity) {
        if (entity == null) return null;
        TelemetryDto dto = new TelemetryDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
    public Telemetry toEntity(TelemetryDto dto) {
        if (dto == null) return null;
        Telemetry entity = new Telemetry();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
