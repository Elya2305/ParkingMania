package com.outofmemory.web;

import com.outofmemory.dto.user.auth.*;
import com.outofmemory.service.UserService;
import com.outofmemory.utils.client.FirebaseAuthClient;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final FirebaseAuthClient authClient;

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

    // todo remove in the future (make request from client side)
    @PostMapping("/token")
    public RefreshTokenResponseDto refreshToken(@RequestBody BaseTokenDto request) {
        log.info("Request on refreshing token - {}", request);
        return authClient.refreshToken(RefreshTokenRequestDto.of(request.getToken()));
    }
}
