package com.infotact.gridweaver.controller;

import com.infotact.gridweaver.dto.BatteryDto;
import com.infotact.gridweaver.service.BatteryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/batteries")
public class BatteryController {
    private final BatteryService service;
    public BatteryController(BatteryService service) { this.service = service; }
    @PostMapping
    public ResponseEntity<BatteryDto> create(@Valid @RequestBody BatteryDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BatteryDto> update(@PathVariable Long id, @Valid @RequestBody BatteryDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BatteryDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping
    public ResponseEntity<List<BatteryDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
