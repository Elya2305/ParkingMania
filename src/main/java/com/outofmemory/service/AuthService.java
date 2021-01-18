package com.outofmemory.service;

import com.outofmemory.dto.user.auth.*;

public interface AuthService {
    TokenDto register(RegRequestDto dto);

    TokenDto login(LoginRequestDto dto);

    RefreshTokenResponseDto refreshToken(BaseTokenDto request);
}
