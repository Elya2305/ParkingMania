package com.outofmemory.web;

import com.outofmemory.dto.user.auth.*;
import com.outofmemory.service.AuthService;
import com.outofmemory.utils.api.ApiResponse;
import com.outofmemory.utils.api.Responses;
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
    public ApiResponse<TokenDto> register(@RequestBody RegRequestDto request) {
        log.info("Request on registration - {}", request);
        TokenDto result = authService.register(request);
        return Responses.okResponse(result);
    }

    @PostMapping("/login")
    public ApiResponse<TokenDto> login(@RequestBody LoginRequestDto request) {
        log.info("Request on login - {}", request);
        TokenDto result = authService.login(request);
        return Responses.okResponse(result);

    }

    @PostMapping("/refresh-token")
    public ApiResponse<RefreshTokenResponseDto> refreshToken(@RequestBody BaseTokenDto request) {
        log.info("Request on refreshing token - {}", request);
        RefreshTokenResponseDto result = authService.refreshToken(request);
        return Responses.okResponse(result);

    }
}
