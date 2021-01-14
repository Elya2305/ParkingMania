package com.outofmemory.utils;

public interface RestHttpClient {
    String get(String url);

    <T> T get(String url, Class<T> response);

    <T> T post(String url, Class<T> response);
}
