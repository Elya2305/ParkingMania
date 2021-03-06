package com.outofmemory.service;

import com.outofmemory.dto.user.UserDto;
import com.outofmemory.dto.user.auth.LoginRequestDto;
import com.outofmemory.dto.user.auth.RegRequestDto;
import com.outofmemory.dto.user.auth.TokenDto;
import com.outofmemory.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDto> all();

    UserDto getCurrent();

    boolean changeUserStatus(String localeId, User.Status status);

    UserDto getByLocaleId(String localeId);
}
