package org.example.practice_ecommerce.dtos.responses.ApiResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse<T> extends BaseResponse{
    private T data;

    public SuccessResponse(T data, int code, String message) {
        super(true, code, message);
        this.data = data;
    }
}
