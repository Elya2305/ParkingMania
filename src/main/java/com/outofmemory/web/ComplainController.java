package com.outofmemory.web;

import com.outofmemory.dto.ComplainDto;
import com.outofmemory.entity.ComplaintStatus;
import com.outofmemory.entity.User;
import com.outofmemory.service.ComplainService;
import com.outofmemory.utils.access_check.RolesHaveAccess;
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

    @GetMapping("/by-user") // todo add pagination
    public List<ComplainDto> allOfCurrentByStatus(@RequestParam ComplaintStatus status) {
        log.info("Request on getting complaints");
        return complainService.allOfCurrent(status);
    }

    @GetMapping
    @RolesHaveAccess(restrict = {User.Role.ADMIN})
    public List<ComplainDto> allByStatus(@RequestParam ComplaintStatus status) {
        log.info("Request on getting complaints");
        return complainService.allByStatus(status);
    }
}
