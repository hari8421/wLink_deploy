package com.ecom.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.exception.ApiErrorResponse;
import com.ecom.exception.BadRequestException;



@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(
    		BadRequestException ex) {
        ApiErrorResponse response = 
            new ApiErrorResponse("error-0001",
                "Already registerd number");
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}