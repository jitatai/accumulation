package com.jt.projects.validation;

public final class ValidationUtilsV2 {
    private ValidationUtilsV2(){}

    /**
     * 错误信息模板
     */
    public static final String IS_EMPTY = "%s不能为空";
    public static final String LESS_THAN_ZERO = "%s小于0";

    public static final <T> void isNull(T param,String fieldName ){
        if (param == null){
            throw new RuntimeException(String.format(IS_EMPTY,fieldName));
        }
    }

    public static final <T> void isNotId(Integer id,String fieldName){
        if (id == null){
            throw new RuntimeException(String.format(IS_EMPTY,fieldName));
        }
        if (id < 0){
            throw new RuntimeException(String.format(LESS_THAN_ZERO,fieldName));
        }
    }

    public void method(){
        ValidationUtilsV2.isNull(null,"用户信息");
        ValidationUtilsV2.isNotId(-1,"用户id");

    }

}
