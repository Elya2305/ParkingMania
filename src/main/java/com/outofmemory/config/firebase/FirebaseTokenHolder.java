package com.outofmemory.config.firebase;

import com.google.firebase.auth.FirebaseToken;

public class FirebaseTokenHolder {
	private final FirebaseToken token;

	public FirebaseTokenHolder(FirebaseToken token) {
		this.token = token;
	}

	public String getEmail() {
		return token.getEmail();
	}

	public String getIssuer() {
		return token.getIssuer();
	}

	public String getName() {
		return token.getName();
	}

	public String getUid() {
		return token.getUid();
	}

}
