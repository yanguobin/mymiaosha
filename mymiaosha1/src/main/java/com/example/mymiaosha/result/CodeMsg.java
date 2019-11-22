package com.example.mymiaosha.result;

public class CodeMsg {
    private Integer code;
    private String msg;

    public static final CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务器异常");

    private CodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
