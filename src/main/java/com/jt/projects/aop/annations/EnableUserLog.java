package com.jt.projects.aop.annations;

import com.jt.projects.aop.aspects.ApiUserLogAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/25 16:15
 * 第一步 引入aop切面依赖
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Import(ApiUserLogAspect.class)
public @interface EnableUserLog {

}
