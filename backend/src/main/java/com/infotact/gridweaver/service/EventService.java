package com.infotact.gridweaver.service;

import com.infotact.gridweaver.dto.EventDto;
import java.util.List;

public interface EventService {
    EventDto create(EventDto dto);
    EventDto update(Long id, EventDto dto);
    EventDto getById(Long id);
    List<EventDto> getAll();
    void delete(Long id);
}
