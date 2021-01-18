package com.outofmemory.service.impl;

import com.outofmemory.config.firebase.FirebaseTokenHolder;
import com.outofmemory.dto.user.UserDto;
import com.outofmemory.dto.user.auth.*;
import com.outofmemory.entity.User;
import com.outofmemory.exception.auth.LoginException;
import com.outofmemory.repository.UserRepository;
import com.outofmemory.service.FirebaseService;
import com.outofmemory.service.UserMapper;
import com.outofmemory.service.UserService;
import com.outofmemory.utils.client.FirebaseAuthClient;
import com.outofmemory.utils.security.AuthGateway;
import com.outofmemory.utils.security.PermissionChecker;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service(value = UserServiceImpl.NAME)
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    public final static String NAME = "UserService";
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final FirebaseService firebaseService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByLocalId(userName)
                .orElseThrow(() -> new NoSuchElementException("Can't find user with localId " + userName));
    }

    @Override
    public List<UserDto> all() {
        PermissionChecker.isAdmin();
        return userRepository.findAll().stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getCurrent() {
        return userMapper.map(getFromDb(AuthGateway.currentUserId()));
    }


    private User getFromDb(String localId) {
        return userRepository.findByLocalId(localId)
                .orElseThrow(() -> new NoSuchElementException("Can't find user with localId " + localId));
    }
}
