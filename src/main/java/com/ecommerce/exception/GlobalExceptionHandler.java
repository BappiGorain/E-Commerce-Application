package com.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.ecommerce.helper.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler
{
    
    // ResourceNotFoundException Handler

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFound(ResourceNotFoundException ex)
    {
        ApiResponse<Void> response = new ApiResponse<Void>(false,ex.getMessage(), null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Validation Errors Handler


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationErrors(MethodArgumentNotValidException ex)
    {
        String errorMessage = ex.getBindingResult()
        .getAllErrors()
        .stream()
        .findFirst()
        .map(error -> error.getDefaultMessage())
        .orElse("Validation failed");

        ApiResponse<Void> response = new ApiResponse<Void>(false, errorMessage, null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    // Handles All Other Errors

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleAllException(Exception ex)
    {
        ApiResponse<Void> response = new ApiResponse<Void>(false, "Internal Server Error", null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
      
}