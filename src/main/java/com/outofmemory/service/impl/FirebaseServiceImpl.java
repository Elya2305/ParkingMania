package com.outofmemory.service.impl;

import com.outofmemory.config.firebase.FirebaseTokenHolder;
import com.outofmemory.service.FirebaseService;
import com.outofmemory.utils.parser.FirebaseParser;
import org.springframework.stereotype.Service;


@Service
public class FirebaseServiceImpl implements FirebaseService {
	@Override
	public FirebaseTokenHolder parseToken(String firebaseToken) {
		return new FirebaseParser().parseToken(firebaseToken);
	}
}
