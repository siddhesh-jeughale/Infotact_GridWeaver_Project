package com.infotact.gridweaver.mapper;

import com.infotact.gridweaver.dto.EventDto;
import com.infotact.gridweaver.entity.Event;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;

@Component
public class EventMapper {
    public EventDto toDto(Event entity) {
        if (entity == null) return null;
        EventDto dto = new EventDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
    public Event toEntity(EventDto dto) {
        if (dto == null) return null;
        Event entity = new Event();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
