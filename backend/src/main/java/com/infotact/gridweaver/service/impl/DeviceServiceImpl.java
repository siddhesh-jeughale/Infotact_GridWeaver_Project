package com.infotact.gridweaver.service.impl;

import com.infotact.gridweaver.dto.DeviceDto;
import com.infotact.gridweaver.entity.Device;
import com.infotact.gridweaver.exception.ResourceNotFoundException;
import com.infotact.gridweaver.mapper.DeviceMapper;
import com.infotact.gridweaver.repository.DeviceRepository;
import com.infotact.gridweaver.service.DeviceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository repository;
    private final DeviceMapper mapper;
    public DeviceServiceImpl(DeviceRepository repository, DeviceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override @Transactional
    public DeviceDto create(DeviceDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }
    @Override @Transactional
    public DeviceDto update(Long id, DeviceDto dto) {
        Device existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
        Device updated = mapper.toEntity(dto);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }
    @Override
    public DeviceDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id)));
    }
    @Override
    public List<DeviceDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }
    @Override @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Not found: " + id);
        repository.deleteById(id);
    }
}
