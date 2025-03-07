package com.tec.VeterinariaCliente.RestTemplate;

public class ResponseGenericExterior<T> {

	    private boolean isSuccess;
	    public ResponseGenericExterior(boolean isSuccess, T data, String message, boolean success) {
			super();
			this.isSuccess = isSuccess;
			this.data = data;
			this.message = message;
			this.success = success;
		}

		public ResponseGenericExterior() {
			super();
		}

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
