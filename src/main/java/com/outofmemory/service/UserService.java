package com.outofmemory.service;

import com.outofmemory.dto.user.UserRegDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean register(UserRegDto dto);
}
