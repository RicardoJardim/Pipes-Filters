package com.architecture.CrossCutting.HttpDecorators.Objects;

public class Error {
    private Double code;
    private Object message;

    public Error(Double code,Object message){
        this.code = code;
        this.message = message;
    }
    public Double getCode() {
        return code;
    }
    public Object getMessage() {
        return message;
    }
    public void setMessage(Object message) {
        this.message = message;
    }
    public void setCode(Double code) {
        this.code = code;
    }
}
