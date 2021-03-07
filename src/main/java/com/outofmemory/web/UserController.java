package com.outofmemory.web;

import com.outofmemory.dto.user.LocalIdDto;
import com.outofmemory.dto.user.UserDto;
import com.outofmemory.entity.User;
import com.outofmemory.service.UserService;
import com.outofmemory.utils.access_check.RolesHaveAccess;
import com.outofmemory.utils.api.ApiResponse;
import com.outofmemory.utils.api.Responses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    @RolesHaveAccess(restrict = User.Role.ADMIN)
    public ApiResponse<List<UserDto>> all() {
        log.info("Request on getting users");
        List<UserDto> result = userService.all();
        return Responses.okResponse(result);
    }

    @GetMapping("/profile")
    public ApiResponse<UserDto> profile() {
        log.info("Request on getting profile");
        UserDto result = userService.getCurrent();
        return Responses.okResponse(result);
    }

    @PostMapping("/block")
    @RolesHaveAccess(restrict = User.Role.ADMIN)
    public ApiResponse<Boolean> blockUser(@RequestBody LocalIdDto request) {
        log.info("Request on blocking user");
        boolean result = userService.changeUserStatus(request.getLocalId(), User.Status.BLOCKED);
        return Responses.okResponse(result);
    }

    @PostMapping("/restore")
    @RolesHaveAccess(restrict = User.Role.ADMIN)
    public ApiResponse<Boolean> restoreUser(@RequestBody LocalIdDto request) {
        log.info("Request on restoring user");
        boolean result = userService.changeUserStatus(request.getLocalId(), User.Status.ACTIVE);
        return Responses.okResponse(result);
    }
}
