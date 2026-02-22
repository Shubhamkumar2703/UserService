package com.user.service.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.user.service.demo.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(
            ResourceNotFoundException exception){

        String message = exception.getMessage();

        ApiResponse response = ApiResponse.builder()
                .message(message)
                .success(false)
                .status(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}