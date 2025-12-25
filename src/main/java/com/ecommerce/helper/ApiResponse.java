package com.ecommerce.helper;

public class ApiResponse<T> {

    public boolean success;
    public String message;
    public T data;

    public ApiResponse(boolean success, String message, T data)
    {
        this.success = success;
        this.message = message;
        this.data = data;
    }


    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    
}
