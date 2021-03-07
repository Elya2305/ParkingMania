package com.outofmemory.web;

import com.outofmemory.dto.ComplainDto;
import com.outofmemory.entity.ComplaintStatus;
import com.outofmemory.entity.User;
import com.outofmemory.service.ComplainService;
import com.outofmemory.utils.access_check.RolesHaveAccess;
import com.outofmemory.utils.api.ApiPageResponse;
import com.outofmemory.utils.api.ApiResponse;
import com.outofmemory.utils.api.PageDto;
import com.outofmemory.utils.api.Responses;
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
    public ApiResponse<Boolean> create(@RequestBody ComplainDto dto) {
        log.info("Request on creating complaint - {}", dto);
        boolean result = complainService.add(dto);
        return Responses.okResponse(result);
    }

    @PutMapping
    public ApiResponse<Boolean> update(@RequestBody ComplainDto dto) {
        log.info("Request on updating complaint - {}", dto);
        boolean result = complainService.update(dto);
        return Responses.okResponse(result);
    }

    @DeleteMapping
    public ApiResponse<Boolean> delete(@RequestParam Integer id) {
        log.info("Request on deleting complaint with id - {}", id);
        boolean result = complainService.delete(id);
        return Responses.okResponse(result);
    }

    @GetMapping("/by-user")
    public ApiPageResponse<List<ComplainDto>> allOfCurrentByStatus(@RequestParam(required = false) ComplaintStatus status,
                                                                   @RequestParam Integer page, Integer pageSize) {
        log.info("Request on getting complaints");
        PageDto<List<ComplainDto>> pageResult = complainService.allOfCurrent(status, page, pageSize);
        return Responses.okPageResponse(pageResult);
    }

    @GetMapping
    @RolesHaveAccess(restrict = {User.Role.ADMIN})
    public ApiPageResponse<List<ComplainDto>> allByStatus(@RequestParam(required = false) ComplaintStatus status,
                                         @RequestParam Integer page, Integer pageSize) {
        log.info("Request on getting complaints");
        PageDto<List<ComplainDto>> pageResult = complainService.allByStatus(status, page, pageSize);
        return Responses.okPageResponse(pageResult);
    }
}
