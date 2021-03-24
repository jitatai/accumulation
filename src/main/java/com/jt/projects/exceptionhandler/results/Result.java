package com.jt.projects.exceptionhandler.results;

import com.jt.projects.exceptionhandler.enums.ResponseEnum;
import lombok.Data;

import java.util.Optional;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/24 14:04
 * 统一返回结果封装
 */
@Data
public class Result<T> {
    private Boolean success;
    private Integer code;
    private String message;
    private T data;

    Result(Boolean success,Integer code,String message,T data){
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造器，不含返回数据
     * @param success
     * @param message
     */
    Result(Boolean success,Integer code,String message){
        this(success,code,message,null);
    }

    /**
     * 静态方法，带返回数据
     * @param data 统一返回结果封装
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data){
        return Optional.ofNullable(data).map(value -> new Result<T>(true,ResponseEnum.SUCCESS.getCode(),ResponseEnum.SUCCESS.getMessage(),value))
                .orElse(new Result<T>(true,ResponseEnum.SUCCESS.getCode(),ResponseEnum.SUCCESS.getMessage()));
    }

    /**
     * 静态方法，不带返回数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(){
        return success(null);
    }

    /**
     * 通用错误返回，传入错误枚举
     * @param responseEnum
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(ResponseEnum responseEnum){
        return new Result<T>(false,responseEnum.getCode(),responseEnum.getMessage());
    }

    /**
     * 通用错误返回，传入错误枚举，支持使用message覆盖
     * @param responseEnum
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(ResponseEnum responseEnum,String message){
        return new Result<T>(false,responseEnum.getCode(),message);
    }

    /**
     * 通用错误返回，只传入message
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String message){
        return error(ResponseEnum.ERROR,message);
    }
}
