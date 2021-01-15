package com.outofmemory.config;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {
    private static final String configPath = "parking-mania-firebase-adminsdk-it6c7-5786908c6b.json";

    @PostConstruct
    public void init() {
        InputStream inputStream = FirebaseConfig.class.getClassLoader().getResourceAsStream(configPath);
        assert inputStream != null;
        FirebaseOptions options = new FirebaseOptions.Builder().setServiceAccount(inputStream).build();
        FirebaseApp.initializeApp(options);
    }
}
