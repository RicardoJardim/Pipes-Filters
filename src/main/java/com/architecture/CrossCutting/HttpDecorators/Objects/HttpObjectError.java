package com.architecture.CrossCutting.HttpDecorators.Objects;

public class HttpObjectError extends AbstractHttpObject {

    private Error error;

    public HttpObjectError(Double code, Object message){
        super.setSuccess(false);
        this.setError(new Error(code, message));
    }
    public Object getError() {
        return error;
    }
    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public boolean getSuccess() {
        return super.getSuccess();
    }
}
