package com.outofmemory.service.impl;

import com.outofmemory.dto.user.UserRegDto;
import com.outofmemory.entity.User;
import com.outofmemory.repository.UserRepository;
import com.outofmemory.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service(value = UserServiceImpl.NAME)
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public final static String NAME = "UserService";


    // todo check
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findByEmail(email);
        if (userDetails == null)
            return null;

        return new org.springframework.security.core.userdetails.User(userDetails.getUsername(),
                userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean register(UserRegDto dto) {
        return false;
    }

    private User getFromDb(String uuid) {
        return userRepository.findByUuid(uuid).orElseThrow(() ->
                new NoSuchElementException("Can't find user with uuid " + uuid));
    }
}
