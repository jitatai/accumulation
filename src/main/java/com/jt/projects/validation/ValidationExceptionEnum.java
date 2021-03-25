package com.jt.projects.validation;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
@Getter
public enum  ValidationExceptionEnum {
    NOT_FOUND(404,"找不到资源异常"),SUCCESS(200,"操作成功"),
    ERROR_PARAM(10000,"错误参数异常");

    ValidationExceptionEnum(int code,String desc){
        this.code = code;
        this.desc = desc;
    }
    private int code;
    private String desc;
    private static Map<Integer,ValidationExceptionEnum> codeMap;

    static {
        codeMap = Arrays.stream(ValidationExceptionEnum.values())
                .collect(Collectors.toMap(ValidationExceptionEnum::getCode,v->v,(v1,v2)->v2));
    }

    public static ValidationExceptionEnum ofCode(int code){
        return codeMap.get(code);
    }
}
