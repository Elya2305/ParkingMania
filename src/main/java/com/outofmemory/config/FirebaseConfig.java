package com.outofmemory.config;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {
    @Value("${config.path}")
    private String configPath;

    @PostConstruct
    public void init() {
        InputStream inputStream = FirebaseConfig.class.getClassLoader().getResourceAsStream(configPath);
        assert inputStream != null;
        FirebaseOptions options = new FirebaseOptions.Builder().setServiceAccount(inputStream).build();
        FirebaseApp.initializeApp(options);
    }
}
