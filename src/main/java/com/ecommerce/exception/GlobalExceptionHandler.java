package com.ecommerce.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.ecommerce.helper.ApiResponse;
import com.ecommerce.model.Category;

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


    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDuplicateCategory(Model model) {
        model.addAttribute("errorMessage", "Category already exists!");
        model.addAttribute("category", new Category());
        return "admin/addCategory";
    }
   


    // Handles All Other Errors

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleAllException(Exception ex)
    {
        ApiResponse<Void> response = new ApiResponse<Void>(false, "Internal Server Error", null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

   
    
      
}