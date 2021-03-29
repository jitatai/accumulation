package com.jt.projects.authorizationrbac.enums;

/**
 * 通用错误枚举
 *
 * @author sunting
 */


import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public enum ExceptionCodeEnum {

    /**
     * 通用结果
     */
    ERROR(-1, "网络错误"),
    SUCCESS(200, "成功"),
    NEED_LOGIN(10001, "需要登录"),
    PERMISSION_DENY(10002, "权限不足");

    private final Integer code;
    private final String desc;

    ExceptionCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static  Map<Integer, ExceptionCodeEnum> cache;
    static {
        cache = Arrays.stream(values()).collect(Collectors.toMap(ExceptionCodeEnum::getCode,v->v));
    }

    public static String getDesc(Integer code) {
        return Optional.ofNullable(cache.get(code))
                .map(ExceptionCodeEnum::getDesc)
                .orElseThrow(() -> new IllegalArgumentException("invalid exception code!"));
    }

}