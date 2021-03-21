package com.jt.projects.validation.springtemplate;

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;


public final class SpringValidatorUtils {
    private SpringValidatorUtils(){}
    public static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
    public static final <T> void valid(T param,Class<?> ... groupClass){
        Set<ConstraintViolation<T>> validateSet = VALIDATOR.validate(param, groupClass);
        if (CollectionUtils.isEmpty(validateSet)){
            return;
        }
        String errorMsg = validateSet.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("&"));
        throw new ValidationException(errorMsg);
    }
}
