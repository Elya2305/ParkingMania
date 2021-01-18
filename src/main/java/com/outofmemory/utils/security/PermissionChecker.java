package com.outofmemory.utils.security;

import com.outofmemory.entity.User;
import com.outofmemory.exception.auth.PermissionException;

public class PermissionChecker {
    public static void isAdmin() {
        if (!User.Role.ADMIN.equals(AuthGateway.currentRole())) {
            throw new PermissionException("Only admins has access for this resource");
        }
    }
}
