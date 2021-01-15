package com.outofmemory.utils.client.impl;

import com.outofmemory.dto.user.auth.LoginRequestDto;
import com.outofmemory.dto.user.auth.LoginResponseDto;
import com.outofmemory.dto.user.auth.RegRequestDto;
import com.outofmemory.dto.user.auth.RegResponseDto;
import com.outofmemory.utils.AbstractHttpClient;
import com.outofmemory.utils.client.FirebaseAuthClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FirebaseAuthClientImpl extends AbstractHttpClient implements FirebaseAuthClient {
    @Value("${firebase.api.key}")
    private String apiKey;
    private static final String REG_URL = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=[API_KEY]";
    private static final String LOGIN_URL = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=[API_KEY]";

    public FirebaseAuthClientImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public RegResponseDto register(RegRequestDto request) {
        return post(url(REG_URL), request, RegResponseDto.class);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        return post(url(LOGIN_URL), request, LoginResponseDto.class);
    }


    @Override
    public HttpHeaders headers() {
        return new HttpHeaders();
    }

    private String url(String baseUrl) {
        return baseUrl.replace("[API_KEY]", apiKey);
    }
}
