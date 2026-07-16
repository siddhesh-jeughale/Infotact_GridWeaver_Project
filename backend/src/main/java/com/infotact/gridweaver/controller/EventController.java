package com.infotact.gridweaver.controller;

import com.infotact.gridweaver.dto.EventDto;
import com.infotact.gridweaver.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService service;
    public EventController(EventService service) { this.service = service; }
    @PostMapping
    public ResponseEntity<EventDto> create(@Valid @RequestBody EventDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EventDto> update(@PathVariable Long id, @Valid @RequestBody EventDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping
    public ResponseEntity<List<EventDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
