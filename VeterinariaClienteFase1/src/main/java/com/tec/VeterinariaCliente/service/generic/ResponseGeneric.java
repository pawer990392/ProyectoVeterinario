package com.tec.VeterinariaCliente.service.generic;


//Esta clases es generica indica la respuesta que devolvera nuestra formato JSON en cada unas de la peticiones 
//que se vallan realizar
public class ResponseGeneric<T> {

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

	public ResponseGeneric(boolean isSuccess, T data, String message) {
		super();
		this.isSuccess = isSuccess;
		this.data = data;
		this.message = message;
	}
	
	public ResponseGeneric() {
		
	}

	@Override
	public String toString() {
		return "ResponseGeneric [isSuccess=" + isSuccess + ", data=" + data + ", message=" + message + "]";
	}
	
	
	
}
