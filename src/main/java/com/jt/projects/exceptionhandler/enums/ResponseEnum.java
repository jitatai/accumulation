package com.jt.projects.exceptionhandler.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/24 13:41
 */
@Getter
public enum  ResponseEnum {
    /**
     * 通用结果
     */
    ERROR(-1, "网络错误"),
    SUCCESS(200, "成功"),

    /**
     * 用户登录
     */
    NEED_LOGIN(900, "用户未登录"),

    /**
     * 参数校验
     */
    ERROR_PARAM(10000, "参数错误"),
    EMPTY_PARAM(10001, "参数为空"),
    ERROR_PARAM_LENGTH(10002, "参数长度错误");

    private Integer code;
    private String message;


    ResponseEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    private static Map<Integer,ResponseEnum> ENUM_CACHE;
    static {
        ENUM_CACHE = Arrays.stream(ResponseEnum.values()).
                collect(Collectors.toMap(ResponseEnum::getCode,v->v,(v1,v2) -> v2));
    }

    public static String getMessage(Integer code){
        return Optional.ofNullable(ENUM_CACHE.get(code)).map(v->v.message)
                .orElseThrow(() -> new IllegalArgumentException("invalid exception code"));
    }
}
