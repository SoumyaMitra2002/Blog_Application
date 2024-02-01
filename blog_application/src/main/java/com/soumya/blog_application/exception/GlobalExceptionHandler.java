package com.soumya.blog_application.exception;

import java.util.HashMap;
import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.soumya.blog_application.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<ApiResponse> resourseNotFoundExceptionHandler(ResourseNotFoundException ex) {
        String message = ex.getMessage();
        ApiResponse response = new ApiResponse();
        response.setMessage(message);
        response.setStatus(false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> map = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            // String fieldName=((FieldError)error).getField();
            // String message=error.getDefaultMessage();
            // map.put(fieldName, message);
            map.put(((FieldError) error).getField().toUpperCase(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> dataIntegrityViolationException(Exception ex) {
        String message = ex.getMessage();
        ApiResponse response = new ApiResponse();
        response.setMessage(message);
        response.setStatus(false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
