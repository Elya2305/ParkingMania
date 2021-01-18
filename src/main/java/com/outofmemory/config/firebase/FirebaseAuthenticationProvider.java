package com.outofmemory.config.firebase;

import com.outofmemory.exception.auth.FirebaseUserNotExistsException;
import com.outofmemory.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class FirebaseAuthenticationProvider extends DaoAuthenticationProvider {
	public FirebaseAuthenticationProvider(@Qualifier(value = UserServiceImpl.NAME) UserDetailsService userService) {
		this.setUserDetailsService(userService);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (!supports(authentication.getClass())) {
			return null;
		}

		UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;

		UserDetails details = this.getUserDetailsService().loadUserByUsername((String) authenticationToken.getPrincipal());
		if (details == null) {
			throw new FirebaseUserNotExistsException();
		}

		authenticationToken = new UsernamePasswordAuthenticationToken(details, authentication.getCredentials(),
				details.getAuthorities());

		return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
