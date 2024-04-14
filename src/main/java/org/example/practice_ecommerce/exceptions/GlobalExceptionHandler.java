package org.example.practice_ecommerce.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.example.practice_ecommerce.dtos.responses.ApiResponse.FailedResponse;
import org.example.practice_ecommerce.dtos.responses.ApiResponse.ResponseFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<FailedResponse> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.internalServerError().body(ResponseFactory.createFailedResponse(INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<FailedResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(404).body(ResponseFactory.createFailedResponse(NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<FailedResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(ResponseFactory.createFailedResponse(BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FailedResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .toList();
        return ResponseEntity.badRequest().body(ResponseFactory.createFailedResponse(BAD_REQUEST.value(), errors.toString()));
    }
}
