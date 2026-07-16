package com.infotact.gridweaver.service.impl;

import com.infotact.gridweaver.dto.BatteryDto;
import com.infotact.gridweaver.entity.Battery;
import com.infotact.gridweaver.exception.ResourceNotFoundException;
import com.infotact.gridweaver.mapper.BatteryMapper;
import com.infotact.gridweaver.repository.BatteryRepository;
import com.infotact.gridweaver.service.BatteryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatteryServiceImpl implements BatteryService {
    private final BatteryRepository repository;
    private final BatteryMapper mapper;
    public BatteryServiceImpl(BatteryRepository repository, BatteryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override @Transactional
    public BatteryDto create(BatteryDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }
    @Override @Transactional
    public BatteryDto update(Long id, BatteryDto dto) {
        Battery existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
        Battery updated = mapper.toEntity(dto);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }
    @Override
    public BatteryDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id)));
    }
    @Override
    public List<BatteryDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }
    @Override @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Not found: " + id);
        repository.deleteById(id);
    }
}
