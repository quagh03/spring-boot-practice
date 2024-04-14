package org.example.practice_ecommerce.dtos.responses.ApiResponse;

public class ResponseFactory<T>{
    public static BaseResponse createResponse(boolean success, int code, String message) {
        return new BaseResponse(success, code, message);
    }

    public static <T> SuccessResponse<T> createSuccessResponse(int code, String message,T data) {
        return new SuccessResponse<>(data, code, message);
    }

    public static FailedResponse createFailedResponse(int code, String message) {
        return new FailedResponse(code, message);
    }
}
