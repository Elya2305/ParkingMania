package com.outofmemory.service.impl;

import com.outofmemory.dto.user.LoginPasswordDto;
import com.outofmemory.dto.user.auth.LoginRequestDto;
import com.outofmemory.dto.user.auth.RegRequestDto;
import com.outofmemory.dto.user.auth.RegResponseDto;
import com.outofmemory.entity.User;
import com.outofmemory.repository.UserRepository;
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findByEmail(email);
        if (userDetails == null)
            return null;

        return new org.springframework.security.core.userdetails.User(userDetails.getUsername(),
                userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean register(RegRequestDto dto) {
        RegResponseDto response = authClient.register(RegRequestDto.of(dto.getEmail(), dto.getPassword()));
        return false;
    }

    private User getFromDb(String uuid) {
        return userRepository.findByUuid(uuid).orElseThrow(() ->
                new NoSuchElementException("Can't find user with uuid " + uuid));
    }
}
