package com.example.demo.config;

public class DomainValueError extends RuntimeException{
    private Integer code;
    private String message;

    public DomainValueError(String message,Integer code){
        this.message=message;
        this.code=code;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
