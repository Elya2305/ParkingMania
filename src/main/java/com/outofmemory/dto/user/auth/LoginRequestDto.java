package com.outofmemory.dto.user.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoginRequestDto extends BaseAuthRequestDto {
    public static LoginRequestDto of(String email, String password) {
        LoginRequestDto request = new LoginRequestDto();
        request.setEmail(email);
        request.setPassword(password);
        return request;
    }
}
