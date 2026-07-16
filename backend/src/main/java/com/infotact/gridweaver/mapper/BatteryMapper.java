package com.infotact.gridweaver.mapper;

import com.infotact.gridweaver.dto.BatteryDto;
import com.infotact.gridweaver.entity.Battery;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;

@Component
public class BatteryMapper {
    public BatteryDto toDto(Battery entity) {
        if (entity == null) return null;
        BatteryDto dto = new BatteryDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
    public Battery toEntity(BatteryDto dto) {
        if (dto == null) return null;
        Battery entity = new Battery();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
