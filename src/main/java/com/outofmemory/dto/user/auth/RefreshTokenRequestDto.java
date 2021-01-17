package com.outofmemory.dto.user.auth;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RefreshTokenRequestDto {
    private String grantType;
    private String refreshToken;

    public static RefreshTokenRequestDto of(String refreshToken) {
        RefreshTokenRequestDto dto = new RefreshTokenRequestDto();
        dto.setGrantType("refresh_token");
        dto.setRefreshToken(refreshToken);
        return dto;
    }
}
