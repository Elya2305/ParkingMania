package com.outofmemory.utils.client;

import com.outofmemory.dto.user.auth.*;

public interface FirebaseAuthClient {
    RegResponseDto register(RegRequestDto request);

    LoginResponseDto login(LoginRequestDto request);

    RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto request);
}
