package com.outofmemory.web;

import com.outofmemory.dto.ComplainDto;
import com.outofmemory.service.ComplainService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
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

    @GetMapping
    public List<ComplainDto> all() {
        log.info("Request on getting complaints");
        return complainService.all();
    }
}
