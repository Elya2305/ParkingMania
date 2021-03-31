package com.outofmemory.utils.api;

import lombok.Data;

import java.util.List;


@Data
public class ApiPageResponse<T> {
    private ApiResponse.Status status;
    private int total;
    private int page;
    private List<T> objects;

    public ApiPageResponse(ApiResponse.Status status, List<T> objects, int total, int page) {
        this.status = status;
        this.objects = objects;
        this.total = total;
        this.page = page;
    }
}
