package com.architecture.CrossCutting.HttpDecorators;
import com.architecture.CrossCutting.HttpDecorators.Objects.AbstractHttpObject;

import org.springframework.http.HttpStatus;

public class HttpErrorDecorator extends HttpDecorator{
    
    public HttpErrorDecorator(AbstractHttpObject source) {
        super(source);
        setSuccess();  
        setStatus(HttpStatus.BAD_REQUEST); 
    }

    public HttpErrorDecorator(AbstractHttpObject source, HttpStatus status) {
        super(source);
        setSuccess();  
        setStatus(status); 
    }

    @Override
    public void setSuccess() {
        super.setSuccess(false);
    }
}
