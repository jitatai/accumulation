package com.jt.projects.exceptionhandler.exception;

import com.jt.projects.exceptionhandler.enums.ResponseEnum;
import lombok.Getter;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/24 13:58
 */
@Getter
public class BizException extends RuntimeException {
    private ResponseEnum responseEnum;
    /**
     * 构造器，有时我们需要将第三方异常转为自定义异常抛出，但又不想丢失原来的异常信息，此时可以传入cause
     * @param responseEnum
     * @param cause
     */
    BizException(ResponseEnum responseEnum,Throwable cause){
        super(cause);
        this.responseEnum = responseEnum;
    }

    /**
     * 构造器，通过错误枚举构建自定义异常
     * @param responseEnum
     */
    BizException(ResponseEnum responseEnum){
        this.responseEnum = responseEnum;
    }
}
