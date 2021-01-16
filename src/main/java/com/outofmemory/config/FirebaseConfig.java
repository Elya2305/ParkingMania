package com.outofmemory.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {
    @Value("${config.path}")
    private String configPath;

    @PostConstruct
    public void init() throws IOException {
        InputStream inputStream = FirebaseConfig.class.getClassLoader().getResourceAsStream(configPath);
        assert inputStream != null;
        GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream);
        FirebaseOptions options = FirebaseOptions.builder().setCredentials(credentials).build();
        FirebaseApp.initializeApp(options);
    }
}
