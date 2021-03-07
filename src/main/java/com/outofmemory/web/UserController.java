package com.outofmemory.web;

import com.outofmemory.dto.user.LocalIdDto;
import com.outofmemory.dto.user.UserDto;
import com.outofmemory.entity.User;
import com.outofmemory.service.UserService;
import com.outofmemory.utils.access_check.RolesHaveAccess;
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
    public List<UserDto> all() {
        log.info("Request on getting users");
        return userService.all();
    }

    @GetMapping("/profile")
    public UserDto profile() {
        log.info("Request on getting profile");
        return userService.getCurrent();
    }

    @PostMapping("/block")
    @RolesHaveAccess(restrict = User.Role.ADMIN)
    public boolean blockUser(@RequestBody LocalIdDto request) {
        log.info("Request on blocking user");
        return userService.changeUserStatus(request.getLocalId(), User.Status.BLOCKED);
    }

    @PostMapping("/restore")
    @RolesHaveAccess(restrict = User.Role.ADMIN)
    public boolean restoreUser(@RequestBody LocalIdDto request) {
        log.info("Request on restoring user");
        return userService.changeUserStatus(request.getLocalId(), User.Status.ACTIVE);
    }
}
