package com.architecture.CrossCutting.TemplateHttp;

import java.util.HashMap;

public class ErrorObject {
    private HashMap<String,String> map;
	public ErrorObject(HashMap<String,String> map) {
       this.map = map;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("{");
        sb.append("error: ").append(this.map);
        sb.append('}');
        return sb.toString();
    }

    public Object getMap() {
        return this.map;
    }

    public void setMap(HashMap<String,String> map) {
        this.map = map;
    }
}

