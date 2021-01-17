package com.outofmemory.utils.parser;

import com.cloudinary.utils.StringUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.outofmemory.config.firebase.FirebaseTokenHolder;
import com.outofmemory.exception.auth.FirebaseTokenInvalidException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FirebaseParser {

	public FirebaseTokenHolder parseToken(String idToken) {
		if (StringUtils.isBlank(idToken)) {
			throw new IllegalArgumentException("Firebase token is blank");
		}
		try {
			FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
			return new FirebaseTokenHolder(firebaseToken);
		} catch (Exception e) {
			throw new FirebaseTokenInvalidException(e.getMessage());
		}
	}
}
