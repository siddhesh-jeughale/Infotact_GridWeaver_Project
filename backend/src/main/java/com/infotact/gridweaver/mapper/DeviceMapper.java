package com.infotact.gridweaver.mapper;

import com.infotact.gridweaver.dto.DeviceDto;
import com.infotact.gridweaver.entity.Device;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;

@Component
public class DeviceMapper {
    public DeviceDto toDto(Device entity) {
        if (entity == null) return null;
        DeviceDto dto = new DeviceDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
    public Device toEntity(DeviceDto dto) {
        if (dto == null) return null;
        Device entity = new Device();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
