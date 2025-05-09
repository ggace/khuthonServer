package org.khuthon.agriserver.dto;

public class Error {
    private String msg;
    private String data;

    public Error(String msg) {
        this.msg = msg;
        this.data = null;
    }

    public String getData() {
        return data;
    }
    public String getMsg() {
        return msg;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
