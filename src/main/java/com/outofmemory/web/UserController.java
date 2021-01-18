package com.outofmemory.web;

import com.outofmemory.dto.user.UserDto;
import com.outofmemory.dto.user.auth.*;
import com.outofmemory.service.UserService;
import com.outofmemory.utils.client.FirebaseAuthClient;
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

    @GetMapping
    public List<UserDto> all() {
        log.info("Request on getting users");
        return userService.all();
    }

    public UserDto profile() {
        log.info("Request on getting profile");
        return userService.getCurrent();
    }



    @GetMapping("/access")
    public boolean haveAccess() {
        return true;
    }
}
