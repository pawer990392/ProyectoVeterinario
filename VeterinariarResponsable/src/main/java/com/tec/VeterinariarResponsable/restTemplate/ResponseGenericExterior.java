package com.tec.VeterinariarResponsable.restTemplate;

import lombok.Data;

@Data
public class ResponseGenericExterior<T> {

    private boolean isSuccess;
    private T data;
    private String message;
    private boolean success;
}
