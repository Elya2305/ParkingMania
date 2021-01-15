package com.outofmemory.dto.user.auth;

import lombok.Data;

@Data
public class BaseAuthResponseDto {
    private String idToken;
    private String email;
    private String refreshToken;
    private String expiresIn;
    private String localId;
}
