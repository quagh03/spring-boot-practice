package org.example.practice_ecommerce.dtos.responses.ApiResponse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FailedResponse extends BaseResponse{
    public FailedResponse(int code, String message) {
        super(false, code, message);
    }
}
