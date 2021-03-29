package com.jt.projects.authorizationrabc.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.*;
import java.lang.reflect.Method;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/29 14:43
 */
@Slf4j
@FirstLevel(value = "first",info = "写在类上的注解")
public class AnnotationUtilsTest {
    @FirstLevel(value = "first",info = "写在方法上的注解")
    public void annotationMethod(){}
    public void noAnnotationMethod(){}

    public static void main(String[] args) throws NoSuchMethodException {
        Class<AnnotationUtilsTest> aClass = AnnotationUtilsTest.class;
        // 获取类上的注解信息
        FirstLevel firstLevel = AnnotationUtils.getAnnotation(aClass, FirstLevel.class);
        SecondLevel secondLevel = AnnotationUtils.getAnnotation(aClass, SecondLevel.class);
        ThirdLevel thirdLevel = AnnotationUtils.getAnnotation(aClass, ThirdLevel.class);
        log.info("class first:{},second:{},third:{}",firstLevel,secondLevel,thirdLevel);

        // 获取到方法上的注解信息
        Method annotationMethod = aClass.getMethod("annotationMethod");
        Method noAnnotationMethod = aClass.getMethod("noAnnotationMethod");
        log.info("annotationMethod first:{},second:{},third:{}",
                AnnotationUtils.getAnnotation(annotationMethod,FirstLevel.class),
                AnnotationUtils.getAnnotation(annotationMethod,SecondLevel.class),
                AnnotationUtils.getAnnotation(annotationMethod,ThirdLevel.class));
        log.info("noAnnotationMethod first:{},second:{},third:{}",
                AnnotationUtils.getAnnotation(noAnnotationMethod,FirstLevel.class),
                AnnotationUtils.getAnnotation(noAnnotationMethod,SecondLevel.class),
                AnnotationUtils.getAnnotation(noAnnotationMethod,ThirdLevel.class));
    }

}

@SecondLevel
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface FirstLevel {
    String value();
    String info();
}

@ThirdLevel
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface SecondLevel {
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface ThirdLevel {
}
