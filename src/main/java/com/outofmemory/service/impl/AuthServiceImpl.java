package com.outofmemory.service.impl;

import com.outofmemory.security.token.FirebaseTokenHolder;
import com.outofmemory.dto.user.auth.*;
import com.outofmemory.entity.User;
import com.outofmemory.exception.auth.LoginException;
import com.outofmemory.repository.UserRepository;
import com.outofmemory.service.AuthService;
import com.outofmemory.service.FirebaseService;
import com.outofmemory.service.UserMapper;
import com.outofmemory.utils.client.FirebaseAuthClient;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserMapper userMapper;
    private final FirebaseAuthClient authClient;
    private final UserRepository userRepository;
    private final FirebaseService firebaseService;

    @Override
    public TokenDto register(RegRequestDto dto) {
        RegResponseDto response = authClient.register(dto);
        User user = userMapper.map(response);
        userRepository.save(user);
        return token(response);
    }

    @Override
    public TokenDto login(LoginRequestDto dto) {
        LoginResponseDto response = authClient.login(dto);
        auth(response.getIdToken());
        validateLogin(response);
        return token(response);
    }

    @Override
    public RefreshTokenResponseDto refreshToken(BaseTokenDto request) {
        return authClient.refreshToken(RefreshTokenRequestDto.of(request.getToken()));
    }

    private void validateLogin(LoginResponseDto response) {
        if (!userRepository.existsByLocalId(response.getLocalId())) {
            throw new LoginException("User don't present in db");
        }
    }

    private void auth(String token) {
        FirebaseTokenHolder holder = firebaseService.parseToken(token);
        String userName = holder.getUid();

        Authentication auth = new UsernamePasswordAuthenticationToken(userName, holder);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private TokenDto token(BaseAuthResponseDto source) {
        TokenDto destination = new TokenDto();
        destination.setExpiresIn(source.getExpiresIn());
        destination.setRefreshToken(source.getRefreshToken());
        destination.setToken(source.getIdToken());
        return destination;
    }
}
