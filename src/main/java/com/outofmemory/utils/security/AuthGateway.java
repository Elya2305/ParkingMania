package com.outofmemory.utils.security;

import com.outofmemory.dto.user.UserDto;
import com.outofmemory.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthGateway {
    public static String currentUserId() {
        return ((UserDto) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getLocalId();
    }

    public static User.Role currentRole() {
        return ((UserDto) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getRole();
    }

    public static User.Status currentStatus() {
        return ((UserDto) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getStatus();
    }
}
