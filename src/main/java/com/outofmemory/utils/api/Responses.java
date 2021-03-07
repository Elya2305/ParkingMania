package com.outofmemory.utils.api;

public class Responses {
    public static <T> ApiResponse<T> okResponse(T body) {
        ApiResponse<T> apiResponse = new ApiResponse<>(ApiResponse.Status.OK);
        apiResponse.setBody(body);
        return apiResponse;
    }

    public static <T> ApiPageResponse<T> okPageResponse(PageDto<T> page) {
        return new ApiPageResponse<>(
                ApiResponse.Status.OK,
                page.getObjectList(),
                page.getTotal(),
                page.getPage()
        );
    }
}
