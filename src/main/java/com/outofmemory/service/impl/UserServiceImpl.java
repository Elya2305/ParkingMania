package com.outofmemory.service.impl;

import com.outofmemory.dto.user.auth.*;
import com.outofmemory.entity.User;
import com.outofmemory.exception.auth.LoginException;
import com.outofmemory.repository.UserRepository;
import com.outofmemory.service.UserMapper;
import com.outofmemory.service.UserService;
import com.outofmemory.utils.client.FirebaseAuthClient;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service(value = UserServiceImpl.NAME)
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    public final static String NAME = "UserService";
    private final UserRepository userRepository;
    private final FirebaseAuthClient authClient;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByLocalId(userName)
                .orElseThrow(() -> new NoSuchElementException("Can't find user with localId " + userName));
    }

    @Override
    public TokenDto register(RegRequestDto dto) {
        RegResponseDto response = authClient.register(RegRequestDto.of(dto.getEmail(), dto.getPassword()));
        User user = userMapper.map(response);
        userRepository.save(user);
        return token(response);
    }

    @Override
    public TokenDto login(LoginRequestDto dto) {
        LoginResponseDto response = authClient.login(dto);
        validateLogin(response);
        return token(response);
    }

    private void validateLogin(LoginResponseDto response) {
        if (!userRepository.existsByLocalId(response.getLocalId())) {
            throw new LoginException("User don't present in db");
        }
    }

    private TokenDto token(BaseAuthResponseDto source) {
        TokenDto destination = new TokenDto();
        destination.setExpiresIn(source.getExpiresIn());
        destination.setRefreshToken(source.getRefreshToken());
        destination.setToken(source.getIdToken());
        return destination;
    }
}
