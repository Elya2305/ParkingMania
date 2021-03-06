package com.outofmemory.service.impl;

import com.outofmemory.dto.user.UserDto;
import com.outofmemory.entity.User;
import com.outofmemory.repository.UserRepository;
import com.outofmemory.service.UserMapper;
import com.outofmemory.service.UserService;
import com.outofmemory.utils.security.AuthGateway;
import lombok.AllArgsConstructor;
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

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByLocalId(userName)
                .orElseThrow(() -> new NoSuchElementException("Can't find user with localId " + userName));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> all() {
        return userRepository.findAll().stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getCurrent() {
        return userMapper.map(getFromDb(AuthGateway.currentUserId()));
    }

    @Override
    public boolean changeUserStatus(String localeId, User.Status status) {
        userRepository.updateUserStatus(localeId, status);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getByLocaleId(String localeId) {
        return userMapper.map(getFromDb(localeId));
    }

    private User getFromDb(String localId) {
        return userRepository.findByLocalId(localId)
                .orElseThrow(() -> new NoSuchElementException("Can't find user with localId " + localId));
    }
}
