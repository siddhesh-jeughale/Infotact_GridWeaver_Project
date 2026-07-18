package com.infotact.gridweaver.service.impl;

import com.infotact.gridweaver.dto.TelemetryDto;
import com.infotact.gridweaver.entity.Telemetry;
import com.infotact.gridweaver.exception.ResourceNotFoundException;
import com.infotact.gridweaver.mapper.TelemetryMapper;
import com.infotact.gridweaver.repository.TelemetryRepository;
import com.infotact.gridweaver.service.TelemetryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TelemetryServiceImpl implements TelemetryService {
    private final TelemetryRepository repository;
    private final TelemetryMapper mapper;
    public TelemetryServiceImpl(TelemetryRepository repository, TelemetryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override @Transactional
    public TelemetryDto create(TelemetryDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }
    @Override @Transactional
    public TelemetryDto update(Long id, TelemetryDto dto) {
        Telemetry existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
        Telemetry updated = mapper.toEntity(dto);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }
    @Override
    public TelemetryDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id)));
    }
    @Override
    public List<TelemetryDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }
    @Override
    public List<TelemetryDto> getLatest() {
        return repository.findTop50ByOrderByTimestampDesc().stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    @Override @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Not found: " + id);
        repository.deleteById(id);
    }
}
