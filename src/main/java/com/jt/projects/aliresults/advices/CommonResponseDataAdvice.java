package com.jt.projects.aliresults.advices;

import com.jt.projects.aliresults.annotations.CosmoController;
import com.jt.projects.aliresults.annotations.IgnoreCosmoResult;
import com.jt.projects.exceptionhandler.results.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
/**
 * @author Administrator
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        // 标注了CosmoController注解 且类与方法上没有IgnoreCosmoResult注解的方法才进行返回值包装
        return methodParameter.getDeclaringClass().isAnnotationPresent(CosmoController.class) &&
                !methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreCosmoResult.class) &&
                !methodParameter.getMethod().isAnnotationPresent(IgnoreCosmoResult.class);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return o instanceof Result ? o :Result.success(o);
    }
}
