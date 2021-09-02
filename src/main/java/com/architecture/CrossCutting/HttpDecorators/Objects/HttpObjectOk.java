package com.architecture.CrossCutting.HttpDecorators.Objects;

public class HttpObjectOk extends AbstractHttpObject {
	private Object data;

    public HttpObjectOk(Object data){
        this.setData(data);
        super.setSuccess(true);
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    @Override
    public boolean getSuccess() {
        return super.getSuccess();
    }

}
