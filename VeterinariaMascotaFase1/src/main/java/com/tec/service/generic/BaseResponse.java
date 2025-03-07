package com.tec.service.generic;

public class BaseResponse<T> {

	public boolean isSuccess; 
	public T data;  
	public String message;
	
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
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
	public BaseResponse() {
		
	}
	
	public BaseResponse(boolean isSuccess, T data, String message) {
		super();
		this.isSuccess = isSuccess;
		this.data = data;
		this.message = message;
	}
	
	
}
