package com.architecture.CrossCutting.HttpDecorators;

import com.architecture.CrossCutting.HttpDecorators.Objects.AbstractHttpObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpDecorator implements IHttpDecorator{

    private AbstractHttpObject wrappee;

    private HttpStatus status;

    HttpDecorator(AbstractHttpObject source) {
        this.wrappee = source;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public void setSuccess(boolean success) {
        wrappee.setSuccess(success);
    }

    @Override
    public void setSuccess() {
        wrappee.setSuccess(true);
    }
    
    public ResponseEntity<AbstractHttpObject> ReturnObjectMsg()
    {
        return new ResponseEntity<>(this.wrappee, this.status);
    }

    
}
