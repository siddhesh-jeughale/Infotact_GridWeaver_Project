package com.infotact.gridweaver.service.impl;

import com.infotact.gridweaver.dto.EventDto;
import com.infotact.gridweaver.entity.Event;
import com.infotact.gridweaver.exception.ResourceNotFoundException;
import com.infotact.gridweaver.mapper.EventMapper;
import com.infotact.gridweaver.repository.EventRepository;
import com.infotact.gridweaver.service.EventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository repository;
    private final EventMapper mapper;
    public EventServiceImpl(EventRepository repository, EventMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override @Transactional
    public EventDto create(EventDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }
    @Override @Transactional
    public EventDto update(Long id, EventDto dto) {
        Event existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
        Event updated = mapper.toEntity(dto);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }
    @Override
    public EventDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id)));
    }
    @Override
    public List<EventDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }
    @Override @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Not found: " + id);
        repository.deleteById(id);
    }
}
