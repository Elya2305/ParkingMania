package com.outofmemory.dto.user.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegRequestDto extends BaseAuthRequestDto {

    public static RegRequestDto of(String email, String password) {
        RegRequestDto request = new RegRequestDto();
        request.setEmail(email);
        request.setPassword(password);
        return request;
    }
}
