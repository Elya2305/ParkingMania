package com.outofmemory.dto.user.auth;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RefreshTokenResponseDto {
    private String expiresIn;
    private String tokenType;
    private String refreshToken;
    private String idToken;
    private String userId;
    private String projectId;
}
