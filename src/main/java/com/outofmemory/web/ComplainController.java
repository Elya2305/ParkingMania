package com.outofmemory.web;

import com.outofmemory.dto.ComplainDto;
import com.outofmemory.service.ComplainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/complaint")
public class ComplainController {
    private final ComplainService complainService;

    @PostMapping
    public boolean create(@RequestBody ComplainDto dto) {
        log.info("Request on creating complaint - {}", dto);
        return complainService.add(dto);
    }

    @PutMapping
    public boolean update(@RequestBody ComplainDto dto) {
        log.info("Request on updating complaint - {}", dto);
        return complainService.update(dto);
    }

    @DeleteMapping
    public boolean delete(@RequestParam Integer id) {
        log.info("Request on deleting complaint with id - {}", id);
        return complainService.delete(id);
    }

    @GetMapping
    public List<ComplainDto> all() {
        log.info("Request on getting complaints");
        return complainService.all();
    }
}
