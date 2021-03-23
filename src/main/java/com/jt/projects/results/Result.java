package com.jt.projects.results;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class Result<T> implements Serializable {
    /**
     * 代码标识
     */
    private int code;
    private String message;
    private Boolean success;
    private T data;

    Result(int code,String message,Boolean success,T data){
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public static <R extends Serializable> Result<R> buildSuccess(R data){
     return new Result<R>(200,null,true,data);
    }

    public static <R extends Serializable> Result<R> buildFailed(String message){
        return new Result<>(0,message,false,null);
    }

}
