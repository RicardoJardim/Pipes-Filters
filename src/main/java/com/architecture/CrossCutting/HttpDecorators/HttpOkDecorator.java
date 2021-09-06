package com.architecture.CrossCutting.HttpDecorators;
import com.architecture.CrossCutting.HttpDecorators.Objects.AbstractHttpObject;

import org.springframework.http.HttpStatus;

public class HttpOkDecorator extends HttpDecorator{

    public HttpOkDecorator(AbstractHttpObject source) {
        super(source);
        setSuccess();  
        setStatus(HttpStatus.OK); 
    }

    public HttpOkDecorator(AbstractHttpObject source,HttpStatus status) {
        super(source);
        setSuccess();  
        setStatus(status); 
    }

    @Override
    public void setSuccess() {
        super.setSuccess(true);
    }

    
}
