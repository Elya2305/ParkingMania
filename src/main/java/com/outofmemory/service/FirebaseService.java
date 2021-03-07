package com.outofmemory.service;


import com.outofmemory.security.token.FirebaseTokenHolder;

public interface FirebaseService {

	FirebaseTokenHolder parseToken(String idToken);

}
