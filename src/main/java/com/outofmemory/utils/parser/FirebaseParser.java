package com.outofmemory.utils.parser;

import com.cloudinary.utils.StringUtils;
import com.google.firebase.ErrorCode;
import com.google.firebase.auth.AuthErrorCode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.outofmemory.exception.auth.TokenExpiredException;
import com.outofmemory.security.token.FirebaseTokenHolder;
import com.outofmemory.exception.auth.FirebaseTokenInvalidException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FirebaseParser {
	private static final String EXPIRED_ID_TOKEN_MSG = "Access token is expired. Please refresh it";

	public FirebaseTokenHolder parseToken(String idToken) {
		if (StringUtils.isBlank(idToken)) {
			throw new IllegalArgumentException("Firebase token is blank");
		}
		try {
			FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
			return new FirebaseTokenHolder(firebaseToken);
		} catch (FirebaseAuthException e) {
			if (AuthErrorCode.EXPIRED_ID_TOKEN.equals(e.getAuthErrorCode())) {
				throw new TokenExpiredException(EXPIRED_ID_TOKEN_MSG);
			}
			throw new FirebaseTokenInvalidException(e.getMessage());
		}
	}
}
