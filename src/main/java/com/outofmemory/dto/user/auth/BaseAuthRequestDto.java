package com.outofmemory.dto.user.auth;

import lombok.Data;

// todo confirm password
@Data
public class BaseAuthRequestDto {
    private String email;
    private String password;
    private boolean returnSecureToken = true;
}
