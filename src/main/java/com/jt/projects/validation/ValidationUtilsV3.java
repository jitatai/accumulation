package com.jt.projects.validation;

public class ValidationUtilsV3 {
    public static final String NULL_FORMAT = "%s不能为空";
    public static final String LESS_THAN_ZERO = "%s不能小于0";

    public static final void isNotNull(Object o,String filedName){
        if (o == null){
            throw new ValidationException(String.format(NULL_FORMAT,filedName));
        }
    }
    public static final void isLessThan0(Integer num,String filedName){
        isNotNull(num,filedName);
        if (num < 0){
            throw new ValidationException(String.format(LESS_THAN_ZERO,filedName));
        }
    }
}
