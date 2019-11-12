package com.example.mymiaosha1.result;

public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    public Result(CodeMsg cm) {
        if (cm == null){
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    public Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
