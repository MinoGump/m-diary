package com.mino.mdiary.entity.vo;

public class ResponseInfo {

    private boolean flag;
    private Integer code;
    private String message;
    private String data;

    public ResponseInfo(boolean flag, Integer code, String msg, String data) {
        this.flag = flag;
        this.code = code;
        this.message = msg;
        this.data = data;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
