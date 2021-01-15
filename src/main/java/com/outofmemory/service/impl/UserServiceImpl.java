package com.outofmemory.service.impl;

import com.outofmemory.entity.User;
import com.outofmemory.repository.UserRepository;
import com.outofmemory.service.ComplainService;
import com.outofmemory.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    // todo check
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findByEmail(email);
        if (userDetails == null)
            return null;

        return new org.springframework.security.core.userdetails.User(userDetails.getUsername(),
                userDetails.getPassword(), userDetails.getAuthorities());
    }

    private User getFromDb(String uuid) {
        return userRepository.findByUuid(uuid).orElseThrow(() ->
                new NoSuchElementException("Can't find user with uuid " + uuid));
    }
}
