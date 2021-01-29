package com.jt.accumulation.annotation;

import org.junit.jupiter.api.Test;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/1/29 15:56
 * 使用注解
 */
public class AnnotationTest {
    @Test
    public void testAnnotation(){
        Class<Demo> demoClass = Demo.class;
        MyAnnotation annotation = demoClass.getAnnotation(MyAnnotation.class);
        System.out.println(annotation.annotationValue());
        System.out.println(annotation.intArrValue());
    }
}
