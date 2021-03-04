package com.outofmemory.utils.client.impl;

import com.outofmemory.dto.user.auth.*;
import com.outofmemory.exception.auth.LoginException;
import com.outofmemory.exception.auth.RefreshTokenException;
import com.outofmemory.exception.auth.RegistrationException;
import com.outofmemory.utils.AbstractHttpClient;
import com.outofmemory.utils.client.FirebaseAuthClient;
import com.outofmemory.utils.helper.MessageExtractor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

// todo fix readable message
@Slf4j
@Service
public class FirebaseAuthClientImpl extends AbstractHttpClient implements FirebaseAuthClient {
    @Value("${firebase.api.key}")
    private String apiKey;
    private static final String REG_URL = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=[API_KEY]";
    private static final String LOGIN_URL = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=[API_KEY]";
    private static final String REFRESH_TOKEN_URL = "https://securetoken.googleapis.com/v1/token?key=[API_KEY]";
    private final MessageExtractor messageExtractor;

    public FirebaseAuthClientImpl(RestTemplate restTemplate, MessageExtractor messageExtractor) {
        super(restTemplate);
        this.messageExtractor = messageExtractor;
    }

    @Override
    public RegResponseDto register(RegRequestDto request) {
        try {
           return post(url(REG_URL), request, RegResponseDto.class);
        } catch (HttpClientErrorException exp) {
            throw new RegistrationException(messageExtractor.extractRegErrorMessage(exp.getResponseBodyAsString()));
        }
    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
       try {
           return post(url(LOGIN_URL), request, LoginResponseDto.class);
       } catch (HttpClientErrorException exp) {
           throw new LoginException(messageExtractor.extractLoginErrorMessage(exp.getResponseBodyAsString()));
       }
    }

    @Override
    public RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto request) {
        try {
            return post(url(REFRESH_TOKEN_URL), request, RefreshTokenResponseDto.class);
        } catch (HttpClientErrorException exp) {
            throw new RefreshTokenException(messageExtractor.extractLoginErrorMessage(exp.getResponseBodyAsString()));
        }
    }

    @Override
    public HttpHeaders headers() {
        return new HttpHeaders();
    }

    private String url(String baseUrl) {
        return baseUrl.replace("[API_KEY]", apiKey);
    }
}
