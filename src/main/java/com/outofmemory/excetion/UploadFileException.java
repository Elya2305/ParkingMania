package com.outofmemory.excetion;

public class UploadFileException extends RuntimeException {
    public UploadFileException() {
    }

    public UploadFileException(String message) {
        super(message);
    }
}
