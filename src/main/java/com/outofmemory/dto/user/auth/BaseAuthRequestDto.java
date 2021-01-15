package com.outofmemory.dto.user.auth;

import lombok.Data;

@Data
public class BaseAuthRequestDto {
    private String email;
    private String password;
    private boolean returnSecureToken = true;
}
