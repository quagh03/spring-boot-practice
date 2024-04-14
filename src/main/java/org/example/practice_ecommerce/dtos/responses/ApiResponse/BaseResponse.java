package org.example.practice_ecommerce.dtos.responses.ApiResponse;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private int code;
    private String message;
    private boolean success;

    public BaseResponse(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
