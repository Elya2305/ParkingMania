package com.outofmemory.utils.api;

public class Responses {
    public static <T> ApiResponse<T> okResponse(T body) {
        return ofStatus(ApiResponse.Status.ERROR, null, body);
    }

    public static <T> ApiResponse<T> errorResponse(String msg) {
        return ofStatus(ApiResponse.Status.ERROR, msg, null);
    }

    public static <T> ApiResponse<T> authErrorResponse(String msg) {
        return ofStatus(ApiResponse.Status.AUTH_ERROR, msg, null);
    }

    public static <T> ApiResponse<T> tokenExpiredResponse(String msg) {
        return ofStatus(ApiResponse.Status.TOKEN_EXPIRED, msg, null);
    }

    private static <T> ApiResponse<T> ofStatus(ApiResponse.Status status, String msg, T body) {
        ApiResponse<T> apiResponse = new ApiResponse<>(status);
        apiResponse.setMessage(msg);
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
