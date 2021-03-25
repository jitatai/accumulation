package com.jt.projects.aop.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/25 16:13
 */
@Aspect
@Slf4j
public class APITimeLogAspect {
    // 编写切面类 编写了@EnableTimeLog注解 将切面类加载进spring容器 把注解放在启动类上
    // 第二步 编写切点表达式

    /**
     * 切点表达式 表示com.jt.projects下所有方法都需要增强（public）
     */
    @Pointcut("execution(* com.jt.projects.aop.controllers.*.* (..))")
    public void pointCut(){}

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint){
        Long startTime = System.currentTimeMillis();
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        log.info("-------耗时-------- {}",(System.currentTimeMillis() - startTime));
        return proceed;
    }
}
