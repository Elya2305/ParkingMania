package com.outofmemory.service;

import com.outofmemory.dto.user.LoginPasswordDto;
import com.outofmemory.dto.user.auth.RegRequestDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean register(RegRequestDto dto);
}
