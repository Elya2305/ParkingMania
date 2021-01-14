package com.outofmemory.utils;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private T data;
    private boolean success;
    private int status;
}
