package com.outofmemory.utils.helper;

public interface MessageExtractor {
    String extractRegErrorMessage(String message);

    String extractLoginErrorMessage(String message);
}
