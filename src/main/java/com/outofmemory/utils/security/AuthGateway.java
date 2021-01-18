package com.outofmemory.utils.security;

import com.outofmemory.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthGateway {
    public static String currentUserId() {
        return SecurityContextHolder.getContext()
                .getAuthentication().getName();
    }

    public static User.Role currentRole() {
        return ((User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getRole();
    }
}
