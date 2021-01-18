package com.outofmemory.service;

import com.outofmemory.dto.user.UserDto;
import com.outofmemory.dto.user.auth.RegResponseDto;
import com.outofmemory.entity.User;

public interface UserMapper {
    User map(RegResponseDto response);

    UserDto map(User user);
}
