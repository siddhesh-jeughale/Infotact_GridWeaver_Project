package com.infotact.gridweaver.controller;

import com.infotact.gridweaver.dto.DeviceDto;
import com.infotact.gridweaver.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/devices")
public class DeviceController {
    private final DeviceService service;
    public DeviceController(DeviceService service) { this.service = service; }
    @PostMapping
    public ResponseEntity<DeviceDto> create(@Valid @RequestBody DeviceDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DeviceDto> update(@PathVariable Long id, @Valid @RequestBody DeviceDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DeviceDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping
    public ResponseEntity<List<DeviceDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
