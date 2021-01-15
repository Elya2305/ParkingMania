package com.outofmemory.utils.client;

import com.outofmemory.dto.user.auth.LoginRequestDto;
import com.outofmemory.dto.user.auth.LoginResponseDto;
import com.outofmemory.dto.user.auth.RegRequestDto;
import com.outofmemory.dto.user.auth.RegResponseDto;

public interface FirebaseAuthClient {
    RegResponseDto register(RegRequestDto request);

    LoginResponseDto login(LoginRequestDto request);
}
