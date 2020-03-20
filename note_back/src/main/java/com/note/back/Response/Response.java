package com.note.back.Response;

public class Response {
    private int status;
    private String msg;
    private Object object;

    public Response(int status, String msg, Object object){
        this.status = status;
        this.msg = msg;
        this.object = object;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
