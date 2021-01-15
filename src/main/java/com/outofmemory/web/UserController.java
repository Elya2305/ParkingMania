package com.outofmemory.web;

import com.outofmemory.dto.user.auth.LoginRequestDto;
import com.outofmemory.dto.user.auth.RegRequestDto;
import com.outofmemory.dto.user.auth.TokenDto;
import com.outofmemory.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public TokenDto register(@RequestBody RegRequestDto request) {
        log.info("Request on registration - {}", request);
        TokenDto response = userService.register(request);
        log.info("Response on registration - {}", response);
        return response;
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginRequestDto request) {
        log.info("Request on login - {}", request);
        TokenDto response = userService.login(request);
        log.info("Response on login - {}", response);
        return response;
    }
}
