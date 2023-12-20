package com.cuii.common;

import java.util.HashMap;

public class Result<T> extends HashMap<String,Object> {
    private String message;

    private Integer code;

    private T data;

    private Result(Integer code, String msg, T data) {
        super.put("message",msg);
        super.put("code", code);
        super.put("data", data);
    }


    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>(200, "操作成功", data);
        return result;
    }

    public static <T> Result<T> success(){
        return success(null);
    }

    public static <T> Result<T> error(String msg){
        Result<T> result = new Result<>(500, msg, null);
        return result;
    }


    public static <T> Result<T> error(){
        return error("操作失败!");
    }

    public Result<T> put(String key,Object o){
        super.put(key,o);
        return this;
    }

    public Result<T> setTotal(long total){
        super.put("total",total);
        return this;
    }


}