package com.architecture.CrossCutting.TemplateHttp;

public class ObjectHttp {
    private boolean success;
	private Object data;
    
	public ObjectHttp(boolean success, Object data) {
       this.success = success;
       this.data = data;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("{");
        sb.append("success: ").append(this.success);
        sb.append(", data: ").append(this.data);
        sb.append('}');
        return sb.toString();
    }

    
    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
