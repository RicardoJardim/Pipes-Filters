package com.architecture.CrossCutting.TemplateHttp;

import org.springframework.http.HttpStatus;

public class HttpOk extends TemplateHttp
{
    @Override
    protected void SetSuccess()
    {
        Success = true;
        Status = HttpStatus.OK;    
    }

    @Override
    protected void SetMessage(Object aMsg)
    {
        Data = aMsg;
    }

}