package com.outofmemory.dto.user.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoginResponseDto extends BaseAuthResponseDto {
    private boolean registered;
}
