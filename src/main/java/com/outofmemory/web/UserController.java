package com.outofmemory.web;

import com.outofmemory.dto.user.auth.*;
import com.outofmemory.service.UserService;
import com.outofmemory.utils.client.FirebaseAuthClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    // todo
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

    @GetMapping("/access")
    public boolean haveAccess() {
        return true;
    }
}
