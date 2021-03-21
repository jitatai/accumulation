package com.jt.projects.validation;

public class ValidationException extends RuntimeException {


    /**
     * 自定义错误码
     */
    private int code;
    /**
     * 报错源异常
     */
    private Exception originException;

    /**
     *
     * @param code
     * @param message
     * @param originException
     */
    ValidationException(int code,String message,Exception originException){
        super(message);
        this.code = code;
        this.originException = originException;
    }

    /**
     * 错误枚举 + 源异常
     */
    ValidationException(ValidationExceptionEnum exceptionEnum,Exception originException){
        /*super(exceptionEnum.getDesc());
        this.code = exceptionEnum.getCode();
        this.originException = originException;*/
        this(exceptionEnum.getCode(),exceptionEnum.getDesc(),originException);
    }

    /**
     * 错误枚举代码 + 源异常
     */
    ValidationException(int enumCode,Exception originException){
        /*super(ValidationExceptionEnum.ofCode(enumCode).getDesc());
        this.code = ValidationExceptionEnum.ofCode(enumCode).getCode();
        this.originException = originException;*/
        this(ValidationExceptionEnum.ofCode(enumCode).getCode(),ValidationExceptionEnum.ofCode(enumCode).getDesc(),originException);
    }

    /**
     * 错误信息 + 源异常
     */
    ValidationException(String message,Exception originException){
        this(ValidationExceptionEnum.ERROR_PARAM.getCode(),message,originException);
    }

    /**
     * 源异常
     */
    ValidationException(Exception originException){
        this(ValidationExceptionEnum.ERROR_PARAM.getCode(),ValidationExceptionEnum.ERROR_PARAM.getDesc(),originException);
    }

    public ValidationException(String message){
        this(ValidationExceptionEnum.ERROR_PARAM.getCode(),ValidationExceptionEnum.ERROR_PARAM.getDesc(),null);
    }

    @Override
    public String toString() {
        return "ValidationException{" +
                "code=" + code +
                "msg=" + super.getMessage() +
                ", originException=" + originException +
                '}';
    }
}
