package com.jt.accumulation.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/1/29 13:51
 * 定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    int intValue() default 1;
    long longValue();
    String stringValue();
    int[] intArrValue();
    String[] stringArrValue();
    MyAnnotation2 annotationValue();
    CityEnum enumValue();
    @interface MyAnnotation2 {
    }
    enum CityEnum {
        BEIJING,
        HANGZHOU,
        SHANGHAI;
    }
}


