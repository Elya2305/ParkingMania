package com.outofmemory.security.provider;

import com.outofmemory.dto.user.UserDto;
import com.outofmemory.exception.auth.FirebaseUserNotExistsException;
import com.outofmemory.service.UserService;
import com.outofmemory.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class FirebaseAuthenticationProvider extends DaoAuthenticationProvider {
    public FirebaseAuthenticationProvider(@Qualifier(value = UserServiceImpl.NAME) UserService userService) {
        this.setUserDetailsService(userService);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }

        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;

        if (authenticationToken.getPrincipal() == null) {
            throw new FirebaseUserNotExistsException();
        }

        UserDto user = (UserDto) authentication.getPrincipal();
        authenticationToken = new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(),
                Collections.singletonList(user.getRole()));

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
