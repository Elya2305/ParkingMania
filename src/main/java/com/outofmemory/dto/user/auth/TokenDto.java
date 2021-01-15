package com.outofmemory.dto.user.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDto {
    private String token;

    public static TokenDto of(String idToken) {
        return new TokenDto(idToken);
    }
}
