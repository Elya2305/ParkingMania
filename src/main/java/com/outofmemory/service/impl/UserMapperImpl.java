package com.outofmemory.service.impl;

import com.outofmemory.dto.user.auth.RegResponseDto;
import com.outofmemory.entity.User;
import com.outofmemory.service.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {
    @Override
    public User map(RegResponseDto source) {
        User destination = new User();
        destination.setLocalId(source.getLocalId());
        destination.setEmail(source.getEmail());
        destination.setRole(User.Role.USER);
        destination.setStatus(User.Status.ACTIVE);
        return destination;
    }
}
