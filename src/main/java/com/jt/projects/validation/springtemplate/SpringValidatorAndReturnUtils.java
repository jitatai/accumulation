package com.jt.projects.validation.springtemplate;

import com.google.common.collect.Iterables;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;


public final class SpringValidatorAndReturnUtils {
    private SpringValidatorAndReturnUtils(){}
    public static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
    /**
     * 校验基于注解校验的对象
     * @param param 被校验对象
     * @param isThrow 是否抛出异常
     * @param <T>
     */
    public static final <T> String validReq(T param,Boolean isThrow){
        if (param == null){
            return exceptionHandle("检验对象不能为空",isThrow);
        }
        Set<ConstraintViolation<T>> validateSet = VALIDATOR.validate(param);
        ConstraintViolation<T> first = Iterables.getFirst(validateSet, null);
        if (CollectionUtils.isEmpty(validateSet)){
            return "";
        }
        String errorMsg = validateSet.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("&"));
        return exceptionHandle(errorMsg,isThrow);
    }

    private static String exceptionHandle(String message, Boolean isThrow) {
        if (isThrow){
            throw new ValidationException(message);
        }
        return message;
    }
}
