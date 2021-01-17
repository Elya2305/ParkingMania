package com.outofmemory.dto.user.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TokenDto extends BaseTokenDto {
    private String refreshToken;
    private String expiresIn;
}
