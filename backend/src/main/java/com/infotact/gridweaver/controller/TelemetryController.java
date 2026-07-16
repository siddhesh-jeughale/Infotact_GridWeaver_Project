package com.infotact.gridweaver.controller;

import com.infotact.gridweaver.dto.TelemetryDto;
import com.infotact.gridweaver.service.TelemetryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/telemetry")
public class TelemetryController {
    private final TelemetryService service;
    public TelemetryController(TelemetryService service) { this.service = service; }
    @PostMapping
    public ResponseEntity<TelemetryDto> create(@Valid @RequestBody TelemetryDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TelemetryDto> update(@PathVariable Long id, @Valid @RequestBody TelemetryDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<TelemetryDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping
    public ResponseEntity<List<TelemetryDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
