package com.outofmemory.web;

import com.outofmemory.dto.user.LoginPasswordDto;
import com.outofmemory.dto.user.auth.RegRequestDto;
import com.outofmemory.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public boolean register(@RequestBody RegRequestDto request) {
        return userService.register(request);
    }
}
