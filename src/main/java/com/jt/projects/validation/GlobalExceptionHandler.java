package com.jt.projects.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * ValidationException 异常处理
     */
    @ExceptionHandler(ValidationException.class)
    public <T> T handleValidationExcepion(ValidationException e){
        // 打印精准的错误日志，方便后端排查
        log.warn("校验参数异常：{}",e.getMessage(),e);
        return (T) e;
    }

    /**
     * RuntimeException 异常处理
     */
    @ExceptionHandler({RuntimeException.class})
    public  <T> T handleValidationException(RuntimeException e){
        // 打印精准的参数错误日志，方便后端排查
        log.warn("参数校验异常：{},{}", e.getMessage(),e.toString());
        return (T) e.toString();
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public Object handleConstraintViolationException(ConstraintViolationException e) {
        log.warn("参数错误: {}", e.getMessage(), e);
        // 一般只需返回泛化的错误信息，比如“参数错误”
        return e.getMessage();
    }
    @ExceptionHandler({BindException.class})
    public Object handleBindException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String message = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining("\n"));
        log.warn("参数错误: {}", message, e);
        // 一般只需返回泛化的错误信息，比如“参数错误”
        return message;
    }
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Object handleMethodXXX(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String message = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining("\n"));
        log.warn("参数错误: {}", message, e);
        // 一般只需返回泛化的错误信息，比如“参数错误”
        return message;
    }

    @ExceptionHandler({IllegalStateException.class})
    public Object handleException(IllegalStateException e) {
        String message = e.getMessage();
        log.warn("参数错误: {}", message, e);
        // 一般只需返回泛化的错误信息，比如“参数错误”
        return message;
    }




}
