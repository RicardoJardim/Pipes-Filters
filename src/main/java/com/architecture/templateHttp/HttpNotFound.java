package com.architecture.templateHttp;

import org.springframework.http.HttpStatus;

public class HttpNotFound extends TemplateHttp
{
    @Override
    protected void SetSuccess()
    {
        Success = false;
        Status = HttpStatus.NOT_FOUND;    
    }

    @Override
    protected void SetMessage(Object aMsg)
    {
        Data = aMsg;
    }


}