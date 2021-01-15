package com.outofmemory.service;

import com.outofmemory.dto.user.auth.LoginRequestDto;
import com.outofmemory.dto.user.auth.RegRequestDto;
import com.outofmemory.dto.user.auth.TokenDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    TokenDto register(RegRequestDto dto);

    TokenDto login(LoginRequestDto dto);
}
