package com.architecture.CrossCutting.HttpDecorators.Objects;

public class Error {
    private Double code;
    private String message;

    public Error(Double code,String message){
        this.code = code;
        this.message = message;
    }
    public Double getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setCode(Double code) {
        this.code = code;
    }
}
