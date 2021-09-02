package com.architecture.CrossCutting.HttpDecorators.Objects;

public abstract class AbstractHttpObject {
    private boolean success;

    public void setSuccess(boolean success) {
        this.success = success;
    }
    public boolean getSuccess() {
        return this.success;
    }
}
