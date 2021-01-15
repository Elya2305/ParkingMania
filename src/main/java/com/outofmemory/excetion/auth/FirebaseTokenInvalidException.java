package com.outofmemory.excetion.auth;

import org.springframework.security.authentication.BadCredentialsException;

public class FirebaseTokenInvalidException extends BadCredentialsException {

	public FirebaseTokenInvalidException(String msg) {
		super(msg);
	}
}
