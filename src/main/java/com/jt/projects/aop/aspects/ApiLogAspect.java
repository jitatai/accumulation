package com.jt.projects.aop.aspects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.projects.aop.entity.LogEntity;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author jiatai.hu
 * @date 2021/3/25 17:05
 * @version 1.0
 */
@Aspect
@Slf4j
public class ApiLogAspect {
    /**
     * 切点表达式 表示com.jt.projects下所有方法都需要增强（public）
     */
    @Pointcut("execution(* com.jt.projects.aop.controllers.*.* (..))")
    public void pointCut(){}

    @Autowired
    HttpServletRequest request;

    @Autowired
    ObjectMapper objectMapper;

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint){
        LogEntity logEntity = new LogEntity();
        // 方法签名 可以理解为对方法信息的封装 包括类信息等
        Signature signature = joinPoint.getSignature();
        String classSimpleName = signature.getDeclaringType().getSimpleName();
        String className = signature.getDeclaringType().getName();
        String methodName = signature.getName();
        logEntity.setMethodName(
                String.format("类名为：%s,方法名为%s,方法全路径为%s",classSimpleName,methodName,(className +"#" + methodName)));
        logEntity.setUrl("接口URL：" + request.getRequestURI() + "请求类型：" + request.getMethod());
        // 一般都会有代理转发，真实的ip会放在X-Forwarded-For
        String xff = request.getHeader("X-Forwarded-For");
        if (xff == null) {
            xff = request.getRemoteAddr();
        } else {
            xff = xff.contains(",") ? xff.split(",")[0] : xff;
        }
        logEntity.setRemoteAddress("远程调用接口："+ xff);

        String queryParam = "";
        Object[] objects = Arrays.stream(joinPoint.getArgs()).filter(arg -> !(arg instanceof ServletRequest ||
                arg instanceof ServletResponse ||
                arg instanceof MultipartFile)).toArray();
        queryParam = objectMapper.valueToTree(objects).toPrettyString();
        if ("GET".equals(request.getMethod())){
            queryParam = request.getQueryString();
        }

        logEntity.setRequestArgs(queryParam);

        Object proceed = null;
        try {
            Long startTime = System.currentTimeMillis();
            proceed = joinPoint.proceed();
            logEntity.setTimeConsuming(System.currentTimeMillis() - startTime);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.info("异常信息：{}", throwable.getMessage());
        }
        log.info("日志信息为：{}",logEntity);
        return proceed;
    }

}
