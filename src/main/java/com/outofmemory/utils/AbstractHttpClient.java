package com.outofmemory.utils;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Log4j2
public abstract class AbstractHttpClient implements RestHttpClient {
    private final RestTemplate restTemplate;

    @Override
    public String get(String url) {
        log.info("Get by url {}", url);
        try {
            HttpEntity<String> request = new HttpEntity<>(headers());
            return restTemplate.exchange(url, HttpMethod.GET, request, String.class).getBody();
        } catch (RuntimeException e) {
            log.error("Error while processing get request");
            throw e;
        }
    }

    @Override
    public <T> T get(String url, Class<T> response) {
        log.info("Get by url {}", url);
        try {
            HttpEntity<T> request = new HttpEntity<>(headers());
            return restTemplate.exchange(url, HttpMethod.GET, request, response).getBody();
        } catch (RuntimeException e) {
            log.error("Error while processing get request");
            throw e;
        }
    }

    @Override
    public <T> T post(String url, Class<T> response) {
        try {
            log.info("Post to {}", url);
            HttpEntity<T> request = new HttpEntity<>(headers());
            return restTemplate.exchange(url, HttpMethod.POST, request, response).getBody();
        }catch (RuntimeException e) {
            log.error("Error while processing post request");
            throw e;
        }
    }

    public abstract HttpHeaders headers();
}
