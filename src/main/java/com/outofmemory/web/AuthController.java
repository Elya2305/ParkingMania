package com.outofmemory.web;

import com.outofmemory.dto.user.auth.*;
import com.outofmemory.service.AuthService;
import com.outofmemory.utils.client.FirebaseAuthClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public TokenDto register(@RequestBody RegRequestDto request) {
        log.info("Request on registration - {}", request);
        return authService.register(request);
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginRequestDto request) {
        log.info("Request on login - {}", request);
        return authService.login(request);
    }

    @PostMapping("/refresh-token")
    public RefreshTokenResponseDto refreshToken(@RequestBody BaseTokenDto request) {
        log.info("Request on refreshing token - {}", request);
        return authService.refreshToken(request);
    }
}
