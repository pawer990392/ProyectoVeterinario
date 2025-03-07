package com.tec.VeterinariarResponsable.service.generic;

public class ResponseGenericExterior<T> {

    private boolean isSuccess;
    private T data;  
    private String message;
    private boolean success;

    // Getters y Setters
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccessFull() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
	
	
}
