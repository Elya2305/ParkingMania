package com.outofmemory.service.impl;

import com.outofmemory.dto.user.LoginPasswordDto;
import com.outofmemory.dto.user.auth.*;
import com.outofmemory.entity.User;
import com.outofmemory.excetion.auth.LoginException;
import com.outofmemory.repository.UserRepository;
import com.outofmemory.service.UserMapper;
import com.outofmemory.service.UserService;
import com.outofmemory.utils.client.FirebaseAuthClient;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service(value = UserServiceImpl.NAME)
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    public final static String NAME = "UserService";
    private final UserRepository userRepository;
    private final FirebaseAuthClient authClient;
    private final UserMapper userMapper;

    @Override
    // reason
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findByEmail(email);
        if (userDetails == null)
            return null;

        return new org.springframework.security.core.userdetails.User(userDetails.getUsername(),
                userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public TokenDto register(RegRequestDto dto) {
        RegResponseDto response = authClient.register(RegRequestDto.of(dto.getEmail(), dto.getPassword()));
        User user = userMapper.map(response);
        userRepository.save(user);
        return TokenDto.of(response.getIdToken());
    }

    @Override
    public TokenDto login(LoginRequestDto dto) {
        LoginResponseDto response = authClient.login(dto);
        validateLogin(response);
        return TokenDto.of(response.getIdToken());
    }

    private void validateLogin(LoginResponseDto response) {
        if (!userRepository.existsByIdToken(response.getIdToken())) {
            throw new LoginException("User don't present in db");
        }
    }

    private User getFromDb(String idToken) {
        return userRepository.findByIdToken(idToken).orElseThrow(() ->
                new NoSuchElementException("Can't find user with idToken " + idToken));
    }
}
